Ext.define('App.view.process.Result', {
    extend: 'Ext.form.Panel',
    alias: 'widget.process-result',
	
	layout: 'anchor',
	
    items: [{
    	xtype:'fieldset',
        title: 'Detalle de finalizaci&oacute;n',
        defaultType: 'displayfield',
        anchor: '100% 35%',
        layout: 'anchor',
	    items: [{
	        fieldLabel: 'Estado final',
	        labelWidth: 85,
	        name: 'status'
	    },{
	        fieldLabel: 'Valores enviados',
	        labelWidth: 105,
	        name: 'sentValues'
	    },{
	        fieldLabel: 'Valores no enviados',
	        labelWidth: 120,
	        name: 'unsentValues'
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