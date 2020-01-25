import {IUser} from './user';

export interface IAccount {
  id?: number;
  name?: string;
  active?: boolean;
  users?: IUser[];
  categoryId?: number;
  budgetId?: number;
  institutionAccountId?: number;
}

export class Account implements IAccount {
  constructor(
    public id?: number,
    public name?: string,
    public active?: boolean,
    public users?: IUser[],
    public categoryId?: number,
    public budgetId?: number,
    public institutionAccountId?: number
  ) {
    this.active = this.active || false;
  }
}
