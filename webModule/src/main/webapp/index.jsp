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
    <script src="js/jquery-2.1.4.min.js"></script>
    <script src="js/angular.min.js"></script>
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
<header>
  <div class="top_page row">
    <div class="col-sm-4">
        <div  ng-controller="UserCtrl"  style="margin-left: 10px">
            <h5> <strong>User:</strong>  {{user.usrName}}</h5>
        </div>
    </div>

      <div class="col-lg-3">
      </div>
      <div class="col-sm-4  pull-right">
         <a href="logout"class="btn btn btn-danger btn-sm pull-right" >Logout</a>
      </div>
     </div>
  </div>
 </header>


        <div ng-view></div>

        <!--<div  ng-controller="TaskOfUserCtrl">
            {{lists}}
        </div>-->


<!-- <a href="templates/add_task.jsp">dd</a>-->
 <div class="tasks-div" ng-controller="TasksCtrl">
     <!--<div ng-repeat="model in tasksOfuser">
         {{model[0]}} : {{model[1]}}
     </div>-->

    <button ng-click="updateTable()" class="btn btn-info btn-sm">Refresh Data</button>
            <table st-table="displayedCollection" st-safe-src="rowCollection" class="table table-striped">
                <thead>
                <tr>
                    <th st-sort="firstName">Task</th>
                    <th st-sort="lastName">Duration</th>

                </tr>
                <!--
                <tr>
                    <th colspan="5"><input st-search="" class="form-control" placeholder="global search ..." type="text"/></th>
                </tr>-->
                </thead>
                <tbody>
                <tr ng-repeat="model in tasksOfuser">
                    <td>{{model[0]}}</td>
                    <td>{{tDate( model[1])}}  </td>

                </tr>
                </tbody>
            </table>
        </div>


</body>
</html>
