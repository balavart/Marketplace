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

/**
 * event when a form is submitted to the server.
 */
function onsubmitHandler() {
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
    alert("There are errors in filling out the form.");
    return false;
  }

  var saleTitle = document.adding_form.sale.value;
  return confirm(
      "Are you sure you want to add bargaining:" + " \" " + saleTitle + " \" "
      + "?");
}

