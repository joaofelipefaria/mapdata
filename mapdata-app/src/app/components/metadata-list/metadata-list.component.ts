import { Component, EventEmitter, Input, Output, OnInit } from '@angular/core';
import { DataService } from '../../services/data.service';
import { IDataService } from '../../services/idata.service';
import { DataServiceFactory } from '../../services/data.service.factory';
import { Metadata } from 'src/app/model/metadata';

@Component({
  selector: 'app-metadata-list',
  templateUrl: './metadata-list.component.html',
  styleUrls: ['./metadata-list.component.css']
})
export class MetadataListComponent implements OnInit {

  @Input() dataId: number | undefined = undefined; 
  @Output() metadataChanged = new EventEmitter<Metadata[]>(); 
  showMetadataForm = false;  
  selectedMetadata: Metadata | null = null;

  metadata: Metadata[] = []; 

  private dataService: IDataService; 

  constructor(private dataServiceFactory: DataServiceFactory) { 
    this.dataService = this.dataServiceFactory.getService();
  }

  ngOnInit(): void {
    this.fetchMetadata();  
  }

  fetchMetadata(): void {
    this.dataService.getMetadataByDataId(this.dataId ?? 0).subscribe(metadata => {
      this.metadata = metadata ?? [];
    });
    this.showMetadataForm = false;
  }

  createMetadata(): void {
    this.selectedMetadata = { id: 0, dataId:this.dataId ?? 0, value1: '', value2: '' };
    this.showMetadataForm = true;
  }

  editMetadata(id: number): void {
    this.dataService.getMetadataById(id, this.dataId ?? 0).subscribe(metadata => {
      if (metadata) {
        metadata.dataId = this.dataId ?? 0;
        this.metadataChanged.emit(this.metadata); 
        this.selectedMetadata = {... metadata};
        this.showMetadataForm = true;
      }
    });
  }

  deleteMetadata(id: number): void {
    this.dataService.deleteMetadata(id, this.dataId ?? 0).subscribe(success => {
      if (success) {
        this.metadata = this.metadata.filter(item => item.id !== id);
        this.metadataChanged.emit(this.metadata);
        this.showMetadataForm = false;
      }
    });
    setTimeout(() => {
      this.fetchMetadata();
    }, 1000);
  }

    onCancel(): void {
      this.selectedMetadata = null;
      this.showMetadataForm = false;
      console.log('Form cancelled');
    }
  
    onFormSubmit(metadata: { id: number, value1: string, value2: string }): void {
      if (this.selectedMetadata?.id) {
        const updatedData: Metadata = { ...this.selectedMetadata, ...metadata };
        this.dataService.updateMetadata(updatedData.id, updatedData).subscribe(updated => {
          if (updated) {
            this.metadata = this.metadata.map(item => item.id === updated.id ? updated : item);
            this.metadataChanged.emit(this.metadata);
            this.showMetadataForm = false;
            console.log('Data updated successfully!');
          }
        });
      } else {
        const newMetadata: Metadata = { id: 0, dataId: this.dataId ?? 0, value1: metadata.value1 , value2: metadata.value2 };
        this.dataService.createMetadata(newMetadata).subscribe(created => {
          this.metadata.push(created);
          this.metadataChanged.emit(this.metadata);
          this.showMetadataForm = false;
          console.log('Data created successfully!');
        });
      }
    }
}
