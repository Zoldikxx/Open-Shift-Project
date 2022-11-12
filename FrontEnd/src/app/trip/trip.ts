import { Station } from "../station/station";

export interface Trip {
    id: number;
    startTime: string;
    endTime: string;
    fromStation: Station;
    toStation: Station;
}