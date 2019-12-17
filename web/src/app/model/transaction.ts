import {Category} from "./category";
import {Institution} from "./institution";

export class Transaction {
  id: number;
  date: Date = new Date();
  description: string;
  category: Category;
  amount: bigint;
  institution: Institution;
}
