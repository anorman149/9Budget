import {ITransaction} from "./transaction";
import {InstitutionType} from "./institution-type.model";

export interface IInstitution {
  id?: number;
  name?: string;
  type?: InstitutionType;
  balance?: number;
  active?: boolean;
  systemAccountId?: number;
  credentialId?: number;
  transactions?: ITransaction[];
}

export class Institution implements IInstitution {
  constructor(
    public id?: number,
    public name?: string,
    public type?: InstitutionType,
    public balance?: number,
    public active?: boolean,
    public systemAccountId?: number,
    public credentialId?: number,
    public transactions?: ITransaction[]
  ) {
    this.active = this.active || false;
  }
}
