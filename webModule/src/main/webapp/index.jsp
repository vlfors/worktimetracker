<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 26.12.2015
  Time: 12:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html ng-app="app" >
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <script src="js/jquery-2.2.2.min.js"></script>
    <script src="js/angular.min.js"></script>
    <script src="js/angular-route.min.js"></script>
    <script src="js/angular-resource.min.js"></script>
    <script src="js/app.js"></script>

    <script src="js/task/controllers.js"></script>
    <style>
    .top_pag{
        padding: 20px 5px 20px 5px;
        background-color: #778899;
    }

    .task-wall
        {

            padding: 10px 0px 10px 0px;
            background-color: #f7f7f7;
            -moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
            -webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
            box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
        }
    .task-wall .task-control.task-control
        {
            position: relative;
            font-size: 16px;
            height: auto;
            padding: 10px;
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
            box-sizing: border-box;
        }
        .tasks-div{
            margin-top: 10px;
        }
    </style>
</head>
<body>



      <div ng-view></div>


</body>
</html>
