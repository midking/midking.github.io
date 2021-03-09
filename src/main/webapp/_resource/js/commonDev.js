/**
 * 개발 공통 스크립트
 */

//쿠키 세팅
function setCookie(name, value, expiredays) {
	
	var todayDate = new Date();
	todayDate.setDate(todayDate.getDate() + expiredays);
	document.cookie = name + "=" + escape(value) + "; path=/; expires=" + todayDate.toGMTString() + ";";
}

//쿠키 가져오기
function getCookie(name){
    var nameOfCookie = name + "=";
    var x = 0;
    while(x <= document.cookie.length){
		var y = (x + nameOfCookie.length);
		if(document.cookie.substring(x, y) == nameOfCookie){
			if ((endOfCookie = document.cookie.indexOf(";", y)) == -1)
				endOfCookie = document.cookie.length;
			return unescape(document.cookie.substring(y, endOfCookie));
		}
		x = document.cookie.indexOf(" ", x) + 1;
		if (x == 0) break;
    }
    return "";
}

//쿠키 삭제
function deleteCookie(name){
	document.cookie = name + '=; expires=Thu, 01 Jan 1999 00:00:10 GMT;';
}