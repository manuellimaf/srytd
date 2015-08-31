Ext.define('App.view.config.ConfigPanel', {
    extend: 'Ext.tab.Panel',
    alias: 'widget.config-panel',
	
	title: 'Configuraci&oacute;n',
	
	items: [{
        xtype: 'user-list'
    },{
    	title: 'Mapeo de dispositivos',
        html: 'Mapeos'
    },{
        xtype: 'config-props',
        colspan: 2
    }]
});