var	xhr;

$('#message_form').submit(function (event) {
	event.preventDefault();
	process();
});
$('#close_results').click(function () {
	stopLoad();
});

function process() {
	$('#waiting_body').show();
	$('#results_body').html('');
	$('#results_dialog').modal({backdrop: 'static', keyboard: false});
	var result,
		token = $("meta[name='_csrf']").attr("content"),
		header = $("meta[name='_csrf_header']").attr("content"),
		reqHeaders = {};
	reqHeaders[header] = token;
	xhr = $.ajax({
		headers: reqHeaders,
		url: 'add',
		method: 'POST',
		dataType: 'json',
		data: $('#message_form').serialize(),
		timeout: 60000,
		error: function(jqXHR, textStatus, errorThrown) {
			if (textStatus != 'abort')
				result = 'Произошла ошибка';
			console.log(errorThrown);
		},
		success: function(data, textStatus, jqXHR) {
			console.log(data);
			result = data;
		},
		complete: function(jqXHR, textStatus) {
			$('#waiting_body').hide();
			if (result.id) {
				$('#results_body').append('<div class="alert alert-success">Сообщение успешно зарегистрировано с номером № <b>' + result.id + '</b></div>');
				$('#message_form').trigger('reset');
			} else if (result)
				$('#results_body').append('<div class="alert alert-danger">Не удалось зарегистрировать сообщение. Сервис временно не доступен.<br>Обратитесь к администратору для уточнения причин</div>');
		}
	});
}

function stopLoad() {
	stopRequest();
	$('#results_dialog').modal('hide');
}

function stopRequest() {
	if (xhr)
		xhr.abort();
}