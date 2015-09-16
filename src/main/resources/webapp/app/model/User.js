Ext.define('App.model.User', {
    extend: 'Ext.data.Model',
    fields: ['id', 'username', 'role', 'roleDesc', 'enabled', 'password', 'passwordConf', 'dateCreated', 'createdBy']
});