Ext.define('App.view.user.List', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.user-list',

    title: 'Usuarios',
	layout: 'fit',    
    store: 'UserStore',
    columns: [
        { text: 'Usuario',  dataIndex: 'username' },
        { text: 'Rol', dataIndex: 'role', flex: 1 },
        { text: 'Activo?', dataIndex: 'active', flex: 1 },
        { text: 'Contrase&nacute;a', dataIndex: 'password', flex: 1 }
    ],
    autoRender: true,
    tbar: {
    	defaults: {
            scale: 'small',
            iconAlign:'left'
        },
    	items: [{
            text: 'Agregar',
            action: 'createUser',
            iconCls: 'add16'
        },{
            text: 'Dar de baja',
            action: 'deactivateUser',
            iconCls: 'add16'
        }]
    }

});
