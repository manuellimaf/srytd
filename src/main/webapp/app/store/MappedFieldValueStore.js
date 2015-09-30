Ext.define('App.store.MappedFieldValueStore', {
    extend: 'Ext.data.Store',

    model: 'App.model.MappedFieldValue',
    proxy: {
        type: 'ajax',
        url: 'api/process/mapped-field-values',
        reader: {
            type: 'json',
            root: 'items',
            totalProperty: 'total'
        }
	},		
	pageSize: 15
});
