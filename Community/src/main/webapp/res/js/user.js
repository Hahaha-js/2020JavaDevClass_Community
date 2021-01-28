function chkItem(ele, nm) {
	if(!ele.value) {
		alert(`${nm}을(를) 작성해 주세요.`);
		ele.focus();		
		return true;	
	}
}

function chkFrm() {
	var frm = document.querySelector('#frm');
	
	if(chkItem(frm.userId, 'Id') 
		|| chkItem(frm.userPw, 'Pw') 
		|| chkItem(frm.nm, '이름')) {
		return false;
	} else if(frm.userPw.value !== frm.userPwRe.value) {
		alert('비밀번호를 확인해 주세요.');
		frm.userPw.focus();
		return false;
	}
}

var loginBtnElem = document.querySelector('#loginBtn')
if(loginBtnElem) {
	var frmElem = document.querySelector('#frm')
	var userIdElem = frmElem.userId
	var userPwElem = frmElem.userPw
	var errMsgElem = document.querySelector('#errMsg')
	function ajax() {
		if(userIdElem.value === '') {
			alert('아이디를 입력해 주세요')
			return
		} else if(userPwElem.value === '') {
			alert('비밀번호를 입력해 주세요')
			return
		}
		var param = {
			userId: userIdElem.value,
			userPw: userPwElem.value
		}
		
		fetch('/user/login', {
			method: 'POST',
			headers: {
            	'Content-Type': 'application/json',
        	},
			body: JSON.stringify(param)
		}).then(function(res) {
			return res.json()
		}).then(function(myJson) {
			console.log(myJson)
		})
	}
	loginBtnElem.addEventListener('click', ajax)
}


//아이디 체크
var chkIdBtnElem = document.querySelector('#chkIdBtn')
if(chkIdBtnElem) {

	function ajax () {
		var frmElem = document.querySelector('#frm')
		var userIdElem = frmElem.userId
		var userIdValue = userIdElem.value
		var idChkMsgElem = frmElem.querySelector('#idChkMsg')
	
		fetch(`/user/chkId/${userIdValue}`)
			.then(function (res) {
				return res.json()
			})
			.then(function(myJson) {
				console.log(myJson);
				if(myJson.result === 1) {
					idChkMsgElem.innerText = '중복된 아이디가 있습니다.';
				} else {
					idChkMsgElem.innerText = '사용할 수 있는 아이디 입니다.';
				}
			})
	}
	
	chkIdBtnElem.addEventListener('click', ajax)
}















