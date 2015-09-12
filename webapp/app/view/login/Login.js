Ext.define('App.view.login.Login', {
    extend: 'Ext.window.Window',
    alias: 'widget.login',
    
    requires: [
        'Ext.form.Panel'
    ],
    
    bodyPadding: 10,
    title: 'R318 - Login',
    closable: false,
    autoShow: true, 
	height: 195,
	resizable: false,		    
    items: [{
    	xtype: 'image',
    	src: 'logo.png',
    	margin: '0 0 10 80'
    },{
        xtype: 'form',
        reference: 'form',
        frame: true,
        items: [{
            xtype: 'textfield',
            name: 'j_username',
            fieldLabel: 'Usuario',
            allowBlank: false
        }, {
            xtype: 'textfield',
            name: 'j_password',
            inputType: 'password',
            fieldLabel: 'Contrase&nacute;a',
            allowBlank: false
        }],
        buttons: [{
            text: 'Ingresar',
            action: 'doLogin',
            formBind: true
        }]
    }]
});