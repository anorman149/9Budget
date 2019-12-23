import { Component, OnInit } from '@angular/core';
import { SwaggerUIBundle } from "swagger-ui-dist"
import {api} from "../../../environments/environment";

@Component({
  selector: 'app-swagger',
  templateUrl: './swagger.component.html',
  styleUrls: ['./swagger.component.scss']
})
export class SwaggerComponent implements OnInit {

  constructor() { }

  ngOnInit() {
    const ui = SwaggerUIBundle({
      dom_id: '#swagger-ui',
      layout: 'BaseLayout',
      defaultModelsExpandDepth: -1,
      presets: [
        SwaggerUIBundle.presets.apis,
        SwaggerUIBundle.SwaggerUIStandalonePreset
      ],
      url: api.swagger,
      docExpansion: 'none',
      operationsSorter: 'alpha',
      supportedSubmitMethods: [],
    });
  }
}
