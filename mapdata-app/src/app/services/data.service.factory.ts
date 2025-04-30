import { Injectable } from '@angular/core';
import { IDataService } from './idata.service';
import { DataService } from './data.service';
import { MockDataService } from './mock.data.service';

@Injectable({
  providedIn: 'root'
})
export class DataServiceFactory {

  constructor() { }

  getService(): IDataService {
    const isProduction = true;
    return isProduction ? new DataService() : new MockDataService();
  }
}
