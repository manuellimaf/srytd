Ext.define('App.model.Process', {
    extend: 'Ext.data.Model',
    fields: [
    	{ name: 'id' }, 
    	{ name: 'startDate' }, 
    	{ name: 'startedBy' }, 
    	{ name: 'valuesFrom' }, 
    	{ name: 'sentValues', mapping: 'result.sentValues' },
    	{ name: 'status' }, 
    	{ name: 'finalStatus', mapping: 'result.status' }
    ]
});