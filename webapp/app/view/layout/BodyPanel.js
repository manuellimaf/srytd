Ext.define('App.view.layout.BodyPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.body-panel',
    title: 'SRyTD',
    tbar: {
    	defaults: {
            scale: 'medium',
            iconAlign:'top'
        },
    	items: [{
            text: 'Env&iacute;os',
            action: 'showProcesses',
            iconCls: 'add16'
        },{
            text: 'Mediciones',
            action: 'showFieldValues',
            iconCls: 'add16'
        },{
            text: 'Configuraci&oacute;n',
            action: 'showConfig',
            iconCls: 'add16'
        },'->',{
            text: 'Salir',
            iconCls: 'add16'
        }]
    },
    items: {
    	xtype: 'process-panel'
    }
});