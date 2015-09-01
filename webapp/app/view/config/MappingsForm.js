Ext.define('App.view.config.MappingsForm', {
    extend: 'Ext.form.Panel',
    
    requires: [
        'Ext.grid.*',
        'Ext.form.*',
        'Ext.layout.container.Column'
    ],

    xtype: 'form-grid',
    alias: 'widget.mappings-form',

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
        store: 'MappingStore',
        height: 400,
        columns: [{
            text: 'Device',
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
            fieldLabel: 'Device',
            name: 'name'
        },{
            fieldLabel: 'Tag',
            name: 'tag'
        },{
    		xtype: 'container',
    		fieldLabel: '&nbsp;',
    		labelSeparator: '',
    		anchor: '-50',
    		layout: 'hbox',
		    layoutConf : {
		        pack: 'start'
		    },
		    items: [{
		    	text: 'Borrar',
		    	xtype: 'button',
		        formBind: true,
		        disabled: true,
		        action: 'delete',
		        iconCls: 'icon-delete'
		    },{
		    	text: 'Actualizar',
		    	xtype: 'button',
		        formBind: true,
		        disabled: true,
		        action: 'update',
		        iconCls: 'icon-save'
		    },{
		    	text: 'Agregar',
		    	xtype: 'button',
		        formBind: true,
		        disabled: true,
		        action: 'save',
		        iconCls: 'icon-add'
		    }]
		}]
	}]
});