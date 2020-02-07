<%--
  Created by IntelliJ IDEA.
  User: Vardan Balaian
  Date: 05.02.2020
  Time: 0:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html lang="en">
<head>
    <title>Product Table</title>
    <meta charset="UTF-8">
    <meta content="The site was developed by student Balaian Vardan" name="description">
    <meta content="en" http-equiv="content-language">
    <meta content="Vardan Balaian" name="author">
    <meta content="copyright held by Balaian Vardan" name="copyright">

    <link href="css/element_styles.css" rel="stylesheet">
    <link href="css/text_styles.css" rel="stylesheet">
    <link href="css/hover_styles.css" rel="stylesheet">
    <link href="css/modal_style.css" rel="stylesheet">

    <script src="js/offer_form_validation.js" type="text/javascript"></script>
    <script src="js/elements_change.js" type="text/javascript"></script>
</head>

<body>

<h1 class="site_name_decor">Online MarketPlace</h1>

<a class="navigation_link" href="homepage">Homepage</a>
<a class="navigation_link" href="login">Log in</a>
<a class="navigation_link" href="registration">Registration</a>

<div class="content">

    <div class="logout_form">
        <span class="user_name_display">Guest</span>
        <a class="logout_link" href="logout">log out</a>
    </div>

    <table>

        <caption class="legend_text">Auction table</caption>

        <thead>

        <tr>

            <th class="cell_header" width="2%">
                ID
            </th>

            <th class="cell_header" width="13%">
                Sale
            </th>

            <th class="cell_header" width="32%">
                Description
            </th>

            <th class="cell_header" width="12%">
                Salesman
            </th>

            <th class="cell_header" width="6%">
                Starting price
            </th>

            <th class="cell_header" width="7%">
                Offer end date
            </th>

            <th class="cell_header" width="6%">
                Best offer
            </th>

            <th class="cell_header" width="12%">
                Bidder
            </th>

            <th class="cell_header" width="10%">
                Status
            </th>

        </tr>
        </thead>

        <tfoot>
        <tr>
            <th class="cell_header" colspan="9">${requestScope.productNumber} Publications</th>
        </tr>
        </tfoot>

        <tbody>

        <c:forEach var="product" items="${requestScope.productList}">
            <tr class="cell_separator">

                <c:if test="${product.biddingByProduct.biddingStatus.statusTitle.equals(requestScope.relevantStatus)}">
                    <td class="cell">${product.productId} </td>
                    <td class="cell">${product.productTitle}</td>
                    <td class="cell">${product.description}</td>
                    <td class="cell">${product.productOwner.fullName}</td>
                    <td class="cell">${product.biddingByProduct.startingPrice} $</td>
                    <td class="cell">${product.biddingByProduct.offerEndDate}</td>
                    <td class="cell">${product.biddingByProduct.bestOffer} $</td>
                    <td class="cell">${product.biddingByProduct.userAsSupposedBidder.fullName}</td>
                    <td class="cell cell_text_align">${product.biddingByProduct.biddingStatus.statusTitle}</td>
                </c:if>

                <c:if test="${product.biddingByProduct.biddingStatus.statusTitle.equals(requestScope.soldStatus)}">
                <td class="sold_cell" >${product.productId} </td>
                <td class="sold_cell" >${product.productTitle}</td>
                <td class="sold_cell" >${product.description}</td>
                <td class="sold_cell" >${product.productOwner.fullName}</td>
                <td class="sold_cell" >${product.biddingByProduct.startingPrice} $</td>
                <td class="sold_cell" >${product.biddingByProduct.offerEndDate}</td>
                <td class="sold_cell" >${product.biddingByProduct.bestOffer} $</td>
                <td class="sold_cell" >${product.biddingByProduct.userAsSupposedBidder.fullName}</td>
                <td class="sold_cell cell_text_align">${product.biddingByProduct.biddingStatus.statusTitle}</td>
                </c:if>

            </tr>
        </c:forEach>

        </tbody>

    </table>

</div>

<footer class="footer"><p class="footer_text">Developed by student Balaian Vardan Ⓢ 2020</p>
</footer>

</body>
</html>
