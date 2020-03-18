import { Moment } from 'moment';
import {ICategory} from './category';
import {IInstitutionAccount} from "./institution-account";
import {IBudget} from "./budget";
import {IAccount} from "./account";

export interface ITransaction {
  id?: number;
  amount?: number;
  date?: Moment;
  description?: string;
  category?: ICategory;
  budget?: IBudget;
  institutionAccount?: IInstitutionAccount;
  account?: IAccount;
}

export class Transaction implements ITransaction {
  constructor(
    public id?: number,
    public amount?: number,
    public date?: Moment,
    public description?: string,
    public category?: ICategory,
    public budget?: IBudget,
    public institutionAccount?: IInstitutionAccount,
    public account?: IAccount
  ) {}
}
