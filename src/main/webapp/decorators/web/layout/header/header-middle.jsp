<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="header-middle-area pt-20 pb-20">
    <div class="container">
        <div class="row align-items-center">
            <div class="col-lg-3">
                <div class="brand-logo">
                    <a href="<c:url value="/"/>">
                        <img src="<c:url value="/assets/website/img/logo/logo.png"/>" alt="brand logo">
                    </a>
                </div>
            </div> <!-- end logo area -->
            <div class="col-lg-9">
                <div class="header-middle-right">
                    <div class="header-middle-shipping mb-20">
                        <div class="single-block-shipping">
                            <div class="shipping-icon">
                                <i class="fa fa-clock-o"></i>
                            </div>
                            <div class="shipping-content">
                                <h5>Thời gian làm việc</h5>
                                <span>Cả tuần: 8.00 - 18.00</span>
                            </div>
                        </div> <!-- end single shipping -->
                        <div class="single-block-shipping">
                            <div class="shipping-icon">
                                <i class="fa fa-truck"></i>
                            </div>
                            <div class="shipping-content">
                                <h5>Miễn phí giao hàng</h5>
                                <span>Đơn hàng trên 2 triệu</span>
                            </div>
                        </div> <!-- end single shipping -->
                        <div class="single-block-shipping">
                            <div class="shipping-icon">
                                <i class="fa fa-money"></i>
                            </div>
                            <div class="shipping-content">
                                <h5>Hoàn tiền 100%</h5>
                                <span>Trong 30 ngày nếu sản phẩm lỗi</span>
                            </div>
                        </div> <!-- end single shipping -->
                    </div>
                    <div class="header-middle-block">
                        <div class="header-middle-searchbox">
                            <input id="search-name-product" type="text" placeholder="Tìm kiếm theo tên sản phẩm...">
                            <button onclick="clickSearch()" class="search-btn"><i class="fa fa-search"></i></button>
                        </div>
                        <script>
                            function clickSearch() {
                                console.log('a')
                                let name = document.getElementById('search-name-product').value
                                if (name !== null && name.trim() !== '') {
                                    window.location = '${pageContext.request.contextPath}/product?name=' + name
                                }
                            }
                        </script>
                        <div class="header-mini-cart">
                            <div class="mini-cart-btn">
                                <i class="fa fa-shopping-cart"></i>
                                <span class="cart-notification">
                                    <c:set var="count" value="${0}"/>
                                    <c:forEach items="${lstCart}" var="cart">
                                        <c:set var="count"
                                               value="${count + cart.quantity}"/>
                                    </c:forEach>
                                    ${count}
                                </span>
                            </div>
                            <div class="cart-total-price">
                                <c:set var="total" value="${0}"/>
                                <c:forEach items="${lstCart}" var="cart">
                                    <c:set var="total"
                                           value="${total + ((cart.price - (cart.price * cart.entityProduct.persenSale)/100) * cart.quantity)}"/>
                                </c:forEach>
                                <fmt:formatNumber value="${total}" type="currency" currencySymbol=""/>₫
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>