angular.module("registration_form", [])
    .controller("AppCtrl", function ($scope, $http) {
        $scope.auth = {};
        let resultMessageLabel = document.getElementById('resultMessage');
        let inputFirstNameEl = document.getElementById('inputFirstName');
        let inputLastNameEl = document.getElementById('inputLastName');
        let inputEmailEl = document.getElementById('inputEmail');
        $scope.sendForm = function (auth) {
            $http({
                method: "POST",
                url: "/api/reg_form",
                data: $.param(auth),
                headers: {"Content-Type": "application/x-www-form-urlencoded"}
            }).then(
                function(data) {
                    resultMessageLabel.style.color = 'green';
                    $scope.message = 'Registered successfully';
                    inputFirstNameEl.value = '';
                    inputLastNameEl.value = '';
                    inputEmailEl.value = '';
                },
                function(error) {
                    resultMessageLabel.style.color = 'red';
                    $scope.message = error.data.message;
                    inputFirstNameEl.value = '';
                    inputLastNameEl.value = '';
                    inputEmailEl.value = '';
                }
            );
        }
    });