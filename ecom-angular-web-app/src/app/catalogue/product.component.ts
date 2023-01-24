import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";
import {PanierService} from "../service/panier.service";
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  products : any;
  searchProductsForm! : FormGroup;
  constructor(private http: HttpClient,
              private fb: FormBuilder,
              private router : Router, private route: ActivatedRoute,
              private panierService:PanierService,
              private toastr: ToastrService) { }

  ngOnInit(): void {
    this.searchProductsForm=this.fb.group({
      keyword : this.fb.control('')
    });

    //Récupération de tous les produits
    this.http.get("http://localhost:8888/INVENTORY-SERVICE/products?projection=p1")
      .subscribe({
        next : (data)=>{
          this.products=data;
        },
        error : (err)=>{}
      });

    this.products.forEach((a : any)=> {
      Object.assign(a, {quantity:1, total : a.price});
    });
  }



  //Recherche de Produit
  searchProducts() {
    let kc=this.searchProductsForm.value['keyword'];
    this.http.get("http://localhost:8888/INVENTORY-SERVICE/products/search/byName?keyword="+kc)
      .subscribe({
        next : value => {
          this.products=value;
        },
        error : err => {
          console.log(err);
        }
      })
  }

  //Ajouter au Panier
  addtocart(prod: any) {
    this.panierService.addToCart(prod);
    this.toastr.success("Votre produit a bien été ajouté au panier!");

  }

  //Détails ¨Produit par identifiant
  handleProductDetails(id : number) {
    this.router.navigateByUrl('/prod-details/'+id)
  }
}
