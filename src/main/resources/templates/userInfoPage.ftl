<!DOCTYPE HTML>
<html>
<head>
    <title>User Info</title>
</head>
<body>
<!-- Include _menu.ftl -->
<#include "_menu.ftl">

    <h2>User Info Page</h2>
    <h3>Welcome : <span>@{request.userPrincipal.name}</span></h3>
    <b>This is protected page!</b> 
    <br/><br/>
    <div>${userInfo}></div>
</body>
</html>