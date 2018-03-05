import { Component, OnInit } from '@angular/core';
import { Reimbursement } from '../../beans/Reimbursement';
import { HttpClient } from '@angular/common/http';
import { UserService } from '../../services/user.service';
import { User } from '../../beans/User';

@Component({
  selector: 'app-manager-home',
  templateUrl: './manager-home.component.html',
  styleUrls: ['./manager-home.component.css']
})
export class ManagerHomeComponent implements OnInit {

  statusShow = false;
  userHistoryShow = false;
  allHistoryShow = false;
  activeUser = this.userService.getUser();
  user = new User();
  userId: number;
  status: number;
  statusList: Array<Reimbursement>;
  userHistoryList: Array<Reimbursement>;
  allHistoryList: Array<Reimbursement>;
  statuses = [
    {id: 1, name: 'Approve'},
    {id: 2, name: 'Deny'},
  ];
  reimbursement = {
    reimbId: '',
    statusId: '',
    resolverId: this.activeUser.user_id,
  };

  updateReimb() {
    this.client.post('http://localhost:8080/ERSProject1/manager/update/', this.reimbursement).subscribe(
      success => {
        alert('Reimbursement updated');
        this.allReimbHistory();
      },
      error => {
        console.log(error);
        alert('Error');
      }
    );
  }

  newUser(user: User) {
    this.client.post('http://localhost:8080/ERSProject1/manager/register/', user).subscribe(
      success => {
        alert('New user added.');
      },
      error => {
        console.log(error);
        alert('Error');
      }
    );
  }

  getByStatus(status: number) {
    this.client.get(`http://localhost:8080/ERSProject1/manager/status/${status}`).subscribe(
      (success: Array<Reimbursement>) => {
        this.statusList = success;
        this.statusShow = true;
        this.allReimbHistory();
      },
      error => {
        console.log(error);
        alert('Error');
      }
    );
  }

  getUserHistory(userId: number) {
    this.client.get(`http://localhost:8080/ERSProject1/manager/history/${userId}`).subscribe(
      (success: Array<Reimbursement>) => {
        this.userHistoryList = success;
        this.userHistoryShow = true;
      },
      error => {
        console.log(error);
        alert('Error');
      }
    );
  }

  allReimbHistory () {
    this.client.get('http://localhost:8080/ERSProject1/manager/history/all').subscribe(
      (success: Array<Reimbursement>) => {
        this.allHistoryList = success;
      },
      error => {
        console.log(error);
        alert('Error');
      }
    );
  }

  constructor(private client: HttpClient, private userService: UserService) { }

  ngOnInit() {
    this.allReimbHistory();
  }



}

/*
  view ALL reimbursements   ----------------
  view the history of a specific user -------------
  view by status   ----------------------

  register a new user    ------------------
  update a reimbursements status (approve or deny)
  create a new reimbursement
*/
