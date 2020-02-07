<%--
  Created by IntelliJ IDEA.
  User: Vardan Balaian
  Date: 2/4/2020
  Time: 3:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Marketplace User Items</title>
    <meta charset="UTF-8">
    <meta content="The site was developed by student Balaian Vardan" name="description">
    <meta content="en" http-equiv="content-language">
    <meta content="Vardan Balaian" name="author">
    <meta content="copyright held by Balaian Vardan" name="copyright">

    <link href="css/element_styles.css" rel="stylesheet">
    <link href="css/text_styles.css" rel="stylesheet">
    <link href="css/hover_styles.css" rel="stylesheet">
</head>

<body>

<h1 class="site_name_decor">Online MarketPlace</h1>

<a class="navigation_link" href="homepage">Homepage</a>
<a class="navigation_link" href="product_table">Auction table</a>

<div class="content">

    <div class="logout_form">
        <span class="user_name_display">${sessionScope.loginName}</span>
        <a class="logout_link" href="logout">log out</a>
    </div>

    <table>

        <caption class="legend_text">Your bidding</caption>

        <thead>


        <tr>

            <th class="new_action_link_position">
                <form autocomplete="off" method="post">
                    <input type="hidden" name="hiddenProductAdding"/>
                <button class="adding_link" type="submit">New auction</button>
                </form>
            </th>

        </tr>

        <tr>

            <th class="cell_header" width="20%">
                Product
            </th>

            <th class="cell_header" width="39%">
                Description
            </th>

            <th class="cell_header" width="7%">
                Starting price
            </th>

            <th class="cell_header" width="7%">
                Offer end date
            </th>

            <th class="cell_header" width="10%">
                Status
            </th>

        </tr>
        </thead>

        <tfoot>
        <tr>
            <th class="cell_header" colspan="9">${requestScope.userProductNumber} Publications</th>
        </tr>
        </tfoot>

        <tbody>


        <c:forEach var="userProduct" items="${requestScope.userProductList}">

            <form autocomplete="off" method="post">

            <tr class="cell_separator">

            <c:if test="${userProduct.biddingByProduct.biddingStatus.statusTitle.equals(sessionScope.relevantStatus)}">
            <td class="cell">${userProduct.productTitle}</td>
            <td class="cell">${userProduct.description}</td>
            <td class="cell">${userProduct.biddingByProduct.startingPrice} $</td>
            <td class="cell">${userProduct.biddingByProduct.offerEndDate}</td>
            <td class="cell cell_text_align">${userProduct.biddingByProduct.biddingStatus.statusTitle}</td>
            <td class="new_offer_cell">

                <input type="hidden" name="hiddenUserProductID" value="${userProduct.productId}">
                <button class="edit_link" type="submit">Edit</button>

            </td>
            </c:if>

            <c:if test="${userProduct.biddingByProduct.biddingStatus.statusTitle.equals(sessionScope.soldStatus)}">
                <td class="sold_cell">${userProduct.productTitle}</td>
                <td class="sold_cell">${userProduct.description}</td>
                <td class="sold_cell">${userProduct.biddingByProduct.startingPrice} $</td>
                <td class="sold_cell">${userProduct.biddingByProduct.offerEndDate}</td>
                <td class="sold_cell cell_text_align">${userProduct.biddingByProduct.biddingStatus.statusTitle}</td>
                <td class="new_offer_cell">

                <input type="hidden" name="hiddenUserProductID" value="${userProduct.productId}">
                <button class="edit_link" type="submit">Edit</button>

                </td>
            </c:if>

        </tr>

        </form>

        </c:forEach>


        </tbody>

    </table>

</div>

<footer class="footer"><p class="footer_text">Developed by student Balaian Vardan Ⓢ 2020</p>
</footer>

</body>
</html>
