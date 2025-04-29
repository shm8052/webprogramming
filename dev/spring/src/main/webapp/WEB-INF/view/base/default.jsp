<!DOCTYPE html>
<html lang="ko">
<%@ include file="head.jsp" %>
<body>
    <div class="container">
        <%-- 네비게이션 --%>
        <%@ include file="navbar.jsp" %>
        <%--// 네비게이션 --%>

        <%-- 페이지 제목 --%>
        <%@ include file="title.jsp" %>
        <%--// 페이지 제목 --%>

        <%-- 메시지 --%>
        <%@ include file="message.jsp" %>
        <%--// 메시지 --%>

        <%-- 페이지 내용 --%>
        <div class="row">
            <div class="col-12">
                페이지 내용
            </div>
        </div>
        <%--// 페이지 내용 --%>
    </div>

    <%@ include file="script.jsp" %>
</body>
</html>