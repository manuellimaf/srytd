Ext.define('App.controller.ConfigPropsController', {
    extend: 'Ext.app.Controller',
	requires: ['App.util.FormSubmit'],
	
	views: ['config.ConfigPropsForm'],
    refs: [{
		selector: 'config-props',
		ref: 'configForm'
	}],
	stores: ['ConfigPropsStore'],
    models: ['ConfigProps'],
	
	init: function() {
		this.control({
			'config-props': {
				afterrender: this.loadConfig
			}
		});
		
		this.control({
            'config-props button[action=update]': {
                click: this.update
            }
        });
	},
	
	loadConfig: function() {
		var form = this.getConfigForm().getForm();
		var data = this.getStore('ConfigPropsStore').getAt(0);
        form.loadRecord(data);
    },
    
	update: function() {
		var store = this.getStore('ConfigPropsStore').reload();
		var form = this.getConfigForm().getForm();
		App.util.FormSubmit.submit(form, '/api/config', store);
	}
	
});