import {Component, HostListener, OnInit} from '@angular/core';
import {PanierService} from "./service/panier.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'ecom-angular-web-app';

  public totalItem : number = 0;
  navbg:any;
  @HostListener('document:scroll') scrollover(){
    console.log(document.body.scrollTop,'scrolllength#');

    if(document.body.scrollTop > 0 || document.documentElement.scrollTop > 0)
    {
      this.navbg = {
        'background-color':'#000000'
      }
    }else
    {
      this.navbg = {}
    }
  }

  constructor(private panierService:PanierService) {}

  ngOnInit(): void {
    this.panierService.getProducts().subscribe(
      res => {
        this.totalItem == res.length;
      }
    );
  }

}
