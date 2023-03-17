# Rover Navigation System

This application simulates rover navigation in a grid.

## Description

* A squad of robotic rovers will be traversing through a rectangular grid. 
* Position of the rover is combination of X and Y coordinates and a letter representing one of the four cardinal compass points (i.e. N, S, E, W). 
  * Ex: 0 0 N - rover is in the bottom left corner and facing North
* Control signals to navigate the rovers are 'L', 'R' and 'M'. 
  * 'L' and 'R' makes the rover spin 90 degrees left or right, respectively, without moving from its current spot.
  * 'M' means move forward one grid point and maintain the same heading.
* Input
  * Upper-right coordinates of the grid, assuming that the lower-left coordinates are 0,0. 
  * Each rover has two lines of input. The first line gives the rover's position, and the second line is a series of instructions telling the rover how to explore the plateau.
* Output
  * The output for each rover will be its final coordinates and heading.
* Each rover will be finished sequentially, which means that the second rover won't start to move until the first one has finished moving.

## Assumptions

* Only one rover will be in a single coordinate, NASA would avoid any conflicts. 
* A rover can't move outside the grid boundaries.
* Each rover will be finished sequentially, no concurrent navigation.

## Code Explanation
### REST API
#### RoverNavigationRestController
All the HTTP requests are handled by this REST Controller in the rover navigation system.
### Processing of Different Commands
#### CommandProcessor
Processor interface for initial processing of different commands.
#### GridSizeProcessor
Command processor for processing grid size commands.
#### NavigationProcessor
Command processor for processing navigation commands.
#### PositionProcessor
Command processor for processing positioning commands.
#### CommandProcessorFactory
Factory with different registered command processors.
#### CommandProcessingService
This is the service for initial processing of commands. This will retrieve a compatible CommandProcessor from the CommandProcessorFactory based on the CommandType.
This will validate the command and process to get split values from the command.
### Positioning and Navigation
#### NavigationService and NavigationServiceImpl
This is the service for handling rover positioning and navigation.
A single grid would be maintained throughout the application (i.e. this can be extended to support multiple grids in future). List of rovers would be maintained with the current position and direction.
This can be used/extended to support any type of grid (square, rectangular, circle etc.).
This service has support for adding a grid, adding rovers, moving rovers, rotating rovers 90 degrees left/right, get a rover's final coordinates and heading.
Validations are added for handling the rover not go beyond the grid boundaries.
#### MovementController
Controller to control the movement of a rover. This has support for moving any number of grid points.
#### RotationController
Controller to control the rotation of rover 90 degrees left and right. We can improve this to add support for rotating more or less than 90 degrees.

### REST API
#### Add Grid
Request

    POST /api/grid HTTP/1.1
    Content-Type: application/json
    Accept: application/json

    {"command" : "5 5"}

Response 

    HTTP/1.1 201 Created
    
#### Add Rover
Request

    POST /api/rover HTTP/1.1
    Content-Type: application/json
    Accept: application/json

    {"command" : "1 2 N"}  

Response

    HTTP/1.1 201 Created

    {
    "roverID": "d92e47a3-730c-423b-ab13-80a62eee8311",
    "position": "1 2 N"
    }

#### Navigate Rover
Request

    PUT /api/rover HTTP/1.1
    Content-Type: application/json
    Accept: application/json

    {"command" : "LMLMLMLMM", "roverId" : "d92e47a3-730c-423b-ab13-80a62eee8311"}

Response

    HTTP/1.1 200 OK

#### Get Rover Position
Request
    
    GET /api/rover/d92e47a3-730c-423b-ab13-80a62eee8311 HTTP/1.1
    Content-Type: application/json
    Accept: application/json

Response

    HTTP/1.1 200 OK

    {
    "roverID": "d92e47a3-730c-423b-ab13-80a62eee8311",
    "position": "1 3 N"
    }

#### Error Responses

    HTTP/1.1 400 BAD_REQUEST

    {"status":"BAD_REQUEST","message":"Invalid number of command values for rover positioning."}
    {"status":"BAD_REQUEST","message":"Invalid values as control signals for rover navigation."}
    {"status":"BAD_REQUEST","message":"Invalid position with the movement in grid."}
    {"status":"BAD_REQUEST","message":"Invalid position in the grid."}
    {"status":"BAD_REQUEST","message":"Invalid number of coordinates for grid size."}

    HTTP/1.1 400 BAD_REQUEST

    {"status":"NOT_FOUND","message":"No existing rover with ID : ABC"}
    {"status":"NOT_FOUND","message":"Rover with ID : ABC not found"}

## Run Application

1) Compile the source code

        mvn compile

2) Run all the tests
        
        mvn clean test

3) Run the application

       mvn spring-boot:run
