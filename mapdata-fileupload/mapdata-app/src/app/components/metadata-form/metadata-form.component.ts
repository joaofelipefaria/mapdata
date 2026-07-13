import { Component, Input, Output, EventEmitter, Inject } from '@angular/core';
import { Metadata } from 'src/app/model/metadata';
import { FileService } from 'src/app/services/file.service';

@Component({
  selector: 'app-metadata-form',
  templateUrl: './metadata-form.component.html',
  styleUrls: ['./metadata-form.component.css']
})
export class MetadataFormComponent {
  constructor(private fileService: FileService) {}
  @Input() metadata: Metadata | null = null;  
  @Input() isEdit: boolean = false;
  @Output() formSubmit = new EventEmitter<Metadata>();
  @Output() cancel = new EventEmitter<void>(); 

  selectedFile: File | null = null;

  get metadataData(): Metadata {
    return this.metadata ?? { id: 0, dataId: 0, value1: '', value2: '', fileName: '' };
  }

  onSubmit(): void {
    if (!this.metadata) {
      return;
    }

    if (this.selectedFile) {
      this.fileService.uploadFile(this.selectedFile).subscribe({
        next: response => {
          console.log('Upload success', response);
          if (this.metadata) {
            this.metadata.fileName = response.filename;
            this.selectedFile = null;
            this.formSubmit.emit(this.metadata);
          }
        },
        error: error => {
          console.error('Upload error', error);
        }
      });
    } else {
      this.formSubmit.emit(this.metadata);
    }
  }

  onCancel(): void {
    this.cancel.emit();
  }

  onRemoveFile(): void {
    if (this.metadata && this.metadata.fileName) {
      this.fileService.deleteFile(this.metadata.fileName).subscribe({
        next: response => {
          console.log('Delete success', response);
          if (this.metadata) {
            this.metadata.fileName = '';
            this.selectedFile = null;
          }
        },
        error: error => {
          console.error('Delete error', error);
        }
      });
    }
  }

  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    const file = input.files?.[0];

    if (file) {
      this.selectedFile = file;
    }
  }

  onDownloadFile(): void {
    if (!this.metadata || !this.metadata.fileName) {
      return;
    }

    this.fileService.getFile(this.metadata.fileName).subscribe({
      next: (blob: Blob) => {
        const url = window.URL.createObjectURL(blob);
        const link = document.createElement('a');
        link.href = url;
        link.download = this.metadata?.fileName || 'file';
        link.click();
        window.URL.revokeObjectURL(url);
      },
      error: error => {
        console.error('Download error', error);
      }
    });
  }
}
