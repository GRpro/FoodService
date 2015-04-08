
$(document).ready(function() {
	$("ul#nav li a").addClass("js");
	$("ul#nav li a").hover(
      function () {
        $(this).stop(true,true).animate({backgroundPosition:"(0 0)"}, 200);
        $(this).animate({backgroundPosition:"(0 -5px)"}, 150);
      }, 
      function () {
        $(this).animate({backgroundPosition:"(0 -149px)"}, 200);

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
    var li1 = document.createElement('a');
    var li2 = document.createElement('a');
    var li3 = document.createElement('a');
    li1.setAttribute("href", '/foodservice.com/form/simpleuser');
    li2.setAttribute("href", '/foodservice.com/form/manager');
    li3.setAttribute("href", '/foodservice.com/form/shopadmin');
    li1.innerHTML = "<li>user</li>";
    li2.innerHTML = "<li>manager</li>";
    li3.innerHTML = "<li>admin</li>";    
    if (list.getElementsByTagName('li').length == 0) {
        list.appendChild(li1);
        list.appendChild(li2);
        list.appendChild(li3);
    } else {
        list.removeChild(list.lastChild);
        list.removeChild(list.lastChild);
        list.removeChild(list.lastChild);
    }
}

