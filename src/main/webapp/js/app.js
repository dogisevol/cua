( function() {

	'use strict';

	/* App Module */

	angular.module( 'app', [ 'ngRoute', 'controllers', 'services' ] )

	.config( [ '$routeProvider', function( $routeProvider ) {

		$routeProvider.when( '/', {
			templateUrl: 'partials/main.html',
			controller: 'appController'
		} ).otherwise( {
			redirectTo: '/'
		} );
	} ] );
} )();