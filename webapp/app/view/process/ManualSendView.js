Ext.define('App.view.process.ManualSendView', {
	extend: 'Ext.window.Window',
	alias: 'widget.manual-send',

	title: 'Forzar recolecci&oacute;n y  env&iacute;o',
    width: 350,
    height: 175,
    constrain: true,
    modal: true,
    layout: 'anchor',
    
    items: {
        xtype: 'form',
	    anchor: '100% 100%',
        defaults: { anchor: '100% 50%' },
		layout: 'anchor',
		items: [{
	    	xtype: 'fieldset',
	        title: 'Mediciones correspondientes a',
	        layout: 'anchor',
			margin: '0 5 0 5',
	        items: [{
	            xtype: 'container',
	            anchor: '100%',
	            layout: 'hbox',
	            items:[{
			        fieldLabel: 'Fecha',
			        labelWidth: 50,
			        width: 150,
			        xtype: 'datefield',
			        name: 'dateFrom',
			        format: 'd/m/Y',
			        allowBlank: false
			    },{
			        fieldLabel: 'Hora desde',
			        labelWidth: 75,
			        xtype: 'numberfield',
			        name: 'hourFrom',
			        allowBlank: false,
			        width: 125,
	        		minValue: 0,
			        maxValue: 23,
			    	margin: '0 0 0 10'
			    }]
			}]
		},{
	    	xtype: 'displayfield',
	    	value: 'Las mediciones corresponden a la hora seleccionada entre el minuto 00 y el 59',
			margin: '0 10 0 10'
	    }],
		
		buttons: [{
	    	text: 'Comenzar',
	        formBind: true,
	        disabled: true,
	        action: 'startProcess',
	        iconCls: 'icon-manual-send'
	    }]
		
    }
});
