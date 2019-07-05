angular.module("login_form", [])
    .controller("AppCtrl", function ($scope, $http) {
        $scope.auth = {};
        let resultMessageLabel = document.getElementById('resultMessage');
        let inputEmailEl = document.getElementById('inputEmailEl');
        let inputPasswordEl = document.getElementById('inputPasswordEl');
        let buttonSubmit = document.getElementById('btnSubmit');

        inputEmailEl.addEventListener('input', () => {
            inputEmailEl.style.color = 'black';
        });

        $scope.sendForm = function (auth) {
            $http({
                method: "POST",
                url: "/login",
                data: $.param(auth),
                headers: {"Content-Type": "application/x-www-form-urlencoded"}
            }).then(
                (response) => {
                    resultMessageLabel.style.color = 'green';
                    $scope.message = "Access granted";
                    inputEmailEl.value = '';
                    inputPasswordEl.value = '';
                    buttonSubmit.disabled = 'true';
                },
                (error) => {
                    resultMessageLabel.style.color = 'red';
                    $scope.message = error.data.message;
                    inputEmailEl.style.color = 'red';
                    inputEmailEl.value = error.data.userLoginDTO.username;
                    inputPasswordEl.value = '';
                    buttonSubmit.disabled = 'true';
                }
            );
        }
    });