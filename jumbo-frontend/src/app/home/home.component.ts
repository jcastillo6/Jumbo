import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { Emitters } from '../emitters/emitters';
import { StoreDistance } from '../model/storeDistance';


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
  authenticated =false;
  serviceError=false;

  constructor(private http:HttpClient,private router:Router ) { 
    this.latitude =new FormControl('',[Validators.required,Validators.pattern(/^[.\d]+$/)]);
    this.longitude=new FormControl('',[Validators.required,Validators.pattern(/^[.\d]+$/)]);
    this.form =new FormGroup({
      latitude:this.latitude ,
      longitude: this.longitude
    });

  }

  getLatitude(){return this.form.get('latitude');}
  getLongitude(){return this.form.get('longitude');}

  ngOnInit(): void {
    Emitters.authEmitter.subscribe((aut: boolean) => {
      this.authenticated = aut;
    } );
    

  }

  onSubmit(){
    this.serviceError=false; 

    if(localStorage.getItem('token')===null){
      this.router.navigate(['/login']);
      return;
    }

    let lat=this.form.get('latitude')?.value;
    let lgt=this.form.get('longitude')?.value;
    this.http.get<StoreDistance[]>(environment.backend+'/store/closest?latitude='+lat+'&longitude='+lgt).subscribe(
      (res:StoreDistance[])=>{
        this.storeDistances=res;
         
      },
      err =>{
        this.serviceError=true;  
        console.log(err);
      }
    );
    this.latitude.reset();
    this.longitude.reset();


  }

}
