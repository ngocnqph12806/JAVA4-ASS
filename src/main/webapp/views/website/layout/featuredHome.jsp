<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: quang
  Date: 7/26/2021
  Time: 7:18 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- featured category area start -->
<div class="feature-category-area mt-md-70">
    <div class="section-title mb-30">
        <div class="title-icon">
            <i class="fa fa-bookmark"></i>
        </div>
        <h3>Mua nhiều nhất</h3>
    </div> <!-- section title end -->
    <!-- featured category start -->
    <div class="featured-carousel-active slick-padding slick-arrow-style">
        <c:forEach items="${lstTopBuyProduct}" var="productTopBuy">
            <!-- product single item start -->
            <div class="product-item fix">
                <div class="product-thumb">
                    <a href="<c:url value="/product?id=${productTopBuy.id}"/>">
                        <c:forEach items="${productTopBuy.lstEntityProductImages}" var="imgProductTopBuy"
                                   varStatus="theCount">
                            <c:if test="${theCount.index == 0}">
                                <img src="<c:url value="${imgProductTopBuy.image}"/>" class="img-pri" height="150px"
                                     alt=""/>
                            </c:if>
                            <c:if test="${theCount.index == 1}">
                                <img src="<c:url value="${imgProductTopBuy.image}"/>" class="img-sec" height="150px"
                                     alt=""/>
                            </c:if>
                        </c:forEach>
                    </a>
                    <div class="product-label">
                        <span>hot</span>
                    </div>
                    <div class="product-action-link">
                        <a href="#${productTopBuy.id}" class="quick_view" data-toggle="modal" data-target="#quick_view"> <span
                                data-toggle="tooltip" data-placement="left" title="Quick view"><i
                                class="fa fa-search"></i></span> </a>
                        <c:if test="${not empty VISIT}">
                            <a href="#" data-toggle="tooltip" data-placement="left" title="Wishlist"><i
                                    class="click_wish_list fa fa-heart-o"></i></a>
                        </c:if>
                        <a href="#" data-toggle="tooltip" data-placement="left" title="Compare"><i
                                class="fa fa-refresh"></i></a>
                        <a href="#" data-toggle="tooltip" data-placement="left" title="Add to cart"><i
                                class="fa fa-shopping-cart"></i></a>
                    </div>
                </div>
                <div class="product-content">
                    <h4><a href="<c:url value="/product?id=${productTopBuy.id}"/>">${productTopBuy.name}</a></h4>
                    <div class="pricebox">
                        <span class="regular-price">
<c:set var="min" value="${0}"/>
                                    <c:set var="max" value="${0}"/>
                                    <c:forEach var="details" items="${productTopBuy.lstEntityProductDetails}">
                                        <c:if test="${details.price >= max}">
                                            <c:set var="max" value="${details.price}"/>
                                        </c:if>
                                    </c:forEach>
                                    <c:forEach var="details" items="${productTopBuy.lstEntityProductDetails}">
                                        <c:if test="${details.price < max}">
                                            <c:set var="min" value="${details.price}"/>
                                        </c:if>
                                    </c:forEach>
                                    <c:if test="${min == 0}">
                                        <fmt:formatNumber value="${max}" type="currency" currencySymbol=""/>₫
                                    </c:if>
                                    <c:if test="${min != 0}">
                                        <fmt:formatNumber value="${min}" type="currency" currencySymbol=""/>₫
                                        - <fmt:formatNumber value="${max}" type="currency" currencySymbol=""/>₫
                                    </c:if>
                        </span>
                        <div class="ratings">
                            <span class="good"><i class="fa fa-star"></i></span>
                            <span class="good"><i class="fa fa-star"></i></span>
                            <span class="good"><i class="fa fa-star"></i></span>
                            <span class="good"><i class="fa fa-star"></i></span>
                            <span><i class="fa fa-star"></i></span>
                            <div class="pro-review">
                                <span>1 review(s)</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- product single item end -->
        </c:forEach>
    </div>
    <!-- featured category end -->
</div>
<!-- featured category area end -->

