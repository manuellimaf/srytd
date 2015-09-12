Ext.define('App.model.ProcessResult', {
    extend: 'Ext.data.Model',
    fields: [
    	{ name: 'status' }, 
    	{ name: 'sentValues' }, 
    	{ name: 'fileName' },
    	{ name: 'errorId', mapping: 'error.identifier' }, 
    	{ name: 'errorMessage', mapping: 'error.message' }, 
    	{ name: 'errorDesc', mapping: 'error.description' } 
    ]
});