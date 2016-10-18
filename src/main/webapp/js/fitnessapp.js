$("#reset-table").click(function () {
  location.reload();});

$("#equipment-submit").click(function() {

//$("#tableData").empty();

    //equipment selectd
    var equipment = $(".equipment:checked");
    //plaeholder for all workout data
    var exerciseData = [];
    //place to store all my promises
    var promises = [];

    //loop over the equipment and make API calls
    equipment.each(function() {
        var def = new $.Deferred();
        $.get("https://wger.de/api/v2/exercise.json/?language=2&limit=999&status=2&equipment=" + $(this).val(), function(data) {

            //loop over the reusles and add the relevent piece to the exercise data array
            $.each(data.results, function(key, val) {
                exerciseData.push(val);
            });
            exerciseData.push(data.results);
            //let it know the promise is resolved
            def.resolve();
        });
        //push promise into promises array
        promises.push(def);
    });

    //do this when all promies have completed
    $.when.apply($, promises).then(function() {
        $.each(exerciseData, function(key, val) {
            if (typeof val.name != 'undefined') {
                console.log(val.name + " - " + val.description);

                $('#tableData').append("<tr><td>" + val.name + "</td><td>" + val.description + "</td><td>" + val.category+ "</td><td>" + val.equipment + "</td></tr>");
            }

        });
    }).then(function() {
      $('#exerciseTable').DataTable();
    });


});
