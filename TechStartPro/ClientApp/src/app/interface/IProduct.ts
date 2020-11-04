import { ICategories } from '../interface/ICategory';

export interface IProducts {
  name: string;
  description: string;
  price: number;
  categories: ICategories[];
}
