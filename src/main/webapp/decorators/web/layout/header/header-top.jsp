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
                            <li>
                                <a href="<c:url value="/signin"/>">Login</a>
                            </li>
                            <li>
                                <a href="<c:url value="/signup"/>">Register</a>
                            </li>
                            <li>
                                <a href="#">my wishlist</a>
                            </li>
                            <li>
                                <a href="#">my cart</a>
                            </li>
                            <li>
                                <a href="#">checkout</a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
    </div>
</div>