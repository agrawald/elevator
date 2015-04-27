var elevatorApp = angular.module('elevatorApp', []);
elevatorApp.controller('elevatorCtrl', function ($scope, $http) {
    $scope.getStatus = function () {
        $http.get('/elevator/' + $scope.elevatorId + '/status').
            success(function (data) {
                $scope.elevatorStatus = data;
            }).error(function (error) {
                console.info(error);
                $scope.error = "Invalid floor!"
            });
    };

});