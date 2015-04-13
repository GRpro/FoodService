function getRelatedUsers(id, webServiceRootUrl, appRootUrl) {

    var firstName = $('#searchFirstName').val();
    var lastName = $('#searchLastName').val();
    var ageMin = $('#searchMinAge').val();
    var ageMax = $('#searchMaxAge').val();
    var systemStatus = $('#systemStatus').val();
    var gender = $('#gender').val();

    var relationKind = $('input[name=relationKind]:radio:checked').val()
    var url = webServiceRootUrl + "/resources/users/simple/" + relationKind;

    var count = 0;
    url += "?id=" + id;
    if(firstName != "") {url += "&firstNameLike=" + firstName;}
    if(lastName != "") {url += "&lastNameLike=" + lastName;}
    if(ageMin != "") {url += "&minAge=" + ageMin;}
    if(ageMax != "") {url += "&maxAge=" + ageMax;}
    if(systemStatus != "NONE") {url += "&systemStatus=" + systemStatus;}
    if(gender != "NONE") {url += "&gender=" + gender;}

    $.ajax({
        type: "GET",
        url: url,
        dataType: 'json',
        success: function(results) {
            //switch (relationKind) {
                //case "friendsOf": {
                //
                //    break;
                //}
                //case "followersOf": {
                //
                //    break;
                //}
                //case "followedBy": {
                //
                //    break;
                //}
                //case "requestedTo": {
                //
                //    break;
                //}
                //case "requestedBy": {
                //
                //    break;
                //}
            //}
            $.each(results, function() {
                $("#resultUserList").append(
                    $('<div id="shop">' +
                    '<h3><a id="detailsLink" href="' + appRootUrl + '/visit/user/' + this.id + '">' + this.firstName + ' ' + this.lastName + '</a></h3>' +
                    '<div id="shopText">' +
                    '<p class="middleText">' + this.email + '</p>' +
                    '<p><b>Status</b> : ' + this.systemStatus + '</p></div>' +
                    '<div id="userImage">' +
                    '<img id="userImage" src="' + appRootUrl + "/content/photo/" + this.photoId + '"/></div>'));
            });
            return results;
        },
        error: function () {
            alert("ERROR");
            return null;
        }
    });
}
