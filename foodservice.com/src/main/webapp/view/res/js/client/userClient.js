function usersByCriterion(webServiceRootUrl, appRootUrl) {

    var firstName = $('#searchFirstName').val();
    var lastName = $('#searchLastName').val();
    var ageMin = $('#searchMinAge').val();
    var ageMax = $('#searchMaxAge').val();
    var systemStatus = $('#systemStatus').val();
    var gender = $('#gender').val();

    var userType = $('input[name=userType]:radio:checked').val()
    var url = webServiceRootUrl + "/resources/users/" + userType + "/byCriterion";

    var count = 0;
    if(firstName != "") {url += ((count==0?"?":"&") + "firstNameLike=" + firstName); count++}
    if(lastName != "") {url += ((count==0?"?":"&") + "lastNameLike=" + lastName); count++}
    if(ageMin != "") {url += ((count==0?"?":"&") + "minAge=" + ageMin); count++}
    if(ageMax != "") {url += ((count==0?"?":"&") + "maxAge=" + ageMax); count++}
    if(systemStatus != "NONE") {url += ((count==0?"?":"&") + "systemStatus=" + systemStatus); count++}
    if(gender != "NONE") {url += ((count==0?"?":"&") + "gender=" + gender); count++}

    $.ajax({
        type: "GET",
        url: url,
        dataType: 'jsonp',
        jsonpCallback: 'callback',
        success: function(results) {
            var bar = document.getElementById("centralBar");

            $.each(results, function() {

                $("#resultUserList").append(
                    $('<div id="shop">' +
                    '<h3><a id="detailsLink" href="">' + this.firstName + ' ' + this.lastName + '</a></h3>' +
                    '<div id="shopText">' +
                    '<p class="middleText">' + this.email + '</p>' +
                    '<p><b>Description</b> : ' + this.systemStatus + '</p></div>' +
                    '<div id="userImage">' +
                    '<img id="userImage" src="' + appRootUrl + "/content/photo/" + this.photoId + '"/></div>'));
            });
            //alert(results);
            return results;
        },
        error: function () {
            //alert("ERROR");
            return null;
        }
    });
}