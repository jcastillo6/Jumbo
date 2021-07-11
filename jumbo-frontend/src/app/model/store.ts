import { ObjectLink } from "./objectLink";

export interface Store{
    id:number,
    addressName:string,
    latitude:number,
    longitude:number,
    city:string,
    street:string,
    street2:string,
    street3:string
    todayOpen:string,
    todayClose:string,
    _Links:ObjectLink    
    
}