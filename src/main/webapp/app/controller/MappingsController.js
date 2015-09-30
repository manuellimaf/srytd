Ext.define('App.controller.MappingsController', {
    extend: 'Ext.app.Controller',
	requires: ['App.util.FormSubmit'],
	
	init: function() {
        this.control({
        	'mappings-list button[action=deleteMapping]': {
    			click: this.deleteMapping
        	},
			'mappings-list': {
    			edit: this.updateMapping
	        },
        	'mappings-list button[action=createMapping]': {
    			click: this.createMapping
        	}
        });
	},
	
	views: ['config.MappingsForm'],
    refs: [{
		selector: 'mappings-list',
		ref: 'gridPanel'
	}],
	stores: ['MappingStore'],
    models: ['Mapping'],
	
	createMapping: function() {
		var record = new App.model.Mapping();
	    var store = this.getStore('MappingStore');
		var editor = this.getGridPanel().getPlugin('row-editor');
		
		store.insert(0, record);
		editor.startEdit(0, 0);
	},
	
	updateMapping: function(editor, e) {
	    var selected = this.getGridPanel().getSelectionModel().selected;
	    if(selected.getCount() > 0) {
	    	var row = selected.first();
		    var mappingId = row.get('id');
			var method = mappingId ? 'PUT' : 'POST';
			
		    Ext.Ajax.request({
	            url: 'api/mapping',
	            method: method,
	            headers: {
	              'Content-Type': 'application/json;charset=utf-8'
	          	},
	          	params: Ext.JSON.encode(e.record.data),
	          	success: function(response, options) {
					e.record.commit();
	            },
	            failure: App.util.FormSubmit.failureHandler
			});
	    }
		
	},
	
	deleteMapping: function() {
	
	    var selected = this.getGridPanel().getSelectionModel().selected;
	    if(selected.getCount() > 0) {
	    	var row = selected.first();
		    var mappingId = row.get('id');
		    var store = this.getStore('MappingStore');
		    if(typeof mappingId != 'undefined' && mappingId != null) {
			    var device = row.get('name');
	
			    Ext.Msg.confirm('Eliminar?', 'Realmente desea eliminar el mapeo para el dispositivo ' + device + '?',
				    function(resp) { 
				    	if(resp == 'yes') {
				            Ext.Ajax.request({
					            url: 'api/mapping/' + mappingId,
					            method: 'DELETE',
					          	success: function(response, options) {
				          			store.reload();
					                Ext.Msg.alert('Info', 'Operaci&oacute;n concretada con &eacute;xito');
					            },
					            failure: App.util.FormSubmit.failureHandler
							});
					    }
					}, this);
			} else {
      			store.reload();
			}
		}
	}
});