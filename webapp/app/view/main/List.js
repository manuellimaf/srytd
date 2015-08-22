Ext.define('App.view.main.List', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.mainlist',

    title: 'Personnel',

    store: 'Personnel',
    columns: [
        { text: 'Name',  dataIndex: 'name' },
        { text: 'Email', dataIndex: 'email', flex: 1 },
        { text: 'Phone', dataIndex: 'phone', flex: 1 }
    ]

});
