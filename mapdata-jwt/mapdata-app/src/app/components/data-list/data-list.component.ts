import { Component, EventEmitter, Input, Output, OnInit, ViewChild } from '@angular/core';
import { DataService } from '../../services/data.service';
import { MetadataListComponent } from '../metadata-list/metadata-list.component';
import { DataServiceFactory } from '../../services/data.service.factory';
import { IDataService } from '../../services/idata.service';
import { Data } from 'src/app/model/data';

@Component({
  selector: 'app-data-list',
  templateUrl: './data-list.component.html',
  styleUrls: ['./data-list.component.css']
})
export class DataListComponent implements OnInit {

  @Input() data: Data[] = [];
  @Output() dataChanged = new EventEmitter<Data[]>();
  showDataForm = false;
  isEdit = false;
  reset = false;
  selectedData: Data | null = null;
  showMetadata = false;

  @ViewChild(MetadataListComponent) metadataListComponent!: MetadataListComponent;
  private dataService: IDataService;

  constructor(private dataServiceFactory: DataServiceFactory) {
    this.dataService = this.dataServiceFactory.getService();
  }

  ngOnInit(): void {
    this.fetchData();
  }

  fetchData(): void {
    this.dataService.getData().subscribe(data => {
      this.data = data;
      console.log('Successfully fetched data!');
      this.showDataForm = false;
      this.showMetadata = false;
    });
  }

  editData(id: number): void {
    this.dataService.getDataById(id).subscribe(data => {
      if (data) {
        this.selectedData = { ...data }; 
        this.isEdit = true; 
        this.showDataForm = true;
        this.showMetadata = false;
      } else {
        console.log('Data not found');
      }
    });
  }

  deleteData(id: number): void {
    this.dataService.deleteData(id).subscribe(success => {
      if (success) {
        this.data = this.data.filter(item => item.id !== id);
        this.dataChanged.emit(this.data);
        console.log('Successfully deleted data!');
        this.showMetadata = false;
      }
    });
    setTimeout(() => {
      this.fetchData();
    }, 1000);
  }

  createData(): void {
    this.selectedData = { id: 0, value: '' };
    this.isEdit = false;
    this.showDataForm = true;
    this.showMetadata = false;
  }

  onCancel(): void {
    this.selectedData = null;
    this.showDataForm = false;
    console.log('Form cancelled');
  }

  onFormSubmit(formData: { id: number, value: string }): void {
    if (this.isEdit && this.selectedData) {
      const updatedData: Data = { ...this.selectedData, ...formData };
      this.dataService.updateData(updatedData.id, updatedData).subscribe(updated => {
        if (updated) {
          this.data = this.data.map(item => item.id === updated.id ? updated : item);
          this.dataChanged.emit(this.data);
          this.showDataForm = false;
          console.log('Data updated successfully!');
        }
      });
    } else {
      const newData: Data = { id: 0, value: formData.value };
      this.dataService.createData(newData).subscribe(created => {
        this.data.push(created);
        this.dataChanged.emit(this.data);
        this.showDataForm = false;
        console.log('Data created successfully!');
      });
    }
  }

  showDetails(id: number): void {
    this.selectedData = { id: id, value: '' }
    this.showMetadata = true;
    this.showDataForm = false;
    if (this.metadataListComponent) {
      this.metadataListComponent.dataId = id;
      this.metadataListComponent.fetchMetadata();
    }
  }
}