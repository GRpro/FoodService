
$(document).ready(function() {
	$("ul#nav li a").addClass("js");
	$("ul#nav li a").hover(
      function () {
        $(this).stop(true,true).animate({backgroundPosition:"(0 0)"}, 200);
        $(this).animate({backgroundPosition:"(0 -5px)"}, 150);
      }, 
      function () {
        $(this).animate({backgroundPosition:"(0 -199px)"}, 200);

      }
    );
});

function checkPasswords() {
    var pass = document.getElementById("password");
    var passconfirm = document.getElementById("passwordconfirm");
    var button = document.getElementById("submitSimpleUserForm");
    if (pass.value && passconfirm.value) {
        if (pass.value == passconfirm.value) {
            pass.setAttribute("style", "border-width: 1px; border-style: solid; border-color: #00FF00");
            passconfirm.setAttribute("style", "border-width: 1px; border-style: solid; border-color: #00FF00");
            button.removeAttribute("disabled");
        } else {
            pass.setAttribute("style", "border-width: 1px; border-style: solid; border-color: #FF0000");
            passconfirm.setAttribute("style", "border-width: 1px; border-style: solid; border-color: #FF0000");
            button.setAttribute("disabled", "true");
        }
    } else {
            pass.setAttribute("style", "border-width: 1px; border-style: dotted; border-color: #000000");
            passconfirm.setAttribute("style", "border-width: 1px; border-style: dotted; border-color: #000000");        
    }
}

function dropList() {
    var list = document.getElementById("dropList");
    var li1 = document.createElement('li');
    li1.innerHTML = "user";
    list.appendChild(li1);
}

