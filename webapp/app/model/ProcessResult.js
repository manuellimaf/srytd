Ext.define('App.model.ProcessResult', {
    extend: 'Ext.data.Model',
    fields: ['status', 'unmappedDevices', 'errorId', 'errorDesc', 'fileName']
});