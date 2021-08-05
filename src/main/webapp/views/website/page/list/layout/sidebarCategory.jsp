<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: quang
  Date: 7/26/2021
  Time: 5:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="sidebar-widget mb-30">
    <div class="sidebar-category">
        <ul>
            <li class="title"><i class="fa fa-bars"></i> categories</li>
            <c:forEach items="${lstCategory}" var="category">
                <li>
                    <a href="<c:url value="/category?id=${category.id}"/>">${category.name}</a><span>(${fn:length(category.lstEntityProducts)})</span>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>

