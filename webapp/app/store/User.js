Ext.define('App.store.User', {
    extend: 'Ext.data.Store',

    model: 'App.model.User',
	autoLoad: {start: 0, limit: 20},
	pageSize: 20,
    proxy: {
        type: 'ajax',
        url: '/api/user',
        reader: {
            type: 'json',
            root: 'items',
            totalProperty: 'total'
        }
    }
});
