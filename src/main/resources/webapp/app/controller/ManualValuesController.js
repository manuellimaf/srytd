Ext.define('App.controller.ManualValuesController', {
    extend: 'Ext.app.Controller',
	requires: ['App.util.FormSubmit'],
	
	init: function() {
        this.control({
        	'manual-values-list button[action=deleteManualValue]': {
	    		click: this.deleteValue
	        },
	        'manual-values-list': {
	    		edit: this.updateValue
	        },
	        'manual-values-list button[action=createManualValue]': {
	    		click: this.saveValue
	        }
        });
	},
	
	views: ['manualFieldValue.ManualFieldValueForm'],
    refs: [{
		selector: 'manual-values-list',
		ref: 'gridPanel'
	}],
	stores: ['ManualFieldValueStore', 'DeviceMappingStore'],
    models: ['ManualFieldValue', 'DeviceMapping'],
	
	saveValue: function() {
		var record = new App.model.ManualFieldValue();
	    var store = this.getStore('ManualFieldValueStore');
		var editor = this.getGridPanel().getPlugin('mfv-row-editor');
		
		store.insert(0, record);
		editor.startEdit(0, 0);
	},
	
	updateValue: function(editor, e) {
	    var selected = this.getGridPanel().getSelectionModel().selected;
	    if(selected.getCount() > 0) {
	    	var row = selected.first();
		    var valueId = row.get('id');
			var method = valueId ? 'PUT' : 'POST';
			var store = this.getStore('ManualFieldValueStore');
		    Ext.Ajax.request({
	            url: '/api/manual-field-values',
	            method: method,
	            headers: {
	              'Content-Type': 'application/json;charset=utf-8'
	          	},
	          	params: Ext.JSON.encode(e.record.data),
	          	success: function(response, options) {
					e.record.commit();
					store.reload();
	            },
	            failure: App.util.FormSubmit.failureHandler
			});
	    }
	},

	deleteValue: function() {
	
	    var selected = this.getGridPanel().getSelectionModel().selected;
	    if(selected.getCount() > 0) {
	    	var row = selected.first();
		    var valueId = row.get('id');
		    var store = this.getStore('ManualFieldValueStore');
		    if(typeof valueId != 'undefined' && valueId != null) {
	
			    Ext.Msg.confirm('Eliminar?', 'Realmente desea eliminar el registro?',
				    function(resp) { 
				    	if(resp == 'yes') {
				            Ext.Ajax.request({
					            url: '/api/manual-field-values/' + valueId,
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