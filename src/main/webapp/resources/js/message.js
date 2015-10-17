$('#message_form').submit(function (event) {
	event.preventDefault();
	alert( "Handler for .submit() called." );
});
$('#form_open').click(function () {
	$('#results_dialog').modal({backdrop: 'static', keyboard: false});
});
$('#close_results').click(function () {
	$('#results_dialog').modal('hide');
});