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
<div class="task-wall">

    <div ng-controller="ShowCurrentCtrl">
    <form novalidate  >
       <span style="margin-left: 10px;margin-right:1px">Task: </span> <span ng-bind="tname" style="margin-left: 1px;margin-right:5px"></span>  <button type="submit" ng-click="sendStatus()" class="btn btn-sm btn-primary">Stop</button>
      <span style="margin-left: 10px;margin-right:1px">Time: </span>  <span  my-current-time="beginTime" style="margin-left: 1px;margin-right:5px"></span>

    </form>


    </div>
</div>