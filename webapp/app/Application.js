Ext.define('App.Application', {
    extend: 'Ext.app.Application',
    
    name: 'App',

    stores: [
        // TODO: add global / shared stores here
    ],
    
    views: [
    	'App.view.layout.MainPanel',
        'App.view.main.Main',
        'App.view.main.List',
        'App.view.login.Login'
    ],
    
    controllers: [
        'LoginController',
        'MainController'
    ],
    
    launch: function () {
        var loggedIn = localStorage.getItem("LoggedIn");
		console.log("Logged in? " + loggedIn);
		
		var name = loggedIn ? 'App.view.layout.MainPanel' : 'App.view.login.Login'
        Ext.create(name);
    }

});
