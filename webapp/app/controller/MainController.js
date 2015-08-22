Ext.define('App.controller.MainController', {
    extend: 'Ext.app.Controller',

	init: function() {
        this.control({
        	'mainlist': {
        		select: this.onItemSelected
        	}
        });
   },
    
    views: ['main.List'],
    stores: ['Personnel', 'MainStore'],
    models: ['Person'],
    
    onItemSelected: function (sender, record) {
        Ext.Msg.confirm('Confirm', 'Are you sure?', 'onConfirm', this);
    },

    onConfirm: function (choice) {
        if (choice === 'yes') {
            //
        }
    }, 
    
    onClickButton: function () {
        // Remove the localStorage key/value
        localStorage.removeItem('LoggedIn');

        // Remove Main View
        this.getView().destroy();

        // Add the Login Window
        Ext.create('login');
    }
});
