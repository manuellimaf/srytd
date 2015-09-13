Ext.define('App.store.MappingStore', {
    extend: 'Ext.data.Store',

    model: 'App.model.Mapping',
	autoLoad: {start: 0, limit: 19},
	pageSize: 19,
	remoteSort: true,
    proxy: {
        type: 'ajax',
        url: '/api/mapping',
        reader: {
            type: 'json',
            root: 'items',
            totalProperty: 'total'
        }
    }
});
