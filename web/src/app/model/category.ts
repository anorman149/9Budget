import {SubCategory} from "./sub-category";

export class Category {
  id: number;
  accountID: number;
  categoryType: string;
  subCategory: SubCategory;
  name: string;
  active: boolean;
}
