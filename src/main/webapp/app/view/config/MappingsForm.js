Ext.define('App.view.config.MappingsForm', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.mappings-list',

   	title: 'Mapeo de dispositivos',
    store: 'MappingStore',
    height: 520,
	tbar: {
    	defaults: {
            scale: 'small',
            iconAlign:'left'
        },
    	items: [{
            text: 'Nuevo',
            action: 'createMapping',
            iconCls: 'icon-add'
        },{
            text: 'Borrar',
            action: 'deleteMapping',
            iconCls: 'icon-delete'
        }]
    },
    selType: 'rowmodel',
    plugins: [
        Ext.create('Ext.grid.plugin.RowEditing', {
            clicksToEdit: 2,
            pluginId: 'row-editor',
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
	        { text: 'Dispositivo', width: 130, dataIndex: 'name', editor: { xtype: 'textfield', allowBlank: false } },
	        { text: 'C&oacute;digo', width: 130, dataIndex: 'code', editor: { xtype: 'textfield', allowBlank: false } },
	        { text: 'Offset<br/>mediciones [segs]', width: 110, dataIndex: 'timeOffset', editor: { xtype: 'numberfield', decimalPrecision: 0,  allowDecimals: false, allowBlank: false } },
	        { 
	        	text: 'Mapeo de tags',
	        	columns: [
					{ text: 'Vol. neto hoy [m3]',  dataIndex: 'volumen_neto_hoy', editor: 'textfield' },
					{ text: 'Vol. desplazado [m3]', width: 110,  dataIndex: 'volumen_desplazado', editor: 'textfield' },
					{ text: 'Vol. bruto acum. [m3]', width: 115,  dataIndex: 'volumen_bruto_acumulado', editor: 'textfield' },
					{ text: 'Vol. acum. 9300 [m3]', width: 110,  dataIndex: 'volumen_acumulado_9300', editor: 'textfield' },
					{ text: 'Vol. hoy 9300 [m3]',  dataIndex: 'volumen_hoy_9300', editor: 'textfield' },
					{ text: 'Temperatura [&ordm;C]',  dataIndex: 'temperatura', editor: 'textfield' },
					{ text: 'SH2 [%]',  dataIndex: 'sh2', editor: 'textfield' },
					{ text: 'Pulsos brutos',  dataIndex: 'pulsos_brutos', editor: 'textfield' },
					{ text: 'Presi&oacute;n [Kg/cm2]',  dataIndex: 'presion', editor: 'textfield' },
					{ text: 'Poder calor&iacute;fico [Kcal/m3]', width: 135,  dataIndex: 'poder_calorifico', editor: 'textfield' },
					{ text: 'N2 [%]',  dataIndex: 'n2', editor: 'textfield' },
					{ text: 'C6 [%]',  dataIndex: 'c6', editor: 'textfield' },
					{ text: 'MF',  dataIndex: 'mf', editor: 'textfield' },
					{ text: 'FCV',  dataIndex: 'fcv', editor: 'textfield' },
					{ text: 'Factor K [P/lt]',  dataIndex: 'factor_k', editor: 'textfield' },
					{ text: 'Densidad relativa',  dataIndex: 'densidad_relativa', editor: 'textfield' },
					{ text: 'CTSH',  dataIndex: 'ctsh', editor: 'textfield' },
					{ text: 'CTL',  dataIndex: 'ctl', editor: 'textfield' },
					{ text: 'CPL',  dataIndex: 'cpl', editor: 'textfield' },
					{ text: 'CO2',  dataIndex: 'co2', editor: 'textfield' },
					{ text: 'Caudal horario 9300 [m3/h]', width: 140,  dataIndex: 'caudal_horario_9300', editor: 'textfield' },
					{ text: 'Caudal horario [m3/h]', width: 115,  dataIndex: 'caudal_horario', editor: 'textfield' },
					{ text: 'C1 [%]',  dataIndex: 'c1', editor: 'textfield' },
					{ text: 'C2 [%]',  dataIndex: 'c2', editor: 'textfield' },
					{ text: 'C3 [%]',  dataIndex: 'c3', editor: 'textfield' },
					{ text: 'IC4 [%]',  dataIndex: 'ic4', editor: 'textfield' },
					{ text: 'NC4 [%]',  dataIndex: 'nc4', editor: 'textfield' },
					{ text: 'IC5 [%]',  dataIndex: 'ic5', editor: 'textfield' },
					{ text: 'NC5 [%]',  dataIndex: 'nc5', editor: 'textfield' },
					{ text: 'Altura l&iacute;quida [m]',  dataIndex: 'altura_liquida', editor: 'textfield' },
					{ text: '% agua',  dataIndex: 'porcentaje_agua', editor: 'textfield' },
					{ text: 'Vol. seco ult. transac. [m3]', width: 150,  dataIndex: 'volumen_seco', editor: 'textfield' },
					{ text: 'Inicio transac.',  dataIndex: 'inicio_transac', editor: 'textfield' },
					{ text: 'Fin transac.',  dataIndex: 'fin_transac', editor: 'textfield' },
					{ text: 'Vol. neto acum. [m3]', width: 110,  dataIndex: 'volumen_neto_acumulado', editor: 'textfield' },
					{ text: 'Densidad [g/cm3]',  dataIndex: 'densidad', editor: 'textfield' },
					{ text: 'Vol. bruto hoy [m3]', width: 105,  dataIndex: 'volumen_bruto_hoy', editor: 'textfield' },
			        { text: 'Creado por', dataIndex: 'createdBy' },
					{ text: 'Fecha de creaci&oacute;n', dataIndex: 'creationDate'}
	        	]
	        }
		]
	},
    dockedItems: [{
        xtype: 'pagingtoolbar',
        store: 'MappingStore',
        dock: 'bottom',
        displayInfo: true
    }]
});