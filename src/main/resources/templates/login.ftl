<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login form's Main</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="http://code.jquery.com/jquery-1.8.3.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.8/angular.min.js"></script>
</head>

<body ng-app="login_form" ng-controller="AppCtrl">

<!-- Include _menu.ftl -->
<#include "_menu.ftl">

<div class="container" style="margin-top: 60px">
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <h3 id="resultMessage">{{message}}</h3>
            <h1 class="page-header">Login Demo</h1>
            <form style="margin-bottom: 30px" name="form" autocomplete="off" novalidate ng-submit="form.$valid && sendForm(auth)">

                <div class="form-group">
                    <label id="inputEmailLabel" for="inputEmailEl">Email address</label>
                    <input type="email"
                           class="form-control"
                           id="inputEmailEl"
                           placeholder="Email"
                           required
                           ng-model="auth.usrname">
                </div>
                <div class="form-group">
                    <label id="inputPasswordLabel" class="control-label" for="inputPasswordEl">Password</label>
                    <input type="password"
                           class="form-control"
                           id="inputPasswordEl"
                           placeholder="Password"
                           required
                           ng-model="auth.password">
                </div>
                <button id="btnSubmit" type="submit" class="btn btn-success" style="margin-top:30px" ng-disabled="form.$invalid">
                    Log in
                </button>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript" src="/js/login.js"></script>
</body>
</html>