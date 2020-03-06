import { Injectable } from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {PrimaryService} from '../model/primary-service';
import {api} from '../../environments/environment';
import {Observable} from 'rxjs';
import {createRequestOption} from '../shared/util/request-util';
import {IUser} from '../model/user';

type EntityResponseType = HttpResponse<IUser>;
type EntityArrayResponseType = HttpResponse<IUser[]>;

@Injectable({
  providedIn: 'root'
})
export class UserService implements PrimaryService {

  constructor(private http: HttpClient) {}

  public getAll() {
    return this.http.get<IUser[]>(api.url + api.user.url );
  }

  public get(user: IUser) {
    return this.http.get<IUser>(api.url + api.user.url + '/' + user.id);
  }

  public query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IUser[]>(api.url + api.user.url, { params: options, observe: 'response' });
  }

  public create(user: IUser) {
    return this.http.put<IUser>(api.url + api.user.url, user);
  }

  public update(user: IUser) {
    return this.http.post<IUser>(api.url + api.user.url, user);
  }

}
