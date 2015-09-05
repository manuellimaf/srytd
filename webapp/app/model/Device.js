Ext.define('App.model.Device', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'id',   type: 'int'}, 
        {name: 'name', type: 'string'}, 
        {name: 'tag', type: 'string'}
    ]
});