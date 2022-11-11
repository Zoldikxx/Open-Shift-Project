import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Station } from './station';
import { StationaerviceService } from './stationaervice.service';

@Component({
    selector: 'app-station',
    templateUrl: './station.component.html',
    providers: [StationaerviceService],
})
export class StationComponent implements OnInit, OnDestroy {
    constructor(private stationService: StationaerviceService) { }
    temp1: any;
    temp2: Station = { id: 0, name: '' };
    editable: boolean = false;
    uneditable: boolean = true;
    newName: string = '';
    pageTitle = 'Stations List';
    stations: Station[] = [];
    stationsNumber: number = 0;
    numberArr: number[] = [];
    sub!: Subscription;

    editStation(station: Station) {
        this.stationService.updateStation(station).subscribe();
        window.location.reload();
    }
    ngOnInit() {
        this.sub = this.stationService.getStation().subscribe({
            next: ad => this.stations = ad
        });
        this.stationsNumber = this.stations.length + 1;
    }

    initiateNumber() {
        for (let i = 0; i < this.numberArr.length; i++) {
            this.numberArr[i] = this.stationsNumber + i;
        }
    }

    startEdit() {
        this.editable = true;
        this.uneditable = false;
    }
    endEdit() {
        this.editable = false;
        this.uneditable = true;
    }

    addStation() {
        this.sub = this.stationService.getStation().subscribe({
            next: ad => this.stations = ad
        });
        this.stationService.addStation(this.temp2).subscribe();
        window.location.reload();
    }

    deleteRow(d: number) {
        this.sub = this.stationService.getStation().subscribe({
            next: ad => this.stations = ad
        });
        this.stationService.deletStation(d).subscribe();
        window.location.reload();
    }
    ngOnDestroy() {
        this.sub.unsubscribe();
    }
}
