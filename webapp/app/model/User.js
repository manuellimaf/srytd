Ext.define('App.model.User', {
    extend: 'Ext.data.Model',
    fields: ['id', 'username', 'role', 'enabled', 'password', 'passwordConf', 'dateCreated', 'createdBy']
});