import { Injectable } from '@angular/core';
import { Observable, of, tap } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { IDataService } from './idata.service';
import { Data } from '../model/data';
import { Metadata } from '../model/metadata';
import { StaticHttpClientService } from './static-http-client.service.util';
@Injectable({
  providedIn: 'root'
})
export class DataService implements IDataService {
  private apiUrl = 'http://localhost:8080/api/mapdata'; // API endpoint

  // CRUD operations for Data

  // Returns all data items from the API
  getData(): Observable<Data[]> {
    return StaticHttpClientService.getHttpClient().get<Data[]>(this.apiUrl + '/all'); // HTTP GET to fetch all data
  }

  // Returns a specific data item by ID from the API
  getDataById(id: number): Observable<Data | undefined> {
    const url = `${this.apiUrl}/${id}`; // API endpoint for fetching a single item by ID
    return StaticHttpClientService.getHttpClient().get<Data>(url); // HTTP GET to fetch data by ID
  }

  // Creates a new data item by sending a POST request to the API
  createData(newData: Data): Observable<Data> {
    const jsonData = JSON.stringify(newData);

    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    return StaticHttpClientService.getHttpClient().post<Data>(this.apiUrl, jsonData, { headers }).pipe(
      tap(response => {
        console.log('Response from server:', response);
      }, error => {
        console.error('Error in POST request:', error);
      })
    );
  }

  // Updates an existing data item by ID through PUT request to the API
  updateData(id: number, updatedData: Data): Observable<Data | undefined> {
    const url = `${this.apiUrl}/${id}`; // API endpoint for updating data by ID
    return StaticHttpClientService.getHttpClient().put<Data>(url, updatedData); // HTTP PUT to update data
  }

  // Deletes a data item by ID through DELETE request to the API
  deleteData(id: number): Observable<boolean> {
    const url = `${this.apiUrl}/${id}`; // API endpoint for deleting data by ID
    return StaticHttpClientService.getHttpClient().delete<boolean>(url); // HTTP DELETE to remove data
  }

  // CRUD operations for Metadata

  // Returns metadata items associated with a specific dataId
  getMetadataByDataId(dataId: number): Observable<Metadata[]> {
    const metadataUrl = `${this.apiUrl}/${dataId}/metadata/`; // Endpoint for metadata by dataId
    return StaticHttpClientService.getHttpClient().get<Metadata[]>(metadataUrl); // HTTP GET to fetch metadata by dataId
  }

  // Returns a specific metadata item by ID
  getMetadataById(id: number, dataId: number): Observable<Metadata | undefined> {
    const metadataUrl = `${this.apiUrl}/${dataId}/metadata/${id}`; // Endpoint for fetching metadata by ID
    return StaticHttpClientService.getHttpClient().get<Metadata>(metadataUrl); // HTTP GET to fetch metadata by ID
  }

  // Creates a new metadata item by sending a POST request to the API
  createMetadata(newMetadata: Metadata): Observable<Metadata> {
    const metadataUrl = `${this.apiUrl}/${newMetadata.dataId}/metadata`; // Adjusted endpoint for creating metadata
    return StaticHttpClientService.getHttpClient().post<Metadata>(metadataUrl, newMetadata); // HTTP POST for creating metadata
  }

  // Updates an existing metadata item by ID through PUT request to the API
  updateMetadata(id: number, updatedMetadata: Metadata): Observable<Metadata | undefined> {
    const metadataUrl = `${this.apiUrl}/${updatedMetadata.dataId}/metadata/${id}`; // Endpoint for updating metadata by ID
    return StaticHttpClientService.getHttpClient().put<Metadata>(metadataUrl, updatedMetadata); // HTTP PUT for updating metadata
  }

  // Deletes a metadata item by ID through DELETE request to the API
  deleteMetadata(id: number, dataId: number): Observable<boolean> {
    const metadataUrl = `${this.apiUrl}/${dataId}/metadata/${id}`; // Endpoint for deleting metadata by ID
    return StaticHttpClientService.getHttpClient().delete<boolean>(metadataUrl); // HTTP DELETE for removing metadata
  }
}