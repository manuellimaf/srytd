Ext.define('App.store.Process', {
    extend: 'Ext.data.Store',

    model: 'App.model.Process',

    data: { items: []},

    proxy: {
        type: 'memory',
        reader: {
            type: 'json',
            rootProperty: 'items'
        }
    }
});
