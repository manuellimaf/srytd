Ext.define('App.controller.MappingsController', {
    extend: 'Ext.app.Controller',
	requires: ['App.util.FormSubmit'],
	
	init: function() {
        this.control({
        	'gridpanel': {
        		selectionchange: this.onSelectionChange
        	}
        });
	},
	
	views: ['config.MappingsForm'],
    refs: [{
		selector: 'mappings-form',
		ref: 'mappingsForm'
	}],
	stores: ['MappingStore'],
    models: ['Mapping'],
	
	onSelectionChange: function(model, records) {
        var rec = records[0];
        if (rec) {
	    	var panel = this.getMappingsForm();
			panel.getForm().loadRecord(rec);
        }
	}
});