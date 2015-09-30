Ext.form.DateField.prototype.format = 'd/m/Y';
Ext.Ajax.timeout = 120000; // 120 seconds

Ext.define('App.Application', {
    extend: 'Ext.app.Application',
    
    name: 'App',

	requires: [
	    'App.util.ActivityMonitor'
	],
	
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
        'App.view.process.ProcessList',
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
    	App.util.ActivityMonitor.init({
			verbose: false,
			maxInactive:  (1000 * 60 * 30), // 30 minutes
			interval: (1000 * 60 * 1), //1 minute
			isInactive: function () {
				Ext.MessageBox.alert('Sesi&oacuten expirada',
					'El tiempo de la sesi&oacuten actual expir&oacute. Debe reloguearse para poder continuar.',
					function () {
						Ext.Ajax.request({
				            url: 'api/auth/invalidate',
				            method: 'POST',
				          	success: function(response, options) {
						        location.href = 'login.html';
				            },
				            failure: function(response, options) {
						        location.href = 'login.html';
					        }
						}, this);
					});
			}
		});
		App.util.ActivityMonitor.start();
		
        Ext.create('App.view.layout.MainPanel');
    }

});
