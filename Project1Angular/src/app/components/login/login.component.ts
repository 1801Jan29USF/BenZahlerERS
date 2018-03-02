import { Component, OnInit } from '@angular/core';
import { CookieService } from 'angular2-cookie/services/cookies.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { User } from '../../beans/User';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  credential =  {
    username: '',
    password: ''
  };

  constructor(private client: HttpClient, private cookie: CookieService, private router: Router, private userService: UserService) { }

  ngOnInit() {
  }

  login () {
    this.client.post('http://localhost:8080/ERSProject1/login', this.credential, {withCredentials: true})
      .subscribe(
        success => {
          this.userService.storeUser(success);
          // this.cookie.putObject('user', success);
          if (success['role_id'] === 0) {
            this.router.navigateByUrl('/emp-home');
          } else if (success['role_id'] === 1) {
            this.router.navigateByUrl('/man-home');
          }

        },
        error => {
          alert('Failed to log in');
          console.log(error);
        });
  }

}
