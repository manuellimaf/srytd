Ext.define('App.view.layout.BodyPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.body-panel',
    header: {
        xtype: 'box',
        id: 'header',
        region: 'north',
        html: '<h1> SRyTD</h1>',
        height: 30
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