window.onload = function () {

  for (var i = 0; i < document.forms[0].elements.length; i++) {
    document.forms[0].elements[i].onfocus = function () {
      this.style.border = " 1px solid #4169E1";
    }

    document.forms[0].elements[i].onblur = function () {
      this.style.border = "1px solid Chocolate";
    }
  }
}