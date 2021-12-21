function moveToDetail(iboard) {
    console.log('iboard : ' + iboard);
    location.href="/board/detail2?iboard=" + iboard;
}
var searchFromElem = document.querySelector('#searchFrom');
console.log(searchFromElem);
if(searchFromElem) {
    searchFromElem.addEventListener('change', function() {
       searchFromElem.submit();
    });
}