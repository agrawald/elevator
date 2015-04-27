<!doctype html>
<html ng-app="elevatorApp">
<head>
    <title>Hello AngularJS</title>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.8/angular.min.js"></script>
    <script src="resources/elevator.js"></script>
</head>

<body>
<div ng-controller="elevatorCtrl">
    <form novalidate>
        <input type="text" ng-model="elevatorId">
        <button ng-click="getStatus()">Status</button>
    </form>
    <p>...life {{elevatorId}}, is moving
        <span ng-show="elevatorStatus.up">up</span>
        <span ng-show="elevatorStatus.down">down</span>
        to level {{elevatorStatus.floorNo}}</p>

    <p style="color:red">{{error}}</p>
</div>
</body>
</html>