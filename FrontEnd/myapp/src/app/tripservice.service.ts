import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { catchError, Observable, tap, throwError } from 'rxjs';
import { Trip } from './trip';
import { environment } from 'src/environments/environment';
@Injectable({
  providedIn: 'root'
})
export class TripserviceService {

  constructor(private http: HttpClient) { }

  public getTrip(): Observable<Trip[]> {
    return this.http.get<Trip[]>(`${environment.apiUrl}/api/trip`).pipe(
      tap((data: any) => console.log('All', JSON.stringify(data))),
      catchError(this.handleError)
    );
  }

  //SignUp
  public addTrip(trip: Trip): Observable<Trip[]> {
    return this.http.post<Trip[]>(`${environment.apiUrl}/api/trip/addtrip`, trip).pipe(
      tap((data: any) => console.log('All', JSON.stringify(data))),
      catchError(this.handleError)); ``
  }

  public deleteTrip(tripid: number): Observable<void> {
    return this.http.delete<void>(`${environment.apiUrl}/api/trip/delete/${tripid}`);
  }

  updateTrip(trip: Trip): Observable<Trip> {
    // http://localhost:8080/api/trip/update/1?startTime=1&endTime=asd&fromStationId=1&toStationId=2
    let updateStationUrl = `${environment.apiUrl}/api/trip/update/${trip.id}/?startTime=${trip.startTime}&endTime=${trip.endTime}&fromStationId=${trip.fromStation.id}&toStationId=${trip.toStation.id}`;
    return this.http.put<Trip>(updateStationUrl, trip).pipe(
      tap(data => console.log('All: ', JSON.stringify(data))),
      catchError(this.handleError)
    );
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
