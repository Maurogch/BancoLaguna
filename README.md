# Banco Laguna
Project for Metodology Systems II

- Frontend made with Vue.js using Veutify
- Backend using Spring Boot

## Objectives

- Learn to work in an agile way using Scrum Methods.
- Make a project with at least two histories per sprint
- Use languages not currently known to incentivate research and learning

## Funcionality

- Currently the SPA can:
    - Display a list of accounts for a user.
    - Display a list of account movments.
    - Make a transfer to another account.
    - (Partially done) maneage notifications when a new transfer is recieved.    

## Configurations

- Project expects a MYSQL database in "localhost/bancolaguna" (It will auto create tables)
    
    If not using external database comment DB lines in application.properties to fallback to H2

- CORS is configured on port 9000

- Install frontend dependencies with "npm install" (in the frontend folder)

- Serve frontend with "npm run serve -- --port 9000" (CORS is expecting the UI to run in that port)

- Vue uses Router with history and an array of routes in a separated file

- There are three folders with frontends:
     - Currently the main one is "frontend2" with a theme applied.
     - "frontend1" used for testing simple components
     - "frontendLogin" a frontend with login and register structures working with vuex dependencies.
     
- Accounts will need to be manually inserted in the database.
 

