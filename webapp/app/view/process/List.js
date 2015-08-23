Ext.define('App.view.process.List', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.process-list',

    title: 'Env&iacute;os',

    store: 'Process',
    columns: [
        { text: 'C&oacute;digo',  dataIndex: 'id' },
        { text: 'Inicio', dataIndex: 'start-date', flex: 1 },
        { text: 'Fecha', dataIndex: 'date', flex: 1 },
        { text: 'Corresponde a', dataIndex: 'hour', flex: 1 },
        { text: 'Registros enviados', dataIndex: 'sent-values', flex: 1 },
        { text: 'Registros no enviados', dataIndex: 'unsent-values', flex: 1 },
        { text: 'Estado final', dataIndex: 'status', flex: 1 }
    ]

});
