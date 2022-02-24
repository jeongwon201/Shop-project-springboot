let header = {
	
	init: function() {
		if(document.cookie) {
			$(".login").css('display', 'block');
			$(".logout").css('display', 'none');
		} else {
			$(".login").css('display', 'none');
			$(".logout").css('display', 'block');
		}
	},
	
	getCookie: function() {
		cName = 'AccessToken' + '=';
		var cookieData = document.cookie;
		var start = cookieData.indexOf(cName);
		var cValue = '';
		if(start != -1) {
			start += cName.length;
			var end = cookieData.indexOf(';', start);
			if(end == -1)
				end = cookieData.length;
		cValue = cookieData.substring(start, end);
		}
		return unescape(cValue);
	}
}

header.init();