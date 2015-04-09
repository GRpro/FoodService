function ShopsByCriterion(webServiceRootUrl, appRootUrl) {

    var nameLike = $('#searchName').val();
    var regionLike = $('#searchRegion').val();
    var cityLike = $('#searchCity').val();
    var streetLike = $('#searchStreet').val();
    var buildingLike = $('#searchBuilding').val();
    var minRating = $('#searchMinRating').val();
    var maxRating = $('#searchMaxRating').val();

    var url = webServiceRootUrl + "/resources/shops/byCriterion";

    var count = 0;
    if(nameLike != "") {url += ((count==0?"?":"&") + "nameLike=" + nameLike); count++}
    if(minRating != "") {url += ((count==0?"?":"&") + "minRating=" + minRating); count++}
    if(maxRating != "") {url += ((count==0?"?":"&") + "maxRating=" + maxRating); count++}
    if(regionLike != "") {url += ((count==0?"?":"&") + "regionLike=" + regionLike); count++}
    if(cityLike != "") {url += ((count==0?"?":"&") + "cityLike=" + cityLike); count++}
    if(streetLike != "") {url += ((count==0?"?":"&") + "streetLike=" + streetLike); count++}
    if(buildingLike != "") {url += ((count==0?"?":"&") + "buildingLike=" + buildingLike); count++}

    $.ajax({
        type: "GET",
        url: url,
        dataType: 'jsonp',
        jsonpCallback: 'callback',
        //crossOrigin: true,
        success: function(results) {
            var bar = document.getElementById("centralBar");

            $.each(results, function() {

                $("#resultShopList").append(
                    $('<div id="shop">' +
                    '<h3><a id="detailsLink" href="">' + this.name + '</a></h3>' +
                    '<div id="shopText">' +
                    '<p class="middleText">' + this.location.city + ', ' + this.location.street + ' ' + this.location.building + '</p>' +
                    '<p><b>Description</b> : ' + this.description + '</p></div>' +
                    '<div id="shopImage">' +
                    '<img height="100px" src="' + appRootUrl + "/content/photo/" + this.primaryPhotoId + '"/></div>'));
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