<!DOCTYPE html>
<#import "/spring.ftl" as s/>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title><@s.message "login.title"/></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="http://code.jquery.com/jquery-1.8.3.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.8/angular.min.js"></script>
</head>

<body ng-app="login_form" ng-controller="AppCtrl">

<div class="container">
    <#include "parts/lang.ftl">
    <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-6">
            <div class="panel panel-default"
            ">
            <div class="panel-heading">
                <h4 class="panel-title"><@s.message "login.panel.title"/></h4>
            </div>
            <div class="panel-body">
                <#if logout>
                    <div class="alert alert-primary" role="alert">
                        <@s.message "login.panel.alert.logout"/>
                    </div>
                </#if>
                <#if error>
                    <div class="alert alert-danger" role="alert">
                        <@s.message "login.panel.alert.error"/>
                    </div>
                </#if>
                <#--
                                    <form class="form-horizontal" name="form" autocomplete="off" novalidate
                                          ng-submit="form.$valid && sendForm(auth)">
                -->
                <form method="post">
                    <div class="form-group">
                        <label id="inputEmailLabel"
                               for="inputEmailEl"><@s.message "login.panel.email"/></label>
                        <input type="email"
                               class="form-control"
                               id="inputEmailEl"
                               placeholder="<@s.message "login.panel.email"/>"
                               required
                               autofocus
                               name="username"
                               ng-model="auth.username">
                    </div>
                    <div class="form-group">
                        <label id="inputPasswordLabel"
                               for="inputPasswordEl"><@s.message "login.panel.password"/></label>
                        <input type="password"
                               class="form-control"
                               id="inputPasswordEl"
                               placeholder="<@s.message "login.panel.password"/>"
                               required
                               name="password"
                               ng-model="auth.password">
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-12">
                            <div class="row">
                                <div class="col-sm-6 checkbox">
                                    <label>
                                        <input type="checkbox"
                                               name='remember-me'> <@s.message "login.panel.rememberMe"/>
                                    </label>
                                </div>
                                <div class="col-sm-6">
                                    <ul class="nav justify-content-end">
                                        <li class="nav-item">
                                            <a class="nav-link active"
                                               href="/signup"><@s.message "login.panel.signup"/></a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button id="btnSubmit" type="submit" class="btn btn-success"
                                    ng-disabled="form.$invalid">
                                <@s.message "login.panel.button.submit"/>
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