Ext.define('App.controller.MappingsController', {
    extend: 'Ext.app.Controller',
	requires: ['App.util.FormSubmit'],
	
	init: function() {
        this.control('mappings-list button[action=deleteMapping]', {
    		click: this.deleteMapping
        });
        this.control('mappings-list', {
    		edit: this.updateMapping
        });
        this.control('mappings-list button[action=save]', {
    		click: this.createMapping
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
	    var store = this.getStore('MappingStore');
	    var form = this.getMappingsForm().getForm();
	    if(form.isValid()) {
	    	App.util.FormSubmit.submit(form, '/api/mapping', store); 
		}
	},
	
	updateMapping: function(editor, e) {
		console.log(e.record);
	    Ext.Ajax.request({
            url: '/api/mapping',
            method: 'PUT',
            headers: {
              'Content-Type': 'application/json;charset=utf-8'
          	},
          	params: Ext.JSON.encode(e.record.data),
          	success: function(response, options) {
				e.record.commit();
            },
            failure: App.util.FormSubmit.failureHandler
		});
	},
	
	deleteMapping: function() {
	
	    var selected = this.getGridPanel().getSelectionModel().selected;
	    if(selected.getCount() > 0) {
	    	var row = selected.first();
		    var mappingId = row.get('id');
		    var device = row.name;
		    var code = row.code;

		    Ext.Msg.confirm('Eliminar?', 'Realmente desea eliminar el mapeo para el dispositivo ' + device + '?',
			    function(resp) { 
			    	if(resp == 'yes') {
					    var store = this.getStore('MappingStore');
			            Ext.Ajax.request({
				            url: '/api/mapping/' + mappingId,
				            method: 'DELETE',
				          	success: function(response, options) {
			          			store.reload();
				                Ext.Msg.alert('Info', 'Operaci&oacute;n concretada con &eacute;xito');
				            },
				            failure: App.util.FormSubmit.failureHandler
						});
				    }
				}, this);
		}
	}
});