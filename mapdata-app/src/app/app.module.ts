import { NgModule, APP_INITIALIZER } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { KeycloakService } from './services/keycloak.service';
import { DataListComponent } from './components/data-list/data-list.component';
import { DataFormComponent } from './components/data-form/data-form.component';
import { MetadataListComponent } from './components/metadata-list/metadata-list.component';
import { MetadataFormComponent } from './components/metadata-form/metadata-form.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

function initializeKeycloak(keycloak: KeycloakService) {
  return () => keycloak.init();
}

@NgModule({
  declarations: [
    AppComponent,
    DataListComponent,
    DataFormComponent,
    MetadataListComponent,
    MetadataFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    {
      provide: APP_INITIALIZER,
      useFactory: initializeKeycloak,
      multi: true,
      deps: [KeycloakService],
    },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
