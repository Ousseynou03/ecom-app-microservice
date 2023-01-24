import { Injectable } from '@angular/core';
import {BehaviorSubject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class PanierService {

  public cartItemList : any = [];
  public productList = new BehaviorSubject<any>([]);

  constructor() { }
  //getter
  getProducts(){
    return this.productList.asObservable();
  }

  //setter
  setProduct(product : any){
    this.cartItemList.push(...product);
    this.productList.next(product);
  }

  //Ajout à la carte
  addToCart(product : any){
    this.cartItemList.push(product);
    this.productList.next(this.cartItemList);
    this.getTotalPrice();
    //console.log(this.cartItemList);

  }

  //Le total dans le panier
  getTotalPrice() : number{
    let grandTotal = 0;
    this.cartItemList.map((a : any)=> {
      grandTotal += a.total;
    });
    return grandTotal;
  }

  //Suppression d'une carte
  removeCartItem(product : any){
    this.cartItemList.map((a : any, index : any)=> {
      if (product.id === a.id){
        this.cartItemList.splice(index, 1)
      }
    });
  }

  //Suppression de tout le panier
  removeAllCart(){
    this.cartItemList = [];
    this.productList.next(this.cartItemList);
  }

}
