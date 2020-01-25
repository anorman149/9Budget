import {ICredential} from './credential';

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
    public credential?: ICredential
  ) {}
}
