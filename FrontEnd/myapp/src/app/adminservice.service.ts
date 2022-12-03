import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { catchError, Observable, tap, throwError } from 'rxjs';
import { Admin } from './Admin';
import { environment } from 'src/environments/environment';


@Injectable({
  providedIn: 'root'
})
export class AdminService {
  constructor(private http: HttpClient) { }

  //Get Admins
  public getAdmin(): Observable<Admin[]> {
    return this.http.get<Admin[]>(`${environment.apiUrl}/api/admin`).pipe(
      tap((data: any) => console.log('All', JSON.stringify(data))),
      catchError(this.handleError)
    );
  }

  //SignUp
  public signUp(admin: Admin): Observable<Admin[]> {
    return this.http.post<Admin[]>(`${environment.apiUrl}/api/admin/signup`, admin).pipe(
      tap((data: any) => console.log('All', JSON.stringify(data))),
      catchError(this.handleError));
  }
  //SignIn
  public signIn(admin: Admin): Observable<void> {
    return this.http.post<void>(`${environment.apiUrl}/api/admin/signin`, admin).pipe(
      tap((data: any) => console.log('All', JSON.stringify(data))),
      catchError(this.handleError));
  }


  private handleError(err: HttpErrorResponse) {
    let errorMessage = '';
    if (err.error instanceof ErrorEvent) {

      errorMessage = err.error.message;
    }
    else {
      errorMessage = err.message;
    }
    console.error(errorMessage);

    return throwError(() => errorMessage);
  }

}