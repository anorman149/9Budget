export interface IInstitution {
  id?: number;
  name?: string;
}

export class Institution implements IInstitution {
  constructor(
    public id?: number,
    public name?: string,
  ) {}
}
