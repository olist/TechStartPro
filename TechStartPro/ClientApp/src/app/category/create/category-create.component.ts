import { Component, OnInit } from '@angular/core';
import { Observable } from "rxjs";
import { Router } from '@angular/router';

import { Papa } from 'ngx-papaparse';
import { Categories } from '../../interface/category';
import { RestService } from '../../../../service/rest-service';

@Component({
  selector: 'app-category-create',
  templateUrl: './category-create.component.html',
  styleUrls: ['./category-create.component.css']
})
export class CategoryCreateComponent implements OnInit {

  constructor(private service: RestService,private papa: Papa, private router: Router) { }

  category: Categories = new Categories();
  submitted = false;
  categoryList = [];

  ngOnInit() {
  }

  newCategory(): void {
    this.submitted = false;
    this.category = new Categories();
  }

  save() {
    console.log(this.category)
    this.service.post('categories', this.category).subscribe(result => console.log(result));
  }

  onSubmit() {
    this.submitted = true;
    for (let i = 0; i < this.categoryList.length; i++) {
      this.category.name = this.categoryList[i].name;
      this.category.id = 0;
      this.save();
    }
  }

  handleFileSelect(evt) {
    var files = evt.target.files; 
    var file = files[0];
    var reader = new FileReader();
    reader.readAsText(file);
    reader.onload = (event: any) => {
      var csv = event.target.result;
      this.papa.parse(csv, {
        skipEmptyLines: true,
        header: true,
        complete: (results) => {
          for (let i = 0; i < results.data.length; i++) {
            let orderDetails = {
              id: 0,
              name: results.data[i].Nome
            };
            this.categoryList.push(orderDetails);
          }
          console.log('Parsed: k', results.data);
        }
      });
    }
  }
}
