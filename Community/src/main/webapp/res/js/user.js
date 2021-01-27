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





