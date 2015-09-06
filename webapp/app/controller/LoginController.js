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
			        location.href = 'index.html';
	            }
			});
		}
    }
});