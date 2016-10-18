$("#reset-table").click(function() {
    location.reload();
});

$("#equipment-submit").click(function() {

    //$("#tableData").empty();

    //equipment selectd
    var equipment = $(".equipment:checked");
    //plaeholder for all workout data
    var exerciseData = [];
    //place to store all my promises
    var promises = [];
    var equipmentText=[];

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
                switch (val.category) {
                    case 10:
                        val.category = "Abs";
                        break;

                    case 8:
                        val.category = "Arms";
                        break;

                    case 12:
                        val.category = "Back";
                        break;

                    case 14:
                        val.category = "Calves";
                        break;

                    case 11:
                        val.category = "Chest";
                        break;

                    case 9:
                        val.category = "Legs";
                        break;

                    case 13:
                        val.category = "Shoulders";
                        break;

                    default:
                }

                $.each(val.equipment,function(key,val) {

                  switch (val) {                      case 1:
                          equipmentText.push("Barbell") ;
                          break;

                      case 8:
                          equipmentText.push( "Bench");
                          break;

                      case 3:
                          equipmentText.push("Dumbbell");
                          break;

                      case 4:
                          equipmentText.push( "Gym Mat");
                          break;

                      case 9:
                          equipmentText.push( "Incline Bench");
                          break;

                      case 10:
                          equipmentText.push("Kettlebell");
                          break;

                      case 7:
                          equipmentText.push( "No Equipment Needed");
                          break;

                      case 6:
                          equipmentText.push("Pull-Up Bar");
                          break;
                      case 2:
                          equipmentText.push( "SZ-Bar");
                          break;
                      default:

                  }

                });


                $('#tableData').append("<tr><td>" + val.name + "</td><td>" + val.description + "</td><td>" + val.category + "</td><td>" + equipmentText + "</td></tr>");

                equipmentText=[];
            }

        });
    }).then(function() {
        $('#exerciseTable').DataTable();
    });


});
