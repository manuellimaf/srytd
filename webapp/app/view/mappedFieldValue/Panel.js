Ext.define('App.view.mappedFieldValue.Panel', {
	extend: 'Ext.window.Window',
	alias: 'widget.mapped-field-value-panel',

	constructor: function(config) {
	    this.title = 'Detalle del env&iacute;o #' + config.processId;
        this.callParent(config);
    },	
	
	height: 600,
    width: 1200,
    layout: 'anchor',
    items: {
    	xtype: 'tabpanel',
	    anchor: '100% 100%',
	    items: [{
	        title: 'Mediciones',
	        xtype: 'mapped-field-value-list'
	    }, {
	        title: 'Resultado',
	        xtype: 'process-result'
	    }]
    }
});
