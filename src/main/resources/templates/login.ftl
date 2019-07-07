<!DOCTYPE html>
<#import "/spring.ftl" as spring/>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><@spring.message "login.title"/></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="http://code.jquery.com/jquery-1.8.3.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.8/angular.min.js"></script>
</head>

<body ng-app="login_form" ng-controller="AppCtrl">

<!-- Include _menu.ftl -->
<#include "_menu.ftl">

<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-default" style="margin-top: 45px">
                <div class="panel-heading">
                    <h3 class="panel-title"><@spring.message "login.panel.title"/></h3>
                </div>
                <div class="panel-body">
                    <#if logout>
                        <div class="alert alert-primary" role="alert">
                            <@spring.message "login.panel.alert.logout"/>
                        </div>
                    </#if>
                    <#if error>
                        <div class="alert alert-danger" role="alert">
                            <@spring.message "login.panel.alert.error"/>
                        </div>
                    </#if>
<#--
                    <form class="form-horizontal" name="form" autocomplete="off" novalidate
                          ng-submit="form.$valid && sendForm(auth)">
-->
                    <form method="post">
                        <div class="form-group">
                            <label id="inputEmailLabel"
                                   for="inputEmailEl"><@spring.message "login.panel.email"/></label>
                            <input type="email"
                                   class="form-control"
                                   id="inputEmailEl"
                                   placeholder="<@spring.message "login.panel.email"/>"
                                   required
                                   autofocus
                                   name="username"
                                   ng-model="auth.username">
                        </div>
                        <div class="form-group">
                            <label id="inputPasswordLabel"
                                   for="inputPasswordEl"><@spring.message "login.panel.password"/></label>
                            <input type="password"
                                   class="form-control"
                                   id="inputPasswordEl"
                                   placeholder="<@spring.message "login.panel.password"/>"
                                   required
                                   name="password"
                                   ng-model="auth.password">
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" name='remember-me'>
                                        <@spring.message "login.panel.rememberMe"/>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button id="btnSubmit" type="submit" class="btn btn-success"
                                        ng-disabled="form.$invalid">
                                    <@spring.message "login.panel.button.submit"/>
                                </button>
                            </div>
                        </div>
                        <input name="${_csrf.parameterName}" value="${_csrf.token}" type="hidden">
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="/js/login.js"></script>
</body>
</html>