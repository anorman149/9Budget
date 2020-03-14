import {ICredential} from './credential';
import {v4 as uuid} from 'uuid';

export interface IUser {
  id?: any;
  firstName?: string;
  lastName?: string;
  email?: string;
  activated?: boolean;
  createdBy?: string;
  createdDate?: Date;
  lastModifiedBy?: string;
  lastModifiedDate?: Date;
  credential?: ICredential;
  accountId?: uuid;
  resetKey?: string;
  activationKey?: string;
}

export class User implements IUser {
  constructor(
    public id?: any,
    public firstName?: string,
    public lastName?: string,
    public email?: string,
    public activated?: boolean,
    public createdBy?: string,
    public createdDate?: Date,
    public lastModifiedBy?: string,
    public lastModifiedDate?: Date,
    public credential?: ICredential,
    public accountId?: uuid,
    public resetKey?: string,
    public activationKey?: string
  ) {}
}
