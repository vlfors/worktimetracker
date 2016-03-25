/**
 * Created by vlad on 31.12.2015.
 */

var wtApp = angular.module('app', ['ngRoute'])
    .controller('NewTaskCtrl',['$scope', '$http','$location','updateService', function ($scope, $http,$location,updateService) {
     $scope.checkAvTask = function() {
        var tstr = updateService.checkCurrentTask();
         if (tstr!=null) {
             $location.path("/CurrentTask");
          }
     };
    $scope.sendPost = function() {

        var data = $scope.task;
        console.log("post the task" + data.tskIname);
        $http.post('rest/task/post', data).success(function(data, status) {
            $scope.result1 = data;
            $scope.task = null;
             console.log("success " + data);
            updateService.updateTasks();
            $location.path("/CurrentTask");


        }).error(function(data, status) {
                     //alert(data);
                     });
    };

}]);
wtApp.factory('myService', function () {
    return {
        tDate: function (timet) {

            fullDays = Math.floor(timet/(60*60*24*1000));
            fullHours = Math.floor((timet-(fullDays*60*60*24*1000))/(60*60*1000));
            fullMinutes = Math.floor((timet-(fullDays*60*60*24*1000)-(fullHours*60*60*1000))/(60*1000));
            sec= Math.floor((timet-(fullDays*60*60*24*1000)-(fullHours*60*60*1000)-(fullMinutes*60*1000))/1000);
            //return  ((fullDays==0)?"":(fullDays+'d ')) + fullHours+'h ' + fullMinutes+'m '+sec+'s';
            return  ((fullDays==0)?"":(fullDays+'d ')) +((fullHours==0)?"":(fullHours+'h '))  + fullMinutes+'m '+sec+'s';
        }
    }
});


wtApp.factory('updateService',['$rootScope','$http', function ($rootScope,$http) {
    var sharedService = {};
    sharedService.updateTasks=function(){
        $rootScope.$broadcast('handleBroadcast');
    };
    sharedService.checkCurrentTask=function(){
             $http.get('rest/task/ctask').success(function (data) {
                 return   data;
             }).error(function(data) {
                                    //alert(data);
                                    });

           };
    return sharedService;
}]);




wtApp.controller('TaskOfUserCtrl', function ($scope, $http) {
    console.log('listsCtrl');
    //this.lists = listFactory.getLists();

    $http.get('rest/task/getus').success(function (data) {
        $scope.lists = data;
       // $scope.tasks = data[0].Task;
    }).error(function(data) {
                           //alert(data);
                           });


});

wtApp.controller('UserCtrl',['$scope','$http', function ($scope, $http) {
    console.log('UserCtrl');

    $http.get('rest/user').success(function (data) {
        $scope.user = data;

    }).error(function(data, status) {
                           //alert(data);
                           });
}]);
wtApp.controller('ShowCurrentCtrl', ['$scope','$http','$location','updateService','myService', function($scope, $http,$location,updateService,myService) {


    console.log('ShowCurrentCtrl');
    $scope.tDate = myService.tDate;
    $scope.loadData = function(){
        $http.get('rest/task/ctask').success(function (data) {
            $scope.ctask = data;
            $scope.tname = $scope.ctask.tskIname;

            }
        );
    };

    $scope.getBeginDate = function(){
            $http.get('rest/task/cbegin').success(function (data) {
                $scope.beginTime = data;

                }
            );
        };
    $scope.sendStatus = function() {

        var data = $scope.ctask.tskIname;
        $http.post('rest/task/ftask', data).success(function(data, status) {
            $scope.result1 = data;
            updateService.updateTasks();
            $scope.ctask = null;
            $location.path("/AddNewTask");
        }).error(function(data, status) {
                              // alert(data);
                               });
    };
    $scope.loadData();
    //test
    $scope.getBeginDate();
}])
.directive('myCurrentTime', ['$interval','myService',  function($interval,myService) {

  function link(scope, element, attrs) {
    var   bTime,
        timeoutId;

    function updateTime() {
      element.text(myService.tDate(bTime==null?0:new Date().getTime()- bTime));
    }

    scope.$watch(attrs.myCurrentTime, function(value) {
       bTime = value;
       updateTime();
    });

    element.on('$destroy', function() {
      $interval.cancel(timeoutId);
    });

    // start the UI update process; save the timeoutId for canceling
    timeoutId = $interval(function() {
      updateTime(); // update DOM
    }, 1000);
  }

  return {
    link: link
  };
}]);;


wtApp.controller('TasksCtrl',['$scope','$http','myService','updateService', function ($scope, $http,myService,updateService) {
    console.log('TasksCtrl');
    $scope.tDate = myService.tDate;
    $scope.updateTable = function() {
        $http.get('rest/task/info').success(function (data) {
                $scope.tasksOfuser = data;

            }).error(function(data) {
                                   //alert(data);
                                   });
    };
    $scope.$on('handleBroadcast',function(){
        $scope.updateTable();
    });
    $scope.updateTable();
}]);


wtApp.controller('Login', ['$scope','$location', function($scope, $location) {


     console.log('Login');

    $scope.loginus = function(){
        $location.path("/Login");
    };

    $scope.loginus();
}]);

wtApp.config(['$routeProvider',function($routeProvider) {
        $routeProvider.
        when('/AddNewTask', {
            templateUrl: 'templates/add_task.jsp'//,controller: 'NewTaskCtrl'
        }).
        when('/CurrentTask', {
            templateUrl: 'templates/current_task.jsp'
            /*, controller: 'ShowCurrentCtrl',
            controller:'UserCtrl'*/
        }).otherwise({
                      templateUrl: 'templates/login.jsp',
                      controller: 'Login'
                 })

    }]);



wtApp.config(['$httpProvider', function ($httpProvider) {

    var interceptor = ['$q', '$window', '$location', '$injector', function($q, $window, $location, $injector) {

        return {
            request: function (config) {
                config.headers = config.headers || {};
                if ($window.sessionStorage.token) {
                    config.headers.Authorization = 'Bearer ' + $window.sessionStorage.token;
                }
                return config;
            },

            requestError: function(rejection) {
                return $q.reject(rejection);
            },

            response: function (response) {
                return response || $q.when(response);
            },

            // Revoke client authentication if 401 is received

            responseError: function(rejection) {
                console.log(rejection);
                // Dynamically get the service since they can't be injected into config
              //  var AuthenticationService = $injector.get('AuthenticationService');

                if (rejection != null && rejection.status === 401 /*&& ($window.sessionStorage.token || AuthenticationService.isLogged)*/) {
                   // delete $window.sessionStorage.token;
                    //AuthenticationService.isLogged = false;
                    $location.path("/Login");
                }

                return $q.reject(rejection);
            }
        };
    }];

    $httpProvider.interceptors.push(interceptor);
}]);



