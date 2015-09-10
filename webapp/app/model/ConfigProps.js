Ext.define('App.model.ConfigProps', {
    extend: 'Ext.data.Model',
    fields: ['companyCode', 'facilityCode', 'ip', 'port', 'ftpUser', 'ftpPassword', 'ftpType', 'alertEmails', 'notificationEmails']
});