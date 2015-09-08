Ext.define('App.view.process.List', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.process-list',

    title: 'Env&iacute;os',
	border: false,
    autoScroll: true,
    store: 'ProcessStore',
    columns: [
        { text: 'C&oacute;digo',  dataIndex: 'id' },
        { text: 'Inicio', dataIndex: 'startDate', flex: 1 },
        { text: 'Iniciado por', dataIndex: 'startedBy', flex: 1 },
        { text: 'Corresponde a', dataIndex: 'valuesFrom', flex: 1 },
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
        itemId: 'process-list-toolbar'
    },
    dockedItems: [{
        xtype: 'pagingtoolbar',
        store: 'ProcessStore',
        dock: 'bottom',
        displayInfo: true
    }]
});
