Ext.define('App.controller.MainPanelController', {
    extend: 'Ext.app.Controller',
    
    init: function() {
        this.control({
            'panel button[action=showProcesses]': {
                click: this.onShowProcesses
            },
            'panel button[action=showFieldValues]': {
                click: this.onShowFieldValues
            },
            'panel button[action=showConfig]': {
                click: this.onShowConfig
            },
            'panel button[action=logout]': {
                click: this.onLogout
            } 
        });
    },
    
    views: ["layout.BodyPanel"],
    refs: [{
		selector: 'body-panel',
		ref: 'bodyPanel'
	}],
    
    clearBody: function() {
    	var panel = this.getBodyPanel();
    	panel.removeAll();
    	return panel;
    },
    
    onShowProcesses: function() {
    	var body = this.clearBody();
    	body.add({xtype: 'process-panel'});
    },

    onShowFieldValues: function() {
    	var body = this.clearBody();
    	body.add({xtype: 'manual-values-form'});
    },

    onShowConfig: function() {
    	var body = this.clearBody();
    	body.add({xtype: 'config-panel'});
    }, 
    
    onLogout: function () {
        // Remove the localStorage key/value
        localStorage.removeItem('LoggedIn');

        // Remove Main View
        this.getView().destroy();

        // Add the Login Window
        Ext.create('login');
    }
});