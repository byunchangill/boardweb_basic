function moveToDetail(iboard) {
    console.log('iboard : ' + iboard);
    location.href="/board/detail?iboard=" + iboard;
}
var searchFromElem = document.querySelector('#searchFrom');
console.log(searchFromElem);
if(searchFromElem) {
    searchFromElem.addEventListener('change', function() {
       searchFromElem.submit();
    });
}