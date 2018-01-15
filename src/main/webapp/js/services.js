( function() {

	'use strict';

	angular.module( 'services', [] )

	.factory( 'locales', function( $http ) {
		return $http.get( 'locales' );
	} );
} )();