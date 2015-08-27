Ext.define('App.store.MappedFieldValueStore', {
    extend: 'Ext.data.Store',

    model: 'App.model.MappedFieldValue',
	autoLoad: {start: 0, limit: 20},
	pageSize: 20,
	// TODO . el load tendría que ser por proceso
    proxy: {
        type: 'ajax',
        url: '/api/mapped-field-value',
        reader: {
            type: 'json',
            root: 'items',
            totalProperty: 'total'
        }
    }
});
