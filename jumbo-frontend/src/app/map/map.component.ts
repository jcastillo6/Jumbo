import { ControlPosition } from '@agm/core';
import { Component, Input, OnInit } from '@angular/core';
import { Location } from '../model/location';
import { StoreDistance } from '../model/storeDistance';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements OnInit {

  @Input() storeDistances:StoreDistance[]=[];
  @Input() location:Location={latitude:52.132633,longitude:5.291266};
  iconUrl="https://developers.google.com/maps/documentation/javascript/examples/full/images/beachflag.png";
  mapType="roadmap";
  zoom=6;
  constructor() {
    console.log("MapComponent "+location);
        
  }

  ngOnInit(): void {
    console.log("MapComponent   ngOnInit"+location);
  }

}
