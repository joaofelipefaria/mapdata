import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { IDataService } from './idata.service';
import { Data } from '../model/data';
import { Metadata } from '../model/metadata';

@Injectable({
  providedIn: 'root'
})
export class MockDataService implements IDataService{

  // Initial mock data and metadata structure with updated fields
  private dataItems: Data[] = [
    { id: 1, value: 'Data 1' },
    { id: 2, value: 'Data 2' },
    { id: 3, value: 'Data 3' }
  ];

  private metadataItems: Metadata[] = [
    { id: 1, dataId: 1, value1: 'Metadata Value 1 for Data 1', value2: 'Metadata Value 2 for Data 1' },
    { id: 2, dataId: 1, value1: 'Metadata Value 1 for Data 1', value2: 'Metadata Value 2 for Data 1' }
  ];

  constructor() { }

  // CRUD operations for Data

  // Returns all data items
  getData(): Observable<Data[]> {
    return of(this.dataItems);
  }

  // Returns a specific data item by ID
  getDataById(id: number): Observable<Data | undefined> {
    const foundData = this.dataItems.find(item => item.id === id);
    return of(foundData);
  }

  // Creates a new data item and adds it to the list
  createData(newData: Data): Observable<Data> {
    this.dataItems.push(newData);
    return of(newData); // Return the newly created data item
  }

  // Updates an existing data item by ID
  updateData(id: number, updatedData: Data): Observable<Data | undefined> {
    const index = this.dataItems.findIndex(item => item.id === id);
    if (index !== -1) {
      this.dataItems[index] = updatedData;
      return of(updatedData); // Return the updated data item
    }
    return of(undefined); // Return undefined if the item is not found
  }

  // Deletes a data item by ID
  deleteData(id: number): Observable<boolean> {
    const index = this.dataItems.findIndex(item => item.id === id);
    if (index !== -1) {
      this.dataItems.splice(index, 1); // Remove the data item from the list
      // Also remove the associated metadata
      this.metadataItems = this.metadataItems.filter(metadata => metadata.dataId !== id);
      return of(true);
    }
    return of(false); // Return false if the item is not found
  }

  // CRUD operations for Metadata
  // Returns metadata items associated with a specific dataId
  getMetadataByDataId(dataId: number): Observable<Metadata[]> {
    return of(this.metadataItems.filter(item => item.dataId === dataId));
  }

  // Returns metadata items associated with a specific dataId
  getMetadataById(id: number, dataId: number): Observable<Metadata | undefined> {
    const foundMetadata = this.metadataItems.find(item => item.id === id);
    return of(foundMetadata);
  }

  // Creates a new metadata item and adds it to the list
  createMetadata(newMetadata: Metadata): Observable<Metadata> {
    this.metadataItems.push(newMetadata);
    return of(newMetadata); // Return the newly created metadata item
  }

  // Updates an existing metadata item by ID
  updateMetadata(id: number, updatedMetadata: Metadata): Observable<Metadata | undefined> {
    const index = this.metadataItems.findIndex(item => item.id === id);
    if (index !== -1) {
      this.metadataItems[index] = updatedMetadata;
      return of(updatedMetadata); // Return the updated metadata item
    }
    return of(undefined); // Return undefined if the item is not found
  }

  // Deletes a metadata item by ID
  deleteMetadata(id: number, dataId: number): Observable<boolean> {
    const index = this.metadataItems.findIndex(item => item.id === id);
    if (index !== -1) {
      this.metadataItems.splice(index, 1); // Remove the metadata item from the list
      return of(true);
    }
    return of(false); // Return false if the item is not found
  }
}
