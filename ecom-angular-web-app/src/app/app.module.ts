import {APP_INITIALIZER, NgModule} from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CustomersComponent } from './customers/customers.component';
import {HttpClientModule} from "@angular/common/http";
import {BillDetailsComponent} from "./bill-details/bill-details.component";
import {BillsComponent} from "./bills/bills.component";
import {ProductsComponent} from "./products/products.component";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { OrdersComponent } from './orders/orders.component';
import { OrderDetailsComponent } from './order-details/order-details.component';
import { ProductComponent } from './catalogue/product.component';
import { ProductDetailsComponent } from './product-details/product-details.component';
import { ConnexionComponent } from './connexion/connexion.component';
import { PanierComponent } from './panier/panier.component';
import {PanierService} from "./service/panier.service";
import { CheckoutComponent } from './checkout/checkout.component';
import {KeycloakAngularModule, KeycloakService} from "keycloak-angular";

export function kcFactory(kcService : KeycloakService){
  return ()=>{
    //Initialisation de keycloak avec les options suivantes
    kcService.init({
        config : {
          realm : "ecom-app-microservice-realm",
          clientId : "ecom-client",
          url : "http://localhost:8080"
        },
        initOptions : {
          //onLoad : "login-required", //=> nécessite l'authentification avant même la navigation
          onLoad : "check-sso", //L'utilisateur peut commencer à naviguer dans la page d'accueil sans authentification
          checkLoginIframe : true
        }
      }
    )
  }
}

@NgModule({
  declarations: [
    AppComponent,
    CustomersComponent,
    BillDetailsComponent,
    BillsComponent,
    ProductsComponent,
    OrdersComponent,
    OrderDetailsComponent,
    ProductComponent,
    ProductDetailsComponent,
    ConnexionComponent,
    PanierComponent,
    CheckoutComponent,

  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        ReactiveFormsModule,
        FormsModule,
        KeycloakAngularModule,
    ],
  providers: [
    {
      provide : APP_INITIALIZER,
      deps : [KeycloakService],
      useFactory : kcFactory,
      multi : true},
    PanierService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
