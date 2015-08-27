Ext.define('App.view.mappedFieldValue.Panel', {
	extend: 'Ext.window.Window',
	alias: 'widget.mapped-field-value-panel',

	constructor: function(config) {
	    this.processId = config.processId; 
	    this.title = 'Detalle del proceso ' + this.processId;
	    
//        config.store = Ext.create('Ext.data.Store', ...
//{
//    extend: 'Ext.data.Store',

//    model: 'App.model.MappedFieldValue',
//	autoLoad: {start: 0, limit: 20},
//	pageSize: 20,
	// TODO . el load tendría que ser por proceso
//    proxy: {
//        type: 'ajax',
//        url: '/api/mapped-field-value',
 //       reader: {
//            type: 'json',
//            root: 'items',
//            totalProperty: 'total'
//        }
//    }
//        ...);
        this.callParent(config);
    },	
	
	height: 400,
    width: 600,
    layout: 'anchor',
    items: {
    	xtype: 'panel',
	    height: 600,
	    items: [{
	        title: 'Mediciones',
	        xtype: 'mapped-field-value-list'
	    }, {
	        title: 'Resultado'
	    }]
    }
});
