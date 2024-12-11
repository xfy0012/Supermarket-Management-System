
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/jsp/common/head.jsp"%>
<section>
<div class="right">
    <img class="wColck" src="${pageContext.request.contextPath}/statics/images/clock.jpg" alt=""/>
    <div class="wFont">
        <h2>${userSession.userName}</h2>
        <p> Welcome to the Supermarket Management System ！</p>
    </div>
</div>
</section>
<%@ include file="/jsp/common/foot.jsp"%>
