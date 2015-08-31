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
    	items: [{
            text: 'Env&iacute;os',
            action: 'showProcesses',
            iconCls: 'icon-send'
        },{
            text: 'Ingreso manual',
            action: 'showFieldValues',
            iconCls: 'icon-manual-value'
        },{
            text: 'Configuraci&oacute;n',
            action: 'showConfig',
            iconCls: 'icon-config'
        },'->',{
            text: 'Salir',
            action: 'logout',
            iconCls: 'icon-logout'
        }]
    },
    items: {
    	xtype: 'process-panel'
    }
});