import {User} from "./user";

export class Account {
  id: number;
  primaryUser: User;
  users: User[] = [];
  active: boolean;
}
