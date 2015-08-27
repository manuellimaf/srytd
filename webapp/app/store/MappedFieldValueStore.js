Ext.define('App.store.MappedFieldValueStore', {
    extend: 'Ext.data.Store',

	constructor: function(config) {
	    this.processId = config.processId; 
	    console.log("Process #" + this.processId + " store created");
		this.proxy = new Ext.data.proxy.Ajax({
	        url: '/api/process/' + this.processId + '/mapped-field-values',
		    model: 'App.model.MappedFieldValue',
	        reader: {
	            type: 'json',
	            root: 'items',
	            totalProperty: 'total'
	        }
		});
		
        this.callParent(config);
	},
	
    model: 'App.model.MappedFieldValue',
	autoLoad: {start: 0, limit: 20},
	pageSize: 20
});
