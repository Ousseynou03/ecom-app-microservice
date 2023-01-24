import { NgModule } from '@angular/core';
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
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        ReactiveFormsModule,
        FormsModule,
    ],
  providers: [
    PanierService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
