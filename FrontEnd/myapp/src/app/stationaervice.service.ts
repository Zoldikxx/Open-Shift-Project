import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { catchError, Observable, tap, throwError } from 'rxjs';
import { Station } from './station';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})

export class StationaerviceService {
  constructor(private http: HttpClient) { }

  public getStation(): Observable<Station[]> {
    return this.http.get<Station[]>(`${environment.apiUrl}/api/station`).pipe(
      tap((data: any) => console.log('All', JSON.stringify(data))),
      catchError(this.handleError)
    );
  }

  public addStation(station: Station): Observable<Station[]> {
    return this.http.post<Station[]>(`${environment.apiUrl}/api/station`, station).pipe(
      tap((data: any) => console.log('All', JSON.stringify(data))),
      catchError(this.handleError));
  }

  public deletStation(stationid: number): Observable<void> {
    return this.http.delete<void>(`${environment.apiUrl}/api/station/${stationid}`);
  }

  updateStation(station: Station): Observable<Station> {
    let updateStationUrl = `${environment.apiUrl}/api/station/update/` + station.id + '?name=' + station.name;
    return this.http.put<Station>(updateStationUrl, station).pipe(
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
