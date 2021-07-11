import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { NavComponent } from './nav/nav.component';
import { HomeComponent } from './home/home.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AuthFilter } from './Interceptors/authFilter';
import { StorelistComponent } from './storelist/storelist.component';
import { AgmCoreModule } from '@agm/core';
import { environment } from 'src/environments/environment';
import { MapComponent } from './map/map.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    NavComponent,
    HomeComponent,
    StorelistComponent,
    MapComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    AgmCoreModule.forRoot({
      apiKey: environment.googleKey
    })
    
  ],
  providers: [ { provide: HTTP_INTERCEPTORS, useClass: AuthFilter, multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
