import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from "rxjs";
import { RestService } from '../../../../service/rest-service';
import { Categories } from '../../interface/category';
import { Products } from '../../interface/product';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

  products: Observable<Products[]>;

  constructor(private service: RestService, private router: Router) { }

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.products = this.service.get('products/getall');
  }

  navigateToProductCreate() {
    this.router.navigate(['/product-create']);
  }

  deleteProduct(id: number) {
    this.service.delete('products/deleteproduct', id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error));
  }

  productEdit(id: number) {
    this.router.navigate(['/product-edit', id]);
  }

}
