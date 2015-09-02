Ext.define('App.controller.ProcessController', {
    extend: 'Ext.app.Controller',

	init: function() {
        this.control('panel button[action=viewDetail]', {
    		click: this.showDetail
        });
        this.control('panel button[action=manualSend]', {
    		click: this.manualSend
        });
        this.control('manual-send button[action=startProcess]', {
    		click: this.startProcess
        });
		this.control('mapped-field-value-list', {
			beforeadd: this.loadFieldValueList
		});
		this.control('mapped-field-value-list button[action=resend]', {
			click: this.resend
		});
		this.control('process-result', {
			beforerender: this.loadProcessResult
		});
        this.control('process-result button[action=downloadFile]', {
    		click: this.downloadFile
        });
    },
    
    views: ['process.List', 'process.Result', 'mappedFieldValue.List', 'process.ManualSendView'],
    stores: ['ProcessStore', 'MappedFieldValueStore', 'ProcessResultStore'],
    models: ['Process'],
    refs: [{
		selector: 'process-list',
		ref: 'processList'
	},{
		selector: 'mapped-field-value-list',
		ref: 'mappedFieldValueList'
	},{
		selector: 'process-result',
		ref: 'processResult'
	},{
		selector: 'manual-send',
		ref: 'manualSendView'
	}],    
    currentWindow: undefined,
    currentProcessId: undefined,
    showDetail: function () {
	    var selected = this.getProcessList().getSelectionModel().selected;
	    if(selected.getCount() > 0) {
		    var processId = selected.first().get('id');
	    
	    	if(this.currentWindow) {
	    		this.currentWindow.close();
	    	}
	    	this.currentProcessId = processId;
	        this.currentWindow = Ext.create('App.view.mappedFieldValue.Panel', {processId: processId});
			this.currentWindow.show();
	    }
    },
    
    manualSend: function () {
    	Ext.create('App.view.process.ManualSendView').show();
    },
    
    startProcess: function () {
    	var window = this.getManualSendView();
		var form = window.down('form').getForm();
		var store = this.getStore('ProcessStore');
		App.util.FormSubmit.submit(form, '/api/process/start', store);
		store.reload();
		window.close();
    },
    
    loadFieldValueList: function() {
    	var store = this.getStore('MappedFieldValueStore');
		store.load({
			params: { processId: this.currentProcessId }
		});
    },

    resend: function() {
    	Ext.Msg.confirm('Re enviar?', 
    		'Realmente desea enviar los datos correspondientes al proceso ' + this.currentProcessId + '? El mismo puede demorar varios minutos.',
		    function(resp) {
		    	if(resp == 'yes') {
			    	Ext.Ajax.request({
					    url: '/api/process/' + this.currentProcessId + '/resend',
					    method: 'POST',
					    success: function(response, opts) {
					        var id = Ext.decode(response.responseText);
					        Ext.Msg.alert('Proceso finalizado', 'Finaliz&oacute; correctamente el proceso de env&iacute;o de datos (nuevo id: ' + id +').');
					    },
					    failure: function(response, opts) {
					        Ext.Msg.alert('Error', 'Se produjo un error al realizar el env&iacute;o.');
					    }
					});
				}
		    },
		    this);
    },
    
    loadProcessResult: function() {
		var form = this.getProcessResult().getForm();
		this.getStore('ProcessResultStore').load({
			params: { processId: this.currentProcessId },
			callback: function(results, operation, success) {
				var data = results[0];
		        form.loadRecord(data);
		    }
		});
    },
    
    downloadFile: function() {
	    var downloadUrl = '/api/process/' + this.currentProcessId + '/file';
	
		var body = Ext.getBody();
		
		var frame = body.createChild({
			tag: 'iframe',
			cls: 'x-hidden',
			id: 'hiddenform-iframe',
			name: 'iframe'
		});
		
		var form = body.createChild({
			tag: 'form',
			cls: 'x-hidden',
			id: 'hiddenform-form',
			action: downloadUrl,
			target: 'iframe'
		});
		
		form.dom.submit();
    }
    
	
});
