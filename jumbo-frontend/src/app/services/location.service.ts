import { Injectable } from '@angular/core';
import { Location } from '../model/location';

@Injectable({
  providedIn: 'root'
})
export class LocationService {
  
  //NETHERLAND LOCATION
  private netherland:Location={latitude:52.132633,longitude:5.291266};
  
  constructor() {

    let location=JSON.parse(localStorage.getItem('location')||'[]');

    if(location.latitude==null){
      this.setToCurrentLocation();

    }

  }

  public get locationValue():Location{
    return JSON.parse(localStorage.getItem('location')||'[]');
  }


  public set locationValue(location:Location){
    localStorage.setItem('location',JSON.stringify(location));
    
  }


  private setToCurrentLocation(){

    navigator.geolocation.getCurrentPosition(pos=>{
      let location = {latitude:pos.coords.latitude,longitude:pos.coords.longitude};
      localStorage.setItem('location',JSON.stringify(location));

    },err=>{
      console.log("error while trying to get the user location, check the rights");
      localStorage.setItem('location',JSON.stringify(this.netherland));
      
    });


  }

  public clearLocation(){
    localStorage.removeItem('location');
  }

  public getDefaultLocation():Location{
    return this.netherland;
  }



}
