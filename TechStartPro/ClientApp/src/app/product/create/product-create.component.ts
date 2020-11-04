import { Component, OnInit, Inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

import { Products } from '../../interface/product';
import { Categories } from '../../interface/category';
import { RestService } from '../../../../service/rest-service';
import { Observable } from "rxjs";

@Component({
  selector: 'app-product-create',
  templateUrl: './product-create.component.html',
  styleUrls: ['./product-create.component.css']
})
export class ProductCreateComponent implements OnInit {
  product: Products = new Products();
  categories: Categories[];
  submitted = false;

  constructor(private service: RestService, private router: Router) { }

  ngOnInit() {
    this.getCategories();
  }

  getCategories() {
    this.service.get("categories").subscribe(result => {
      console.log(result);
      this.categories = result;
    });
  }

  save() {
    this.service
      .post('products', this.product).subscribe(data => {
        console.log(data);
        this.product = new Products();
        this.gotoList();
      },
        error => console.log(error));
  }

  onSubmit() {
    this.submitted = true;
    this.save();
  }

  gotoList() {
    this.router.navigate(['/product-list']);
  }

}
