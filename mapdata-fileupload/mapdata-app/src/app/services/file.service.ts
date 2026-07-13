import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { StaticHttpClientService } from './static-http-client.service.util';

@Injectable({
  providedIn: 'root'
})
export class FileService {
  private apiUrl = 'http://localhost:8080/api/files'; // API endpoint

  constructor() {}

  // Returns a specific file by filename
  getFile(filename: string): Observable<Blob> {
    const url = `${this.apiUrl}/get/${filename}`; // API endpoint for fetching a file
    return StaticHttpClientService.getHttpClient().get<Blob>(url, { responseType: 'blob' as 'json' });
  }

  // Deletes a file by filename
  deleteFile(filename: string): Observable<{ message: string }> {
    const url = `${this.apiUrl}/delete/${filename}`; // API endpoint for deleting a file
    return StaticHttpClientService.getHttpClient().delete<{ message: string }>(url);
  }

  // Uploads a file to the server
  uploadFile(file: File): Observable<{ message: string; filename: string }> {
    const formData = new FormData();
    formData.append('file', file);

    return StaticHttpClientService.getHttpClient().post<{ message: string; filename: string }>(
      `${this.apiUrl}/upload`,
      formData
    );
  }
}
