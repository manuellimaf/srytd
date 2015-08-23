Ext.define('App.controller.ProcessController', {
    extend: 'Ext.app.Controller',

//	init: function() {
//        this.control({
//        	'mainlist': {
//        		select: this.onItemSelected
//        	}
//        });
//   },
    
    views: ['process.List'],
    stores: ['Process'],
    models: ['Process'],
    
    onItemSelected: function (sender, record) {
        Ext.Msg.confirm('Confirm', 'Are you sure?', 'onConfirm', this);
    },

    onConfirm: function (choice) {
        if (choice === 'yes') {
            //
        }
    }
});
