function headerInvoker(data) {
	var $element = $(data.html);
	$('#script_header').after($element);
}