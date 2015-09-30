Ext.define('App.store.ConfigPropsStore', {
    extend: 'Ext.data.Store',

    model: 'App.model.ConfigProps',
	autoLoad: true,
    proxy: {
        type: 'ajax',
        url: 'api/config',
        reader: {
            type: 'json'
        }
    }
});
