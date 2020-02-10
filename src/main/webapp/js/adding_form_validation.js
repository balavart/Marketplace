/**
 * registration of a document upload event.
 */
if (window.addEventListener) {
  window.addEventListener("load", init, false);
} else if (window.attachEvent) {
  window.attachEvent("onload", init);
}

/**
 * register event handlers for form elements.
 */
function init() {
  document.adding_form.sale.onchange = saleOnChange;
  document.adding_form.description.onchange = descriptionOnChange;
  document.adding_form.startprice.onchange = startpriceOnChange;
  document.adding_form.onsubmit = onsubmitHandler;
  var sendBtn = document.getElementById("dataSendButton");
  sendBtn.onclick = onsubmitHandler;
}

/**
 * method of checking the value in an element by regular expression.
 */
function validate(element, pattern) {
  var result = element.value.search(pattern);

  if (result === -1) {
    element.classList.add("invalid");
    element.classList.remove("valid");
  } else {
    element.classList.add("valid");
    element.classList.remove("invalid");
  }
}

/**
 * event handlers for changing text in the sale window.
 */
function saleOnChange() {
  var pattern = "^[a-zA-Z0-9_-№:,<>/-\\s]{3,20}$";
  validate(this, pattern);
}

/**
 * event handlers for changing text in the description window.
 */
function descriptionOnChange() {
  var pattern = "^[a-zA-Z0-9_-№:,<>/-\\s]{10,150}$";
  validate(this, pattern);
}

/**
 * event handlers for changing text in the startprice window.
 */
function startpriceOnChange() {
  var pattern = "^\\d+(,\\d{3})*(\\.\\d{1,2})?$";
  validate(this, pattern);
}

function invalidFilling() {

// Get the modal
  var modal = document.getElementById("wrongFillingModal");
  modal.style.display = "block";

  // Get the button that opens the modal
  var exitBtn = document.getElementById("exitButton");

  // Get the <span> element that closes the modal
  var span = modal.getElementsByClassName("close")[0];

  exitBtn.onclick = function () {
    modal.style.display = "none";
  };

  // When the user clicks on <span> (x), close the modal
  span.onclick = function () {
    modal.style.display = "none";
  };

  // When the user clicks anywhere outside of the modal, close it
  window.onclick = function (event) {
    if (event.target === modal) {
      modal.style.display = "none";
    }
  }
}

function warning(enteredSaleTitle) {

// Get the modal
  var modal = document.getElementById("warningModal");
  modal.style.display = "block";

  var saleTitle = document.getElementById("saleTitle");
  saleTitle.innerHTML = enteredSaleTitle;

  // Get the button that opens the modal
  var okBtn = document.getElementById("confirmButton");
  var cancelBtn = document.getElementById("negativeButton");

  // Get the <span> element that closes the modal
  var span = modal.getElementsByClassName("close")[0];

  okBtn.onclick = function () {
    modal.style.display = "none";
    document.forms.adding_form.submit();
  };

  cancelBtn.onclick = function () {
    modal.style.display = "none";
  };

  // When the user clicks on <span> (x), close the modal
  span.onclick = function () {
    modal.style.display = "none";
  };

  // When the user clicks anywhere outside of the modal, close it
  window.onclick = function (event) {
    if (event.target === modal) {
      modal.style.display = "none";
    }
  }
}

/**
 * event when a form is submitted to the server.
 */
function onsubmitHandler(event) {
  event.preventDefault();

  var invalid = false;

  for (var i = 0; i < document.adding_form.elements.length; ++i) {
    var element = document.adding_form.elements[i];
    if (element.type === "text" && element.onchange) {
      element.onchange();
      if (element.classList.contains("invalid")) {
        invalid = true;
      }
    }
  }

  if (invalid) {
    invalidFilling();
  } else {
    warning(document.adding_form.sale.value);
  }
}

