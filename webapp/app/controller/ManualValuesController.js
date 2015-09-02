Ext.define('App.controller.ManualValuesController', {
    extend: 'Ext.app.Controller',
	requires: ['App.util.FormSubmit'],
	
	init: function() {
        this.control('gridpanel#manual-value-list', {
        	selectionchange: this.onSelectionChange
        });
        this.control('gridpanel#manual-value-list button[action=deleteManualValue]', {
    		click: this.deleteManualValue
        });
        this.control('manual-values-form button[action=update]', {
    		click: this.updateMapping
        });
        this.control('manual-values-form button[action=save]', {
    		click: this.saveValue
        });
	},
	
	views: ['manualFieldValue.ManualFieldValueForm'],
    refs: [{
		selector: 'manual-values-form',
		ref: 'manualValuesForm'
	},{
		selector: 'gridpanel#manual-values-list',
		ref: 'gridPanel'
	}],
	stores: ['ManualFieldValueStore'],
    models: ['ManualFieldValue'],
	
	onSelectionChange: function(model, records) {
        var rec = records[0];
        if (rec) {
	    	var panel = this.getManualValuesForm();
			panel.getForm().loadRecord(rec);
        }
	},
	
	saveValue: function() {
	    var store = this.getStore('ManualFieldValueStore');
	    var form = this.getManualValuesForm().getForm();
	    if(form.isValid()) {
	    	App.util.FormSubmit.submit(form, '/api/manual-field-values', store); 
		}
	},
	
	updateMapping: function() {
	    var store = this.getStore('ManualFieldValueStore');
	    var form = this.getManualValuesForm().getForm();
	    if(form.isValid() && form.getValues().id) {
	    	App.util.FormSubmit.update(form, '/api/manual-field-values', store); 
		}
	},

	deleteManualValue: function() {
	    var form = this.getManualValuesForm().getForm();
	    if(form.isValid()) {
	    	var values = form.getValues();
		    var id = values.id;
		    var device = values.deviceId;
		    var store = this.getStore('ManualFieldValueStore');
		    Ext.Msg.confirm('Eliminar?', 'Realmente desea eliminar los datos para el dispositivo ' + device + '?',
			    function(resp) { 
			    	if(resp == 'yes') {
				    	App.util.FormSubmit.delete('/api/manual-field-values/' + id, store); 
				    }
				}, this);
		}
	}
	
});