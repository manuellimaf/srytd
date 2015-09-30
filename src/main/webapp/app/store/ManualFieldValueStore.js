Ext.define('App.store.ManualFieldValueStore', {
    extend: 'Ext.data.Store',

    model: 'App.model.ManualFieldValue',
	pageSize: 15,
	autoLoad: true,
    proxy: {
        type: 'ajax',
        url: 'api/manual-field-values',
        reader: {
            type: 'json',
            root: 'items',
            totalProperty: 'total'
        }
	}
});
