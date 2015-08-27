Ext.define('App.controller.ProcessController', {
    extend: 'Ext.app.Controller',

	init: function() {
        this.control({
        	'panel button[action=viewDetail]': {
        		click: this.showDetail
        	}
        });
    },
    
    views: ['process.List'],
    stores: ['Process'],
    models: ['Process'],
    refs: [{
		selector: 'process-list',
		ref: 'processList'
	}],    
    currentWindow: undefined,
    showDetail: function () {
    	console.log(this.getProcessList().getSelectionModel());
	    var selected = this.getProcessList().getSelectionModel().selected;
	    if(selected.getCount() > 0) {
		    var processId = selected.first().get('id');
			console.log("processId: " + processId);
	    
	    	if(this.currentWindow) {
	    		this.currentWindow.close();
	    	}
	        this.currentWindow = Ext.create('App.view.mappedFieldValue.Panel', {processId: processId});
			this.currentWindow.show();
	    }
    }

});
