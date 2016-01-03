jQuery.extend({
	handleError: function( s, xhr, status, e ) 		{
		// If a local callback was specified, fire it
		if ( s.error ) {
			s.error.call( s.context || s, xhr, status, e );
		}

		// Fire the global callback
		if ( s.global ) {
			(s.context ? jQuery(s.context) : jQuery.event).trigger( "ajaxError", [xhr, s, e] );
		}
	},
    createUploadIframe: function(uri) {
		//create frame
        var frameId = 'jUploadFrame';
        var iframeHtml = '<iframe id="' + frameId + '" name="' + frameId + '" style="position:absolute; top:-9999px; left:-9999px"';
		if(window.ActiveXObject) {
            if(typeof uri== 'boolean'){
				iframeHtml += ' src="' + 'javascript:false' + '"';
            }
            else if(typeof uri== 'string'){
				iframeHtml += ' src="' + uri + '"';
            }
		}
		iframeHtml += ' />';
		jQuery(iframeHtml).appendTo(document.body);
        return jQuery('#' + frameId).get(0);
    },
    createUploadForm: function(fileElementId, data) {
		//create form
		var formId = 'jUploadForm';
		var fileId = 'jUploadFile';
		var form = jQuery('<form  action="" method="POST" name="' + formId + '" id="' + formId + '" enctype="multipart/form-data"></form>');
		if(data) {
			for(var i in data) {
				jQuery('<input type="hidden" name="' + i + '" value="' + data[i] + '" />').appendTo(form);
			}
		}

		var oldElement = jQuery('#' + fileElementId);
		var newElement = jQuery(oldElement).clone();
		jQuery(oldElement).attr('id', fileId);
		jQuery(oldElement).before(newElement);
		jQuery(oldElement).appendTo(form);

		//set attributes
		jQuery(form).css('position', 'absolute');
		jQuery(form).css('top', '-1200px');
		jQuery(form).css('left', '-1200px');
		jQuery(form).appendTo('body');
		return form;
    },
    ajaxFileUpload: function(s) {
        // TODO introduce global settings, allowing the client to modify them for all requests, not only timeout
        s = jQuery.extend({}, jQuery.ajaxSettings, s);
		var form = jQuery.createUploadForm(s.fileElementId, (typeof(s.data) == 'undefined' ? false : s.data));
		var io = jQuery.createUploadIframe(s.secureuri);
		var frameId = 'jUploadFrame';
		var formId = 'jUploadForm';
        var requestDone = false;
        // Wait for a response to come back
        var uploadCallback = function(isTimeout) {
        	requestDone = true;
        }
        
        // Timeout checker
        if ( s.timeout > 0 ) {
            setTimeout(function(){
                // Check to see if the request is still happening
                if( !requestDone ) uploadCallback( "timeout" );
            }, s.timeout);
        }
        
        try {
			var form = jQuery('#' + formId);
			jQuery(form).attr('action', s.url);
			jQuery(form).attr('method', 'POST');
			jQuery(form).attr('target', frameId);
            if(form.encoding) {
				jQuery(form).attr('encoding', 'multipart/form-data');
            }
            else {
				jQuery(form).attr('enctype', 'multipart/form-data');
            }
            jQuery(form).submit();
        } catch(e) {
            jQuery.handleError(s, {}, null, e);
        }

		jQuery('#' + frameId).load(uploadCallback );
        return {abort: function () {}};
    },
    clearUploadFrame: function(){
		var frameId = 'jUploadFrame';
		var formId = 'jUploadForm';
		var io = jQuery('#' + frameId);
		var form = jQuery('#' + formId);
        io.unbind();
		io.remove();
		form.remove();
    }
})

