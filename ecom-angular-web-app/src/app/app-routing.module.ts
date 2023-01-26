import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {CustomersComponent} from "./customers/customers.component";
import {BillsComponent} from "./bills/bills.component";
import {BillDetailsComponent} from "./bill-details/bill-details.component";
import {ProductsComponent} from "./products/products.component";
import {OrdersComponent} from "./orders/orders.component";
import {OrderDetailsComponent} from "./order-details/order-details.component";
import {ProductComponent} from "./catalogue/product.component";
import {ConnexionComponent} from "./connexion/connexion.component";
import {ProductDetailsComponent} from "./product-details/product-details.component";
import {PanierComponent} from "./panier/panier.component";
import {CheckoutComponent} from "./checkout/checkout.component";


const routes: Routes = [

  {
    path : "", component : ProductComponent,
  },
  {
    path : "panier", component : PanierComponent,
  },
  {
    path : "prod-details/:id", component : ProductDetailsComponent,
  },
  {
    path : "connexion", component : ConnexionComponent,
  },
  {
    path : "catalogue", component : ProductComponent,
  },
  {
    path : "customers", component : CustomersComponent
  },
  {
    path : "bills/:customerId", component : BillsComponent
  },
  {
    path : "bill-details/:id", component : BillDetailsComponent
  },
  {
    path : "products", component : ProductsComponent
  },
  {
    path : "orders/:customerId", component : OrdersComponent
  },
  {
    path : "order-details/:id", component : OrderDetailsComponent
  },
  {
    path : "product", component : ProductComponent
  },
  {
    path : "checkout", component :CheckoutComponent,
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
