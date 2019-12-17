import {Credential} from "./credential";
import {Account} from "./account";
import {Transaction} from "./transaction";

export class Institution {
  id: number;
  name: string;
  type: string;
  balance: bigint;
  credential: Credential;
  account: Account;
  active: boolean;
  transactions: Transaction[] = [];
}
