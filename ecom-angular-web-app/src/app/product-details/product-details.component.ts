import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {

  products! : any;
  productDetailsId!: any;
  constructor(private http:HttpClient, private router : Router, private route: ActivatedRoute) {
    this.productDetailsId=route.snapshot.params['id'];
  }



  ngOnInit(): void {
    this.http.get("http://localhost:8888/INVENTORY-SERVICE/products"+this.productDetailsId)
      .subscribe({
        next : (data)=>{
          this.products=data;
        },
        error : (err)=>{}
      });
  }

}
