import { CategoriesInterface } from '../categoria/categoria.model';

export interface ProductsInterface {
  name: string;
  description: string;
  price: number;
  categories: CategoriesInterface[];
}
