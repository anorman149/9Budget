import {CategoryType} from "./category-type.model";
import {ISubCategory} from "./sub-category";

export interface ICategory {
  id?: number;
  type?: CategoryType;
  active?: boolean;
  accountId?: number;
  subCategory?: ISubCategory;
}

export class Category implements ICategory {
  constructor(
    public id?: number,
    public type?: CategoryType,
    public active?: boolean,
    public accountId?: number,
    public subCategory?: ISubCategory
  ) {
    this.active = this.active || false;
  }
}
