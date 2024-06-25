# Voll.med - API

## About the project

Voll.med is a fictional medical clinic that needs an appointment management application. The application should have functionalities for registering doctors and patients, as well as scheduling and canceling appointments.

---

## Features

- [x] CRUD operations for doctors;
- [x] CRUD operations for patients;
- [x] Appointment scheduling;
- [x] Appointment cancellation.

### How to Use This Project

1. Clone the Repository:
  ```
git clone https://github.com/AfranioCaires/vollmed-api.git
  ```

2. Set Up Database Configuration:
- Ensure you have MySQL installed and running.
- Set the following environment variables in your development environment or in an `.env` file:
  ```
  spring.datasource.url=jdbc:mysql://${DB_HOST}:${DB_PORT}/${DB_NAME}
  spring.datasource.username=${DB_USER}
  spring.datasource.password=${DB_PASSWORD}
  ```
  Replace `${DB_HOST}`, `${DB_PORT}`, `${DB_NAME}`, `${DB_USER}`, and `${DB_PASSWORD}` with your actual database connection details.

3. Build the Project:
  ```
mvn clean install
  ```
4. Run the Application:
Run the Spring Boot application using Maven: 
```
mvn spring-boot:run
  ```


## Technologies

The following technologies were used in developing the REST API for the project:

- **[Java 17](https://www.oracle.com/java)**
- **[Spring Boot 3](https://spring.io/projects/spring-boot)**
- **[Maven](https://maven.apache.org)**
- **[MySQL](https://www.mysql.com)**
- **[Hibernate](https://hibernate.org)**
- **[Flyway](https://flywaydb.org)**
- **[Lombok](https://projectlombok.org)**

---


# API Documentation
## Table of Contents

1. [Overview](#overview)
2. [Servers](#servers)
3. [Paths](#paths)
    - [/pacientes](#pacientes)
        - [GET /pacientes](#get-pacientes)
        - [PUT /pacientes](#put-pacientes)
        - [POST /pacientes](#post-pacientes)
    - [/medicos](#medicos)
        - [GET /medicos](#get-medicos)
        - [PUT /medicos](#put-medicos)
        - [POST /medicos](#post-medicos)
    - [/consultas](#consultas)
        - [POST /consultas](#post-consultas)
        - [DELETE /consultas](#delete-consultas)
    - [/auth](#auth)
        - [POST /auth](#post-auth)
    - [/pacientes/{id}](#pacientes-id)
        - [GET /pacientes/{id}](#get-pacientes-id)
        - [DELETE /pacientes/{id}](#delete-pacientes-id)
    - [/medicos/{id}](#medicos-id)
        - [GET /medicos/{id}](#get-medicos-id)
        - [DELETE /medicos/{id}](#delete-medicos-id)


## Servers
| URL                   | Description          |
|-----------------------|----------------------|
| http://localhost:8080 | Generated server URL |

## Paths

### /pacientes

#### GET /pacientes
- **Tags:** patient-controller
- **Operation ID:** patientListData
- **Parameters:**

  | Name      | In    | Required | Schema                    |
    |-----------|-------|----------|---------------------------|
  | pageable  | query | true     | [Pageable](#pageable)     |

**Responses:**

| Code | Description | Content        |
  |------|-------------|----------------|
| 200  | OK          | [PagePatientListData](#pagepatientlistdata) |

#### PUT /pacientes
- **Tags:** patient-controller
- **Operation ID:** updatePatientData
- **Request Body:**

  | Content Type   | Schema                          |
    |----------------|---------------------------------|
  | application/json | [PatientUpdateData](#patientupdatedata) |

**Responses:**

| Code | Description | Content     |
  |------|-------------|-------------|
| 200  | OK          | string      |

#### POST /pacientes
- **Tags:** patient-controller
- **Operation ID:** registerPatient
- **Request Body:**

  | Content Type   | Schema                           |
    |----------------|----------------------------------|
  | application/json | [PatientRegisterData](#patientregisterdata) |

**Responses:**

| Code | Description | Content     |
  |------|-------------|-------------|
| 200  | OK          | string      |

### /medicos

#### GET /medicos
- **Tags:** physician-controller
- **Operation ID:** physicianList
- **Parameters:**

  | Name      | In    | Required | Schema                    |
    |-----------|-------|----------|---------------------------|
  | pageable  | query | true     | [Pageable](#pageable)     |

**Responses:**

| Code | Description | Content        |
  |------|-------------|----------------|
| 200  | OK          | [PagePhysicianListData](#pagephysicianlistdata) |

#### PUT /medicos
- **Tags:** physician-controller
- **Operation ID:** updatePhysicianData
- **Request Body:**

  | Content Type   | Schema                            |
    |----------------|-----------------------------------|
  | application/json | [PhysicianUpdateData](#physicianupdatedata) |

**Responses:**

| Code | Description | Content     |
  |------|-------------|-------------|
| 200  | OK          | string      |

#### POST /medicos
- **Tags:** physician-controller
- **Operation ID:** register
- **Request Body:**

  | Content Type   | Schema                              |
    |----------------|-------------------------------------|
  | application/json | [PhysicianRegisterData](#physicianregisterdata) |

**Responses:**

| Code | Description | Content     |
  |------|-------------|-------------|
| 200  | OK          | string      |

### /consultas

#### POST /consultas
- **Tags:** appointment-controller
- **Operation ID:** createAppointment
- **Request Body:**

  | Content Type   | Schema                          |
    |----------------|---------------------------------|
  | application/json | [AppointmentData](#appointmentdata) |

**Responses:**

| Code | Description | Content     |
  |------|-------------|-------------|
| 200  | OK          | object      |

#### DELETE /consultas
- **Tags:** appointment-controller
- **Operation ID:** cancel
- **Request Body:**

  | Content Type   | Schema                             |
    |----------------|------------------------------------|
  | application/json | [AppointmentCancelData](#appointmentcanceldata) |

**Responses:**

| Code | Description | Content     |
  |------|-------------|-------------|
| 200  | OK          | string      |

### /auth

#### POST /auth
- **Tags:** auth-controller
- **Operation ID:** signIn
- **Request Body:**

  | Content Type   | Schema                        |
    |----------------|-------------------------------|
  | application/json | [UserAuthData](#userauthdata) |

**Responses:**

| Code | Description | Content     |
  |------|-------------|-------------|
| 200  | OK          | string      |

### /pacientes/{id}

#### GET /pacientes/{id}
- **Tags:** patient-controller
- **Operation ID:** getPatient
- **Parameters:**

  | Name | In   | Required | Schema          |
    |------|------|----------|-----------------|
  | id   | path | true     | integer (int64) |

**Responses:**

| Code | Description | Content     |
  |------|-------------|-------------|
| 200  | OK          | string      |

#### DELETE /pacientes/{id}
- **Tags:** patient-controller
- **Operation ID:** deletePatient
- **Parameters:**

  | Name | In   | Required | Schema          |
    |------|------|----------|-----------------|
  | id   | path | true     | integer (int64) |

**Responses:**

| Code | Description | Content     |
  |------|-------------|-------------|
| 200  | OK          | string      |

### /medicos/{id}

#### GET /medicos/{id}
- **Tags:** physician-controller
- **Operation ID:** getPhysician
- **Parameters:**

  | Name | In   | Required | Schema          |
    |------|------|----------|-----------------|
  | id   | path | true     | integer (int64) |

**Responses:**

| Code | Description | Content     |
  |------|-------------|-------------|
| 200  | OK          | string      |

#### DELETE /medicos/{id}
- **Tags:** physician-controller
- **Operation ID:** deletePhysician
- **Parameters:**

  | Name | In   | Required | Schema          |
    |------|------|----------|-----------------|
  | id   | path | true     | integer (int64) |

**Responses:**

| Code | Description | Content     |
  |------|-------------|-------------|
| 200  | OK          | string      |

## Components

### Schemas

#### AddressData
| Property   | Type   | Description |
|------------|--------|-------------|
| street     | string |             |
| neighbour  | string |             |
| zipcode    | string |             |
| city       | string |             |
| state      | string |             |
| complement | string |             |
| number     | string |             |

#### PatientUpdateData
- **Required:** id

  | Property     | Type                         | Description |
    |--------------|------------------------------|-------------|
  | id           | integer (int64)              |             |
  | name         | string                       |             |
  | phoneNumber  | string                       |             |
  | address      | [AddressData](#addressdata)  |             |

#### PhysicianUpdateData
- **Required:** id

  | Property     | Type                         | Description |
    |--------------|------------------------------|-------------|
  | id           | integer (int64)              |             |
  | name         | string                       |             |
  | phoneNumber  | string                       |             |
  | address      | [AddressData](#addressdata)  |             |

#### PatientRegisterData
- **Required:** address, cpf, email, name, phoneNumber

  | Property     | Type                         | Description                                    |
    |--------------|------------------------------|------------------------------------------------|
  | name         | string                       |                                                |
  | email        | string                       |                                                |
  | phoneNumber  | string                       |                                                |
  | cpf          | string                       | pattern: (?!(\\d)\\1{2}.\\1{3}.\\1{3}-\\1{2})\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2} |
  | address      | [AddressData](#addressdata)  |                                                |

#### PhysicianRegisterData
- **Required:** address, crm, email, name, phoneNumber, specialty

  | Property     | Type                         | Description                                    |
    |--------------|------------------------------|------------------------------------------------|
  | name         | string                       |                                                |
  | email        | string                       |                                                |
  | phoneNumber  | string                       |                                                |
  | crm          | string                       | pattern: \\d{4,6}                               |
  | specialty    | string                       | enum: ORTOPEDIA, CARDIOLOGIA, GINECOLOGIA, DERMATOLOGIA |
  | address      | [AddressData](#addressdata)  |                                                |

#### AppointmentData
- **Required:** date, idPatient

  | Property     | Type                         | Description                                    |
    |--------------|------------------------------|------------------------------------------------|
  | idPhysician  | integer (int64)              |                                                |
  | idPatient    | integer (int64)              |                                                |
  | date         | string (date-time)           |                                                |
  | specialty    | string                       | enum: ORTOPEDIA, CARDIOLOGIA, GINECOLOGIA, DERMATOLOGIA |

#### UserAuthData
| Property     | Type   | Description |
|--------------|--------|-------------|
| login        | string |             |
| password     | string |             |

#### Pageable
| Property     | Type      | Description |
|--------------|-----------|-------------|
| sort         | object    |             |
| offset       | integer (int64) |             |
| pageNumber   | integer (int32) |             |
| pageSize     | integer (int32) |             |
| paged        | boolean   |             |
| unpaged      | boolean   |             |

#### PagePatientListData
| Property     | Type                         | Description |
|--------------|------------------------------|-------------|
| content      | array of [PatientListData](#patientlistdata) |  |
| pageable     | [Pageable](#pageable)        |             |
| totalElements| integer (int64)              |             |
| totalPages   | integer (int32)              |             |
| size         | integer (int32)              |             |
| number       | integer (int32)              |             |
| sort         | object                       |             |
| first        | boolean                      |             |
| last         | boolean                      |             |
| numberOfElements | integer (int32)           |             |
| empty        | boolean                      |             |

#### PagePhysicianListData
| Property     | Type                         | Description |
|--------------|------------------------------|-------------|
| content      | array of [PhysicianListData](#physicianlistdata) |  |
| pageable     | [Pageable](#pageable)        |             |
| totalElements| integer (int64)              |             |
| totalPages   | integer (int32)              |             |
| size         | integer (int32)              |             |
| number       | integer (int32)              |             |
| sort         | object                       |             |
| first        | boolean                      |             |
| last         | boolean                      |             |
| numberOfElements | integer (int32)           |             |
| empty        | boolean                      |             |

#### PatientListData
| Property     | Type     | Description |
|--------------|----------|-------------|
| id           | integer (int64) |      |
| name         | string   |             |
| email        | string   |             |
| phoneNumber  | string   |             |
| cpf          | string   |             |

#### PhysicianListData
| Property     | Type     | Description |
|--------------|----------|-------------|
| id           | integer (int64) |      |
| name         | string   |             |
| email        | string   |             |
| phoneNumber  | string   |             |
| crm          | string   |             |
| specialty    | string   |             |

#### AppointmentCancelData
**Required:** idAppointment, reason

| Property      | Type            | Description |
  |---------------|-----------------|-------------|
| idAppointment | integer (int64) |             |
| reason        | string          |             |
