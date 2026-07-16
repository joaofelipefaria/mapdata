import { HttpClient, HttpHeaders } from '@angular/common/http';

export class StaticHttpClientService {
  static httpClient: HttpClient;

  static setHttpClient(http: HttpClient) {
    StaticHttpClientService.httpClient = http;
  }

  static getHttpClient(): HttpClient {
    return StaticHttpClientService.httpClient;
  }

  static getAuthHeaders(): HttpHeaders {
    const token = localStorage.getItem('jwt_token');
    return new HttpHeaders({
      Authorization: token ? `Bearer ${token}` : ''
    });
  }
}
