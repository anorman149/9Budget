// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
const budgetAPI = {
  url: "/budgets"
};

const accountAPI = {
  url: "/accounts"
};

const homeAPI = {
  url: "/home"
};

const authAPI = {
  urlAuth: "/oauth/token",
  urlLogin: "/login"
};

export const authentication = {
  idleTimeout: 90, //3 Minutes
  idleTime: 90, //3 Minutes
};

export const api = {
  url: "/9budget/api/v1",
  budget: budgetAPI,
  account: accountAPI,
  auth: authAPI,
  home: homeAPI
};

export const storage = {
  name: "authUser",
};
