Ext.define('App.controller.MappingsController', {
    extend: 'Ext.app.Controller',
	requires: ['App.util.FormSubmit'],
	
	views: ['config.MappingsForm'],
    refs: [{
		selector: 'mappings-form',
		ref: 'mappingsForm'
	}],
	stores: ['MappingStore'],
    models: ['Mapping']
	
});