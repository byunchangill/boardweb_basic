<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="wrap">
    <div>
        <form action="/user/login" method="post" id="frm">
            <div>ID<input type="text" name="uid" placeholder="아이디"></div>
            <div>PASSWORD<input type="password" name="upw" placeholder="비밀번호"></div>
            <div><input type="submit" value="로그인"></div>
        </form>
        <div>
            <input type="button" value="비밀번호 보기" id="btnShowPw">
            <a href="/user/join"><input type="button" value="회원가입"></a>
        </div>
    </div>
</div>
<script src="/res/js/user/login.js"></script>
