# Voll - Managing your clinic

Voll is a webapp for managing medical clinics, you can register doctors and patients, schedule appointments and easily manage your clinic.

The back-end is a rest api built in java/spring and the front-end is made with vue.js.

The main functions are done (doctor, patient and appointment crud).

The front-end code needs some refactoring and things like colored notifications, more time options when scheduling a appointment. 

## Tech-Stack

Main technologies used in the project.

<table>
      <thead>
        <tr>
          <th>Front End</th>
          <th>Back End</th>
          <th>Libraries</th>
        </tr>
      </thead>
      <tbody>
            <tr>
              <td>Vue.js</td>
              <td>Spring</td>
              <td>Maska</td>
            </tr>
            <tr>
              <td>Node.js</td>
              <td>Java</td>
              <td></td>
            </tr>
            <tr>
              <td>TypeScript</td>
              <td>MySQL/MariaDB</td>
              <td></td>
            </tr>
      </tbody>
  </table>

## Installation

1. Configure the application.properties file in the api/src/main/resources folder and create a database with the configured name and a user to login. Build and run the api.

2. Go to the folder web-client/voll and run:

```
npm install
npm run serve
```
