Ext.define('App.view.layout.BodyPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.body-panel',
    header: {
        xtype: 'box',
        id: 'header',
        region: 'north',
        html: '<img style="vertical-align: middle; margin-bottom: 5px; margin-left: 10px; margin-top: 3px;" src="logo.png"/><div style="display: inline-block; margin-bottom: 4px; margin-left: 15px; vertical-align: bottom; font-size: 15;font-weight: bold;">R318</div>',
        style: "background: none !important;"
    },
    tbar: {
    	defaults: {
            scale: 'medium',
            iconAlign:'left	'
        },
        itemId: 'header-menu'
    },
    items: {
    	xtype: 'process-panel'
    }
});