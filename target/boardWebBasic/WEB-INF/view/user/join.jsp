<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <form action="/user/join" method="post" id="frm" onsubmit="return joinChk();">
        <div><input type="text" name="uid" placeholder="아이디" required></div>
        <div><input type="password" name="upw" placeholder="비밀번호" required></div>
        <div><input type="password" id="reupw" placeholder="비밀번호 확인" required></div>
        <div><input type="text" name="nm" placeholder="이름" required></div>
        <div>
            성별 : <label>여성 <input type="radio" value="2" name="gender"></label>
                    <label>남성 <input type="radio" value="1" name="gender" checked></label>
        </div>
        <div>
            <input type="submit" value="회원가입">
            <input type="reset" value="초기화">
        </div>
    </form>
</div>
<script src="/res/js/user/join.js"></script>
