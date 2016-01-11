/**
 * Created by vlad on 31.12.2015.
 */



var wtApp = angular.module('app', [])
    .controller('NewTaskCtrl',['$scope', '$http','$location','updateService', function ($scope, $http,$location,updateService) {
    $scope.sendPost = function() {
        //var data = $.param($scope.task);
        var data = $scope.task;
        $http.post('rest/task/post', data).success(function(data, status) {

            $scope.result1 = data;
            $scope.task = null;
           // $scope = $scope.$new(true);
           // $scope.loadData();
            updateService.updateTasks();
            $location.path("/CurrentTask");

        })
    }

}]);
wtApp.factory('myService', function () {
    return {
        tDate: function (timet) {

            fullDays = Math.floor(timet/(60*60*24*1000));
            fullHours = Math.floor((timet-(fullDays*60*60*24*1000))/(60*60*1000));
            fullMinutes = Math.floor((timet-(fullDays*60*60*24*1000)-(fullHours*60*60*1000))/(60*1000));
            sec= Math.floor((timet-(fullDays*60*60*24*1000)-(fullHours*60*60*1000)-(fullMinutes*60*1000))/1000)
            //return  ((fullDays==0)?"":(fullDays+'d ')) + fullHours+'h ' + fullMinutes+'m '+sec+'s';
            return  ((fullDays==0)?"":(fullDays+'d ')) +((fullHours==0)?"":(fullHours+'h '))  + fullMinutes+'m '+sec+'s';
        }
    }
});
wtApp.factory('updateService', function ($rootScope) {
    var sharedService = {};
    sharedService.updateTasks=function(){
        $rootScope.$broadcast('handleBroadcast');
    }
    return sharedService;
});

    /*.controller('NewTaskCtrl', ['$scope', function($scope) {
 /*       $scope.master = {};
        $scope.sendPost = function() {
            var data = $.param($scope.task);
            $http.post("/rest/task/post", data).success(function(data, status) {
                $scope.result1 = data;
            })
        }
        $scope.update = function(task) {
            $scope.master = angular.copy(task);
        };

        $scope.reset = function() {
            $scope.task = angular.copy($scope.master);
        };

        $scope.reset();


    }]);
    */
/*
$http({
    method: 'POST',
    url: url,
    data: xsrf,
    headers: {'Content-Type': 'application/x-www-form-urlencoded'}
})*/
/*
myApp.controller('NewTaskCtrl', function ($scope, $http) {
$scope.sendPost = function() {
    var data = $.param($scope.task);
    $http.post("/rest/task/post", data).success(function(data, status) {
        $scope.result1 = data;
    })
}
});*/
wtApp.controller('TaskOfUserCtrl', function ($scope, $http) {
    console.log('listsCtrl');
    //this.lists = listFactory.getLists();

    $http.get('rest/task/getus').success(function (data) {
        $scope.lists = data;
       // $scope.tasks = data[0].Task;
    });


});
wtApp.controller('UserCtrl',['$scope','$http', function ($scope, $http) {
    console.log('UserCtrl');
    //this.lists = listFactory.getLists();
    $http.get('rest/user').success(function (data) {
        $scope.user = data;
        // $scope.tasks = data[0].Task;
    });
}]);
wtApp.controller('UserCtrl',['$scope','$http', function ($scope, $http) {
    console.log('UserCtrl');
    //this.lists = listFactory.getLists();
    $http.get('rest/user').success(function (data) {
        $scope.user = data;
        // $scope.tasks = data[0].Task;
    }
    );
}]);
wtApp.controller('ShowCurrentCtrl', ['$scope','$http','$location','updateService', function($scope, $http,$location,updateService) {


    console.log('ShowCurrentCtrl');
    //this.lists = listFactory.getLists();
    $scope.loadData = function(){
        $http.get('rest/task/ctask').success(function (data) {
            $scope.ctask = data;
            // $scope.tasks = data[0].Task;
            }
    );
    }
    $scope.sendStatus = function() {
        //var data = $.param($scope.task);
        var data = $scope.ctask.tskIname;
        $http.post('rest/task/ftask', data).success(function(data, status) {
            $scope.result1 = data;
           // $scope = $scope.$new(true);
            updateService.updateTasks();
            $location.path("/AddNewTask");
            $scope.ctask = null;
             $scope.$apply();

        })
    }
    $scope.loadData();
}]);


wtApp.controller('TasksCtrl',['$scope','$http','myService','updateService', function ($scope, $http,myService,updateService) {
    console.log('UserCtrl');
    //this.lists = listFactory.getLists();
    $scope.tDate = myService.tDate;
    $scope.updateTable = function() {
        $http.get('rest/task/info').success(function (data) {
                $scope.tasksOfuser = data;
                // $scope.tasks = data[0].Task;
            }
        );
    };
    $scope.$on('handleBroadcast',function(){
        $scope.updateTable();
    });
    $scope.updateTable();
}]);



wtApp.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.
        when('/AddNewTask', {
            templateUrl: 'templates/add_task.jsp',
            controller: 'NewTaskCtrl'
        }).
        when('/CurrentTask', {
            templateUrl: 'templates/current_task.jsp',
            controller: 'ShowCurrentCtrl'
        }).otherwise({
            redirectTo: '/AddNewTask'
        });
    }])
