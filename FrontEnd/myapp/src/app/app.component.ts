import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Admin } from './Admin';
import { AdminService } from './adminservice.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [AdminService]
})
export class AppComponent implements OnInit, OnDestroy {
  title = 'myapp';
  errorMessag: String = "";
  sub!: Subscription;
  public admin: Admin[] = [];
  constructor(private adminservice: AdminService) { }


  ngOnInit(): void {
    this.sub = this.adminservice.getAdmin().subscribe({
      next: ad => this.admin = ad,
      error: err => this.errorMessag = err
    });
  }
  ngOnDestroy() {
    this.sub.unsubscribe();
  }

}
