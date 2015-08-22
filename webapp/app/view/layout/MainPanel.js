Ext.define('App.view.layout.MainPanel', {
    extend: 'Ext.container.Viewport',
    layout: 'fit',

    items: [{
    	xtype: 'panel',
        title: 'Title',
        html: 'My App goes here'
	}]

});