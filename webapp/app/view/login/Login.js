Ext.define('App.view.login.Login', {
    extend: 'Ext.window.Window',
    alias: 'widget.login',
    
    requires: [
        'Ext.form.Panel'
    ],
    
    bodyPadding: 10,
    title: 'Login Window',
    closable: false,
    autoShow: true, 
    
    items: {
        xtype: 'form',
        reference: 'form',
        items: [{
            xtype: 'textfield',
            name: 'username',
            fieldLabel: 'Username',
            allowBlank: false
        }, {
            xtype: 'textfield',
            name: 'password',
            inputType: 'password',
            fieldLabel: 'Password',
            allowBlank: false
        }],
        buttons: [{
            text: 'Login',
            action: 'doLogin',
            formBind: true
        }]
    }
});