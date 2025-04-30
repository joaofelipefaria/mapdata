import { HttpClient } from '@angular/common/http';

export class StaticHttpClientService {
  static httpClient: HttpClient;

  static setHttpClient(http: HttpClient) {
    StaticHttpClientService.httpClient = http;
  }

  static getHttpClient(): HttpClient {
    return StaticHttpClientService.httpClient;
  }
}
