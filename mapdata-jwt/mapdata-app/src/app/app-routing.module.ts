import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DataListComponent } from './components/data-list/data-list.component';
import { DataFormComponent } from './components/data-form/data-form.component';
import { MetadataListComponent } from './components/metadata-list/metadata-list.component';
import { MetadataFormComponent } from './components/metadata-form/metadata-form.component';

const routes: Routes = [
  {
    path: 'data-list',
    component: DataListComponent
  },
  {
    path: 'data-form',
    component: DataFormComponent
  },
  {
    path: 'metadata-list',
    component: MetadataListComponent
  },
  {
    path: 'metadata-form',
    component: MetadataFormComponent
  },
  { path: '**', redirectTo: 'data-list' }, // Redirect to data-list for any unknown routes
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}