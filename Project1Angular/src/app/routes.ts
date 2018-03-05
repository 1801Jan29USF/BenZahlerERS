import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { EmployeeHomeComponent } from './components/employee-home/employee-home.component';
import { ManagerHomeComponent } from './components/manager-home/manager-home.component';
import { ManLoggedGuard } from './guards/man-logged.guard';
import { EmpLoggedGuard } from './guards/emp-logged.guard';




export const appRoutes: Routes = [
    {
        path: 'login',
        component: LoginComponent
    },
    {
        path: 'emp-home',
        component: EmployeeHomeComponent,
        canActivate: [ EmpLoggedGuard ]
    },
    {
        path: 'man-home',
        component: ManagerHomeComponent,
        canActivate: [ ManLoggedGuard ]
    },
    {
        path: '**',
        pathMatch: 'full',
        redirectTo: 'login'
      }
];
