import {
    Component,
  } from "@angular/core";
import { Router } from "@angular/router";
import { TokenStorageService } from "app/services/token-storage.service";
import { MenuItem } from "primeng/api";
  import { PanelMenuModule } from 'primeng/panelmenu';
  
  @Component({
    selector: "app-panel-menu",
    standalone: true,
    imports: [PanelMenuModule],
    template: `
        <p-panelMenu [model]="items" styleClass="w-full" />
    `
  })
  export class PanelMenuComponent {
    constructor(private tokenStorage:TokenStorageService,private router:Router){};
    public readonly items: MenuItem[] = [
        {
            label: 'Accueil',
            icon: 'pi pi-home',
            routerLink: ['/home']
        },
        {
            label: 'Produits',
            icon: 'pi pi-barcode',
            routerLink: ['/products/list']
        },
  {
    label: 'Déconnexion',
    icon: 'pi pi-sign-out',
    command: () => this.logout()
  }
    ]
      logout(){
        this.tokenStorage.clearToken();
        this.router.navigate(['/login']);
  }
  }

  