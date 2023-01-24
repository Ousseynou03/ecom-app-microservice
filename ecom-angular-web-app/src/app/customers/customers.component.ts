import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css']
})
export class CustomersComponent implements OnInit {
  customers : any;
  searchCustomerForm! : FormGroup;
  constructor(private http: HttpClient,
              private router: Router,
              private fb: FormBuilder) { }



  ngOnInit(): void {

    this.searchCustomerForm=this.fb.group({
      keyword : this.fb.control('')
    });
    //------------------
    this.http.get("http://localhost:8888/CUSTOMER-SERVICE/customers?projection=customerProj1")
      .subscribe({
        next : (data)=>{
          this.customers=data;
        },
        error : (err)=>{}
      });
  }

  //Recherche de Produit
  searchCustomers() {
    let kc=this.searchCustomerForm.value['keyword'];
    this.http.get("http://localhost:8888/CUSTOMER-SERVICE/customers/search/byName?keyword="+kc)
      .subscribe({
        next : value => {
          this.customers=value;
        },
        error : err => {
          console.log(err);
        }
      })
  }

  handleGetBills(id:number) {
    this.router.navigateByUrl("/bills/"+id);
  }
  handleGetOrders(id:number) {
    this.router.navigateByUrl("/orders/"+id);
  }
}
