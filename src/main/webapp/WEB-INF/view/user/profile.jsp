<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
    <c:set var="pImg" value="defaultProfile.png"/>
    <c:if test="${requestScope.data.profileImg != null}">
        <c:set var="pImg" value="profile/${sessionScope.loginUser.iuser}/${requestScope.data.profileImg}"/>
    </c:if>
    <div class="circular--img circular--size300"><img src="/res/img/${pImg}"></div>
    <div>
        <div>아이디 : ${requestScope.data.uid}</div>
        <div>이름 : ${requestScope.data.nm}</div>
        <div>성별 : ${requestScope.data.gender == 1 ? '남자' : '여자'}</div>
        <div>가입일 : ${requestScope.data.rdt}</div>
    </div>
    <div>
        <form action="/user/profile" method="post" enctype="multipart/form-data">
            <div><label>이미지 : <input type="file" name="profileImg" accept="image/*"></label></div>
            <div><input type="submit" value="이미지 업로드"></div>
        </form>
    </div>
</div>