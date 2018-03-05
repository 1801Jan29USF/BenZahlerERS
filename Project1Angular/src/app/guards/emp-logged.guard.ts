import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { CookieService } from 'angular2-cookie/services/cookies.service';
import { UserService } from '../services/user.service';

@Injectable()
export class EmpLoggedGuard implements CanActivate {
  constructor(private cookie: CookieService, private router: Router, private userService: UserService) {

  }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    if (this.userService.getUser().role_id === 0) {
      return true;
    } else {
      this.router.navigateByUrl('/login');
    }
  }
}
