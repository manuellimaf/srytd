Ext.define('App.controller.ProcessController', {
    extend: 'Ext.app.Controller',

	init: function() {
        this.control({
        	'panel button[action=viewDetail]': {
        		click: this.showDetail
        	}
        });
		this.control({
			'process-result': {
				afterrender: this.loadProcessResult
			}
		});
        this.control({
        	'process-result button[action=downloadFile]': {
        		click: this.downloadFile
        	}
        });
    },
    
    views: ['process.List', 'process.Result'],
    stores: ['Process', 'ProcessResultStore'],
    models: ['Process'],
    refs: [{
		selector: 'process-list',
		ref: 'processList'
	},{
		selector: 'process-result',
		ref: 'processResult'
	}],    
    currentWindow: undefined,
    currentProcessId: undefined,
    showDetail: function () {
    	console.log(this.getProcessList().getSelectionModel());
	    var selected = this.getProcessList().getSelectionModel().selected;
	    if(selected.getCount() > 0) {
		    var processId = selected.first().get('id');
			console.log("processId: " + processId);
	    
	    	if(this.currentWindow) {
	    		this.currentWindow.close();
	    	}
	    	this.currentProcessId = processId;
	        this.currentWindow = Ext.create('App.view.mappedFieldValue.Panel', {processId: processId});
			this.currentWindow.show();
	    }
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
