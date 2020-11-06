function checkTime() {
    var departTime = new Date($('input[name="departTime"]').val());
    var arriveTime = new Date($('input[name="arriveTime"]').val());
    if (departTime.getTime() < Date.now()){
        document.getElementById("departTime").setCustomValidity("Incorrect departure time. Select a date starting from today");
    } else if (departTime.getTime() > arriveTime.getTime()) {
        document.getElementById("arriveTime").setCustomValidity("Incorrect arrival time.Select a date after the departure date");
    } else {
        document.getElementById("departTime").setCustomValidity('');
        document.getElementById("arriveTime").setCustomValidity('');
    }
}

function checkSameAirports() {
    let elementById = document.getElementById('departureAirport');
    let e = elementById.options[elementById.selectedIndex].value;
    let elementById1 = document.getElementById('destinationAirport');
    let ee = elementById1.options[elementById1.selectedIndex].value;
    if (e === ee){
        document.getElementById("departureAirport").setCustomValidity("Departure and arrival airports can't be the same");
    } else {
        document.getElementById("departureAirport").setCustomValidity('');
    }
}
