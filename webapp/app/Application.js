Ext.form.DateField.prototype.format = 'd/m/Y';

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
        'App.view.manualFieldValue.ManualFieldValueForm',
        'App.view.config.ConfigPanel',
		'App.view.config.MappingsForm',
        'App.view.config.UsersForm',
        'App.view.config.ConfigPropsForm'
    ],
    
    controllers: [
        'LoginController',
        'MainPanelController',
        'ProcessController',
        'ManualValuesController',
        'UsersController',
        'MappingsController',
        'ConfigPropsController'
    ],
    
    launch: function () {
        Ext.create('App.view.layout.MainPanel');
    }

});
