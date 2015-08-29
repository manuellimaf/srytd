Ext.define('App.view.process.List', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.process-list',

    title: 'Env&iacute;os',
	height: 500,
	border: false,
    
    store: 'Process',
    columns: [
        { text: 'C&oacute;digo',  dataIndex: 'id' },
        { text: 'Inicio', dataIndex: 'startDate', flex: 1 },
        { text: 'Iniciado por', dataIndex: 'startedBy', flex: 1 },
        { text: 'Corresponde a', dataIndex: 'startDate', flex: 1 },
        { text: 'Enviados', dataIndex: 'sentValues', flex: 1 },
        { text: 'No enviados', dataIndex: 'unsentValues', flex: 1 },
        { text: 'Estado actual', dataIndex: 'status', flex: 1 },
        { text: 'Estado final', dataIndex: 'finalStatus', flex: 1 }
    ],
    autoRender: true,
    tbar: {
    	defaults: {
            scale: 'small',
            iconAlign:'left'
        },
    	items: [{
            text: 'Forzar env&iacute;o',
            action: 'send',
            iconCls: 'add16'
        },'-',{
            text: 'Ver detalle',
            action: 'viewDetail',
            iconCls: 'add16'
        }]
    },
    dockedItems: [{
        xtype: 'pagingtoolbar',
        store: this.store,
        dock: 'bottom',
        displayInfo: true
    }]

});
