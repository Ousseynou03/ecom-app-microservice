import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-order-details',
  templateUrl: './order-details.component.html',
  styleUrls: ['./order-details.component.css']
})
export class OrderDetailsComponent implements OnInit {

  orderService : any;
  orderServiceId! :number;
  constructor(private http:HttpClient, private router : Router, private route: ActivatedRoute) {
    this.orderServiceId=route.snapshot.params['id'];
  }

  ngOnInit(): void {
    this.http.get("http://localhost:8888/ORDER-SERVICE/fullOrder/"+this.orderServiceId)
      .subscribe({
        next : (data)=>{
          this.orderService=data;
        },
        error : (err)=>{}
      });
  }


  handleOrderDetails(id:number) {
    this.router.navigateByUrl('/order-details/'+id)
  }

}
