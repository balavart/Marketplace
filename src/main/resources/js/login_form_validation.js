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
  document.login_form.login.onchange = loginOnChange;
  document.login_form.password.onchange = passwordOnChange;
  document.login_form.onsubmit = onsubmitHandler;
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
 * event handlers for changing text in the login window.
 */
function loginOnChange() {
  var pattern = "^[a-zA-Z][a-zA-Z0-9-_\\.]{3,20}$";
  validate(this, pattern);
}

/**
 * text change event handlers in the password window.
 */
function passwordOnChange() {
  var pattern = "^[a-zA-Z0-9_-]{6,20}$";
  validate(this, pattern);
}

/**
 * event when a form is submitted to the server.
 */
function onsubmitHandler() {
  var invalid = false;

  for (var i = 0; i < document.login_form.elements.length; ++i) {
    var element = document.login_form.elements[i];
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

  var loginName = document.login_form.login.value;
  return confirm("Are you sure you want to log in as: " + " \" " + loginName + " \" ");


  // smoke.confirm ("Are you sure you want to log in as: " + " \" " + loginName + " \" ", function (result) {
  //   if (result === false) return; //Выбрали отмена
  //   smoke.alert ("Файл был удален!", function (result) {window.location = '';})
  // })
}