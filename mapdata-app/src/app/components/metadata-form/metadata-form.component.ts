import { Component, Input, Output, EventEmitter } from '@angular/core';
import { Metadata } from 'src/app/model/metadata';

@Component({
  selector: 'app-metadata-form',
  templateUrl: './metadata-form.component.html',
  styleUrls: ['./metadata-form.component.css']
})
export class MetadataFormComponent {
  @Input() metadata: Metadata | null = null;  
  @Input() isEdit: boolean = false;
  @Output() formSubmit = new EventEmitter<Metadata>();
  @Output() cancel = new EventEmitter<void>(); 

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
