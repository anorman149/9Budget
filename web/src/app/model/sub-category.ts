export interface ISubCategory {
  id?: number;
  name?: string;
}

export class SubCategory implements ISubCategory {
  constructor(public id?: number, public name?: string) {}
}
