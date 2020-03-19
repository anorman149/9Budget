export interface ICredential {
  id?: number;
  username?: string;
  password?: string;
  custom?: string;
}

export class Credential implements ICredential {
  constructor(
    public id?: number,
    public username?: string,
    public password?: string,
    public custom?: string
  ) {}
}
