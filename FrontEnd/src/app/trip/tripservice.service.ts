import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { catchError, Observable, tap, throwError } from 'rxjs';
import { Trip } from './trip';
@Injectable({
  providedIn: 'root'
})
export class TripserviceService {

  constructor(private http: HttpClient) { }

  public getTrip(): Observable<Trip[]> {
    return this.http.get<Trip[]>(`http://localhost:8080/api/trip`).pipe(
      tap((data: any) => console.log('All', JSON.stringify(data))),
      catchError(this.handleError)
    );
  }

  //SignUp
  public addTrip(trip: Trip): Observable<Trip[]> {
    return this.http.post<Trip[]>(`http://localhost:8080/api/trip/addtrip`, trip).pipe(
      tap((data: any) => console.log('All', JSON.stringify(data))),
      catchError(this.handleError)); ``
  }

  public deleteTrip(tripid: number): Observable<void> {
    return this.http.delete<void>(`http://localhost:8080/api/trip/delete/${tripid}`);
  }

  updateTrip(trip: Trip): Observable<Trip> {
    // http://localhost:8080/api/trip/update/1?startTime=1&endTime=asd&fromStationId=1&toStationId=2
    let updateStationUrl = `http://localhost:8080/api/trip/update/${trip.id}/?startTime=${trip.startTime}&endTime=${trip.endTime}&fromStationId=${trip.fromStation.id}&toStationId=${trip.toStation.id}`;
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
