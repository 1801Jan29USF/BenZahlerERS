import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { EmployeeHomeComponent } from './components/employee-home/employee-home.component';
import { ManagerHomeComponent } from './components/manager-home/manager-home.component';




export const appRoutes: Routes = [
    {
        path: 'login',
        component: LoginComponent
    },
    {
        path: 'emp-home',
        component: EmployeeHomeComponent
    },
    {
        path: 'man-home',
        component: ManagerHomeComponent
    },
    {
        path: '**',
        pathMatch: 'full',
        redirectTo: 'login'
      }
];
