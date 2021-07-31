<%@include file="/commont/taglib.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title><dec:title default="Trang chủ"></dec:title></title>
    <%@include file="layout/head.jsp" %>
</head>
<body>

<jsp:include page="layout/switcherColor.jsp"/>

<div class="wrapper">

    <jsp:include page="layout/header/header.jsp"/>

    <!-- hero slider end -->
    <dec:body></dec:body>
    <!-- footer area start -->
    <jsp:include page="layout/footer/footer.jsp"/>
    <!-- footer area end -->
</div>

<script>

    $('.header-mini-cart').click(function(e){
        window.location = 'http://localhost:8081/ASS_JV4_war_exploded/cart'
    })

    $('a').click(function (e) {
        if(e.currentTarget.href.includes('product?id=')){
            e.preventDefault();
            let id = e.currentTarget.href;
            id = id.substring(id.lastIndexOf('product?id=') + 11)
            try {
                if (Number(id)) {
                    $.ajax({
                        url: "/ASS_JV4_war_exploded/load/product",
                        type: "get", //send it through get method
                        data: {
                            view: id
                        },
                        success: function (response) {
                        },
                        error: function (xhr) {
                            //Do Something to handle error
                        }
                    });
                    setTimeout(function(){
                        window.location = e.currentTarget.href
                    },10)
                }
            } catch (x) {
                e.preventDefault();
            }
        }
        if (e.currentTarget.className === 'quick_view') {
            let id = e.currentTarget.href;
            id = id.substring(id.lastIndexOf('#') + 1)
            try {
                if (Number(id)) {
                    $.ajax({
                        url: "/ASS_JV4_war_exploded/load/web/product",
                        type: "get", //send it through get method
                        data: {
                            id: id
                        },
                        success: function (response) {
                            document.getElementById('quick_view_show').innerHTML = response;
                        },
                        error: function (xhr) {
                            //Do Something to handle error
                        }
                    });
                }
            } catch (x) {
                e.preventDefault();
            }
        }
    })
</script>
<jsp:include page="layout/js.jsp"/>
</body>
</html>
