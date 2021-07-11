import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Store } from '../model/store';
import { StoreDistance } from '../model/storeDistance';

@Injectable({
  providedIn: 'root'
})
export class StoreServiceService {
  
  constructor(private http:HttpClient) { }


  public getNearbyStores(lat:number,lng:number) : Observable<StoreDistance[]>{
    return this.http.get<StoreDistance[]>(`${environment.backend}/stores/nearbystores?latitude=${lat}&longitude=${lng}`);
  }

  public createAll(stores:Store[]) : Observable<Store[]>{
    return this.http.post<Store[]>(`${environment.backend}/stores/createall`, stores);

  }
  

}
