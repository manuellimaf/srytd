Ext.define('App.Application', {
    extend: 'Ext.app.Application',
    
    name: 'App',

    stores: [
        // TODO: add global / shared stores here
    ],
    
    views: [
        'App.view.login.Login',
    	'App.view.layout.MainPanel',
        'App.view.layout.BodyPanel',
        'App.view.process.ProcessPanel',
        'App.view.process.ManualSendView',
        'App.view.mappedFieldValue.Panel',
        'App.view.mappedFieldValue.List',
        'App.view.process.List',
        'App.view.fieldValue.FieldValuePanel',
        'App.view.config.ConfigPanel',
        'App.view.user.List',
        'App.view.configProps.Form'
    ],
    
    controllers: [
        'LoginController',
        'MainPanelController',
        'ProcessController',
        'UserController',
        'ConfigPropsController'
    ],
    
    launch: function () {
        var loggedIn = localStorage.getItem("LoggedIn");
		console.log("Logged in? " + loggedIn);
		
		var name = loggedIn ? 'App.view.layout.MainPanel' : 'App.view.login.Login'
        Ext.create(name);
    }

});
