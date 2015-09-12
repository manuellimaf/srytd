Ext.define('App.model.DeviceMapping', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'id',   type: 'int'}, 
        {name: 'name', type: 'string'}, 
        {name: 'code', type: 'string'}
    ]
});