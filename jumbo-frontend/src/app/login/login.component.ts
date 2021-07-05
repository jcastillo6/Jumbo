import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { Emitters } from '../emitters/emitters';

export interface TokenResponse{
  jwt: string
}

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  invalidLogin:boolean=false;
  form: FormGroup;
  userName:FormControl;
  password:FormControl;
  

  constructor(private http:HttpClient,private router:Router) { 
    this.userName =new FormControl('',Validators.required);
    this.password=new FormControl('',Validators.required);

    this.form =new FormGroup({
      userName:this.userName ,
      password: this.password
    });


  }

  ngOnInit(): void {
  }

  getUsername(){return this.form.get('userName');}
  getPassword(){return this.form.get('password');}

   onSubmit() {
    let password=this.getPassword()?.value;
    let username=this.getUsername()?.value;
    localStorage.clear();
    console.log(this.form.getRawValue());
    this.http.post<TokenResponse>(environment.backend+"/login",this.form.getRawValue()).subscribe((resp:TokenResponse)=>{
      if(resp){
       
        localStorage.setItem('token',resp.jwt);
        Emitters.authEmitter.emit(true);
        this.router.navigate(['/']);
        console.log("Login success");
        

      }
      },
      err =>
      {
        Emitters.authEmitter.emit(false);
        this.invalidLogin=true;
        this.userName.reset();
        this.password.reset();

      }


    );   
    
  }
  
}
