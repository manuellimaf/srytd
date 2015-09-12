Ext.define('App.store.DeviceMappingStore', {
    extend: 'Ext.data.Store',

    model: 'App.model.DeviceMapping',
	autoLoad: true,
	proxy: {
        type: 'ajax',
		url: '/api/device-mapping',
        reader: {
            type: 'json'
        }
	}
});
