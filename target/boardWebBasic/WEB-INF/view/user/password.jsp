<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <form action="/user/password" method="post" id="frm">
        <div><input type="password" name="upw" placeholder="현재 비밀번호"></div>
        <div><input type="password" name="changedUpw" placeholder="변경 비밀번호"></div>
        <div><input type="password" name="changedUpwConfirm" placeholder="변경 비밀번호 확인"></div>
    </form>
    <div><input type="button" value="비밀번호 변경" id="submitBtn"></div>
</div>
<script src="/res/js/user/password.js"></script>