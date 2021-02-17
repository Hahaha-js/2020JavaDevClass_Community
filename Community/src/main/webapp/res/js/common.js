var menuItemsElem = document.querySelector('.menuItems')
var menuList = menuItemsElem.children
for(var i=0; i<menuList.length; i++) {
	var li = menuList[i]
	li.querySelector('a').addEventListener('click', function() {
		sessionStorage.removeItem('pageInfo')
	})
}