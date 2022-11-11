import { Component } from '@angular/core';
import { Trip } from './trip';
import { Station } from '../station/station';
import { TripserviceService } from './tripservice.service';
import { StationaerviceService } from '../station/stationaervice.service';
import { Subscription } from 'rxjs';

@Component({
    selector: 'app-trip',
    templateUrl: './trip.component.html',
    providers: [TripserviceService, StationaerviceService],
})
export class TripComponent {
    constructor(
        private tripService: TripserviceService,
        private stationService: StationaerviceService
    ) { }
    temp1: any;
    temp2: Trip = {
        id: 0,
        startTime: '',
        endTime: '',
        fromStation: '',
        toStation: '',
    };
    pageTitle = 'Trips List';
    editable: boolean = false;
    uneditable: boolean = true;
    addRowBoolean: boolean = false;
    trips: Trip[] = [];
    stations: Station[] = [];
    tripsNumber: number = 0;
    numberArr: number[] = [];
    sub!: Subscription;

    editStationST(startTime: string, id: number) {
        this.temp1 = this.trips.find((x) => x.id === id);
        this.temp1.startTime = startTime;
        this.sub = this.tripService.updateTrip(this.temp1).subscribe();

    }
    editStationET(endTime: string, id: number) {
        this.temp1 = this.trips.find((x) => x.id === id);
        this.temp1.endTime = endTime;
        this.sub = this.tripService.updateTrip(this.temp1).subscribe();

    }
    editStationFS(fromStation: string, id: number) {
        this.temp1 = this.trips.find((x) => x.id === id);
        this.temp1.fromStation = fromStation;
        this.sub = this.tripService.updateTrip(this.temp1).subscribe();

    }
    editStationTS(toStation: string, id: number) {
        this.temp1 = this.trips.find((x) => x.id === id);
        this.temp1.toStation = toStation;
        this.sub = this.tripService.updateTrip(this.temp1).subscribe();

    }

    printList() {
        console.log(this.trips);
    }

    ngOnInit() {
        this.sub = this.stationService.getStation().subscribe({
            next: ad => this.stations = ad
        });
        this.sub = this.tripService.getTrip().subscribe({
            next: ad => this.trips = ad
        });
    }

    startEdit() {
        this.editable = true;
        this.uneditable = false;
    }
    endEdit() {
        this.editable = false;
        this.uneditable = true;
        window.location.reload();
    }

    addTrip() {
        this.tripService.addTrip(this.temp2).subscribe();
        this.sub = this.tripService.getTrip().subscribe({
            next: ad => this.trips = ad
        });
        window.location.reload();
    }

    deleteRow(d: number) {
        this.sub = this.tripService.getTrip().subscribe({
            next: ad => this.trips = ad
        });
        this.tripService.deleteTrip(d).subscribe();
        window.location.reload();
    }
}