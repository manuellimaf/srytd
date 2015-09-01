Ext.define('App.view.mappedFieldValue.List', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.mapped-field-value-list',

	store: 'MappedFieldValueStore',
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
			{ text: 'Calif.', width: 50, dataIndex: 'volumen_neto_hoy_q' },
			{ text: 'Vol. desplazado [M3]', width: 110,  dataIndex: 'volumen_desplazado' },
			{ text: 'Calif.', width: 50,  dataIndex: 'volumen_desplazado_q' },
			{ text: 'Vol. bruto acum. [M3]', width: 115,  dataIndex: 'volumen_bruto_acumulado' },
			{ text: 'Calif.', width: 50,  dataIndex: 'volumen_bruto_acumulado_q' },
			{ text: 'Vol. acum. 9300 [M3]', width: 110,  dataIndex: 'volumen_acumulado_9300' },
			{ text: 'Calif.', width: 50,  dataIndex: 'volumen_acumulado_9300_q' },
			{ text: 'Vol. hoy 9300 [M3]',  dataIndex: 'volumen_hoy_9300' },
			{ text: 'Calif.', width: 50,  dataIndex: 'volumen_hoy_9300_q' },
			{ text: 'Temperatura [&ordm;C]',  dataIndex: 'temperatura' },
			{ text: 'Calif.', width: 50,  dataIndex: 'temperatura_q' },
			{ text: 'SH2 [%]',  dataIndex: 'sh2' },
			{ text: 'Calif.', width: 50,  dataIndex: 'sh2_q' },
			{ text: 'Pulsos brutos',  dataIndex: 'pulsos_brutos' },
			{ text: 'Calif.', width: 50,  dataIndex: 'pulsos_brutos_q' },
			{ text: 'Presi&oacute;n [Kg/cm2]',  dataIndex: 'presion' },
			{ text: 'Calif.', width: 50,  dataIndex: 'presion_q' },
			{ text: 'Poder calor&iacute;fico [Kcal/m3]', width: 135,  dataIndex: 'poder_calorifico' },
			{ text: 'Calif.', width: 50,  dataIndex: 'poder_calorifico_q' },
			{ text: 'N2 [%]',  dataIndex: 'n2' },
			{ text: 'Calif.', width: 50,  dataIndex: 'n2_q' },
			{ text: 'C6 [%]',  dataIndex: 'c6' },
			{ text: 'Calif.', width: 50,  dataIndex: 'c6_q' },
			{ text: 'MF',  dataIndex: 'mf' },
			{ text: 'Calif.', width: 50,  dataIndex: 'mf_q' },
			{ text: 'FCV',  dataIndex: 'fcv' },
			{ text: 'Calif.', width: 50,  dataIndex: 'fcv_q' },
			{ text: 'Factor K [P/lt]',  dataIndex: 'factor_k' },
			{ text: 'Calif.', width: 50,  dataIndex: 'factor_k_q' },
			{ text: 'Densidad relativa',  dataIndex: 'densidad_relativa' },
			{ text: 'Calif.', width: 50,  dataIndex: 'densidad_relativa_q' },
			{ text: 'CTSH',  dataIndex: 'ctsh' },
			{ text: 'Calif.', width: 50,  dataIndex: 'ctsh_q' },
			{ text: 'CTL',  dataIndex: 'ctl' },
			{ text: 'Calif.', width: 50,  dataIndex: 'ctl_q' },
			{ text: 'CPL',  dataIndex: 'cpl' },
			{ text: 'Calif.', width: 50,  dataIndex: 'cpl_q' },
			{ text: 'CO2',  dataIndex: 'co2' },
			{ text: 'Calif.', width: 50,  dataIndex: 'co2_q' },
			{ text: 'Caudal horario 9300 [m3/h]', width: 140,  dataIndex: 'caudal_horario_9300' },
			{ text: 'Calif.', width: 50,  dataIndex: 'caudal_horario_9300_q' },
			{ text: 'Caudal horario [m3/h]', width: 115,  dataIndex: 'caudal_horario' },
			{ text: 'Calif.', width: 50,  dataIndex: 'caudal_horario_q' },
			{ text: 'C1 [%]',  dataIndex: 'c1' },
			{ text: 'Calif.', width: 50,  dataIndex: 'c1_q' },
			{ text: 'C2 [%]',  dataIndex: 'c2' },
			{ text: 'Calif.', width: 50,  dataIndex: 'c2_q' },
			{ text: 'C3 [%]',  dataIndex: 'c3' },
			{ text: 'Calif.', width: 50,  dataIndex: 'c3_q' },
			{ text: 'IC4 [%]',  dataIndex: 'ic4' },
			{ text: 'Calif.', width: 50,  dataIndex: 'ic4_q' },
			{ text: 'NC4 [%]',  dataIndex: 'nc4' },
			{ text: 'Calif.', width: 50,  dataIndex: 'nc4_q' },
			{ text: 'IC5 [%]',  dataIndex: 'ic5' },
			{ text: 'Calif.', width: 50,  dataIndex: 'ic5_q' },
			{ text: 'NC5 [%]',  dataIndex: 'nc5' },
			{ text: 'Calif.', width: 50,  dataIndex: 'nc5_q' },
			{ text: 'Altura l&iacute;quida [m]',  dataIndex: 'altura_liquida' },
			{ text: 'Calif.', width: 50,  dataIndex: 'altura_liquida_q' },
			{ text: '% agua',  dataIndex: 'porcentaje_agua' },
			{ text: 'Calif.', width: 50,  dataIndex: 'porcentaje_agua_q' },
			{ text: 'Vol. seco ult. transac. [m3]', width: 150,  dataIndex: 'volumen_seco' },
			{ text: 'Calif.', width: 50,  dataIndex: 'volumen_seco_q' },
			{ text: 'Inicio transac.',  dataIndex: 'inicio_transac' },
			{ text: 'Calif.', width: 50,  dataIndex: 'inicio_transac_q' },
			{ text: 'Fin transac.',  dataIndex: 'fin_transac' },
			{ text: 'Calif.', width: 50,  dataIndex: 'fin_transac_q' },
			{ text: 'Vol. neto acum. [m3]', width: 110,  dataIndex: 'volumen_neto_acumulado' },
			{ text: 'Calif.', width: 50,  dataIndex: 'volumen_neto_acumulado_q' },
			{ text: 'Densidad [g/cm3]',  dataIndex: 'densidad' },
			{ text: 'Calif.', width: 50,  dataIndex: 'densidad_q' },
			{ text: 'Vol. bruto hoy [m3]', width: 105,  dataIndex: 'volumen_bruto_hoy' },
			{ text: 'Calif.', width: 50,  dataIndex: 'volumen_bruto_hoy_q' }
	    ]
    },
    autoRender: true,
    tbar: {
    	items: [{
            scale: 'small',
            iconAlign:'left',
            text: 'Reenviar todo',
            action: 'resend',
            iconCls: 'icon-resend'
        }]
    },
    dockedItems: [{
        xtype: 'pagingtoolbar',
        store: this.store,
        dock: 'bottom',
        displayInfo: true
    }],
    
    qRenderer: function(val) {
        if (val === 'ok') {
            return '<span style="color:green;">' + val + '</span>';
        } else {
            return '<span style="color:red;">' + val + '</span>';
        }
        return val;
    }

});
