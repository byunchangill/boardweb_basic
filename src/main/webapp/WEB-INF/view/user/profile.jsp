<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <div>프로필 이미지</div>
    <div>
        <div>이름 :</div>
        <div>성별 :</div>
    </div>
    <div>
        <form action="/user/profile" method="post" enctype="multipart/form-data">
            <div><label>이미지 : <input type="file" name="profileImg"></label></div>
            <div><input type="submit" value="이미지 업로드"></div>
        </form>
    </div>
</div>
