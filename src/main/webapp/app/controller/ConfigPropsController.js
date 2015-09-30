Ext.define('App.controller.ConfigPropsController', {
    extend: 'Ext.app.Controller',
	requires: ['App.util.FormSubmit'],
	
	views: ['config.ConfigPropsForm', 'config.ConfigPanel'],
    refs: [{
		selector: 'config-props',
		ref: 'configForm'
	},{
		selector: 'config-panel',
		ref: 'configPanel'
	}],
	stores: ['ConfigPropsStore'],
    models: ['ConfigProps'],
	
	init: function() {
		this.control({
			'config-panel': {
				afterrender: this.addChild
			},
			'config-props': {
				afterrender: this.loadConfig
			},
			'config-props button[action=update]': {
                click: this.update
            }
        });
        
	},
	addChild: function() {
		var panel = this.getConfigPanel();
		panel.add({ xtype: 'mappings-list' });
        if(localStorage.getItem('ROLE') == 'ADMIN') {
	        panel.add({ xtype: 'users-form' });
			panel.add({ xtype: 'config-props'});
        }
        panel.setActiveTab(0);
		
	},	
	loadConfig: function() {
		var form = this.getConfigForm().getForm();
		var data = this.getStore('ConfigPropsStore').getAt(0);
        form.loadRecord(data);
    },
    
	update: function() {
		var store = this.getStore('ConfigPropsStore').reload();
		var form = this.getConfigForm().getForm();
		App.util.FormSubmit.submit(form, 'api/config', store);
	}
	
});