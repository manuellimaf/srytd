Ext.define('App.controller.ProcessController', {
    extend: 'Ext.app.Controller',

    runner: null,
    task: null,

	init: function() {
        this.control({
	        'process-list': {
	        	afterrender: this.addToolbarButtons
	        },
	        'panel button[action=viewDetail]': {
	    		click: this.showDetail
	        }
	        ,'panel button[action=manualSend]': {
	    		click: this.manualSend
	        },
	        'manual-send button[action=startProcess]': {
	    		click: this.startProcess
	        },
	        'mapped-field-value-list': {
				beforeadd: this.loadFieldValueList,
	        	afterrender: this.addToolbar
			},
			'mapped-field-value-list button[action=resend]': {
				click: this.resend
			},
			'process-result': {
				beforerender: this.loadProcessResult
			},
			'process-result button[action=downloadFile]': {
	    		click: this.downloadFile
	        }
		});
		
		Ext.apply(this, {}, {
            runner: new Ext.util.TaskRunner(),
            ui: Ext.getBody(),
            task: {
                run: this.refreshGrid,
                interval: (1000 * 10),
                scope: this
            }
        });
        this.runner.start(this.task);
    },
    
    views: ['process.ProcessList', 'process.Result', 'mappedFieldValue.List', 'process.ManualSendView'],
    stores: ['ProcessStore', 'MappedFieldValueStore', 'ProcessResultStore'],
    models: ['Process'],
    refs: [{
		selector: 'process-list',
		ref: 'processList'
	},{
		selector: '#process-list-toolbar',
		ref: 'processListTb'
	},{
		selector: 'mapped-field-value-list',
		ref: 'mappedFieldValueList'
	},{
		selector: 'process-result',
		ref: 'processResult'
	},{
		selector: 'manual-send',
		ref: 'manualSendView'
	},{
		selector: '#mapped-values-toolbar',
		ref: 'mappedValueListTb'
	}],    
	addToolbarButtons: function() {
		var role = localStorage.getItem('ROLE');
		this.getProcessListTb().add({
            text: 'Ver detalle',
            action: 'viewDetail',
            iconCls: 'icon-detail'
        });
        
        if(role == 'ADMIN' || role == 'USER') {
	        this.getProcessListTb().add('->');
	        this.getProcessListTb().add({
	            text: 'Env&iacute;o manual',
	            action: 'manualSend',
	            iconCls: 'icon-manual-send'
	        });
		}
	},
	addToolbar: function() {
		var role = localStorage.getItem('ROLE');
        if(role == 'ADMIN' || role == 'USER') {
			this.getMappedValueListTb().add({
	            scale: 'small',
	            iconAlign:'left',
	            text: 'Reenviar todo',
	            action: 'resend',
	            iconCls: 'icon-resend'
	        });
		}
	},
    currentWindow: undefined,
    currentProcessId: undefined,
    showDetail: function () {
	    var selected = this.getProcessList().getSelectionModel().selected;
	    if(selected.getCount() > 0) {
		    var processId = selected.first().get('id');
	    
	    	if(this.currentWindow) {
	    		this.currentWindow.close();
	    	}
	    	this.currentProcessId = processId;
	        this.currentWindow = Ext.create('App.view.mappedFieldValue.Panel', {processId: processId});
			this.currentWindow.show();
	    }
    },
    
    manualSend: function () {
    	Ext.create('App.view.process.ManualSendView').show();
    },
    
    refreshGrid: function() {
    	console.log("Refresing grid");
    	var grid = this.getProcessList();
    	grid.view.loadMask.maskOnDisable = false;
		var emptyLoadMask = grid.view.loadMask.disable();
		this.getStore('ProcessStore').reload(function(){
            emptyLoadMask.enable();
        });
    },
    
    startProcess: function () {
    	var window = this.getManualSendView();
		var form = window.down('form').getForm();
		var store = this.getStore('ProcessStore');
		App.util.FormSubmit.submit(form, '/api/process/start', store);
		Ext.Msg.alert('Informaci&oacute;n', 'El proceso comenz&oacute; correctamente, el mismo puede demorar varios minutos.');
		window.close();
    },
    
    loadFieldValueList: function() {
    	var store = this.getStore('MappedFieldValueStore');
		store.proxy.extraParams = { processId: this.currentProcessId };
		store.load();
    },

    resend: function() {
    	Ext.Msg.confirm('Re enviar?', 
    		'Realmente desea enviar los datos correspondientes al proceso ' + this.currentProcessId + '? El mismo puede demorar varios minutos.',
		    function(resp) {
		    	if(resp == 'yes') {
			    	Ext.Ajax.request({
					    url: '/api/process/' + this.currentProcessId + '/resend',
					    method: 'POST',
					    success: function(response, opts) {
					        var id = Ext.decode(response.responseText);
					        Ext.Msg.alert('Proceso finalizado', 'Finaliz&oacute; correctamente el proceso de env&iacute;o de datos (nuevo id: ' + id +').');
					    },
					    failure: function(response, opts) {
					        Ext.Msg.alert('Error', 'Se produjo un error al realizar el env&iacute;o.');
					    }
					});
				}
		    },
		    this);
    },
    
    loadProcessResult: function() {
		var form = this.getProcessResult().getForm();
		this.getStore('ProcessResultStore').load({
			params: { processId: this.currentProcessId },
			callback: function(results, operation, success) {
				var data = results[0];
		        form.loadRecord(data);
		    }
		});
    },
    
    downloadFile: function() {
	    var downloadUrl = '/api/process/' + this.currentProcessId + '/file';
	
		var body = Ext.getBody();
		
		var frame = body.createChild({
			tag: 'iframe',
			cls: 'x-hidden',
			id: 'hiddenform-iframe',
			name: 'iframe'
		});
		
		var form = body.createChild({
			tag: 'form',
			cls: 'x-hidden',
			id: 'hiddenform-form',
			action: downloadUrl,
			target: 'iframe'
		});
		
		form.dom.submit();
    }
    
	
});
