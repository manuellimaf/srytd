Ext.define('App.controller.MappingsController', {
    extend: 'Ext.app.Controller',
	requires: ['App.util.FormSubmit'],
	
	init: function() {
        this.control({
        	'gridpanel': {
        		selectionchange: this.onSelectionChange
        	}
        });
        this.control({
        	'gridpanel button[action=deleteMapping]': {
        		click: this.deleteMapping
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
	},
	
	deleteMapping: function() {
	    var form = this.getMappingsForm().getForm();
	    if(form.isValid()) {
	    	var values = form.getValues();
		    var mappingId = values.id;
		    var device = values.name;
		    var tag = values.tag;
		    var store = this.getStore('MappingStore');
		    Ext.Msg.confirm('Eliminar?', 'Realmente desea eliminar el mapeo ' + name + ' - ' + tag + '?',
			    function(resp) { 
			    	console.log(resp);
			    	if(resp == 'yes') {
				    	App.util.FormSubmit.delete('/api/mapping/' + mappingId, store); 
				    }
				}, this);
		}
	}
});