function loadingActive(type) {
	$(".loader")[0].className = "loader active";
	$("." + type)[0].className = type + " none";
}

function loadingUnactive(type) {
	$(".loader")[0].className = "loader";
	$("." + type)[0].className = type;
}

function loadingActiveById(id) {
	$(".loader")[0].className = "loader active";
	$("#" + id).hide();
}

function loadingUnactiveById(id) {
	$(".loader")[0].className = "loader";
	$("#" + id).show();
}
