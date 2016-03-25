
<header>
  <div class="top_page row">
    <div class="col-sm-4">
        <div  ng-controller="UserCtrl"  style="margin-left: 10px">
            <h5> <strong>User:</strong>  {{user.usrName}}</h5>
        </div>
    </div>

      <div class="col-lg-3">
      </div>
      <div class="col-sm-4 pull-right">
         <a href="logout" class="btn btn btn-danger btn-sm pull-right" >Logout</a>
      </div>

  </div>
 </header>
<div ng-controller="NewTaskCtrl">
    <div class="row">


    <form name ="myForm" novalidate  >
        <div class="col-sm-4">
            <input type="text" ng-model="task.tskIname"  size="30" class="form-control input-sm" placeholder="Enter new task"  required autofocus/>
        </div>
        <div class="col-sm-8">
         <button type="button" ng-click="sendPost()" class="btn btn-sm btn-success">
            <i class="glyphicon glyphicon-plus">
            </i> New Task
            </button>
        </div>
        <input type="hidden" ng-model="task.tskId"  value=""  />

        <span class="error" ng-show="myForm.input.$error.required">Required!</span><br>


    </form>

    {{result1}}
    <!--{{task}}-->
    </div>
</div>
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