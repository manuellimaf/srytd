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
	          		store.reload();
	                Ext.Msg.alert('Info', 'Operaci&oacute;n concretada con &eacute;xito');
	            },
	            failure: function(response, options) {
	            	if(response.status <= 0) {
	            		Ext.Msg.alert('Error', 'No es posible conectarse al servidor');
	            	} else if(response.status == 450) {
	            		// TODO - user exception
	            	} else {
		                Ext.Msg.alert('Error ' + response.status, response.responseText);
	            	}
	            }
			});
        }
    }
});