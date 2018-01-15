( function() {

	'use strict';

	angular.module( 'controllers', [] ).controller( 'appController', function( $scope, $http, $window, locales, ) {
        $scope.obj = {}
        $scope.obj.locale = ''
		locales.then( function( result ) {
			$scope.locales = result.data;
			var lang = $window.navigator.language || $window.navigator.userLanguage;
			for(var i=0; i<$scope.locales.length; i++){
			    if(lang.indexOf($scope.locales[i]) > -1){
			        $scope.obj.locale= $scope.locales[i]
			        $scope.defaultLocale = $scope.obj.locale
			        break
			    }
			}
		} );

        $scope.reset = function(){
            $scope.obj.query = ''
            $scope.obj.locale = $scope.defaultLocale
            $scope.error = ''
            $scope.result = ''
        }

        $scope.encode = function(){
            $http.get( 'encode', {params : $scope.obj }).then(
                function(result){
                    $scope.result = result.data
                    $scope.error = ''
                },
                function(result){
                    $scope.result = ''
                    $scope.error = result.data
                }
            )
        }

       $scope.decode = function(){
                $http.get( 'decode', {params : $scope.obj }).then(
                    function(result){
                        $scope.result = result.data
                        $scope.error = ''
                    },
                     function(result){
                        $scope.result = ''
                         $scope.error = result.data
                     }
                )
            }

	} )
} )();
