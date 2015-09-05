Ext.define('App.store.DeviceStore', {
    extend: 'Ext.data.Store',

    model: 'App.model.Device',
	autoLoad: true,
	proxy: {
        type: 'ajax',
		url: '/api/device',
        reader: {
            type: 'json'
        }
	}
});
