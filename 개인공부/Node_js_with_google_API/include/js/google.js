var map;
var mousedUp = false;
var markers = [];
var locations = [];
var inputLat,inputLng,inputName;
var count=0;
var pointA = null;
var pointB = null;
function initMap()
{
	
	map = new google.maps.Map(document.getElementById('map'),
	{
		center: {lat: 33.55, lng: 130.4},
		zoom: 12
	});
	
	makeMarker(map);
}

function makeMarker(map)
{
	google.maps.event.addListener(map, 'click', function(event){
		setMapOnAll(null);
		placeMarker(event.latLng);
		var result = [me.latLng.lat(), me.latLng.lng()];
        transition(result);
});
}

function placeMarker(location)
{
	var marker = new google.maps.Marker
	({
		position: location,
		map: map
	});
	markers.push(marker);
	document.getElementById("Lat").value = location.lat();
	document.getElementById("Lng").value = location.lng();
	
}

function setMapOnAll(map) 
{
	for (var i = 0; i < markers.length; i++) {
	  markers[i].setMap(map);
	}
}

function init()
{
	var input = document.getElementById('locationTextField');
	var autocomplete = new google.maps.places.Autocomplete(input);
	var Searched_lat,Searched_lng;
	
	google.maps.event.addListener(autocomplete, 'place_changed', function () 
	{
		var marker;
		var place = autocomplete.getPlace();
		var size_x = 60; 
		var size_y = 60;
		var strlength = document.getElementById("locationTextField").value;
		strlength.length;
	
		var image = new google.maps.MarkerImage( 'http://www.larva.re.kr/home/img/boximage3.png',
                '',
                '',
                new google.maps.Size(size_x, size_y));
	
    	Searched_lat = place.geometry.location.lat();
    	Searched_lng = place.geometry.location.lng();
		var markLocation = new google.maps.LatLng(Searched_lat, Searched_lng);
	
		map = new google.maps.Map(document.getElementById('map'),
		{
			center: {lat: Searched_lat, lng: Searched_lng},
			zoom: strlength.length
		});
		marker = new google.maps.Marker
		({
			position: markLocation, 
			map: map,
			icon: image,
			animation: google.maps.Animation.DROP,
			title: place.name 
		});
		markers.push(marker);
		makeMarker(map);
		if(document.getElementById("timeLine").style.display==="none")
		{
			document.getElementById("Lat").value = Searched_lat;
			document.getElementById("Lng").value = Searched_lng;
		}
  });
}

function goto(pName,inputLat,inputLng,ox)
{
	var markLocation = new google.maps.LatLng(inputLat,inputLng);
	var image = new google.maps.MarkerImage( 'http://www.larva.re.kr/home/img/boximage3.png',
                '',
                '',
                new google.maps.Size(60, 60));

	map = new google.maps.Map(document.getElementById('map'),
	{
		center: {lat: inputLat, lng: inputLng},
		zoom: 18
	});
	var marker = new google.maps.Marker
	({
		position: markLocation, 
		map: map,
		icon: image, 
		animation: google.maps.Animation.DROP,
		title: 'serachedLocation' 
	});
	markers.push(marker);
	makeMarker(map);
	if(ox === 'o')
		getRoute(pName,map,inputLat,inputLng);
}

function getRoute(pName,map,inputLat,inputLng)
{
	
	if(pointA ===null)
	{
		pointA = new google.maps.LatLng(inputLat, inputLng);
		document.getElementById("origin-input").value = pName;
	}
	else if(pointA != null && pointB ===null)
	{
		pointB = new google.maps.LatLng(inputLat, inputLng);
		document.getElementById("destination-input").value = pName;
	}
	var directionsService = new google.maps.DirectionsService;
	var directionsDisplay = new google.maps.DirectionsRenderer
	({
    map: map
	});
	
	if(document.getElementById("destination-input").value != '')
		calculateAndDisplayRoute(directionsService, directionsDisplay, pointA, pointB);
}

function removeRoute(ob1Name)
{	
	if(document.getElementById("destination-input").value != '')
	{
		if(ob1Name != document.getElementById("origin-input").value)
		{
			document.getElementById("origin-input").value = document.getElementById("destination-input").value;
			pointA = pointB;
		}
		document.getElementById("destination-input").value = '';
		pointB = null;
	}
	else if(ob1Name==='null')
	{
		document.getElementById("origin-input").value = '';
		pointA = null;
	}

}

function calculateAndDisplayRoute(directionsService, directionsDisplay, pointA, pointB) {
  directionsService.route({
    origin: pointA,
    destination: pointB,
    travelMode: google.maps.TravelMode.DRIVING
  }, function(response, status) {
    if (status == google.maps.DirectionsStatus.OK) {
      directionsDisplay.setDirections(response);
    } else {
      window.alert('Directions request failed due to ' + status);
    }
  });
}



google.maps.event.addDomListener(window, 'load', init);