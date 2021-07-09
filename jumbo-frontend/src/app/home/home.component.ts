import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { StoreDistance } from '../model/storeDistance';
import { AccountServiceService } from '../services/account-service.service';
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

  constructor(private http:HttpClient,private router:Router,private accountService:AccountServiceService,private storeService:StoreServiceService) { 
    this.latitude =new FormControl('',[Validators.required,Validators.pattern(/^[.\d]+$/)]);
    this.longitude=new FormControl('',[Validators.required,Validators.pattern(/^[.\d]+$/)]);
    this.form =new FormGroup({
      latitude:this.latitude ,
      longitude: this.longitude
    });

  }

  getLatitude(){return this.form.controls.latitude.value;}
  getLongitude(){return this.form.controls.longitude.value;}

  ngOnInit(): void {
  
    if(!this.accountService.userValue.userName)
      this.router.navigate(["/login"]);

  }

  onSubmit(){
    this.serviceError=false; 
    this.storeService.getNearbyStores(this.getLatitude(),this.getLongitude()).subscribe((result:StoreDistance[])=>{
      this.storeDistances=result;
    },
    err =>{
      err.error.errorCode
      if(err.error && err.error.errorCode)
      console.log(`Error occur while calling nearbay stores, errorCode= ${err.error.errorCode} errorMessage= ${err.error.errorMessage}`);
      
      this.serviceError=true;

    });
    this.latitude.reset();
    this.longitude.reset();


  }

}
