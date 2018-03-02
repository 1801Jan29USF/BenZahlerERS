import { Injectable } from '@angular/core';
import { User } from '../beans/User';

@Injectable()
export class UserService {

  user = new User();

  storeUser (inUser: object) {
    this.user.user_id = inUser['user_id'];
    this.user.username = inUser['username'];
    this.user.user_first_name = inUser['user_first_name'];
    this.user.user_last_name = inUser['user_last_name'];
    this.user.user_email = inUser['user_email'];
    this.user.role_id = inUser['role_id'];
  }

  getUser () {
    return this.user;
  }

  constructor() { }

}
