import { Component, OnInit } from '@angular/core';
import { Emitters } from '../emitters/emitters';
import { AccountServiceService } from '../services/account-service.service';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit {

  authenticated =false;
  constructor(private accountService:AccountServiceService) { }

  ngOnInit(): void {
    Emitters.authEmitter.subscribe((aut: boolean) => {
      this.authenticated = aut;
    } );
    
    if(this.accountService.userValue.userName==null){
      Emitters.authEmitter.next(false);
    }
    else{
      Emitters.authEmitter.next(true);
    }


  }

  logout(): void{
    this.accountService.logout();
  }

}
