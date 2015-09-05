Ext.define('App.view.config.ConfigPanel', {
    extend: 'Ext.tab.Panel',
    alias: 'widget.config-panel',
	
	title: 'Configuraci&oacute;n',
	
	items: [
		{ xtype: 'mappings-form' },
		{ xtype: 'users-form' },
		{ xtype: 'config-props' }
    ]
});