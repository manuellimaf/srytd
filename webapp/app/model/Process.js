Ext.define('App.model.Process', {
    extend: 'Ext.data.Model',
    fields: ['id', 'startDate', 'startedBy', 'valuesFrom', 'sentValues', 'unsentValues', 'status']
});