import { Component, ViewChild } from '@angular/core';
import { DataListComponent } from './components/data-list/data-list.component';
import { StaticHttpClientService } from './services/static-http-client.service.util';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'mapdata-app';
  showDataList = false;

  @ViewChild(DataListComponent) dataListComponent!: DataListComponent;

  constructor(private http: HttpClient) {
    StaticHttpClientService.setHttpClient(http);
    this.loginOnLoad();
  }

  private loginOnLoad(): void {
    const payload = {
      username: 'admin',
      password: 'admin123'
    };

    this.http.post<any>('http://localhost:8080/login', payload).subscribe({
      next: (response) => {
        localStorage.setItem('jwt_token', response.token);
        console.log('Login realizado com sucesso');
      },
      error: (err) => {
        console.error('Erro ao realizar login', err);
      }
    });
  }

  queryData(): void {
    this.showDataList = true; 
    if (this.dataListComponent) {
      this.dataListComponent.fetchData();
    }
  }
}
