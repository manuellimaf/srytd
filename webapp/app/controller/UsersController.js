Ext.define('App.controller.UsersController', {
    extend: 'Ext.app.Controller',
	requires: ['App.util.FormSubmit'],
	
	init: function() {
        this.control('gridpanel#users-list', {
        	selectionchange: this.onSelectionChange
        });
/*
        this.control('gridpanel#mappings-list button[action=deleteMapping]', {
    		click: this.deleteMapping
        });
        this.control('mappings-form button[action=update]', {
    		click: this.updateMapping
        });
        this.control('mappings-form button[action=save]', {
    		click: this.createMapping
        });
*/	},
	
	views: ['config.UsersForm'],
    refs: [{
		selector: 'users-form',
		ref: 'usersForm'
	},{
		selector: 'gridpanel#users-list',
		ref: 'gridPanel'
	}],
	stores: ['UserStore'],
    models: ['User'],
	
	onSelectionChange: function(model, records) {
        var rec = records[0];
        if (rec) {
	    	var panel = this.getUsersForm();
			panel.getForm().loadRecord(rec);
        }
	} /*,
	
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
		    Ext.Msg.confirm('Eliminar?', 'Realmente desea eliminar el mapeo ' + name + ' - ' + tag + '?',
			    function(resp) { 
			    	if(resp == 'yes') {
				    	App.util.FormSubmit.delete('/api/mapping/' + mappingId, store); 
				    }
				}, this);
		}
	}*/
});