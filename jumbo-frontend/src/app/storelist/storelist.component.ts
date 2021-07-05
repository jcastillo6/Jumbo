import { Component, Input, OnInit } from '@angular/core';
import { StoreDistance } from '../model/storeDistance';

@Component({
  selector: 'app-storelist',
  templateUrl: './storelist.component.html',
  styleUrls: ['./storelist.component.css']
})
export class StorelistComponent implements OnInit {
  @Input() storeDistances:StoreDistance[]=[];
  constructor() { }

  ngOnInit(): void {
  }

}
