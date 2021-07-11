import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Location } from '../model/location';
import { StoreDistance } from '../model/storeDistance';
import { AccountServiceService } from '../services/account-service.service';
import { LocationService } from '../services/location.service';
import { StoreServiceService } from '../services/store-service.service';



@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  form: FormGroup;
  latitude:FormControl;
  longitude:FormControl;
  storeDistances:StoreDistance[]=[];
  serviceError=false;
  showForm=false;
  location:Location;


  constructor(private http:HttpClient,private router:Router,private accountService:AccountServiceService,private storeService:StoreServiceService,private locationService:LocationService) { 
    this.latitude =new FormControl('',[Validators.required]);
    this.longitude=new FormControl('',[Validators.required]);
    this.location= this.locationService.locationValue;

    this.form =new FormGroup({
      latitude:this.latitude ,
      longitude: this.longitude
    });

  }

  getLatitude(){return this.form.controls.latitude.value;}
  getLongitude(){return this.form.controls.longitude.value;}

  ngOnInit(): void {

    this.location= this.locationService.locationValue;
    if(this.accountService.userValue.userName==null){

      this.router.navigate(["/login"]);
    }
    else{
      if(this.location.latitude==null){
        this.location=this.locationService.getDefaultLocation();
      }
      this.getStores(this.location.latitude,this.location.longitude);
    }

  }

  onSubmit(){
    this.serviceError=false;
    this.locationService.locationValue={latitude:this.getLatitude(),longitude:this.getLongitude()};
    location.reload();
  }


  getStores(lat:number,lng:number){
    this.storeService.getNearbyStores(lat,lng).subscribe((result:StoreDistance[])=>{
      this.storeDistances=result;
    },
    err =>{
      // 404 means that the backend app could handle the request, and a message with the description error was sended
      if(err.status==404){
        console.log(`Error occur while calling nearbay stores, errorCode= ${err.error.errorCode} errorMessage= ${err.error.errorMessage}`);
      }
      if(err.status==403){
        console.log("not valid credentials");
        this.accountService.logout();
      }
      else{
        console.log(`Error occur while calling nearbay stores unkown error ${err}`);
      }
      this.serviceError=true;

    });
  }


  displayForm(){
    this.showForm=true;

  }

}
