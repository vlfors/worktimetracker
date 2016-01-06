<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 03.01.2016
  Time: 0:30
  To change this template use File | Settings | File Templates.
--%>

<div class="task-wall">

    <div ng-controller="ShowCurrentCtrl">
    <form novalidate  >
       <span style="margin-left: 10px;margin-right:1px">Task: </span> <span style="margin-left: 1px;margin-right:5px">{{ctask.tskIname}}</span>  <button type="submit" ng-click="sendStatus()" class="btn btn-sm btn-primary">Stop</button>

    </form>


    </div>
</div>