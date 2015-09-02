Ext.define('App.view.manualFieldValue.ManualFieldValueForm', {
    extend: 'Ext.form.Panel',
    alias: 'widget.manual-values-form',

    xtype: 'form-grid',
   	title: 'Ingreso de datos manual',
    
    frame: true,
    bodyPadding: 5,
    
    fieldDefaults: {
        labelAlign: 'left',
        labelWidth: 90,
        msgTarget: 'side'
    },

    items: [{
        margin: '0 0 10 0',
        xtype: 'fieldset',
        title:'Carga manual',
        collapsible: true,
        layout: {
	        type: 'table',
	        columns: 3,
    	    tdAttrs: {
		        style: { padding: '0 10' }
		    }
	    },
	    defaults: {
	    	labelWidth: 150
	    },
	    defaultType: 'textfield',
        items: [
        	{ fieldLabel: 'Dispositivo', name: 'deviceId' },
        	{ fieldLabel: 'Tag', name: 'tag' },
        	{ fieldLabel: 'F. medici&oacute;n', name: 'timestamp' },
        	{ fieldLabel: 'Vol. neto hoy [M3]', name: 'volumen_neto_hoy' },
			{ fieldLabel: 'Vol. desplazado [M3]', name: 'volumen_desplazado' },
			{ fieldLabel: 'Vol. bruto acum. [M3]', name: 'volumen_bruto_acumulado' },
			{ fieldLabel: 'Vol. acum. 9300 [M3]', name: 'volumen_acumulado_9300' },
			{ fieldLabel: 'Vol. hoy 9300 [M3]', name: 'volumen_hoy_9300' },
			{ fieldLabel: 'Temperatura [&ordm;C]', name: 'temperatura' },
			{ fieldLabel: 'SH2 [%]',  name: 'sh2' },
			{ fieldLabel: 'Pulsos brutos',  name: 'pulsos_brutos' },
			{ fieldLabel: 'Presi&oacute;n [Kg/cm2]', name: 'presion' },
			{ fieldLabel: 'Poder calor&iacute;fico [Kcal/m3]', name: 'poder_calorifico' },
			{ fieldLabel: 'N2 [%]',  name: 'n2' },
			{ fieldLabel: 'C6 [%]',  name: 'c6' },
			{ fieldLabel: 'MF',  name: 'mf' },
			{ fieldLabel: 'FCV',  name: 'fcv' },
			{ fieldLabel: 'Factor K [P/lt]',  name: 'factor_k' },
			{ fieldLabel: 'Densidad relativa', name: 'densidad_relativa' },
			{ fieldLabel: 'CTSH',  name: 'ctsh' },
			{ fieldLabel: 'CTL',  name: 'ctl' },
			{ fieldLabel: 'CPL',  name: 'cpl' },
			{ fieldLabel: 'CO2',  name: 'co2' },
			{ fieldLabel: 'Caudal horario 9300 [m3/h]', name: 'caudal_horario_9300' },
			{ fieldLabel: 'Caudal horario [m3/h]', name: 'caudal_horario' },
			{ fieldLabel: 'C1 [%]',  name: 'c1' },
			{ fieldLabel: 'C2 [%]',  name: 'c2' },
			{ fieldLabel: 'C3 [%]',  name: 'c3' },
			{ fieldLabel: 'IC4 [%]',  name: 'ic4' },
			{ fieldLabel: 'NC4 [%]',  name: 'nc4' },
			{ fieldLabel: 'IC5 [%]',  name: 'ic5' },
			{ fieldLabel: 'NC5 [%]',  name: 'nc5' },
			{ fieldLabel: 'Altura l&iacute;quida [m]',  name: 'altura_liquida' },
			{ fieldLabel: '% agua',  name: 'porcentaje_agua' },
			{ fieldLabel: 'Vol. seco ult. transac. [m3]', name: 'volumen_seco' },
			{ fieldLabel: 'Inicio transac.',  name: 'inicio_transac' },
			{ fieldLabel: 'Fin transac.',  name: 'fin_transac' },
			{ fieldLabel: 'Vol. neto acum. [m3]', name: 'volumen_neto_acumulado' },
			{ fieldLabel: 'Densidad [g/cm3]',  name: 'densidad' },
			{ fieldLabel: 'Vol. bruto hoy [m3]', name: 'volumen_bruto_hoy' },
			{ xtype: 'hiddenfield', name: 'id' }
		]
	},{
        xtype: 'gridpanel',
        itemId: 'manual-value-list',
        store: 'ManualFieldValueStore',
        height: 300,
		tbar: {
	    	items: [{
	            text: 'Borrar',
	            action: 'deleteManualValue',
	            iconCls: 'icon-delete'
	        }]
	    },
		columns: {
		    	defaults : {
		    		draggable: false,
		    		sortable: false,
		    		hideable: false
		    	},
		    	items: [
					{ text: 'Dispositivo',  dataIndex: 'deviceId' },
					{ text: 'Tag',  dataIndex: 'tag' },
					{ text: 'F. medici&oacute;n',  dataIndex: 'timestamp' },
					{ text: 'Tipo', width: 50,  dataIndex: 'valueType' },
					{ text: 'Vol. neto hoy [M3]',  dataIndex: 'volumen_neto_hoy' },
					{ text: 'Vol. desplazado [M3]', width: 110,  dataIndex: 'volumen_desplazado' },
					{ text: 'Vol. bruto acum. [M3]', width: 115,  dataIndex: 'volumen_bruto_acumulado' },
					{ text: 'Vol. acum. 9300 [M3]', width: 110,  dataIndex: 'volumen_acumulado_9300' },
					{ text: 'Vol. hoy 9300 [M3]',  dataIndex: 'volumen_hoy_9300' },
					{ text: 'Temperatura [&ordm;C]',  dataIndex: 'temperatura' },
					{ text: 'SH2 [%]',  dataIndex: 'sh2' },
					{ text: 'Pulsos brutos',  dataIndex: 'pulsos_brutos' },
					{ text: 'Presi&oacute;n [Kg/cm2]',  dataIndex: 'presion' },
					{ text: 'Poder calor&iacute;fico [Kcal/m3]', width: 135,  dataIndex: 'poder_calorifico' },
					{ text: 'N2 [%]',  dataIndex: 'n2' },
					{ text: 'C6 [%]',  dataIndex: 'c6' },
					{ text: 'MF',  dataIndex: 'mf' },
					{ text: 'FCV',  dataIndex: 'fcv' },
					{ text: 'Factor K [P/lt]',  dataIndex: 'factor_k' },
					{ text: 'Densidad relativa',  dataIndex: 'densidad_relativa' },
					{ text: 'CTSH',  dataIndex: 'ctsh' },
					{ text: 'CTL',  dataIndex: 'ctl' },
					{ text: 'CPL',  dataIndex: 'cpl' },
					{ text: 'CO2',  dataIndex: 'co2' },
					{ text: 'Caudal horario 9300 [m3/h]', width: 140,  dataIndex: 'caudal_horario_9300' },
					{ text: 'Caudal horario [m3/h]', width: 115,  dataIndex: 'caudal_horario' },
					{ text: 'C1 [%]',  dataIndex: 'c1' },
					{ text: 'C2 [%]',  dataIndex: 'c2' },
					{ text: 'C3 [%]',  dataIndex: 'c3' },
					{ text: 'IC4 [%]',  dataIndex: 'ic4' },
					{ text: 'NC4 [%]',  dataIndex: 'nc4' },
					{ text: 'IC5 [%]',  dataIndex: 'ic5' },
					{ text: 'NC5 [%]',  dataIndex: 'nc5' },
					{ text: 'Altura l&iacute;quida [m]',  dataIndex: 'altura_liquida' },
					{ text: '% agua',  dataIndex: 'porcentaje_agua' },
					{ text: 'Vol. seco ult. transac. [m3]', width: 150,  dataIndex: 'volumen_seco' },
					{ text: 'Inicio transac.',  dataIndex: 'inicio_transac' },
					{ text: 'Fin transac.',  dataIndex: 'fin_transac' },
					{ text: 'Vol. neto acum. [m3]', width: 110,  dataIndex: 'volumen_neto_acumulado' },
					{ text: 'Densidad [g/cm3]',  dataIndex: 'densidad' },
					{ text: 'Vol. bruto hoy [m3]', width: 105,  dataIndex: 'volumen_bruto_hoy' }
			    ]
		    },
    	    dockedItems: [{
	        xtype: 'pagingtoolbar',
	        store: 'ManualFieldValueStore',
	        dock: 'bottom',
	        displayInfo: true
	    }]
    }]
});
