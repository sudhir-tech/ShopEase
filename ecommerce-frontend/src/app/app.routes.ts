import { Routes } from '@angular/router';

import { Home } from './pages/home/home';
import { Products } from './pages/products/products';
import { Login } from './pages/login/login';
import { Cart } from './pages/cart/cart';
import { authGuard } from './guards/auth-guard';
import { Register } from './pages/register/register';
import { Orders } from './pages/orders/orders';
import { Admin } from './pages/admin/admin';

export const routes: Routes = [
  {
    path: '',
    component: Home
  },
  {
    path: 'login',
    component: Login
  },
  {
    path: 'products',
    component: Products,
    canActivate: [authGuard]
  },
  {
    path: 'cart',
    component: Cart,
    canActivate: [authGuard]
  },
  {
    path: 'register',
    component: Register
},
{
    path: 'orders',
    component: Orders
},
{
  path: 'admin',
  component: Admin,
  canActivate: [authGuard]
}
];