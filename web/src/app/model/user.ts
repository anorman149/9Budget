import {ICredential} from './credential';
import {v4 as uuid} from 'uuid';
import { Moment } from 'moment';

export interface IUser {
  id?: any;
  firstName?: string;
  lastName?: string;
  email?: string;
  phone?: string; //panit
  activated?: boolean;
  createdBy?: string;
  createdDate?: Date;
  lastModifiedBy?: string;
  lastModifiedDate?: Date;
  credential?: ICredential;
  accountId?: uuid;
  resetKey?: string;
  activationKey?: string;
  locked?: boolean;
  lastFailedLogin?: Moment;
  lockedOutUntil?: Moment;
  failedLoginAttempts?: number;
}

export class User implements IUser {
  constructor(
    public id?: any,
    public firstName?: string,
    public lastName?: string,
    public email?: string,
    public  phone?: string,
    public activated?: boolean,
    public createdBy?: string,
    public createdDate?: Date,
    public lastModifiedBy?: string,
    public lastModifiedDate?: Date,
    public credential?: ICredential,
    public accountId?: uuid,
    public resetKey?: string,
    public activationKey?: string,
    public locked?: boolean,
    public lastFailedLogin?: Moment,
    public lockedOutUntil?: Moment,
    public failedLoginAttempts?: number
  ) {}
}
