import {Component, OnInit} from '@angular/core';
import {Institution} from "../../model/institution";
import {InstitutionService} from "../../service/institution.service";
import {MessageService} from "primeng/api";

;

@Component({
  selector: 'app-institution',
  templateUrl: './institution.component.html',
  styleUrls: ['./institution.component.scss']
})
export class InstitutionComponent implements OnInit {
  institutions?: Institution[];

  constructor(private institutionService: InstitutionService
    , private messageService: MessageService) {
  }

  ngOnInit() {

    this.institutionService.getAll().subscribe(
      data => {
        this.institutions = data ? data : [];
        this.messageService.add({summary: 'Institution Loaded', severity: 'success', sticky: false})
      },
      error => this.messageService.add({summary: 'Failed to Institution: ' + error, severity: 'error', sticky: false})
    );


  }

}
