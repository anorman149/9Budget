import {CategoryType} from './category-type.model';
import {ISubCategory} from './sub-category';
import {v4 as uuid} from 'uuid';

export interface ICategory {
  id?: uuid;
  type?: CategoryType;
  active?: boolean;
  accountId?: number;
  subCategory?: ISubCategory;
}

export class Category implements ICategory {
  constructor(
    public id?: uuid,
    public type?: CategoryType,
    public active?: boolean,
    public accountId?: number,
    public subCategory?: ISubCategory
  ) {
    this.active = this.active || false;
  }
}
