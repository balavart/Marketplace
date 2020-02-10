<%--
  Created by IntelliJ IDEA.
  User: Vardan Balaian
  Date: 31.01.2020
  Time: 0:46
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta content="The site was developed by student Balaian Vardan" name="description">
    <meta content="en" http-equiv="content-language">
    <meta content="Vardan Balaian" name="author">
    <meta content="copyright held by Balaian Vardan" name="copyright">

    <link href="css/element_styles.css" rel="stylesheet">
    <link href="css/text_styles.css" rel="stylesheet">
    <link href="css/hover_styles.css" rel="stylesheet">
    <link href="css/modal_style.css" rel="stylesheet">

    <script src="js/login_form_validation.js" type="text/javascript"></script>
    <script src="js/elements_change.js" type="text/javascript"></script>

    <title>Marketplace Login</title>
</head>

<body>

<h1 class="site_name_decor">Online MarketPlace</h1>

<c:if test="${sessionScope.loggedUser==null && sessionScope.guestMod==null}">
    <a class="navigation_link" href="product_table">Auction table</a>
</c:if>

<c:if test="${sessionScope.loggedUser!=null}">
    <a class="navigation_link" href="product_table">Auction table</a>
</c:if>

<c:if test="${sessionScope.guestMod}">
    <a class="navigation_link" href="guest_product_table">Auction table</a>
</c:if>

<a class="navigation_link" href="homepage">Homepage</a>
<a class="navigation_link" href="registration">Registration</a>

<div class="content">

    <c:if test="${sessionScope.loggedUser!=null}">
        <div class="logout_form">
            <span class="user_name_display">${sessionScope.loginName}</span>
            <a class="logout_link" href="logout">log out</a>
        </div>
    </c:if>

    <c:if test="${sessionScope.guestMod}">
        <div class="logout_form">
            <span class="user_name_display">Guest</span>
            <a class="logout_link" href="logout">log out</a>
        </div>
    </c:if>

    <form name="login_form" autocomplete="on" method="post">
        <fieldset>

            <legend class="legend_text">
                Login form
            </legend>

            <div class="login_panel">

                <p>
                    <label class="label_input" for="login">
                        Login:
                        <input class="user_data_input" id="login" maxlength="20" minlength="4"
                               name="login"
                               pattern="^[a-zA-Z][a-zA-Z0-9-_\.]{3,20}$"
                               placeholder="Enter username"
                               required
                               title="Use 4-20 Latin characters. Only letters, numbers, hyphens and underscores are allowed. The first character must be a letter."
                               type="text"/>
                    </label>
                </p>

                <p>
                    <br>
                    <label class="label_input" for="password">
                        Password:
                        <input class="user_data_input" id="password" maxlength="20"
                               minlength="6" name="password"
                               pattern="^[a-zA-Z0-9_-]{6,20}$"
                               placeholder="Enter password" required
                               title="Use 6-20 Latin characters. Only letters, numbers, hyphens and underscores are allowed."
                               type="password"/>
                    </label>
                </p>
                <br>

                <span class="error_form">
                    ${requestScope.errorMessage}
                </span>

                <hr>

                <p>
                    <button class="registration-button" id="dataSendButton" type="submit">Sign in
                    </button>
                    <button class="registration-button" type="reset">Reset form</button>
                </p>

                <p>
                    <a class="guest_link" href="guest_product_table">Log in as a guest</a>
                </p>

            </div>

        </fieldset>

    </form>

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
        <p id="warningMessage">Are you sure you want to log in as: <span id="userName"></span></p>

        <div class="modal-footer">
            <button class="registration-button" id="confirmButton" type="button">OK</button>
            <button class="registration-button" id="negativeButton" type="button">Cancel</button>
        </div>

    </div>
</div>

</body>
</html>
