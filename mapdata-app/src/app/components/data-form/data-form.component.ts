import { Component, Input, Output, EventEmitter } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Data } from '../../model/data';

@Component({
  selector: 'app-data-form',
  templateUrl: './data-form.component.html',
  styleUrls: ['./data-form.component.css']
})
export class DataFormComponent {
  @Input() formData: Data | null = null; 
  @Input() isEdit: boolean = false;  
  @Output() formSubmit = new EventEmitter<{ id: number, value: string }>();
  @Output() cancel = new EventEmitter<void>();

  onSubmit(): void {
    if (this.formData) {
      this.formSubmit.emit(this.formData);
    }
  }

  onCancel(): void {
    this.cancel.emit();
  }
}
