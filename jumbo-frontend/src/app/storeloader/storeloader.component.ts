import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AccountServiceService } from '../services/account-service.service';
import { StoreServiceService } from '../services/store-service.service';
import { jsonValidator } from './jsonValidator';

@Component({
  selector: 'app-storeloader',
  templateUrl: './storeloader.component.html',
  styleUrls: ['./storeloader.component.css']
})
export class StoreloaderComponent implements OnInit {

  private invalidLogin:boolean=false;
  form: FormGroup;
  objectText:FormControl;
  success=false;
  requestMade=false;

  

  constructor(private http:HttpClient,private router:Router,private accountService:AccountServiceService,private storeService:StoreServiceService) { 
    this.objectText =new FormControl('',[Validators.required,Validators.maxLength(10000),jsonValidator]);
    

    this.form =new FormGroup({
      objectText:this.objectText      
    });


  }

  ngOnInit(): void {
    if(this.accountService.userValue.userName==null){

      this.router.navigate(["/login"]);
    }
  }

  private getObjectText(): string {return this.form.controls.objectText.value}
 

  public onSubmit() {
    let stores=JSON.parse(this.getObjectText());
    this.storeService.createAll(stores).subscribe(res =>{
      this.success=true;
      this.requestMade=true;
      this.objectText.reset();
    },
    err =>{
      this.requestMade=true;
      this.success=false;
    }
    );
    
  }

}
