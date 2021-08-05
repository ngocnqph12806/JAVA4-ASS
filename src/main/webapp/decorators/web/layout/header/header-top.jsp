<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="header-top-area bg-gray text-center text-md-left">
    <div class="container">
        <div class="row">
            <div class="col-lg-6 col-md-5">
                <div class="header-call-action">
                    <a href="#">
                        <i class="fa fa-envelope"></i>
                        info@website.com
                    </a>
                    <a href="#">
                        <i class="fa fa-phone"></i>
                        0123456789
                    </a>
                </div>
            </div>
            <div class="col-lg-6 col-md-7">
                <div class="header-top-right float-md-right float-none">
                    <nav>
                        <ul>
                            <c:if test="${empty VISIT}">
                                <li>
                                    <a href="#click-login" data-toggle="modal">Đăng nhập</a>
                                </li>
                            </c:if>
                            <c:if test="${not empty VISIT}">
                                <li>
                                    <a href="<c:url value="/logout"/>">Đăng xuất</a>
                                </li>
                                <li>
                                    <a href="<c:url value="/payment-visit"/>">Hoá đơn mua hàng</a>
                                </li>
                                <li>
                                    <a href="<c:url value="/cart"/>">Giỏ hàng</a>
                                </li>
                                <li>
                                    <a href="<c:url value="/checkout"/>">Thanh toán</a>
                                </li>
                            </c:if>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
    </div>
</div>