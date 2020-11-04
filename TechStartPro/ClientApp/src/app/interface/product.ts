import { ProductCategory } from '../interface/productcategory';
import { Categories } from '../interface/category';

export class Products {
  id: number;
  name: string;
  description: string;
  price: number;
  productcategory: ProductCategory[];
  categories: Categories[]; 
}
