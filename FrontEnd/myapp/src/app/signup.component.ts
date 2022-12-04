import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { HttpErrorResponse } from '@angular/common/http';
import { Admin } from './Admin';
import { AdminService } from './adminservice.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css'],
  providers: [AdminService]
})
export class SignupComponent implements OnInit {
  admin: Admin = {
    email: "",
    id: 0,
    password: "",
    userName: "",
    activation: false
  }


  errorMessag: String = "";
  sub!: Subscription;
  constructor(private adminservice: AdminService) { }
  ngOnInit(): void {

  }

  signup() {
    this.adminservice.signUp(this.admin).subscribe();
  }
}
