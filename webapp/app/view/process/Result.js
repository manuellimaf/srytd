Ext.define('App.view.process.Result', {
    extend: 'Ext.form.Panel',
    alias: 'widget.process-result',
	
	layout: 'anchor',
	frame: true,
    items: [{
    	xtype:'fieldset',
        title: 'Detalle de finalizaci&oacute;n',
        defaultType: 'displayfield',
        anchor: '100% 30%',
        layout: 'anchor',
	    items: [{
	        fieldLabel: 'Estado final',
	        labelWidth: 85,
	        name: 'status'
	    },{
	        fieldLabel: 'Registros enviados',
	        labelWidth: 115,
	        name: 'sentValues'
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
		    	margin: '0 0 0 10',
		    	text: 'Descargar',
		    	action: 'downloadFile',
		    	iconCls: 'icon-download'
		    }]
	    }]        
    },{
    	xtype:'fieldset',
        title: 'Errores',
        anchor: '100% 65%',
        layout: 'anchor',
	    items: [{
	        fieldLabel: 'C&oacute;digo de error',
	        xtype: 'displayfield',
	        name: 'errorId'
	    },{
	        fieldLabel: 'Mensaje de error',
	        xtype: 'displayfield',
	        name: 'errorMessage'
	    },{
	        fieldLabel: 'Descripci&oacute;n',
	        xtype: 'textarea',
	        anchor: '100% 75%',
	        name: 'errorDesc'
	    }]
    }]
});