import { Component, Input, Output, EventEmitter, Inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Metadata } from 'src/app/model/metadata';

@Component({
  selector: 'app-metadata-form',
  templateUrl: './metadata-form.component.html',
  styleUrls: ['./metadata-form.component.css']
})
export class MetadataFormComponent {
  constructor(@Inject(HttpClient) private http: HttpClient) {}
  @Input() metadata: Metadata | null = null;  
  @Input() isEdit: boolean = false;
  @Output() formSubmit = new EventEmitter<Metadata>();
  @Output() cancel = new EventEmitter<void>(); 

  selectedFile!: File;

  get metadataData(): Metadata {
    return this.metadata ?? { id: 0, dataId: 0, value1: '', value2: '' };
  }

  onSubmit(): void {
    if (this.metadata) {
      this.formSubmit.emit(this.metadata); 
    }
  }
  onCancel(): void {
    this.cancel.emit();
  }
}
