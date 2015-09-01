Ext.define('App.view.config.MappingsForm', {
    extend: 'Ext.form.Panel',
    
    requires: [
        'Ext.grid.*',
        'Ext.form.*',
        'Ext.layout.container.Column'
    ],

    xtype: 'form-grid',
    alias: 'widget.mappings-form',

   	title: 'Mapeo de dispositivos',
    
    frame: true,
    bodyPadding: 5,
    layout: 'column',
    
    initComponent: function(){
        Ext.apply(this, {
            width: 880,
            fieldDefaults: {
                labelAlign: 'left',
                labelWidth: 90,
                anchor: '100%',
                msgTarget: 'side'
            },

            items: [{
                columnWidth: 0.65,
                xtype: 'gridpanel',
                store: 'MappingStore',
                height: 400,
                columns: [{
                    text: 'Device',
                    flex: 1,
                    dataIndex: 'deviceId'
                }, {
                    text: 'Tag',
                    width: 75,
                    dataIndex: 'tagName'
                }],
                listeners: {
                    scope: this,
                    selectionchange: this.onSelectionChange
                }
            }, {
                columnWidth: 0.35,
                margin: '0 0 0 10',
                xtype: 'fieldset',
                title:'Mapeo de dispositivos',
                layout: 'anchor',
                defaultType: 'textfield',
                items: [{
                    fieldLabel: 'Device',
                    name: 'deviceId'
                },{
                    fieldLabel: 'Tag',
                    name: 'tagName'
                }]
            }]
        });
        this.callParent();
    },
    
    onSelectionChange: function(model, records) {
        var rec = records[0];
        if (rec) {
            this.getForm().loadRecord(rec);
        }
    }
});