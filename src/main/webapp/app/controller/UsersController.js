Ext.define('App.controller.UsersController', {
    extend: 'Ext.app.Controller',
	requires: ['App.util.FormSubmit'],
	
	init: function() {
        this.control('gridpanel#users-list', {
        	selectionchange: this.onSelectionChange
        });
        this.control('gridpanel#users-list button[action=disable]', {
    		click: this.disableUser
        });
        this.control('gridpanel#users-list button[action=enable]', {
    		click: this.enableUser
        });
        this.control('users-form button[action=update]', {
    		click: this.updateUser
        });
        this.control('users-form button[action=save]', {
    		click: this.createUser
        });
	},
	
	views: ['config.UsersForm'],
    refs: [{
		selector: 'users-form',
		ref: 'usersForm'
	},{
		selector: 'gridpanel#users-list',
		ref: 'gridPanel'
	}],
	stores: ['UserStore', 'RoleStore'],
    models: ['User', 'Role'],
	
	onSelectionChange: function(model, records) {
        var rec = records[0];
        if (rec) {
	    	var panel = this.getUsersForm();
			panel.getForm().loadRecord(rec);
	    	var form = panel.getForm();
			form.setValues([{id: 'passwordConf', value: rec.data.password}]);			
        }
	},
	
	disableUser: function() { this.changeState('disable') },
	enableUser: function() { this.changeState('enable') },
	changeState: function(action) {
	    var selected = this.getGridPanel().getSelectionModel().selected;
	    if(selected.getCount() > 0) {
		    var userId = selected.first().get('id');
            Ext.Ajax.request({
	            url: 'api/user/' + userId + '/' + action,
	            method: 'PUT',
	            headers: {
	              'Content-Type': 'application/json;charset=utf-8'
	          	},
	          	params: { 'enabled': false },
	          	success: function(response, options) {
				    var store = this.getStore('UserStore');
          			store.reload();
	                Ext.Msg.alert('Info', 'Operaci&oacute;n concretada con &eacute;xito');
	            },
	            failure: App.util.FormSubmit.failureHandler,
	            scope: this
			});
		}
	},
	
	createUser: function() {
	    var store = this.getStore('UserStore');
	    var form = this.getUsersForm().getForm();
	    if(form.isValid()) {
	    	App.util.FormSubmit.submit(form, 'api/user', store); 
		}
	},
	
	updateUser: function() {
	    var store = this.getStore('UserStore');
	    var form = this.getUsersForm().getForm();
	    if(form.isValid() && form.getValues().id) {
	    	App.util.FormSubmit.update(form, 'api/user', store); 
		}
	}
});