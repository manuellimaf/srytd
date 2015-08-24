Ext.define('App.view.configProps.Form', {
    extend: 'Ext.form.Panel',
    alias: 'widget.config-props',
	
	title: 'Variables del sistema',
	height: 250,
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

	store: 'ConfigPropsStore',
	
    items: [{
    	xtype:'fieldset',
        title: 'C&oacute;digos',
        defaultType: 'textfield',
        defaults: {anchor: '100%'},
        layout: 'anchor',
	    items: [{
	        fieldLabel: 'C&oacute;digo Empresa',
	        name: 'companyCode',
	        allowBlank: false
	    },{
	        fieldLabel: 'Pto. de env&iacute;o',
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
                items: [{
                    xtype:'textfield',
                    fieldLabel: 'IP',
                    allowBlank: false,
                    name: 'ip',
                    anchor:'95%'
                }, {
                    xtype:'textfield',
                    fieldLabel: 'Usuario',
                    allowBlank: false,
                    name: 'ftpUser',
                    anchor:'95%'
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
	    }]
    }],

    buttons: [{
    	text: 'Guardar',
        formBind: true,
        disabled: true,
        action: 'update'
    }]
});