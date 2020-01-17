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
  document.register_form.login.onchange = loginOnChange;
  document.register_form.password.onchange = passwordOnChange, validatePassword;
  document.register_form.password_replay.onchange = passwordOnChange, validatePassword;
  document.register_form.fullName.onchange = fullNameOnChange;
  document.register_form.city.onchange = cityOnChange;
  document.register_form.email.onchange = emailOnChange;
  document.register_form.phone.onchange = phoneOnChange;
  document.register_form.onsubmit = onsubmitHandler;
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
 * password matching method.
 */
function validatePassword() {
  var passwordReplay = document.register_form.password_replay.value;
  var password = document.register_form.password.value;

  if (password !== passwordReplay) {
    document.register_form.password_replay.setCustomValidity(
        "Passwords Don't Match");
  } else {
    document.register_form.password_replay.value.setCustomValidity('');
  }
}

/**
 * text change event handlers in the full name window.
 */
function fullNameOnChange() {
  var pattern = "^[A-Z][a-zA-Z\\-]{1,20}\\s[A-Z][a-zA-Z\\-]{1,20}(\\s[A-Z][a-zA-Z\\-]{1,20})?$";
  validate(this, pattern);
}

/**
 * event handlers for changing text in a city window.
 */
function cityOnChange() {
  var pattern = "^[a-zA-Z]+(?:[- `][a-zA-Z]+)*$";
  validate(this, pattern);
}

/**
 * text change event handlers in the email window.
 */
function emailOnChange() {
  var pattern = "^[_a-zA-Z0-9-\\+-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{2,20})$";
  validate(this, pattern);
}

/**
 * text change event handlers in the phone number window.
 */
function phoneOnChange() {
  var pattern = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$";
  validate(this, pattern);
}

/**
 * event when a form is submitted to the server.
 */
function onsubmitHandler() {
  var invalid = false;

  for (var i = 0; i < document.register_form.elements.length; ++i) {
    var element = document.register_form.elements[i];
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

  var fullName = document.register_form.fullName.value;
  return confirm("Hello" + " \" " + fullName + " \" \n" +
      "By creating a profile, you consent to the processing of personal data.");
}

