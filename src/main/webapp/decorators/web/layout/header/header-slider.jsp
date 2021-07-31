<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="category-toggle-wrap">
    <div class="category-toggle">
        category
        <div class="cat-icon">
            <i class="fa fa-angle-down"></i>
        </div>
    </div>
    <nav class="category-menu category-style-2">
        <ul>
            <li class="menu-item-has-children"><a href="<c:url value="/product"/>"><i class="fa fa-camera"></i> camera</a>
                <!-- Mega Category Menu Start -->
                <ul class="category-mega-menu">
                    <li class="menu-item-has-children">
                        <a href="<c:url value="/category"/>">Loại</a>
                        <ul>
                            <c:forEach items="${lstCategory}" var="category">
                                <li><a href="<c:url value="/category?id=${category.id}"/>">${category.name}</a></li>
                            </c:forEach>
                        </ul>
                    </li>
                    <li class="menu-item-has-children">
                        <a href="<c:url value="/brand"/>">Thương hiệu</a>
                        <ul>
                            <c:forEach items="${lstBrand}" var="brand">
                                <li><a href="<c:url value="/brand?id=${brand.id}"/>">${brand.name}</a></li>
                            </c:forEach>
                        </ul>
                    </li>
                    <li class="menu-item-has-children">
                        <a href="shop-grid-left-sidebar.html">accessories</a>
                        <ul>
                            <li><a href="shop-grid-left-sidebar.html">Power Bank</a></li>
                            <li><a href="shop-grid-left-sidebar.html">Data Cable</a></li>
                            <li><a href="shop-grid-left-sidebar.html">Power Cable</a></li>
                            <li><a href="shop-grid-left-sidebar.html">Battery</a></li>
                        </ul>
                    </li>
                    <li class="menu-item-has-children">
                        <a href="shop-grid-left-sidebar.html">headphone</a>
                        <ul>
                            <li><a href="shop-grid-left-sidebar.html">Desktop Headphone</a></li>
                            <li><a href="shop-grid-left-sidebar.html">Mobile Headphone</a></li>
                            <li><a href="shop-grid-left-sidebar.html">Wireless Headphone</a></li>
                            <li><a href="shop-grid-left-sidebar.html">LED Headphone</a></li>
                        </ul>
                    </li>
                </ul><!-- Mega Category Menu End -->
            </li>
            <li class="menu-item-has-children"><a href="shop-grid-left-sidebar.html"><i class="fa fa-book"></i> smart
                phones</a>
                <!-- Mega Category Menu Start -->
                <ul class="category-mega-menu">
                    <li class="menu-item-has-children">
                        <a href="shop-grid-left-sidebar.html">Smartphone</a>
                        <ul>
                            <li><a href="shop-grid-left-sidebar.html">Samsome</a></li>
                            <li><a href="shop-grid-left-sidebar.html">GL Stylus</a></li>
                            <li><a href="shop-grid-left-sidebar.html">Uawei</a></li>
                            <li><a href="shop-grid-left-sidebar.html">Cherry Berry</a></li>
                            <li><a href="shop-grid-left-sidebar.html">uPhone</a></li>
                        </ul>
                    </li>
                    <li class="menu-item-has-children">
                        <a href="shop-grid-left-sidebar.html">headphone</a>
                        <ul>
                            <li><a href="shop-grid-left-sidebar.html">Desktop Headphone</a></li>
                            <li><a href="shop-grid-left-sidebar.html">Mobile Headphone</a></li>
                            <li><a href="shop-grid-left-sidebar.html">Wireless Headphone</a></li>
                            <li><a href="shop-grid-left-sidebar.html">LED Headphone</a></li>
                            <li><a href="shop-grid-left-sidebar.html">Over-ear</a></li>
                        </ul>
                    </li>
                    <li class="menu-item-has-children">
                        <a href="shop-grid-left-sidebar.html">accessories</a>
                        <ul>
                            <li><a href="shop-grid-left-sidebar.html">Power Bank</a></li>
                            <li><a href="shop-grid-left-sidebar.html">Data Cable</a></li>
                            <li><a href="shop-grid-left-sidebar.html">Power Cable</a></li>
                            <li><a href="shop-grid-left-sidebar.html">Battery</a></li>
                            <li><a href="shop-grid-left-sidebar.html">OTG Cable</a></li>
                        </ul>
                    </li>
                    <li class="menu-item-has-children">
                        <a href="shop-grid-left-sidebar.html">accessories</a>
                        <ul>
                            <li><a href="shop-grid-left-sidebar.html">Power Bank</a></li>
                            <li><a href="shop-grid-left-sidebar.html">Data Cable</a></li>
                            <li><a href="shop-grid-left-sidebar.html">Power Cable</a></li>
                            <li><a href="shop-grid-left-sidebar.html">Battery</a></li>
                            <li><a href="shop-grid-left-sidebar.html">OTG Cable</a></li>
                        </ul>
                    </li>
                </ul><!-- Mega Category Menu End -->
            </li>
            <li><a href="shop-grid-left-sidebar.html"><i class="fa fa-clock-o"></i> watch</a></li>
            <li><a href="shop-grid-left-sidebar.html"><i class="fa fa-television"></i> electronic</a></li>
            <li><a href="shop-grid-left-sidebar.html"><i class="fa fa-tablet"></i> tablet</a></li>
            <li><a href="shop-grid-left-sidebar.html"><i class="fa fa-book"></i> books</a></li>
            <li><a href="shop-grid-left-sidebar.html"><i class="fa fa-microchip"></i> microchip</a></li>
            <li><a href="shop-grid-left-sidebar.html"><i class="fa fa-bullhorn"></i> bullhorn</a></li>
        </ul>
    </nav>
</div>