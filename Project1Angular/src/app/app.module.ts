import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import {AppComponent} from './app.component';
import {NavComponent} from './nav/nav.component';
import { appRoutes } from './routes';
import { CookieService } from 'angular2-cookie/services/cookies.service';
import { LoginComponent } from './components/login/login.component';
import { HttpClientModule } from '@angular/common/http';
import { EmployeeHomeComponent } from './components/employee-home/employee-home.component';
import { ManagerHomeComponent } from './components/manager-home/manager-home.component';
import { UserService } from './services/user.service';
import { StatusPipe } from './pipes/status.pipe';
import { TypePipe } from './pipes/type.pipe';


@NgModule({
  imports: [
    BrowserModule,
    HttpModule,
    HttpClientModule,
    NgbModule.forRoot(),
    RouterModule.forRoot(appRoutes, {useHash: true}),
    FormsModule
  ],
  declarations: [
    AppComponent,
    NavComponent,
    LoginComponent,
    EmployeeHomeComponent,
    ManagerHomeComponent,
    StatusPipe,
    TypePipe,

   ],
  providers: [
    CookieService,
    UserService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
