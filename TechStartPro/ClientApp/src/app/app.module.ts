import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { NgSelectConfig, NgSelectModule } from '@ng-select/ng-select';

import { AppComponent } from './app.component';
import { NavMenuComponent } from './nav-menu/nav-menu.component';
import { HomeComponent } from './home/home.component';
import { ProductCreateComponent } from './product/create/product-create.component';
import { ProductListComponent } from './product/list/product-list.component';
import { CategoryCreateComponent } from './category/create/category-create.component';
import { ProductEditComponent } from './product/edit/product-edit.component';

@NgModule({
  declarations: [
    AppComponent,
    NavMenuComponent,
    HomeComponent,
    ProductCreateComponent,
    ProductListComponent,
    [AppComponent],
    CategoryCreateComponent,
    ProductEditComponent
  ],
  imports: [
    BrowserModule.withServerTransition({ appId: 'ng-cli-universal' }),
    HttpClientModule,
    FormsModule,
    NgSelectModule,
    RouterModule.forRoot([
      { path: '', component: HomeComponent, pathMatch: 'full' },
      { path: 'product-create', component: ProductCreateComponent },
      { path: 'category-create', component: CategoryCreateComponent },
      { path: 'product-edit/:id', component: ProductEditComponent },
      { path: 'product-list', component: ProductListComponent }
    ])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {

  constructor(private config: NgSelectConfig) {
    this.config.notFoundText = 'Nenhum resultado encontrado';
  }

}
