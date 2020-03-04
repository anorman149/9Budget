import {IUser} from './user';
import {v4 as uuid} from 'uuid';

export interface IAccount {
  id?: uuid;
  name?: string;
  active?: boolean;
  users?: IUser[];
  categoryId?: number;
  budgetId?: number;
  institutionAccountId?: number;
}

export class Account implements IAccount {
  constructor(
    public id?: uuid,
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
