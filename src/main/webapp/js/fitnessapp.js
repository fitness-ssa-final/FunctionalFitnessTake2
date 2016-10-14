//Old table

// var populateData= $.get('https://wger.de/api/v2/exercise.json/?language=2&limit=1000000&equipment=3',
//         function(data) {
//             var muscleCategories = [{
//                 "id": 10,
//                 "name": "Abs"
//             }, {
//                 "id": 8,
//                 "name": "Arms"
//             }, {
//                 "id": 12,
//                 "name": "Back"
//             }, {
//                 "id": 14,
//                 "name": "Calves"
//             }, {
//                 "id": 11,
//                 "name": "Chest"
//             }, {
//                 "id": 9,
//                 "name": "Legs"
//             }, {
//                 "id": 13,
//                 "name": "Shoulders"
//             }];
//
//             var equipmentNames = [{
//                 "id": 1,
//                 "name": "Barbell"
//             }, {
//                 "id": 8,
//                 "name": "Bench"
//             }, {
//                 "id": 3,
//                 "name": "Dumbbell"
//             }, {
//                 "id": 4,
//                 "name": "Gym mat"
//             }, {
//                 "id": 9,
//                 "name": "Incline bench"
//             }, {
//                 "id": 10,
//                 "name": "Kettlebell"
//             }, {
//                 "id": 7,
//                 "name": "none (bodyweight exercise)"
//             }, {
//                 "id": 6,
//                 "name": "Pull-up bar"
//             }, {
//                 "id": 5,
//                 "name": "Swiss Ball"
//             }, {
//                 "id": 2,
//                 "name": "SZ-Bar"
//             }];
//             for (var i = 0; i < data.results.length; i++) {
//
//                 var name = data.results[i].name;
//                 var description = data.results[i].description;
//                 var muscleCategory = data.results[i].category;
//                 var equipment = data.results[i].equipment;
//
//                 $.each(muscleCategories, function(key, val) {
//                     if (val.id == muscleCategory) {
//                         muscleCategoryText = val.name;
//                         return true;
//                     }
//
//                 });
//
//                 $.each(equipmentNames, function(key, val) {
//
//                     if (val.id == equipment) {
//                         equipmentText = val.name;
//                         return true;
//                     }
//
//                 });
//
//
//
//                 $('#tableData').append("<tr><td>" + name + "</td><td>" + description + "</td><td>" + muscleCategoryText + "</td><td>" + equipmentText + "</td></tr>");
//
//
//
//
//             }
//
//             $.when(populateData).done(function() {
//               $('#exerciseTable').DataTable();
//             });
//
//         });

//new table data
<!DOCTYPE html>
<html>
<head>
	<title></title>
</head>
<body>

<input class="equipment" type="checkbox" checked name="equipment[]" value="1" />barbell<br />
<input class="equipment" type="checkbox" checked name="equipment[]" value="2" />sz-bar<br />
<input class="equipment" type="checkbox" checked name="equipment[]" value="3" />dumbbell<br />
<input class="equipment" type="checkbox" checked name="equipment[]" value="4" />Gym Matt<br />

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

<script>

$(function(){


	//equipment selectd
	var equipment = $(".equipment:checked")
	//plaeholder for all workout data
	var exerciseData = []
	//place to store all my promises
	var promises = [];

	//loop over the equipment and make API calls
	equipment.each(function(){
		var def = new $.Deferred();
		$.get("https://wger.de/api/v2/exercise.json/?language=2&limit=999&status=2&equipment=" + $(this).val(), function(data){

			//loop over the reusles and add the relevent piece to the exercise data array
			$.each(data.results, function(key, val){
				exerciseData.push(val)
			})
			exerciseData.push(data.results)
			//let it know the promise is resolved
			def.resolve()
		})
		//push promise into promises array
		promises.push(def);
	})

	//do this when all promies have completed
	$.when.apply($, promises).then(function () {
    	$.each(exerciseData, function(key, val){
    		if(typeof val.name != 'undefined'){
    			console.log(val.name + " - " + val.description)
    		}
    	})
	});

})

</script>
</body>
</html>
