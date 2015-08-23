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
    	body.add({xtype: 'field-value-panel'});
    },

    onShowConfig: function() {
    	var body = this.clearBody();
    	body.add({xtype: 'config-panel'});
    }
});