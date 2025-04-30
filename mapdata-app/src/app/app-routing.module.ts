import { NgModule } from '@angular/core';
import { RouterModule, Routes, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { DataListComponent } from './components/data-list/data-list.component';
import { DataFormComponent } from './components/data-form/data-form.component';
import { MetadataListComponent } from './components/metadata-list/metadata-list.component';
import { MetadataFormComponent } from './components/metadata-form/metadata-form.component';

declare const Keycloak: any; // Declaração para usar o Keycloak diretamente

class KeycloakGuard implements CanActivate {
  async canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Promise<boolean> {
    const keycloak = new Keycloak({
      url: 'http://localhost:6080/', // URL do Keycloak
      realm: 'mapdata-realm', // Nome do Realm
      clientId: 'mapdata-client', // ID do Cliente
    });

    // Inicializa o Keycloak
    const authenticated = await keycloak.init({
      onLoad: 'check-sso', // Verifica se o usuário está autenticado
      checkLoginIframe: false,
    });

    if (!authenticated) {
      keycloak.login(); // Redireciona para o login do Keycloak
      return false;
    }

    return true; // Permite o acesso à rota
  }
}

const routes: Routes = [
  {
    path: 'data-list',
    component: DataListComponent,
    canActivate: [KeycloakGuard], // Protege a rota com o guard personalizado
  },
  {
    path: 'data-form',
    component: DataFormComponent,
    canActivate: [KeycloakGuard], // Protege a rota com o guard personalizado
  },
  {
    path: 'metadata-list',
    component: MetadataListComponent,
    canActivate: [KeycloakGuard], // Protege a rota com o guard personalizado
  },
  {
    path: 'metadata-form',
    component: MetadataFormComponent,
    canActivate: [KeycloakGuard], // Protege a rota com o guard personalizado
  },
  { path: '**', redirectTo: 'data-list' }, // Redireciona rotas inválidas para 'data-list'
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}