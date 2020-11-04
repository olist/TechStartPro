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

  id: number;
  product: Products;
  submitted = false;

  constructor(private service: RestService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit() {
    this.product = new Products();

    this.id = this.route.snapshot.params['id'];

    this.service.get('products/get')
      .subscribe(data => {
        console.log(data)
        this.product = data;
      }, error => console.log(error));
  }

  save() {
    this.service
      .put('products', this.service).subscribe(data => {
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
