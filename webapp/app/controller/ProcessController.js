Ext.define('App.controller.ProcessController', {
    extend: 'Ext.app.Controller',

	init: function() {
        this.control({
        	'panel button[action=viewDetail]': {
        		click: this.showDetail
        	}
        });
		this.control({
			'mapped-field-value-list': {
				beforeadd: this.loadFieldValueList
			}
		});
		this.control({
			'process-result': {
				beforerender: this.loadProcessResult
			}
		});
        this.control({
        	'process-result button[action=downloadFile]': {
        		click: this.downloadFile
        	}
        });
    },
    
    views: ['process.List', 'process.Result', 'mappedFieldValue.List'],
    stores: ['Process', 'MappedFieldValueStore', 'ProcessResultStore'],
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
    loadFieldValueList: function() {
    	var store = this.getStore('MappedFieldValueStore');
		store.load({
			params: { processId: this.currentProcessId }
		});
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
    	alert("download file for process " + this.currentProcessId);
    }
    
	
});
