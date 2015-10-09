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
					xtype: 'fieldcontainer',
		            fieldLabel: 'Tipo',
		            defaultType: 'radiofield',
		            defaults: {
			            allowBlank: false,
		                flex: 1
		            },
		            layout: 'hbox',
		            items:[{
	                    boxLabel  : 'FTP',
	                    name      : 'ftpType',
	                    inputValue: 'FTP',
	                    checked: true
	                },{
	                    boxLabel  : 'FTPS',
	                    name      : 'ftpType',
	                    inputValue: 'FTPS'
	                },{
	                    boxLabel  : 'SFTP',
	                    name      : 'ftpType',
	                    inputValue: 'SFTP'
	                }]
	            },{
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
	        		xtype: 'fieldset',
			    	layout: 'hbox',
			    	border: false,
			    	style: { padding: 0 },
	        		items: [
			        	{ fieldLabel: 'IP y puerto', xtype: 'textfield', name: 'ip', allowBlank: false, width: 208 },
			        	{ fieldLabel: '', xtype: 'numberfield', name: 'port', allowBlank: false, maxValue: 65535, minValue: 0, margin: '0 0 0 10', labelWidth: 0, width: 90 }
	        		]
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