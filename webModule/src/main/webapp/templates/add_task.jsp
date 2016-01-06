<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 03.01.2016
  Time: 0:31
  To change this template use File | Settings | File Templates.
--%>
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

        <!--<button type="submit" ng-click="sendPost()" class="btn btn-success">Add</button>-->


    </form>

    {{result1}}
    {{task}}
    </div>
</div>