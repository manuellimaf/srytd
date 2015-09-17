Ext.define('App.view.manualFieldValue.ManualFieldValueForm', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.manual-values-list',

   	title: 'Ingreso de datos manual',
	store: 'ManualFieldValueStore',
    height: 520,
	tbar: {
		defaults: {
            scale: 'small',
            iconAlign:'left'
        },
    	items: [{
            text: 'Nuevo',
            action: 'createManualValue',
            iconCls: 'icon-add'
        },{
            text: 'Borrar',
            action: 'deleteManualValue',
            iconCls: 'icon-delete'
        }]
    },
    selType: 'rowmodel',
    plugins: [
        Ext.create('Ext.grid.plugin.RowEditing', {
            clicksToEdit: 2,
            pluginId: 'mfv-row-editor',
            autoCancel: true
        })
    ],
    columns: {
    	defaults : {
    		draggable: false,
    		sortable: false,
    		hideable: false
    	},
    	items: [
			{ text: 'Dispositivo',  dataIndex: 'deviceId', width: 150, 
				editor: {
					xtype: 'combobox',  
					valueField: 'name',
					displayField: 'name',
					editable: false,
					selectOnTab: true,
					store: 'DeviceMappingStore', 
					allowBlank: false
				} 
			},
			{ text: 'C&oacute;digo',  dataIndex: 'code' },
			{ text: 'Fecha de medici&oacute;n', dataIndex: 'valueDate', width: 110, 
				renderer: function(value) {
					if(typeof value == 'object') {
						return value ? Ext.Date.dateFormat(value, 'd/m/Y') : '';
					}
					return value;
				},
				editor: {
					xtype: 'datefield',
					allowBlank: false,
					format: 'd/m/Y'
				}
			},
			{ text: 'Hora', dataIndex: 'valueTime', width: 65, 
				renderer: function(value) {
					if(typeof value == 'object') {
						return value ? Ext.Date.dateFormat(value, 'H:i') : '';
					}
					return value;
				},
				editor: {
					xtype: 'timefield', 
					allowBlank: false,
					format: 'H:i'
				}
			},
			{ text: 'Tipo', width: 50,  dataIndex: 'valueType' },
			{ text: 'Vol. neto hoy [m3]',  dataIndex: 'volumen_neto_hoy', editor: { xtype: 'numberfield', decimalPrecision: 9 } },
			{ text: 'Vol. desplazado [m3]', width: 110,  dataIndex: 'volumen_desplazado', editor: { xtype: 'numberfield', decimalPrecision: 9 } },
			{ text: 'Vol. bruto acum. [m3]', width: 115,  dataIndex: 'volumen_bruto_acumulado', editor: { xtype: 'numberfield', decimalPrecision: 9 } },
			{ text: 'Vol. acum. 9300 [m3]', width: 110,  dataIndex: 'volumen_acumulado_9300', editor: { xtype: 'numberfield', decimalPrecision: 9 } },
			{ text: 'Vol. hoy 9300 [m3]',  dataIndex: 'volumen_hoy_9300', editor: { xtype: 'numberfield', decimalPrecision: 9 } },
			{ text: 'Temperatura [&ordm;C]',  dataIndex: 'temperatura', editor: { xtype: 'numberfield', decimalPrecision: 9 } },
			{ text: 'SH2 [%]',  dataIndex: 'sh2', editor: { xtype: 'numberfield', decimalPrecision: 9 } },
			{ text: 'Pulsos brutos',  dataIndex: 'pulsos_brutos', editor: { xtype: 'numberfield', decimalPrecision: 9 } },
			{ text: 'Presi&oacute;n [Kg/cm2]',  dataIndex: 'presion', editor: { xtype: 'numberfield', decimalPrecision: 9 } },
			{ text: 'Poder calor&iacute;fico [Kcal/m3]', width: 135,  dataIndex: 'poder_calorifico', editor: { xtype: 'numberfield', decimalPrecision: 9 } },
			{ text: 'N2 [%]',  dataIndex: 'n2', editor: { xtype: 'numberfield', decimalPrecision: 9 } },
			{ text: 'C6 [%]',  dataIndex: 'c6', editor: { xtype: 'numberfield', decimalPrecision: 9 } },
			{ text: 'MF',  dataIndex: 'mf', editor: { xtype: 'numberfield', decimalPrecision: 9 } },
			{ text: 'FCV',  dataIndex: 'fcv', editor: { xtype: 'numberfield', decimalPrecision: 9 } },
			{ text: 'Factor K [P/lt]',  dataIndex: 'factor_k', editor: { xtype: 'numberfield', decimalPrecision: 9 } },
			{ text: 'Densidad relativa',  dataIndex: 'densidad_relativa', editor: { xtype: 'numberfield', decimalPrecision: 9 } },
			{ text: 'CTSH',  dataIndex: 'ctsh', editor: { xtype: 'numberfield', decimalPrecision: 9 } },
			{ text: 'CTL',  dataIndex: 'ctl', editor: { xtype: 'numberfield', decimalPrecision: 9 } },
			{ text: 'CPL',  dataIndex: 'cpl', editor: { xtype: 'numberfield', decimalPrecision: 9 } },
			{ text: 'CO2',  dataIndex: 'co2', editor: { xtype: 'numberfield', decimalPrecision: 9 } },
			{ text: 'Caudal horario 9300 [m3/h]', width: 140,  dataIndex: 'caudal_horario_9300', editor: { xtype: 'numberfield', decimalPrecision: 9 } },
			{ text: 'Caudal horario [m3/h]', width: 115,  dataIndex: 'caudal_horario', editor: { xtype: 'numberfield', decimalPrecision: 9 } },
			{ text: 'C1 [%]',  dataIndex: 'c1', editor: { xtype: 'numberfield', decimalPrecision: 9 } },
			{ text: 'C2 [%]',  dataIndex: 'c2', editor: { xtype: 'numberfield', decimalPrecision: 9 } },
			{ text: 'C3 [%]',  dataIndex: 'c3', editor: { xtype: 'numberfield', decimalPrecision: 9 } },
			{ text: 'IC4 [%]',  dataIndex: 'ic4', editor: { xtype: 'numberfield', decimalPrecision: 9 } },
			{ text: 'NC4 [%]',  dataIndex: 'nc4', editor: { xtype: 'numberfield', decimalPrecision: 9 } },
			{ text: 'IC5 [%]',  dataIndex: 'ic5', editor: { xtype: 'numberfield', decimalPrecision: 9 } },
			{ text: 'NC5 [%]',  dataIndex: 'nc5', editor: { xtype: 'numberfield', decimalPrecision: 9 } },
			{ text: 'Altura l&iacute;quida [m]',  dataIndex: 'altura_liquida', editor: { xtype: 'numberfield', decimalPrecision: 9 } },
			{ text: '% agua',  dataIndex: 'porcentaje_agua', editor: { xtype: 'numberfield', decimalPrecision: 9 } },
			{ text: 'Vol. seco ult. transac. [m3]', width: 150,  dataIndex: 'volumen_seco', editor: { xtype: 'numberfield', decimalPrecision: 9 } },
			{ text: 'Inicio transac.',  dataIndex: 'inicio_transac', editor: { xtype: 'numberfield', decimalPrecision: 9 } },
			{ text: 'Fin transac.',  dataIndex: 'fin_transac', editor: { xtype: 'numberfield', decimalPrecision: 9 } },
			{ text: 'Vol. neto acum. [m3]', width: 110,  dataIndex: 'volumen_neto_acumulado', editor: { xtype: 'numberfield', decimalPrecision: 9 } },
			{ text: 'Densidad [g/cm3]',  dataIndex: 'densidad', editor: { xtype: 'numberfield', decimalPrecision: 9 } },
			{ text: 'Vol. bruto hoy [m3]', width: 105,  dataIndex: 'volumen_bruto_hoy', editor: { xtype: 'numberfield', decimalPrecision: 9 } },
			{ text: 'Registrado por',  dataIndex: 'createdBy' },
			{ text: 'F. registro',  dataIndex: 'dateCreated' }
	    ]
	},
    dockedItems: [{
        xtype: 'pagingtoolbar',
        store: 'ManualFieldValueStore',
        dock: 'bottom',
        displayInfo: true
    }]
});
