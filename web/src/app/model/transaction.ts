import { Moment } from 'moment';
import {ICategory} from "./category";

export interface ITransaction {
  id?: number;
  amount?: number;
  date?: Moment;
  description?: string;
  category?: ICategory;
  budgetId?: number;
  institutionId?: number;
}

export class Transaction implements ITransaction {
  constructor(
    public id?: number,
    public amount?: number,
    public date?: Moment,
    public description?: string,
    public category?: ICategory,
    public budgetId?: number,
    public institutionId?: number
  ) {}
}
