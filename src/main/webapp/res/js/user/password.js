var frmElem = document.querySelector('#frm');
var submitBtnElem = document.querySelector('#submitBtn');

submitBtnElem.addEventListener('click', function () {
    if (frmElem.upw.value.length < 5 || frmElem.changedUpw.value.length < 5) {
        alert('비밀번호를 5자 이상 작성해 주세요.');
    } else if (frmElem.changedUpw.value !== frmElem.changedUpwConfirm.value) {
        alert('비밀번호를 확인해 주세요.');
    } else if (frmElem.upw.value === frmElem.changedUpw.value) {
        alert('기존 비밀번호로는 변경 할 수 없습니다.');
    } else  {
        frmElem.submit();
    }
});