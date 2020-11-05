import { Component, OnInit, Inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';

import { Products } from '../../interface/product';
import { Categories } from '../../interface/category';
import { RestService } from '../../../../service/rest-service';

@Component({
  selector: 'app-product-edit',
  templateUrl: './product-edit.component.html',
  styleUrls: ['./product-edit.component.css']
})
export class ProductEditComponent implements OnInit {
  product: Products = new Products();
  categories: Categories[];
  id: number;
  submitted = false;

  constructor(private service: RestService, private router: Router, private route: ActivatedRoute) {
    this.route.params.subscribe(params => {
      this.productConstructor(params['id']);
    });
  }

  ngOnInit() {
    this.getCategories();
  }

  productConstructor(id: string) {
    this.service.get(`products/${id}`).subscribe(result => {
      this.product = result;
    });
  }

  getCategories() {
    this.service.get("categories").subscribe(result => {
      this.categories = result;
    });
  }

  save() {
    this.service
      .put(`products/${this.product.id}`, this.product).subscribe(data => {
        console.log(data)
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
