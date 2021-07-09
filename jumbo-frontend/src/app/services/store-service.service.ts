import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { StoreDistance } from '../model/storeDistance';

@Injectable({
  providedIn: 'root'
})
export class StoreServiceService {

  constructor(private http:HttpClient) { }


  getNearbyStores(lat:number,lng:number){
    return this.http.get<StoreDistance[]>(`${environment.backend}/stores/nearbystores?latitude=${lat}&longitude=${lng}`);
  }  


  
  
}
