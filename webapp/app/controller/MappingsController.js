Ext.define('App.controller.MappingsController', {
    extend: 'Ext.app.Controller',
	requires: ['App.util.FormSubmit'],
	
	init: function() {
        this.control('gridpanel#mappings-list', {
        	selectionchange: this.onSelectionChange
        });
        this.control('gridpanel#mappings-list button[action=deleteMapping]', {
    		click: this.deleteMapping
        });
        this.control('mappings-form button[action=update]', {
    		click: this.updateMapping
        });
        this.control('mappings-form button[action=save]', {
    		click: this.createMapping
        });
	},
	
	views: ['config.MappingsForm'],
    refs: [{
		selector: 'mappings-form',
		ref: 'mappingsForm'
	},{
		selector: 'gridpanel#mappings-list',
		ref: 'gridPanel'
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
	
	createMapping: function() {
	    var store = this.getStore('MappingStore');
	    var form = this.getMappingsForm().getForm();
	    if(form.isValid()) {
	    	App.util.FormSubmit.submit(form, '/api/mapping', store); 
		}
	},
	
	updateMapping: function() {
	    var store = this.getStore('MappingStore');
	    var form = this.getMappingsForm().getForm();
	    if(form.isValid() && form.getValues().id) {
	    	App.util.FormSubmit.update(form, '/api/mapping', store); 
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
		    Ext.Msg.confirm('Eliminar?', 'Realmente desea eliminar el mapeo ' + device + ' - ' + tag + '?',
			    function(resp) { 
			    	if(resp == 'yes') {
				    	App.util.FormSubmit.delete('/api/mapping/' + mappingId, store); 
				    }
				}, this);
		}
	}
});