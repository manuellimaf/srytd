Ext.define('App.view.config.ConfigPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.config-panel',
	
	title: 'Configuraci&oacute;n',
	height: 500,
	//border: false,
	
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
	            height: 250,
	            verticalAlign: 'top'
	        }
	    },
	    tdAttrs: {
	        style: {
	            width: '50%'
	        }
	    }
	},
	
	items: [{
        xtype: 'user-list'
    },{
        html: 'Mapeos'
    },{
        html: 'General config',
        colspan: 2
    }]
});