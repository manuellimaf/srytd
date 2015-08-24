Ext.define('App.view.config.ConfigPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.config-panel',
	
	title: 'Configuraci&oacute;n',
	height: 600,
	
	layout: {
		type: 'table',
		columns: 2,
		tableAttrs: {
	        style: {
	            width: '100%'
	        }
	    },
	    trAttrs: {
	        style: {
	            verticalAlign: 'top'
	        }
	    },
	    tdAttrs: {
	        style: {
	            width: '50%',
	            padding: 5
	        }
	    }
	},
	
	items: [{
        xtype: 'user-list'
    },{
        html: 'Mapeos'
    },{
        xtype: 'config-props',
        colspan: 2
    }]
});