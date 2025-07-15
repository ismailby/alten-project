import { Routes } from "@angular/router";
import { HomeComponent } from "./shared/features/home/home.component";
import { authRoutes } from "./auth/auth.routes";
import { DashboardLayoutComponentComponent } from "./DashboardLayout/features/dashboard-layout-component/dashboard-layout-component.component";
import { LoginComponent } from "./auth/features/login/login.component";
import { RegisterComponent } from "./auth/features/register/register.component";

export const APP_ROUTES: Routes = [
  {
    path: '',
    component: HomeComponent, // layout vide
    children: [
      { path: 'login', component: LoginComponent },
      { path: 'register', component: RegisterComponent },
    ]
  },
  {
    path: '',
    component: DashboardLayoutComponentComponent, // layout avec menu/splitter
    children: [
      { path: 'home', component: HomeComponent },
      {
        path: 'products',
        loadChildren: () =>
          import('./products/products.routes').then((m) => m.PRODUCTS_ROUTES)
      },
      {
        path: 'cart',
        loadChildren: () =>
          import('./cart/cart.routes').then((m) => m.CART_ROUTES)
      },
    ]
  },
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: '**', redirectTo: 'home' }
];
