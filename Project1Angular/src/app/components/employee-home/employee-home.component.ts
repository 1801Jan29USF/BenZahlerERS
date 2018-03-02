import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserService } from '../../services/user.service';
import { User } from '../../beans/User';
import { Reimbursement } from '../../beans/Reimbursement';


@Component({
  selector: 'app-employee-home',
  templateUrl: './employee-home.component.html',
  styleUrls: ['./employee-home.component.css']
})
export class EmployeeHomeComponent implements OnInit {
/*
  Create a new reimbursement
  View their pending reimbursements
  View their past reimbursements
*/
  show = true;
  user = this.userService.getUser();

  reimbursement = {
    amount: '',
    description: '',
    authorId: this.user.user_id,
    typeId: '3'
  };

  types = [
    {id: 0, name: 'Lodging'},
    {id: 1, name: 'Travel'},
    {id: 2, name: 'Food'},
    {id: 3, name: 'Other'}
  ];

  reimbList: Array<Reimbursement>;
  fullReimbList: Array<Reimbursement>;

  constructor(private client: HttpClient, private userService: UserService) { }

  newReimb () {
    if (this.reimbursement.amount === '' || this.reimbursement.description === '') {
      alert('Enter an amount and a description.');
    } else {
      this.client.post('http://localhost:8080/ERSProject1/employee', this.reimbursement)
      .subscribe(
        success => {
          alert('Reimbursement Submitted');
          this.getPendingReimb();
          this.fullReimbHistory();
        },
        error => {
          alert('Error submitting reimbursement. Check in with your administrator.');
        });
    }
  }

  getPendingReimb () {
    this.client.get(`http://localhost:8080/ERSProject1/employee/status/${this.user.user_id}`).subscribe(
      (success: Array<Reimbursement>) => {
        this.reimbList = success;
      },
      error => {
        console.log(error);
      }
    );
  }

  fullReimbHistory () {
    this.client.get(`http://localhost:8080/ERSProject1/employee/history/${this.user.user_id}`).subscribe(
      (success: Array<Reimbursement>) => {
        this.fullReimbList = success;
      },
      error => {
        console.log(error);
      }
    );
  }

  ngOnInit() {
    this.getPendingReimb();
    this.fullReimbHistory();
  }


}
