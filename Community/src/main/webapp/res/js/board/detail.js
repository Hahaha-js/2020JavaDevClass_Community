var data = document.querySelector('#data')
var btnDelElem = document.querySelector('#btnDel')
if(btnDelElem) {
	btnDelElem.addEventListener('click', function() {
		if(confirm('삭제하시겠습니까?')) {
			ajax()
		}
	})
	
	function ajax () {
		var {pk, category} = data.dataset
		/*
		var pk = data.dataset.pk
		var category = data.dataset.category
		*/
		fetch(`/board/del/${pk}`, {
			method: 'delete'
		})
	}
}