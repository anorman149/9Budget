import {ICategory} from './category';
import {IInstitutionAccount} from "./institution-account";
import {IBudget} from "./budget";
import {IAccount} from "./account";

export interface ITransaction {
  id?: number;
  amount?: number;
  date?: Date;
  description?: string;
  category?: ICategory;
  budgetID?: number;
  budgetName?: string;
  institutionAccount?: IInstitutionAccount;
  account?: number;
}

export class Transaction implements ITransaction {
  constructor(
    public id?: number,
    public amount?: number,
    public date?: Date,
    public description?: string,
    public category?: ICategory,
    public budgetID?: number,
    public budgetName?: string,
    public institutionAccount?: IInstitutionAccount,
    public account?: number
  ) {}
}
