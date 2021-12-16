<%@ page import="com.koreait.basic.board.model.entity.BoardCmtEntity" %>
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
        <form action="/board/cmt/reg" method="post">
            <input type="hidden" name="iboard" value="${requestScope.data.iboard}">
            <input type="text" name ="ctnt" placeholder="댓글을 남깁시다." >
            <input type="submit" value="댓글 작성">
        </form>
        </div>
    </c:if>
    <div>
        <table>
            <tr>
                <th>내용</th>
                <th>작성자</th>
                <th>작성일</th>
                <th>비고</th>
            </tr>
            <c:forEach items="${requestScope.cmtList}" var="item">
                <tr>
                    <td><c:out value="${item.ctnt}"/></td>
                    <td>${item.writerNm}</td>
                    <td>${item.rdt}</td>
                    <td>
                        <c:if test="${sessionScope.loginUser.iuser == item.writer}">
                            <button onclick="openModForm(${item.icmt}, '<c:out value="${item.ctnt}" escapeXml="true"/>')">수정</button>
                            <button onclick="isDelCmt(${requestScope.data.iboard}, ${item.icmt});">삭제</button>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<div class="cmtModContainer">
    <div class="cmtModBody">
        <form action="/board/cmt/mod" method="post" id="cmtModForm">
            <input type="hidden" name="iboard" value="${requestScope.data.iboard}">
            <input type="hidden" name="icmt">
            <div><input type="text" name="ctnt" placeholder="댓글내용"></div>
            <div>
                <input type="submit" value="수정">
                <input type="button" value="취소" id="btnCancel">
            </div>
        </form>
    </div>
</div>
<script src="/res/js/board/detail.js?ver=2"></script>
