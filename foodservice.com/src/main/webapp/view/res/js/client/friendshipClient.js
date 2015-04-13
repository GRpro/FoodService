function sendRequest(webServiceRootUrl, applicantId, acceptorId) {
    var state = "NEW";
    var url = webServiceRootUrl + "/resources/friendship?applicantId=" + applicantId + "&acceptorId=" + acceptorId + "&state=" + state;

    $.ajax({
        type: "POST",
        url: url,
        dataType: 'json',
        crossDomain: true,
        success: function(results) {
            alert("request was sent");
            return results;
        },
        error: function () {
            alert("ERROR");
            return null;
        }
    });
}

function deleteRequest(webServiceRootUrl, user1Id, user2Id) {
    var state = "NEW";
    var url = webServiceRootUrl + "/resources/friendship/byCouple?user1Id=" + user1Id + "&user2Id=" + user2Id;

    $.ajax({
        type: "DELETE",
        url: url,
        dataType: 'json',
        success: function(results) {
            alert("DELETED");
            return results;
        },
        error: function () {
            alert("ERROR");
            return null;
        }
    });
}

/**
 *
 * @param webServiceRootUrl
 * @param state ACCEPTED or REFUSED not NEW
 */
function decideOnRequest(webServiceRootUrl, applicantId, acceptorId, state) {
    var url = webServiceRootUrl + "/resources/friendship/byCouple?applicantId=" + applicantId + "&acceptorId=" + acceptorId + "&state=" + state;

    $.ajax({
        type: "PUT",
        url: url,
        dataType: 'json',
        success: function(results) {
            alert("State was set to " + state);
            return results;
        },
        error: function () {
            alert("ERROR");
            return null;
        }
    });
}
