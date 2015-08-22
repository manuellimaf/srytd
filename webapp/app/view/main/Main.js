//Ext.require('Ext.container.Viewport');

Ext.define('App.view.main.Main', {
    extend: 'Ext.tab.Panel',
	alias: 'widget.app-main',

    requires: [
        'Ext.window.MessageBox',

        //'App.view.main.MainModel',
        'App.view.main.List'
    ],

    //model: '',
    layout: 'fit',

    ui: 'navigation',

    //tabBarHeaderPosition: 1,
    //titleRotation: 0,
    //tabRotation: 0,

    header: {
        layout: {
            align: 'stretchmax'
        },
        title: {
            bind: {
                text: 'SOME NAME'
            },
            flex: 0
        },
        iconCls: 'fa-th-list'
    },

    tabBar: {
        flex: 1,
        layout: {
            align: 'stretch',
            overflowHandler: 'none'
        }
    },

    defaults: {
        bodyPadding: 20
    },

    items: [{
        title: 'Home',
        iconCls: 'fa-home',
        // The following grid shares a store with the classic version's grid as well!
        items: [{
            xtype: 'mainlist'
        }]
    }, {
        title: 'Users',
        iconCls: 'fa-user',
        bind: {
            html: 'loremIpsum'
        }
    }, {
        title: 'Groups',
        iconCls: 'fa-users',
        bind: {
            html: 'loremIpsum'
        }
    }, {
        title: 'Settings',
        iconCls: 'fa-cog',
        bind: {
            html: 'loremIpsum'
        }
    }]
});
