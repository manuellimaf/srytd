Ext.define('App.store.RoleStore', {
    extend: 'Ext.data.Store',

    model: 'App.model.Role',
	autoLoad: true,
	proxy: {
        type: 'ajax',
		url: 'api/user/all-roles',
        reader: {
            type: 'json'
        }
	}
});
