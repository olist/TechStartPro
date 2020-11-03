import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-produto-list',
  templateUrl: './produto-list.component.html',
  styleUrls: ['./produto-list.component.css']
})
export class ProdutoListComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
  }

  navigateToProductCreate() {
    this.router.navigate(['/produto']);
  }

}
