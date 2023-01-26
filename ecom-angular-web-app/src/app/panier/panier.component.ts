import { Component, OnInit } from '@angular/core';
import {PanierService} from "../service/panier.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-panier',
  templateUrl: './panier.component.html',
  styleUrls: ['./panier.component.css']
})
export class PanierComponent implements OnInit {

  public products : any = [];
  public grandTotal : number = 0;
  constructor(private panierService:PanierService,
              private router:Router,) { }

  ngOnInit(): void {
    this.panierService.getProducts().subscribe(
      res => {
        this.products = res;
        this.grandTotal = this.panierService.getTotalPrice();
      }
    );
  }

  removeItem(item: any){
    this.panierService.removeCartItem(item);
  }
  emptycart(){
    this.panierService.removeAllCart();
  }

  //Button pour passer à la commande
  onCheckout() {
    if (this.panierService.cartItemList != 0){
      this.router.navigateByUrl("/checkout");
    }
  }
}
