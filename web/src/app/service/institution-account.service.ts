import {Injectable} from '@angular/core';
import {PrimaryService} from "../model/primary-service";
import {HttpClient, HttpResponse} from "@angular/common/http";
import {IInstitution, Institution} from "../model/institution";
import {api} from "../../environments/environment";
import {Observable} from "rxjs";
import {createRequestOption} from "../shared/util/request-util";
import {IInstitutionAccount} from "../model/institution-account";

type EntityResponseType = HttpResponse<IInstitutionAccount>;
type EntityArrayResponseType = HttpResponse<IInstitutionAccount[]>;

@Injectable({
  providedIn: 'root'
})
export class InstitutionAccountService implements PrimaryService {

  constructor(private http: HttpClient) {
  }

  public getAll() {
    return this.http.get<IInstitutionAccount[]>(api.url + api.institutionAccount.url);
  }

  public get(institutionAccount: IInstitutionAccount) {
    return this.http.get<IInstitutionAccount>(api.url + api.institutionAccount.url + '/' + institutionAccount.id);
  }

  public query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IInstitutionAccount[]>(api.url + api.institutionAccount.url, {
      params: options,
      observe: 'response'
    });
  }

  public create(institutionAccount: IInstitutionAccount): any {
    return this.http.post<IInstitutionAccount>(api.url + api.institutionAccount.url, institutionAccount);
  }

  public update(institutionAccount: IInstitutionAccount): any {
    return this.http.put<IInstitutionAccount>(api.url + api.institutionAccount.url, institutionAccount);
  }
}
