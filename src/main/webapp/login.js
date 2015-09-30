Ext.application({
    name: 'App',
    extend: 'Ext.app.Application',
    appFolder: 'app',
    views: [
        'App.view.login.Login'
    ],
    controllers: [
        'LoginController'
    ],
    launch: function () {
        Ext.create('App.view.login.Login');
    }
});
