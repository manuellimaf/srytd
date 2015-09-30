Ext.define('App.store.ProcessStore', {
    extend: 'Ext.data.Store',

    model: 'App.model.Process',
	autoLoad: {start: 0, limit: 20},
	pageSize: 20,
	remoteSort: true,
    proxy: {
        type: 'ajax',
        url: 'api/process',
        reader: {
            type: 'json',
            root: 'items',
            totalProperty: 'total'
        }
    }
});
