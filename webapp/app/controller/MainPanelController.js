Ext.define('App.controller.MainPanelController', {
    extend: 'Ext.app.Controller',
    
    init: function() {
        this.control({
			'body-panel': {
				afterrender: this.addMenu
			},
            'panel button[action=showProcesses]': {
                click: this.onShowProcesses
            },
            'panel button[action=showFieldValues]': {
                click: this.onShowFieldValues
            },
            'panel button[action=showConfig]': {
                click: this.onShowConfig
            },
            'panel button[action=logout]': {
                click: this.onLogout
            } 
        });
    },
    
    views: ["layout.BodyPanel"],
    refs: [{
		selector: 'body-panel',
		ref: 'bodyPanel'
	},{
		selector: '#header-menu',
		ref: 'headerMenu'
	}],
    
    addMenu: function() {
    	var role = localStorage.getItem('ROLE');
		var menuBar = this.getHeaderMenu();
		menuBar.add({
            text: 'Env&iacute;os',
            action: 'showProcesses',
            iconCls: 'icon-send'
        });
        if(role == 'ADMIN' || role == 'USER' ) {
	        menuBar.add({
	            text: 'Ingreso manual',
	            action: 'showFieldValues',
	            iconCls: 'icon-manual-value'
	        });
	        menuBar.add({
	            text: 'Configuraci&oacute;n',
	            action: 'showConfig',
	            iconCls: 'icon-config'
	        });
        }
        menuBar.add('->');
        menuBar.add({
            text: 'Salir',
            action: 'logout',
            iconCls: 'icon-logout'
        });
		
	},
    clearBody: function() {
    	var panel = this.getBodyPanel();
    	panel.removeAll();
    	return panel;
    },
    
    onShowProcesses: function() {
    	var body = this.clearBody();
    	body.add({xtype: 'process-panel'});
    },

    onShowFieldValues: function() {
    	var body = this.clearBody();
    	body.add({xtype: 'manual-values-form'});
    },

    onShowConfig: function() {
    	var body = this.clearBody();
    	body.add({xtype: 'config-panel'});
    }, 
    
    onLogout: function () {
        Ext.Ajax.request({
            url: '/api/auth/invalidate',
            method: 'POST',
          	success: function(response, options) {
		        location.href = 'login.html';
            },
            failure: function(response, options) {
	        	if(response.status <= 0) {
    	    		Ext.Msg.alert('Error', 'No es posible conectarse al servidor');
        		} else {
	                Ext.Msg.alert('Error ' + response.status, response.responseText);
	        	}
	        }
		}, this);
    }
});