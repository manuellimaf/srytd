Ext.define('App.store.UserStore', {
    extend: 'Ext.data.Store',

    model: 'App.model.User',
	autoLoad: {start: 0, limit: 15},
	pageSize: 15,
	remoteSort: true,
    proxy: {
        type: 'ajax',
        url: 'api/user',
        reader: {
            type: 'json',
            root: 'items',
            totalProperty: 'total'
        }
    }
});
