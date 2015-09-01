Ext.define('App.controller.UserController', {
    extend: 'Ext.app.Controller',

//	init: function() {
//        this.control({
//        	'user-list': {
//        		select: this.onItemSelected
//        	}
//        });
//   },
    
    views: ['user.List'],
    stores: ['UserStore'],
    models: ['User']
    
});
