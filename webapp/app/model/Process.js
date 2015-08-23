Ext.define('App.model.Process', {
    extend: 'Ext.data.Model',
    fields: ['id', 'start-date', 'date', 'hour', 'sent-values', 'unsent-values', 'status']
});