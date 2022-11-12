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
    temp2: any = {
        id: 0,
        startTime: '',
        endTime: '',
        fromStation: {
            id: 0,
            name: ""
        },
        toStation: {
            id: 0,
            name: ""
        }
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
        let y = this.stations.find(
            (x) => x.name == fromStation
        );
        console.log(y);
        this.temp1.fromStation = y;
        this.sub = this.tripService.updateTrip(this.temp1).subscribe();

    }
    editStationTS(toStation: string, id: number) {
        let y = this.stations.find(
            (x) => x.name == toStation
        );
        console.log(y);
        this.temp1 = this.trips.find((x) => x.id === id);
        this.temp1.toStation = y;
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
    setFromStation(event: any) {
        this.temp2.fromStation = this.stations.find(
            (x) => x.name == event.target.value
        );
        console.log(event.target.value);
    }
    setToStation(event: any) {
        this.temp2.toStation = this.stations.find(
            (x) => x.name == event.target.value
        );
        console.log(event.target.value);
    }

    addTrip() {
        console.log(this.temp2);
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