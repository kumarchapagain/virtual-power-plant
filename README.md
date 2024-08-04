Power Plant System API documentation
======================
## Framework Tools
#### Spring Boot 3
#### Java Jdk 17
#### h2database

## Table of Contents

[TOC]: #
1. [Battery](#battery)
    - [ENDPOINTS](#endpoints)
    - [CREATE BATTERIES](#create)
    - [BATTERY IN RANGE](#read)

### ENDPOINTS
```
  GET http://localhost:8080
```
```
Response:
Status: 200 (Success)
Body:
{
    "_links": {
        "create-batteries": {
            "href": "http://localhost:8080/api/v1/batteries"
        },
        "batteries-in-range": {
            "href": "http://localhost:8080/api/v1/batteries?from=0&to=0"
        }
    }
}
```

### CREATE BATTERIES
```
  POST http://localhost:8080/api/v1/batteries
  Header: Content-Type application/json
  Body:
 [
  {
    "name": "Duracell",
    "postCode": 5000,
    "wattCapacity": 13000
  },
  {
    "name": "Arron",
    "postCode": 6010,
    "wattCapacity": 5000
  },
  {
    "name": "Exide",
    "postCode": 5040,
    "wattCapacity": 4500
  },
  {
    "name": "Battery",
    "postCode": 7000,
    "wattCapacity": 6000
  }
]
```
```
Response:
Status: 201 (Created)
Body:
{
    "_embedded": {
        "batteryList": [
            {
                "name": "Duracell",
                "postCode": 5000,
                "wattCapacity": 13000.0
            },
            {
                "name": "Arron",
                "postCode": 6010,
                "wattCapacity": 5000.0
            },
            {
                "name": "Exide",
                "postCode": 5040,
                "wattCapacity": 4500.0
            },
            {
                "name": "Battery",
                "postCode": 7000,
                "wattCapacity": 6000.0
            }
        ]
    },
    "_links": {
        "self": {
            "href": "http://localhost:8080/api/v1/batteries"
        },
        "batteries-in-range": {
            "href": "http://localhost:8080/api/v1/batteries?from=0&to=0"
        }
    }
}
```

### BATTERY IN RANGE
```
  GET http://localhost:8080/api/v1/batteries?from=5000&to=6000
```
```
Response:
Status: 200 (Success)
Body:
{
    "batteries": [
        "Duracell",
        "Exide"
    ],
    "totalWattCapacity": 17500.0,
    "averageWattCapacity": 8750.0,
    "_links": {
        "self": {
            "href": "http://localhost:8080/api/v1/batteries?from=5000&to=6000"
        }
    }
}
```