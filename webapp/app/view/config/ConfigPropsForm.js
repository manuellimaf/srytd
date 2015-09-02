Ext.define('App.view.config.ConfigPropsForm', {
    extend: 'Ext.form.Panel',
    alias: 'widget.config-props',
	
	title: 'Variables del sistema',
	border: 0,
	frame: true,
		
	layout: {
		type: 'table',
		columns: 2,
		tableAttrs: {
	        style: { width: '100%' }
	    },
	    trAttrs: {
	        style: { verticalAlign: 'top' }
	    },
	    tdAttrs: {
	        style: {
	            width: '50%',
	            padding: 5
	        }
	    }
	},

	store: 'ConfigPropsStore',
	
    items: [{
    	xtype:'fieldset',
        title: 'C&oacute;digos',
        defaultType: 'textfield',
        defaults: {anchor: '100%'},
        layout: 'anchor',
	    items: [{
	        fieldLabel: 'C&oacute;digo empresa',
	        name: 'companyCode',
	        allowBlank: false
	    },{
	        fieldLabel: 'Punto de env&iacute;o',
	        name: 'facilityCode',
	        allowBlank: false
	    }]        
    },{
    	xtype:'fieldset',
        title: 'FTP Server',
        defaults: {anchor: '100%'},
        layout: 'anchor',
	    items: [{
            xtype: 'container',
            anchor: '100%',
            layout: 'hbox',
            items:[{
                xtype: 'container',
                flex: 1,
                layout: 'anchor',
		        defaults: {
		        	anchor: '95%',
		        	allowBlank: false
		        },
                items: [{
                    xtype:'textfield',
                    fieldLabel: 'IP',
                    name: 'ip'
                }, {
                    xtype:'textfield',
                    fieldLabel: 'Usuario',
                    allowBlank: false,
                    name: 'ftpUser'
                }]
            },{
                xtype: 'container',
                flex: 1,
                layout: 'anchor',
                items: [{
                    xtype:'numberfield',
                    fieldLabel: 'Puerto',
                    allowBlank: false,
                    name: 'port',
                    anchor:'100%',
                    maxValue: 65535,
        			minValue: 0
                },{
                    xtype:'textfield',
                    fieldLabel: 'Password',
                    allowBlank: false,
            		inputType: 'password',
                    name: 'ftpPassword',
                    anchor:'100%'
                }]
            }]
        }]       
    },{
    	xtype:'fieldset',
    	colspan: 2,
        title: 'Emails',
        defaultType: 'textfield',
        defaults: {anchor: '100%'},
        layout: 'anchor',
	    items: [{
	        fieldLabel: 'Alertas',
	        name: 'alertEmails'
	    },{
	        fieldLabel: 'Notificaciones',
	        name: 'notificationEmails'
	    }, {
	    	xtype: 'displayfield',
	    	value: '* Puede indicar varias direcciones de mail serpar&aacute;ndolas con comas (,)'
	    }]
    }],

    buttons: [{
    	text: 'Guardar',
        formBind: true,
        disabled: true,
        action: 'update',
        iconCls: 'icon-save'
    }]
});