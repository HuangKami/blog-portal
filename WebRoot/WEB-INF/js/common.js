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

function zeroFirst(num){
    if (num < 10){
        return "0" + num;
    }
    else{
 return "" + num;        
}
}

Date.prototype.toLocaleString = function() {
    return this.getFullYear() + "-" + (this.getMonth() + 1) + "-" + this.getDate() 
    	+ " " + zeroFirst(this.getHours()) + ":" + zeroFirst(this.getMinutes());
};