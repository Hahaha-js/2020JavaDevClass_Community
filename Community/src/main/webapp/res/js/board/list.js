function goToDetail (boardPk) {
	location.href = `/board/detail?boardPk=${boardPk}`
}

var gPage = 1
var listContentElem = document.querySelector('#listContent')
var category = listContentElem.dataset.category
var selRowCntElem = document.querySelector('#selRowCnt') //글갯수
selRowCntElem.addEventListener('change', function() {	
	getBoardList(1)
	getMaxPageNum()
})

function getBoardList (page) {
	
	if(!page) {
		page = 1
	}
	var rowCnt = selRowCntElem.value
	var info = {
		rowCnt,
		page,		
	}
	sessionStorage.setItem('pageInfo', JSON.stringify(info))
	
	fetch(`/board/listData?category=${category}&page=${page}&rowCnt=${rowCnt}`)
	.then(res => res.json())
	.then(myJson => {		
		boardProc(myJson)
	})	
}

function boardProc(myJson) {
	if(myJson.length === 0) {
		listContentElem.innerHTML = '<div>글이 없습니다.</div>'
		return
	}
	
	var table = document.createElement('table')
	table.classList.add('basic-table')
	
	var htr = document.createElement('tr')
	htr.innerHTML = `
		<td>번호</td>
		<td>제목</td>
		<td>조회수</td>
		<td>작성일</td>
		<td>작성자</td>		
	`
	table.append(htr)
	
	myJson.forEach(function(item) {
		var tr = document.createElement('tr')
		tr.classList.add('record')
		tr.addEventListener('click', function() {
			goToDetail(item.boardPk)
		})
		var td1 = document.createElement('td')
		var td2 = document.createElement('td')
		var td3 = document.createElement('td')
		var td4 = document.createElement('td')
		var td5 = document.createElement('td')
		
		tr.append(td1)
		tr.append(td2)
		tr.append(td3)
		tr.append(td4)
		tr.append(td5)
		
		td1.innerText = item.seq
		td2.innerText = item.title
		td3.innerText = item.hits
		td4.innerText = item.regDt
		td5.innerText = item.writerNm
		
		table.append(tr)
	})	
	
	listContentElem.innerHTML = ''
	listContentElem.append(table)
}




function getMaxPageNum() {
	var rowCnt = selRowCntElem.value
	fetch(`/board/getMaxPageNum?category=${category}&rowCnt=${rowCnt}`)
	.then(res => res.json())
	.then(myJson => {
		pageProc(myJson)
	})
}


var pagingContentElem = document.querySelector('#pagingContent')
function pageProc (myJson) {
	pagingContentElem.innerHTML = null
	for(let i=1; i<=myJson; i++) {
		const span = document.createElement('span')
		span.innerText = i
		span.classList.add('pointer') //span마다 pointer 클래스 주세요
		
		if(gPage === i) {
			span.classList.add('selected')
		}
		
		//span에 click이벤트를 건다. 클릭하면 getBoardList 함수 호출
		span.addEventListener('click', function() {
			getBoardList(i)
			pageHighlight(this)
		})
		
		pagingContentElem.append(span)
	}
}

function pageHighlight(ele) {
	//모든 span의 selected 클래스를 빼준다.
	var selectedSpan = pagingContentElem.querySelector('.selected')
	if(selectedSpan) {
		selectedSpan.classList.remove('selected')	
	}

	//나의 span에 selected 클래스 추가한다.
	ele.classList.add('selected')
}



var pageInfoTxt = sessionStorage.getItem('pageInfo')
if(pageInfoTxt) {
	var pageInfo = JSON.parse(pageInfoTxt)
	gPage = pageInfo.page
	selRowCntElem.value = pageInfo.rowCnt
}

getBoardList(gPage)
getMaxPageNum()
























