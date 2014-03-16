<!DOCTYPE html>
<html ng-app>
<head>

    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.0-beta.1/angular.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.0-beta.1/angular-resource.min.js"></script>

    <style media="screen" type="text/css">
        .done-true {
            text-decoration: line-through;
            color: grey;
        }
    </style>

    <title></title>
</head>
<body>
<h2>TODO</h2>
<div ng-controller="TodoCtrl">
    <span>{{remaining()}} of {{todos.length}} remaining</span>
    [<a href="" ng-click="archive()">Archive</a>]

    <ul class="unstyled">
        <li ng-repeat="todo in todos">
            <input type="checkbox" ng-model="todo.done"/>
            <span class="done-{{todo.done}}">{{todo.text}}</span>
        </li>
    </ul>

    <form ng-submit="addTodo()">
        <input type="text" ng-model="todoText" size="30"
               placeholder="add new todo here">
        <input class="btn-primary" type="submit" value="add">
    </form>
</div>

<script>
       /*angular.module.factory('todoService', function($http) {
            return {
                getTodos: function() {
                    //return the promise directly.
                    return $http.get('http://localhost:8080/todo')
                            .then(function(result) {
                                return result.data;
                            });
                }
            }
        })*/

     /*.module.controller('TodoCtrl', function($scope, todoService) {*/
        function TodoCtrl($scope, $http) {
        /*$scope.todos = [
            {text:'learn angular', done:true},
            {text:'build an angular app', done:false}];*/
            $http({method: 'GET', url: 'http://localhost:8080/todo'}).
                    success(function(data, status, headers, config) {
                        alert(status)
                        $scope.todos = data;
                    }).
                    error(function(data, status, headers, config) {
                        // called asynchronously if an error occurs
                        // or server returns response with an error status.
                        alert("status: " + status + " = " + data);
                    });

        /*$scope.todos = $todoService.getTodos();*/

        $scope.addTodo = function() {
            $scope.todos.push({text:$scope.todoText, done:false});
            $scope.todoText = '';
        };

        $scope.remaining = function() {
            var count = 0;
            angular.forEach($scope.todos, function(todo) {
                count += todo.done ? 0 : 1;
            });
            return count;
        };

        $scope.archive = function() {
            var oldTodos = $scope.todos;
            $scope.todos = [];
            angular.forEach(oldTodos, function(todo) {
                if (!todo.done) $scope.todos.push(todo);
            });
        };
    }
</script>
</body>
</html>