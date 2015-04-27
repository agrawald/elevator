#Elevator Simulator

The maven project is a Spring MVC and REST application with Angular.js as the front-end engine.
To simulate the elevators I have used publisher-subscriber pattern.

You can find ElevatorPub as the publisher of the floor calls and ElevatorSub to be the subscribe associated with each elevator. In our case we have two elevators. When the web application starts we initialise and start the elevator thread as a singleton from ElevatorPubSingleton.

The web application would be hosted on integrated jetty server. The entire application can be started using the following command
	mvn clean jetty:run

##REST API

BuildingController has REST API related to the elevator call made from each floor
1. PUT /building/up/{floorNo}: Used to call a lift to go up from the floor with number as floorNo
2. PUT /building/down/{floorNo}: Used to call a lift to go down from the floor with number as floorNo

ElevatorController has REST API related to the elevator call made from the inside of the elevator
1. PUT /elevator/{elevatorId}/goto/{floorNo}: called when a person inside the elevator press a button to goto a floor
2. GET /elevator/{elevatorId}/status: To get the status of the elevator identified by the elevatorId
3. PUT /elevator/{elevatorId}/stop: To stop the elevator identified by the elevatorId
4. GET /elevator: To get the template index.jsp to see the status of the elevators

##3rd Party API
I have also exposed a API to be given to the hardward company identified by BuildingSvc and ElevatorSvc.



