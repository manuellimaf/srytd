Ext.define('App.view.config.MappingsForm', {
    extend: 'Ext.form.Panel',
    alias: 'widget.mappings-form',

    xtype: 'form-grid',
   	title: 'Mapeo de dispositivos',
    
    frame: true,
    bodyPadding: 5,
    layout: 'column',
    width: 880,
    
    fieldDefaults: {
        labelAlign: 'left',
        labelWidth: 90,
        anchor: '100%',
        msgTarget: 'side'
    },

    items: [{
        columnWidth: 0.45,
        xtype: 'gridpanel',
        itemId: 'mappings-list',
        store: 'MappingStore',
        height: 400,
		tbar: {
	    	defaults: {
	            scale: 'small',
	            iconAlign:'left'
	        },
	    	items: [{
	            text: 'Borrar',
	            action: 'deleteMapping',
	            iconCls: 'icon-delete'
	        }]
	    },
        columns: [{
            text: 'Dispositivo',
            flex: 1,
            dataIndex: 'name'
        }, {
            text: 'Tag',
            flex: 1,
            dataIndex: 'tag'
        }, {
            text: 'Creado por',
            flex: 1,
            dataIndex: 'createdBy'
        }, {
            text: 'Fecha de creaci&oacute;n',
            flex: 1,
            dataIndex: 'creationDate'
        }]
    }, {
        columnWidth: 0.35,
        margin: '0 0 0 10',
        xtype: 'fieldset',
        title:'Mapeo de dispositivos',
        layout: 'anchor',
        defaultType: 'textfield',
        items: [{
            fieldLabel: 'Dispositivo',
            allowBlank: false,
            name: 'name'
        },{
            fieldLabel: 'Tag',
            allowBlank: false,
            name: 'tag'
        }, {
	        xtype: 'hiddenfield',
	        name: 'id'
    	},{
    		xtype: 'fieldset',
    		layout: 'hbox',
    		border: 'false',
		    layoutConf : {
		        pack: 'end',
		        padding: 10
		    },
		    items: [{
		        xtype: 'displayfield',
	    		labelSeparator: '&nbsp;',
		        fieldLabel: '&nbsp;',
		        value: '&nbsp;'
		    },{
		    	xtype: 'button',
		    	text: 'Actualizar',
		    	margin: '0 0 0 10',
		        formBind: true,
		        disabled: true,
		        action: 'update',
		        iconCls: 'icon-save'
		    },{
		    	xtype: 'button',
		    	text: 'Nuevo',
		    	margin: '0 0 0 10',
		        formBind: true,
		        disabled: true,
		        action: 'save',
		        iconCls: 'icon-add'
		    }]
		}]
	}]
});