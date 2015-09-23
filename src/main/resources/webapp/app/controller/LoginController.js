Ext.define('App.controller.LoginController', {
    extend: 'Ext.app.Controller',
	requires: ['App.util.FormSubmit'],
	
	init: function() {
        this.control({
            'login button[action=doLogin]': {
                click: this.onLoginClick
            }
        });
    },
    
	views: ["login.Login"],
	refs: [{
		selector: 'login',
		ref: 'loginView'
	},{
		selector: 'login form',
		ref: 'loginForm'
	}],
	
    onLoginClick: function() {
	    var form = this.getLoginForm();
	    if(form.isValid()) {
            Ext.Ajax.request({
	            url: '/api/auth',
	            method: 'POST',
	          	params: form.getValues(true),
	          	success: function(response, options) {

		            Ext.Ajax.request({
			            url: '/api/auth/role',
			          	success: function(response, options) {
			          		var role = response.responseText;
			          		localStorage.setItem('ROLE', role);
					        location.href = 'index.html';
					    }
					});
	            },
	            failure: function(response, options) {
		        	if(response.status == 401) {
		        		Ext.Msg.alert('No autorizado', 'Usuario/Contrase&nacute;a inv&aacute;lidos');
		        	} else {
		                Ext.Msg.alert('Error ' + response.status, response.responseText);
		        	}
		        }
			});
		}
    }
});