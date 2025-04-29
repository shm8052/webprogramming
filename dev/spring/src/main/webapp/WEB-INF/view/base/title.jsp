<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${page_title != null}">
<div class="row">
    <div class="col-12">
        <h1>${pageTitle}</h1>
    </div>
</div>
</c:if>