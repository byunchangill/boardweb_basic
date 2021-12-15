
var frm = document.querySelector('#frm');
if (frm) {
    // function proc() { // 1번 방법.
    //     alert('전송!');
    // }
    // frm.addEventListener('submit', proc); // 1번 방법.

    // var proc2 = function () { // 2번 방법.
    //     alert('전송!');
    // }
    // frm.addEventListener('submit', proc2); // 2번 방법.

    // frm.addEventListener('submit', function () { // 3번 방법. 가장 많이 쓰는 방법.
    //     alert('전송');
    // });

    // function frmSubmitEvent(e) { // 3번 풀어쓴 방법.
    //     alert('전송!!');
    //     e.preventDefault();
    // }
    // frm.addEventListener('submit', frmSubmitEvent);

    frm.addEventListener('submit', function (e) { // 3번 방법. 가장 많이 쓰는 방법. 파라미터 쓸때.
        if(frm.uid.value.length < 5 || frm.uid.value.length > 20) {
            alert('아이디는 5~20글자 입니다.');
            e.preventDefault();// 문제 발생시 submit 이 하는걸 막는다
        } else if(frm.upw.value.length < 5){
            alert('비밀번호를 확인해주세요.');
            e.preventDefault();// 문제 발생시 submit 이 하는걸 막는다
        }
    });

    var btnShowPw = document.querySelector('#btnShowPw');
    if(btnShowPw) {
        btnShowPw.addEventListener('click', function () {
            if(frm.upw.type === 'password') {
                frm.upw.type = 'text'; // type 을 password 에서 text 로 봐꿈.
                btnShowPw.value = '비밀번호 숨기기';
            } else  {
                frm.upw.type = 'password';
                btnShowPw.value = '비밀번호 보이기';
            }
        });
    }
}

