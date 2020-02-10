<%--
  Created by IntelliJ IDEA.
  User: Vardan Balaian
  Date: 31.01.2020
  Time: 1:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

<div class="content">

    <div class="logout_form">
        <span class="user_name_display">${sessionScope.loginName}</span>
        <a class="logout_link" href="logout">log out</a>
    </div>

    <table>

        <caption class="legend_text">Auction table</caption>

        <thead>

        <tr>
            <th colspan="2">
                <a class="my_item_link" href="my_products">My bidding</a>
            </th>
        </tr>


        <tr>
            <th colspan="9">

                <c:if test="${sessionScope.priceErrorExists}">
                        <span class="error_form">
                                ${sessionScope.priceErrorMessage}
                        </span>
                </c:if>

                <c:if test="${sessionScope.ownerOfferErrorExists}">
                    <span class="error_form">
                        ${sessionScope.ownerOfferErrorMessage}
                    </span>
                </c:if>

            </th>
        </tr>


        <tr>

            <th class="cell_header" style="width: 1%">
                ID
            </th>

            <th class="cell_header" style="width: 13%">
                Sale
            </th>

            <th class="cell_header" style="width: 30%">
                Description
            </th>

            <th class="cell_header" style="width: 12%">
                Salesman
            </th>

            <th class="cell_header" style="width: 8%">
                Starting price
            </th>

            <th class="cell_header" style="width: 7%">
                Offer end date
            </th>

            <th class="cell_header" style="width: 8%">
                Best offer
            </th>

            <th class="cell_header" style="width: 12%">
                Bidder
            </th>


            <th class="cell_header" style="width: 12%">
                My offer
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

                <c:if test="${product.biddingByProduct.biddingStatus.statusTitle.equals(sessionScope.relevantStatus)}">
                <td class="cell_header">${product.productId} </td>
                <td class="cell">${product.productTitle}</td>
                <td class="cell">${product.description}</td>
                <td class="cell">${product.productOwner.loginName}</td>
                <td class="cell">${product.biddingByProduct.startingPrice} $</td>
                <td class="cell">${product.biddingByProduct.offerEndDate}</td>

                    <c:if test="${product.productOwner.userId==product.biddingByProduct.userAsSupposedBidder.userId}">
                        <td class="cell"></td>
                        <td class="cell"></td>
                    </c:if>

                    <c:if test="${product.productOwner.userId!=product.biddingByProduct.userAsSupposedBidder.userId}">
                        <td class="cell">${product.biddingByProduct.bestOffer} $</td>
                        <td class="cell">${product.biddingByProduct.userAsSupposedBidder.fullName}</td>
                    </c:if>

                    <td>

                        <form autocomplete="off" method="post" name="offer_form">

                           <input class="offer_input" id="offer" maxlength="7" minlength="1"
                                   name="offer"
                                   pattern="^\d+(,\d{3})*(\.\d{1,2})?$" placeholder="Offer price"
                                   required
                                   title="Enter the amount in the format: 999(.99)"
                                   type="text"/><span class="cell_header">$</span>

                            <button name="hiddenItemID" value="${product.productId}" class="offer_button" type="submit" id="dataSendButton">Bid</button>

                        </form>

                    </td>

                </c:if>

                <c:if test="${product.biddingByProduct.biddingStatus.statusTitle.equals(sessionScope.soldStatus)}">
                    <td class="cell_header">${product.productId} </td>
                    <td class="sold_cell">${product.productTitle}</td>
                    <td class="sold_cell">${product.description}</td>
                    <td class="sold_cell">${product.productOwner.loginName}</td>
                    <td class="sold_cell">${product.biddingByProduct.startingPrice} $</td>
                    <td class="sold_cell">${product.biddingByProduct.offerEndDate}</td>

                    <c:if test="${product.productOwner.userId==product.biddingByProduct.userAsSupposedBidder.userId}">
                        <td class="sold_cell"></td>
                        <td class="sold_cell"></td>
                    </c:if>

                    <c:if test="${product.productOwner.userId!=product.biddingByProduct.userAsSupposedBidder.userId}">
                        <td class="sold_cell">${product.biddingByProduct.bestOffer} $</td>
                        <td class="sold_cell">${product.biddingByProduct.userAsSupposedBidder.fullName}</td>
                    </c:if>

                    <td class="sold_cell cell_text_align">${product.biddingByProduct.biddingStatus.statusTitle}</td>
                </c:if>

            </tr>

        </c:forEach>

        </tbody>

    </table>

</div>

<footer class="footer"><p class="footer_text">Developed by student Balaian Vardan Ⓢ 2020</p>
</footer>

<!-- The Modal -->
<div class="modal" id="wrongFillingModal">
    <!-- Modal content -->
    <div class="modal-content">
        <span class="close">&times;</span>
        <p id="wrongMessage">There are errors in filling out the form.</p>


        <div class="modal-footer">
            <button class="registration-button" id="exitButton" type="button">Exit</button>
        </div>

    </div>
</div>

<!-- The Modal -->
<div class="modal" id="warningModal">
    <!-- Modal content -->
    <div class="modal-content">
        <span class="close">&times;</span>
        <p id="warningMessage">Are you sure you want to make a bid of: <span
                id="enteredOffer"></span></p>

        <div class="modal-footer">
            <button class="registration-button" id="confirmButton" type="button">OK</button>
            <button class="registration-button" id="negativeButton" type="button">Cancel</button>
        </div>

    </div>
</div>
</body>
</html>
