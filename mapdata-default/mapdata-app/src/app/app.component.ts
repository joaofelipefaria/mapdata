import { Component, ViewChild } from '@angular/core';
import { DataListComponent } from './components/data-list/data-list.component';
import { StaticHttpClientService } from './services/static-http-client.service.util';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'mapdata-app';
  showDataList = false;

  @ViewChild(DataListComponent) dataListComponent!: DataListComponent;

  constructor(http: HttpClient) {
    StaticHttpClientService.setHttpClient(http);
  }

  queryData(): void {
    this.showDataList = true; 
    if (this.dataListComponent) {
      this.dataListComponent.fetchData();
    }
  }
}
