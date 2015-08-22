Ext.define('App.controller.LoginController', {
    extend: 'Ext.app.Controller',
	
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
	}],
	
    onLoginClick: function() {

        // This would be the ideal location to verify the user's credentials via
        // a server-side lookup. We'll just move forward for the sake of this example.
		alert("perform ajax validation!!");
		
        // Set the localStorage value to true
        localStorage.setItem("LoggedIn", true);

        // Remove Login Window
        this.getLoginView().destroy();

		console.log("Create app-main");
        // Add the main view to the viewport
        Ext.create('App.view.layout.MainPanel');

    }
});