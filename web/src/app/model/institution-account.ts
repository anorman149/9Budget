import {InstitutionType} from './institution-type.model';
import {ITransaction} from './transaction';

export interface IInstitutionAccount {
  id?: number;
  name?: string;
  type?: InstitutionType;
  balance?: number;
  active?: boolean;
  accountId?: number;
  credentialId?: number;
  institutionId?: number;
  transactions?: ITransaction[];
}

export class InstitutionAccount implements IInstitutionAccount {
  constructor(
    public id?: number,
    public name?: string,
    public type?: InstitutionType,
    public balance?: number,
    public active?: boolean,
    public accountId?: number,
    public credentialId?: number,
    public institutionId?: number,
    public transactions?: ITransaction[]
  ) {
    this.active = this.active || false;
  }
}
