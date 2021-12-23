// 댓글작성 버튼.
var cmtNewFrmElem = document.querySelector('#cmtNewFrm');
var newSubmitBtnElem = cmtNewFrmElem.querySelector('input[type = submit]');
newSubmitBtnElem.addEventListener('click', function (e) {
    e.preventDefault();
    if(cmtNewFrmElem.ctnt.value.length === 0) {
        alert('댓글 내용을 작성해 주세요.');
        return;
    }

    var iuser = parseInt(cmtListContainerElem.dataset.loginuserpk);
    if(!iuser) {
        alert('로그인하고 작성해주세요..');
        return;
    }

    var param = {
        'iboard': cmtListContainerElem.dataset.iboard,
        'ctnt': cmtNewFrmElem.ctnt.value
    };

    var url = '/board/cmt?proc=ins';
    fetch(url, {
        'method': 'POST',
        'headers': {'Content-Type': 'application/json'},
        'body': JSON.stringify(param)
    }).then(function (res) {
        return res.json();
    }).then(function (data) {
        switch (data.result) {
            case 0:
                alert('댓글 달기를 할 수 없습니다.');
                break;
            case 1:
                cmtNewFrmElem.ctnt.value = '';
                cmtListContainerElem.innerHTML = null;
                getList();
                break;
        }
    }).catch(function (err) {
        console.log(err);
        alert('댓글 달기에 실패하였습니다.')
    });
});

// 댓글 수정.
var cmtModContainerElem = document.querySelector('.cmtModContainer');
// 댓글 수정 취소 버튼 클릭 이벤트 연결.
var btnCancelElem = cmtModContainerElem.querySelector('#btnCancel');
    btnCancelElem.addEventListener('click', cancelBtnEvent);
function cancelBtnEvent() {
    cmtModContainerElem.style.display = 'none';
    var selectedElem = document.querySelector('.cmt_selected');
    selectedElem.classList.remove('cmt_selected');
}
// 댓글 수정 버튼 클릭 이벤트 연결.
var cmtModFormElem = cmtModContainerElem.querySelector('#cmtModForm');
var submitBtnElem = cmtModContainerElem.querySelector('input[type = submit][value = 수정]');
submitBtnElem.addEventListener('click', function (e) {
    e.preventDefault();
    var url = '/board/cmt?proc=upd';
    var param = {
        'icmt': cmtModFormElem.icmt.value,
        'ctnt': cmtModFormElem.ctnt.value
    };
    fetch(url, {
        'method': 'POST',
        'headers': {
            'Content-Type': 'application/json'
        },
        'body': JSON.stringify(param)
    }).then(function (res) {
        return res.json();
    }).then(function (data) {
        console.log(data.result);
        switch (data.result) {
            case 0: // 수정 실패
                alert('댓글 수정에 실패하였습니다.')
                break;
            case 1: // 수정 성공
                modCtnt(param.ctnt);
                cancelBtnEvent();
                break;
        }
    }).catch(function (err) {
        console.log(err);
    });
});

function openModForm(icmt, ctnt) {
    cmtModContainerElem.style.display = 'flex';
    cmtModFormElem.icmt.value = icmt;
    cmtModFormElem.ctnt.value = ctnt;
}

function modCtnt(ctnt) {
    var selectedElem = document.querySelector('.cmt_selected');
    var tdCtntElem = selectedElem.children[0];
    tdCtntElem.innerText = ctnt;
}

// 댓글 리스트.
var cmtListContainerElem = document.querySelector('#cmtListContainer');
if(cmtListContainerElem) { // ajax 용.
    function getList() {
        var iboard =cmtListContainerElem.dataset.iboard;
        var url = '/board/cmt?iboard=' + iboard;

        fetch(url).then(function (res) {
            return res.json();
        }).then(function (data) {
            console.log(data);
            displayCmt(data);
        }).catch(function (err) {
            console.log(err);
        });
    }

    function displayCmt(data) {
        var tableElem = document.createElement('table');
        tableElem.innerHTML=
            `<tr> 
                <th>내용</th>
                <th>작성자</th>
                <th>작성일</th>
                <th>비고</th>
            </tr>`; // 탬플릿 리터널. 홑따옴표로 감싼다.
        cmtListContainerElem.appendChild(tableElem);

        var loginUserPk = cmtListContainerElem.dataset.loginuserpk === '' ? 0 : Number(cmtListContainerElem.dataset.loginuserpk);
        // var loginUserPk = parseInt(cmtListContainerElem.dataset.loginuserpk);
        // var loginUserPk = Number(cmtListContainerElem.dataset.loginuserpk);

        data.forEach(function (item) {
           var tr = document.createElement('tr');
           var ctnt = item.ctnt.replaceAll('<', '&lt;').replaceAll('>', '&gt;');
           tr.innerHTML =
               `
                <td>${ctnt}</td>
                <td>${item.writerNm}</td>
                <td>${item.rdt}</td>
               `;
                tableElem.appendChild(tr);

                var lastTd = document.createElement('td');
                tr.appendChild(lastTd);

                if(loginUserPk === item.writer) {
                    var btnMod = document.createElement('button');
                    btnMod.innerText = '수정';
                    btnMod.addEventListener('click', function () {
                        tr.classList.add('cmt_selected'); // 클래스 추가해준다.
                        var ctnt = tr.children[0].innerText;
                        openModForm(item.icmt, ctnt);
                    });
                    var btnDel = document.createElement('button');
                    btnDel.innerText = '삭제';
                    btnDel.addEventListener('click', function () {
                       if(confirm('삭제 하시겠습니까?.')) {
                           var param = {
                               icmt: item.icmt
                           };
                           var url = '/board/cmt?proc=del';
                           fetch(url, {
                               'method': 'POST',
                               'headers': {'Content-Type': 'application/json'},
                               'body': JSON.stringify(param)
                           }).then(function (res) {
                               return res.json();
                           }).then(function (data) {
                               switch (data.result) {
                                   case 0: // 삭제 실패.
                                       alert('댓글 삭제를 할수없습니다.')
                                       break;
                                   case 1: // 삭제 성공.
                                       tr.remove();
                                       break;
                               }
                           }).catch(function (err) {
                               alert('댓글 삭제를 실패하였습니다.')
                           })
                       }
                    });

                    lastTd.appendChild(btnMod);
                    lastTd.appendChild(btnDel);
                }
        });
    }
    getList();
}


