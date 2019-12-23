import {Credential} from "./credential";

export class User {
  id: number;
  firstName: string;
  lastName: string;
  email: string;
  phone: string;
  active: boolean;
  credential: Credential;
  locked: boolean;
}
