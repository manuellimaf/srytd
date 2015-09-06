Ext.define('App.view.config.UsersForm', {
    extend: 'Ext.form.Panel',
    alias: 'widget.users-form',

    xtype: 'form-grid',
   	title: 'Usuarios',
    
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
        itemId: 'users-list',
        store: 'UserStore',
        height: 400,
		tbar: {
	    	defaults: {
	            scale: 'small',
	            iconAlign:'left'
	        },
	    	items: [{
	            text: 'Deshabilitar',
	            action: 'disable',
	            iconCls: 'icon-delete'
	        },{
	            text: 'Habilitar',
	            action: 'enable',
	            iconCls: 'icon-enable'
	        }]
	    },
        columns: [{
            text: 'Usuario',
            flex: 1,
            dataIndex: 'username'
        }, {
            text: 'Rol',
            flex: 1,
            dataIndex: 'role'
        }, {
            text: 'Creado por',
            flex: 1,
            dataIndex: 'createdBy'
        }, {
            text: 'Fecha de creaci&oacute;n',
            flex: 1,
            dataIndex: 'dateCreated'
        }, {
            xtype: 'booleancolumn', 
            text: 'Habilitado?',
            trueText: 'Si',
            falseText: 'No', 
            flex: 1,
            dataIndex: 'enabled'
        }],
	    dockedItems: [{
	        xtype: 'pagingtoolbar',
	        store: 'UserStore',
	        dock: 'bottom',
	        displayInfo: true
	    }]
    }, {
        columnWidth: 0.35,
        margin: '0 0 0 10',
        xtype: 'fieldset',
        title:'Usuario',
        layout: 'anchor',
        defaultType: 'textfield',
        items: [{
            fieldLabel: 'Usuario',
            allowBlank: false,
            name: 'username'
        },{
            fieldLabel: 'Contrase&nacute;a',
            allowBlank: false,
            inputType: 'password',
            name: 'password'
        },{
            fieldLabel: 'Confirmar contrase&nacute;a',
            allowBlank: false,
            inputType: 'password',
            name: 'passwordConf'
        },{ 
    		fieldLabel: 'Rol', 
    		xtype: 'combobox', 
    		name: 'role',  
    		valueField: 'id',
    		displayField: 'name',
			editable: false,
			selectOnTab: true,
			store: 'RoleStore', 
			allowBlank: false
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