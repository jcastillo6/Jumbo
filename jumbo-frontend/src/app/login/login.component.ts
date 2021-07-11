
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { first } from 'rxjs/operators';
import { AccountServiceService } from '../services/account-service.service';
import { LocationService } from '../services/location.service';

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
  

  constructor(private router:Router,private accountService:AccountServiceService,private locationService:LocationService) { 
    this.userName =new FormControl('',Validators.required);
    this.password=new FormControl('',Validators.required);

    this.form =new FormGroup({
      userName:this.userName ,
      password: this.password
    });


  }

  ngOnInit(): void {
  }

  private getUsername(): string {return this.form.controls.userName.value}
  private getPassword(): string {return this.form.controls.password.value}

  public onSubmit() {
    
    this.accountService.login(this.getUsername(),this.getPassword()).pipe(first()).subscribe({
        next: () => {
            this.router.navigate(['/']);
            
        },
        error: error => {
          console.log("Login error");
          this.invalidLogin=true;
          this.userName.reset();
          this.password.reset();
        }
    });
    
  }
  
}
