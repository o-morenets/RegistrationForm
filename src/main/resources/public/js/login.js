angular.module("login_form", [])
    .controller("AppCtrl", function ($scope, $http) {
        $scope.auth = {};
        let resultMessageLabel = document.getElementById('resultMessage');
        let inputEmailEl = document.getElementById('inputEmailEl');
        let inputPasswordEl = document.getElementById('inputPasswordEl');

        $scope.sendForm = function (auth) {
            $http({
                method: "POST",
                url: "/login",
                data: $.param(auth),
                headers: {"Content-Type": "application/x-www-form-urlencoded"}
            }).then(
                (data) => {
                    resultMessageLabel.style.color = 'green';
                    $scope.message = "Access granted";
                    inputEmailEl.value = '';
                    inputPasswordEl.value = '';
                },
                (error) => {
                    resultMessageLabel.style.color = 'red';
                    $scope.message = error.data.message;
                    inputEmailEl.value = '';
                    inputPasswordEl.value = '';
                }
            );
        }
    });