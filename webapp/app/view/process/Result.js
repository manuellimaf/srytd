Ext.define('App.view.process.Result', {
    extend: 'Ext.form.Panel',
    alias: 'widget.process-result',
	
	layout: 'anchor',
	
    items: [{
    	xtype:'fieldset',
        title: 'Detalle de finalizaci&oacute;n',
        defaultType: 'displayfield',
        anchor: '100% 25%',
        layout: 'anchor',
	    items: [{
	        fieldLabel: 'Estado final',
	        name: 'status'
	    },{
	        fieldLabel: 'Disp. sin mapeo',
	        name: 'unmappedDevices'
	    },{
	    	xtype: 'fieldset',
	    	layout: 'hbox',
	    	border: false,
	    	style: { padding: 0 },
	    	items: [{
		        xtype: 'displayfield',
		        fieldLabel: 'Archivo generado',
		        name: 'fileName'
		    }, {
		    	xtype: 'button',
		    	text: 'Descargar',
		    	action: 'downloadFile'
		    }]
	    }]        
    },{
    	xtype:'fieldset',
        title: 'Errores',
        anchor: '100% 75%',
        layout: 'fit',
        collapsible: true,
        collapsed: true,
	    items: [{
	        fieldLabel: 'C&oacute;digo de error',
	        type: 'displayfield',
	        name: 'errorId'
	    },{
	        fieldLabel: 'Error',
	        type: 'textArea',
	        name: 'errorDesc'
	    }]
    }]
});