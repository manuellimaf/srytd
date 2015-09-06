Ext.define('App.util.FormSubmit', {
    statics: {
        submit: function (form, url, store) {
            Ext.Ajax.request({
	            url: url,
	            method: 'POST',
	            headers: {
	              'Content-Type': 'application/json;charset=utf-8'
	          	},
	          	params: Ext.JSON.encode(form.getValues()),
	          	success: function(response, options) {
	          		if(store)
	          			store.reload();
	                Ext.Msg.alert('Info', 'Operaci&oacute;n concretada con &eacute;xito');
	            },
	            failure: App.util.FormSubmit.failureHandler
			});
        },
        update: function (form, url, store) {
            Ext.Ajax.request({
	            url: url,
	            method: 'PUT',
	            headers: {
	              'Content-Type': 'application/json;charset=utf-8'
	          	},
	          	params: Ext.JSON.encode(form.getValues()),
	          	success: function(response, options) {
	          		if(store)
	          			store.reload();
	                Ext.Msg.alert('Info', 'Operaci&oacute;n concretada con &eacute;xito');
	            },
	            failure: App.util.FormSubmit.failureHandler
			});
        },
        delete: function (url, store) {
            Ext.Ajax.request({
	            url: url,
	            method: 'DELETE',
	          	success: function(response, options) {
	          		if(store)
	          			store.reload();
	                Ext.Msg.alert('Info', 'Operaci&oacute;n concretada con &eacute;xito');
	            },
	            failure: App.util.FormSubmit.failureHandler
			});
        },
        failureHandler: function(response, options) {
        	if(response.status <= 0) {
        		Ext.Msg.alert('Error', 'No es posible conectarse al servidor');
        	} else if(response.status == 450) {
        		Ext.Msg.alert('Error de usaurio', response.responseText);
        	} else if(response.status == 401) {
        		Ext.Msg.alert('No autorizado', response.responseText);
        	} else {
                Ext.Msg.alert('Error ' + response.status, response.responseText);
        	}
        }
    }
});