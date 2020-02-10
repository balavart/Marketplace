<%--
  Created by IntelliJ IDEA.
  User: Vardan Balaian
  Date: 2/7/2020
  Time: 3:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <title>Adding Item</title>
    <meta charset="UTF-8">
    <meta content="The site was developed by student Balaian Vardan" name="description">
    <meta content="en" http-equiv="content-language">
    <meta content="Vardan Balaian" name="author">
    <meta content="copyright held by Balaian Vardan" name="copyright">

    <link href="css/element_styles.css" rel="stylesheet">
    <link href="css/text_styles.css" rel="stylesheet">
    <link href="css/hover_styles.css" rel="stylesheet">
    <link href="css/modal_style.css" rel="stylesheet">

    <script src="js/adding_form_validation.js" type="text/javascript"></script>
    <script src="js/elements_change.js" type="text/javascript"></script>
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

        <caption class="legend_text">Adding product</caption>

        <thead>

        <tr>

            <th class="cell_header" style="width: 15%">
                Sale
            </th>

            <th class="cell_header" style="width: 35%">
                Description
            </th>

            <th class="cell_header" style="width: 15%">
                Salesman
            </th>

            <th class="cell_header" style="width: 10%">
                Starting price
            </th>

            <th class="cell_header" style="width: 12%">
                Offer end date
            </th>

            <th class="cell_header" style="width: 10%">
                Status
            </th>

        </tr>

        </thead>

        <tbody>

        <tr>

            <form autocomplete="off" method="post" name="adding_form">

                <td class="cell">
                    <label for="sale">
                        <input class="adding_input adding_input_width" id="sale" maxlength="20" minlength="3"
                               placeholder="Product"
                               name="sale" pattern="^[a-zA-Z0-9_-№:,<>/-\s]{3,20}$"
                               required title="Use 3-20 Latin characters."
                               type="text"/>
                    </label>
                </td>

                <td class="cell">
                    <label for="description">
          <textarea class="adding_textarea" id="description" maxlength="150"
                    minlength="10" name="description" placeholder="Description"
                    required title="Use 10-150 Latin characters."></textarea>
                    </label>
                </td>

                <td class="cell">${sessionScope.loggedUser.fullName}</td>

                <td class="cell">
                    <label for="startprice"><input class="adding_input start_price_width" id="startprice"
                                                   maxlength="7"
                                                   minlength="1" name="startprice"
                                                   placeholder="Startprice"
                                                   pattern="^\d+(,\d{3})*(\.\d{1,2})?$"
                                                   required
                                                   title="Enter the amount in the format: 999(.99)"
                                                   type="text"/><span class="cell_header">$</span>
                    </label>
                </td>

                <td class="cell">
                    <label for="end_date"><input class="adding_input adding_input_width" id="end_date"
                                                 name="end_date" required value="${requestScope.currentDate}"
                                                 type="date" title="Select offer end date."/>
                    </label>
                </td>

                <td class="cell">
                    <label>
                        <select class="adding_input adding_input_width" name="status" required title="Select status.">
                            <option selected>At auction</option>
                            <option>Sold</option>
                        </select>
                    </label>
                </td>

        <tfoot style="text-align: center">
        <tr>
            <td colspan="6">
                <button class="adding_button" type="reset">Reset</button>
                <button class="adding_button" type="submit" id="dataSendButton">Confirm</button>
                <a class="cancel_href" href="my_products">Cancel</a>
            </td>
        </tr>
        </tfoot>

        </form>

        </tr>

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
        <p id="warningMessage">Are you sure you want to add bargaining: <span id="saleTitle"></span>
        </p>

        <div class="modal-footer">
            <button class="registration-button" id="confirmButton" type="button">OK</button>
            <button class="registration-button" id="negativeButton" type="button">Cancel</button>
        </div>

    </div>
</div>
</body>

</html>
