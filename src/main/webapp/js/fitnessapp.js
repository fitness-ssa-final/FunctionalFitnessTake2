//Reload page when "Reset" is clicked
$("#reset-table").click(function() {
    location.reload();
});

//Do this when submit button is pressed
$("#equipment-submit").click(function() {

    //Equipment selected
    var equipment = $(".equipment:checked");
    //Placeholder for all workout data
    var exerciseData = [];
    //Place to store all my promises
    var promises = [];
    //Place to store all equipment text
    var equipmentText=[];

    //Loop over the equipment and make API calls. Store in 'exerciseData' array
    equipment.each(function() {
        var def = new $.Deferred();
        $.get("https://wger.de/api/v2/exercise.json/?language=2&limit=999&status=2&equipment=" + $(this).val(), function(data) {

            //Loop over the API results and add the relevent piece to the exercise data array
            $.each(data.results, function(key, val) {
                exerciseData.push(val);
            });

            exerciseData.push(data.results);
            //Let it know the promise is resolved
            def.resolve();
        });

        //Add promise to promises array
        promises.push(def);
    });

    //Do this when all promies have completed
    $.when.apply($, promises).then(function() {

      //Loop over 'exceriseData' array
        $.each(exerciseData, function(key, val) {

          //If the 'name' area has somthing in it then replace it with the text in the switch statment
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

                //Loop over equipment array and replace with text below
                $.each(val.equipment,function(key,val) {

                  switch (val) {
                      case 1:
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
                //Add this to the data table.
                $('#tableData').append("<tr><td>" + val.name + "</td><td>" + val.description + "</td><td>" + val.category + "</td><td>" + equipmentText + "</td></tr>");

                //clear the equipmentText array
                equipmentText=[];
            }
        });
    })

    //After those promises are completed format the table.
    .then(function() {
        $('#exerciseTable').DataTable();
    });
});
