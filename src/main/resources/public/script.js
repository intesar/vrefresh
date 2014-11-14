// create the module and name it scotchApp
var scotchApp = angular.module('scotchApp', [
    'ngRoute',
    'ngResource'
]);

// configure our routes
scotchApp.config(function ($routeProvider) {
    $routeProvider

            // route for the home page
            .when('/', {
                templateUrl: 'pages/home.html',
                controller: 'mainController'
            })

            // route for the about page
            .when('/vcenter', {
                templateUrl: 'pages/vcenter.html',
                controller: 'vcenterController'
            })

            .when('/vcenter/:id', {
                templateUrl: 'pages/vcenter_edit.html',
                controller: 'vcenterEditController'
            })

            // route for the about page
            .when('/host', {
                templateUrl: 'pages/host.html',
                controller: 'hostController'
            })

            // route for the about page
            .when('/host/:id', {
                templateUrl: 'pages/host_edit.html',
                controller: 'hostEditController'
            })

            // route for the about page
            .when('/task', {
                templateUrl: 'pages/task.html',
                controller: 'taskController'
            })

            // route for the about page
            .when('/task/:id', {
                templateUrl: 'pages/task_edit.html',
                controller: 'taskEditController'
            })
            
            // route for the about page
            .when('/schedule', {
                templateUrl: 'pages/schedule.html',
                controller: 'scheduleController'
            })
            
            // route for the about page
            .when('/schedule/:id', {
                templateUrl: 'pages/schedule_edit.html',
                controller: 'scheduleEditController'
            })

});

// create the controller and inject Angular's $scope
scotchApp.controller('mainController', function ($scope) {
    // create a message to display in our view
    $scope.message = 'Everyone come and see how good I look!';
});

angular.module('scotchApp')
        .provider('VCenter', function () {
            this.$get = ['$resource', function ($resource) {
                    return $resource('vcenters/:id', {id: '@id'}, {
                        update: {
                            method: 'PUT'
                        }
                    });
                }];
        });

angular.module('scotchApp')
        .provider('Host', function () {
            this.$get = ['$resource', function ($resource) {
                    return $resource('hosts/:id', {id: '@id'}, {
                        update: {
                            method: 'PUT'
                        }
                    });
                }];
        });

angular.module('scotchApp')
        .provider('Task', function () {
            this.$get = ['$resource', function ($resource) {
                    return $resource('tasks/:id', {id: '@id'}, {
                        update: {
                            method: 'PUT'
                        }
                    });
                }];
        });

angular.module('scotchApp')
.provider('Schedule', function () {
    this.$get = ['$resource', function ($resource) {
            return $resource('schedules/:id', {id: '@id'}, {
                update: {
                    method: 'PUT'
                }
            });
        }];
});

scotchApp.controller('vcenterController', function ($scope, VCenter) {
    $scope.message = 'Look! I am an vcenterController page.';
    $scope.objs = VCenter.query();
});

scotchApp.controller('vcenterEditController', function ($scope, $routeParams, VCenter, $window) {
    $scope.message = 'Look! I am an vcenterEditController page.';

    $scope.params = $routeParams;

    $scope.obj = new VCenter();

    $scope.loadVCenter = function () {
        VCenter.get({id: $scope.params.id}, function (obj) {
            $scope.obj = obj;
        });
    };

    $scope.loadVCenter();

    $scope.updateVCenter = function () {
        if ($scope.obj.id > 0) {
            $scope.obj.$update(function () {
                $window.location.href = '#/vcenter';
            });
        } else {
            $scope.obj.$save(function () {
                $window.location.href = '#/vcenter';
            });
        }
    }
});

scotchApp.controller('hostController', function ($scope, Host) {
    $scope.message = 'Look! I am an hostController page.';
    $scope.objs = Host.query();
});

scotchApp.controller('hostEditController', function ($scope, $routeParams, Host, VCenter, $window) {
    $scope.message = 'Look! I am an hostEditController page.';
    $scope.obj = new Host();
    $scope.vcs = VCenter.query();
    $scope.params = $routeParams;

    $scope.loadHost = function () {
        Host.get({id: $scope.params.id}, function (obj) {
            $scope.obj = obj;
        });
    };

    $scope.loadHost();

    $scope.updateHost = function () {
        if ($scope.obj.id > 0) {
            $scope.obj.$update(function () {
                $window.location.href = '#/host';
            });
        } else {
            $scope.obj.$save(function () {
                $window.location.href = '#/host';
            });
        }
    }
});

scotchApp.controller('taskController', function ($scope, Task) {
    $scope.message = 'Look! I am an taskController page.';
    $scope.objs = Task.query();
});

scotchApp.controller('taskEditController', function ($scope, $routeParams, Task, $window) {
    $scope.message = 'Look! I am an taskEditController page.';
    $scope.obj = new Task();
    $scope.params = $routeParams;

    $scope.loadTask = function () {
        Task.get({id: $scope.params.id}, function (obj) {
            $scope.obj = obj;
        });
    };

    $scope.loadTask();

    $scope.updateTask = function () {
        if ($scope.obj.id > 0) {
            $scope.obj.$update(function () {
                $window.location.href = '#/task';
            });
        } else {
            $scope.obj.$save(function () {
                $window.location.href = '#/task';
            });
        }
    }
});

scotchApp.controller('scheduleController', function ($scope, Schedule) {
    $scope.message = 'Look! I am an scheduleController page.';
    $scope.objs = Schedule.query();
});

scotchApp.controller('scheduleEditController', function ($scope, $routeParams, Schedule, Task, Host, $window) {
    $scope.message = 'Look! I am an scheduleEditController page.';
    
    $scope.vms = Host.query();
    $scope.scripts = Task.query();
    
    $scope.obj = new Schedule();
    $scope.params = $routeParams;

    $scope.loadSchedule = function () {
    	Schedule.get({id: $scope.params.id}, function (obj) {
            $scope.obj = obj;
        });
    };

    $scope.loadSchedule();

    $scope.updateSchedule = function () {
        if ($scope.obj.id > 0) {
            $scope.obj.$update(function () {
                $window.location.href = '#/schedule';
            });
        } else {
            $scope.obj.$save(function () {
                $window.location.href = '#/schedule';
            });
        }
    }
});

scotchApp.controller('contactController', function ($scope) {
    $scope.message = 'Contact us! JK. This is just a demo.';
});