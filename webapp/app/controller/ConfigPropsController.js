Ext.define('App.controller.ConfigPropsController', {
    extend: 'Ext.app.Controller',
	
	views: ['configProps.Form'],
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
		var form = this.getConfigForm().getForm();
		Ext.Ajax.request({
            url: '/api/config',
            method: 'POST',
            headers: {
              'Content-Type': 'application/json;charset=utf-8'
          	},
          	params: Ext.JSON.encode(form.getValues())
		});
	}
	
});