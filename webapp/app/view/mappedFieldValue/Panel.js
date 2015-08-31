Ext.define('App.view.mappedFieldValue.Panel', {
	extend: 'Ext.window.Window',
	alias: 'widget.mapped-field-value-panel',

	constructor: function(config) {
	    this.title = 'Detalle del env&iacute;o #' + config.processId;
        this.callParent(config);
    },	
	
    width: 1200,
    height: 500,
    constrain: true,
    modal: true,
    layout: 'fit',
    
    items: {
    	xtype: 'tabpanel',
	    items: [{
	        title: 'Mediciones',
	        xtype: 'mapped-field-value-list'
	    }, {
	        title: 'Resultado',
	        xtype: 'process-result'
	    }]
    }
});
