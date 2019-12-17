import {Category} from "./category";
import {Account} from "./account";
import {Transaction} from "./transaction";

export class Budget {
  id: number;
  name: string;
  category: Category;
  amount: bigint;
  timing: string;
  useLeftOver: boolean;
  account: Account;
  active: boolean;
  transactions: Transaction[] = [];
}
