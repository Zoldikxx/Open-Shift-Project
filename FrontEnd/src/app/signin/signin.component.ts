import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Admin } from '../Admin';
import { AdminService } from '../adminservice.service';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent implements OnInit {

  admin: Admin = {
    email: "",
    id: 0,
    password: "",
    userName: "",
    activation: false
  }
  temp: boolean = false;
  temp3: any;

  admins: Admin[] = [];
  newAdmin: any;
  signinUrl: string = "http://localhost:4200/signin";

  errorMessag: String = "";
  sub!: Subscription;
  constructor(private adminservice: AdminService, private router: Router) { }

  getAdmins() {
    this.adminservice.getAdmin().subscribe(
      {
        next: ad => this.admins = ad,
        error: err => this.errorMessag = err
      }
    );
  }
  checkUser(name: string, password: string) {
    this.temp3 = this.admins.find((x) => x.userName === name);
    if (this.temp3.password === password) {
      this.router.navigate(['/trip']);
    }
    else {
      alert("Wrong Cridentials");
    }

  }

  ngOnInit(): void {
    this.getAdmins();
  }
  signIn(): void {
    this.adminservice.signIn(this.admin).subscribe();

  }
}
