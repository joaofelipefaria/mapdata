import { Injectable } from '@angular/core';

declare const Keycloak: any;

@Injectable({
  providedIn: 'root',
})
export class KeycloakService {
  private keycloakInstance: any;

  constructor() {
    this.keycloakInstance = new Keycloak({
      url: 'http://localhost:6080/', // URL do Keycloak
      realm: 'mapdata-realm', // Nome do Realm
      clientId: 'mapdata-client', // ID do Cliente
    });
  }

  init(): Promise<boolean> {
    return this.keycloakInstance
      .init({
        onLoad: 'login-required', // Redireciona para login automaticamente
        checkLoginIframe: false,
      })
      .then((authenticated: boolean) => {
        console.log('Authenticated:', authenticated);
        return authenticated;
      })
      .catch((err: any) => {
        console.error('Keycloak initialization failed', err);
        return false;
      });
  }

  getToken(): string {
    return this.keycloakInstance.token;
  }

  logout(): void {
    this.keycloakInstance.logout();
  }

  getUserProfile(): Promise<any> {
    return this.keycloakInstance.loadUserProfile();
  }
}