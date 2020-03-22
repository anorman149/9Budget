import { Injectable } from '@angular/core';
import {PrimaryService} from "../model/primary-service";
import {HttpClient, HttpResponse} from "@angular/common/http";
import {Budget, IBudget} from "../model/budget";
import {api} from "../../environments/environment";
import {Observable} from "rxjs";
import {createRequestOption} from "../shared/util/request-util";
import {IInstitution, Institution} from "../model/institution";

type EntityResponseType = HttpResponse<IInstitution>;
type EntityArrayResponseType = HttpResponse<IInstitution[]>;

@Injectable({
  providedIn: 'root'
})
export class InstitutionService implements PrimaryService{

  constructor(private http: HttpClient) {}

  public getAll() {
    return this.http.get<Institution[]>(api.url + api.institution.url );
  }

  public get(institution: Institution) {
    return this.http.get<Institution>(api.url + api.institution.url + '/' + institution.id);
  }

  public query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IInstitution[]>(api.url + api.institution.url, { params: options, observe: 'response' });
  }

  public create(institution: Institution): any {
    return this.http.post<Institution>(api.url + api.institution.url, institution);
  }

  public update(institution: Institution): any {
    return this.http.put<Institution>(api.url + api.institution.url, institution);
  }
}
