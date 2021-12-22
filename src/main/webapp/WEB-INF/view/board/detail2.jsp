<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<link rel="stylesheet" href="/res/css/board/detail.css">
<div>
    <c:if test="${sessionScope.loginUser.iuser == requestScope.data.writer}">
        <div>
            <a href="/board/del?iboard=${requestScope.data.iboard}"><button>삭제</button></a>
            <a href="/board/regmod?iboard=${requestScope.data.iboard}"><button>수정</button></a>
        </div>
    </c:if>
    <c:if test="${sessionScope.loginUser != null}">
        <div class="fav">
            <c:choose>
                <c:when test="${requestScope.isHeart == 1}">
                    <a href="/board/heart?proc=2&iboard=${requestScope.data.iboard}"><i class="fas fa-thumbs-up"></i></a>
                </c:when>
                <c:otherwise>
                <a href="/board/heart?proc=1&iboard=${requestScope.data.iboard}"><i class="far fa-thumbs-up"></i></a>
                </c:otherwise>
            </c:choose>
        </div>
    </c:if>
    <div>글번호: ${requestScope.data.iboard}</div>
    <div>글제목: <c:out value="${requestScope.data.title}"/></div>
    <div>글내용: <c:out value="${requestScope.data.ctnt}"/></div>
    <div>조회수: <c:out value="${requestScope.data.hit}"/></div>
    <div>작성자(이름): <c:out value="${requestScope.data.writerNm}"/></div>
    <div>등록일시: <c:out value="${requestScope.data.rdt}"/></div>
    <div>좋아요 개수 : ${requestScope.data.cnt}</div>

    <c:if test="${sessionScope.loginUser != null}">
        <div>
        <form id="cmtNewFrm">
            <input type="text" name ="ctnt" placeholder="댓글을 남깁시다." >
            <input type="submit" value="댓글 작성">
        </form>
        </div>
    </c:if>
    <div id="cmtListContainer" data-iboard="${requestScope.data.iboard}"
                               data-loginuserpk="${sessionScope.loginUser.iuser}"></div> <!-- ajax 용. -->
</div>
<div class="cmtModContainer">
    <div class="cmtModBody">
        <form id="cmtModForm" onsubmit="return false;">
            <input type="hidden" name="icmt">
            <div><input type="text" name="ctnt" placeholder="댓글내용"></div>
            <div>
                <input type="submit" value="수정">
                <input type="button" value="취소" id="btnCancel">
            </div>
        </form>
    </div>
</div>
<script src="/res/js/board/detail2.js?ver=3"></script>
