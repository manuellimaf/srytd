Ext.define('App.store.ProcessResultStore', {
    extend: 'Ext.data.Store',

    model: 'App.model.ProcessResult',
    proxy: {
        type: 'ajax',
        url: 'api/process/result',
        reader: {
            type: 'json'
        }
    }
});
