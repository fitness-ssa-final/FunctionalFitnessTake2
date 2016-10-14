package gov.ssa.functionalfitness.api;

import static org.hamcrest.CoreMatchers.equalTo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import gov.ssa.functionalfitness.entity.UserExercises;

public class ConnectToWgerAPI {

	// wger API Endpoint returning json
	protected static String endpoint = "https://wger.de/api/v2/exercise.json/?language=2&limit=999";
	// url encoding
	protected static String charset = "UTF-8";

	public static UserExercises connectingToWgerAPI(String userEquipment) {

		UserExercises exerciseList = new UserExercises();
		// ArrayList<String> printExercise= new ArrayList<>();

		try {
			String queryString = String.format("equipment=%s", URLEncoder.encode(userEquipment, charset));

			URL wgerExercises = new URL(endpoint + "&" + queryString);
			HttpURLConnection connection = (HttpURLConnection) wgerExercises.openConnection();
			connection.setRequestMethod("GET");

			if (connection.getResponseCode() != 200) {
				throw new RuntimeException("Failed:HTTP error code:" + connection.getResponseCode());
			} else {
				// read response into buffer
				BufferedReader jsonReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

				JSONParser jsonParser = new JSONParser();
				JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonReader);

				// get an array from the JSON object
				JSONArray results = (JSONArray) jsonObject.get("results");

				Iterator<?> i = results.iterator();

				// take each value from the json array separately
				while (i.hasNext()) {
					JSONObject innerObj = (JSONObject) i.next();

					exerciseList.setExerciseName(innerObj.get("name"));
					exerciseList.setExerciseComment(innerObj.get("description"));
					exerciseList.setMuscleCategory(innerObj.get("category"));
					exerciseList.setRequiredEquipmemt(innerObj.get("equipment"));

					if (exerciseList.getMuscleCategory().equals(13)) {
						exerciseList.setMuscleCategory("Shoulders");
					} else if (exerciseList.getMuscleCategory().equals(innerObj.get(10))) {
						exerciseList.setMuscleCategory("Abs");
					} else if (exerciseList.getMuscleCategory().equals(innerObj.get(8))) {
						exerciseList.setMuscleCategory("Arms");
					} else if (exerciseList.getMuscleCategory().equals(12)) {
						exerciseList.setMuscleCategory("Back");
					} else if (exerciseList.getMuscleCategory().equals(14)) {
						exerciseList.setMuscleCategory("Calves");
					} else if (exerciseList.getMuscleCategory().equals(11)) {
						exerciseList.setMuscleCategory("Chest");
					} else if (exerciseList.getMuscleCategory().equals(9)) {
						exerciseList.setMuscleCategory("Legs");
					}

					if (exerciseList.getRequiredEquipmemt().equals(1)) {
						exerciseList.setMuscleCategory("Barbell");
					} else if (exerciseList.getRequiredEquipmemt().equals(8)) {
						exerciseList.setMuscleCategory("Bench");
					} else if (exerciseList.getRequiredEquipmemt().equals(3)) {
						exerciseList.setMuscleCategory("Dumbbell");
					} else if (exerciseList.getRequiredEquipmemt().equals(4)) {
						exerciseList.setMuscleCategory("Gym Mat");
					} else if (exerciseList.getRequiredEquipmemt().equals(9)) {
						exerciseList.setMuscleCategory("Incline Bench");
					} else if (exerciseList.getRequiredEquipmemt().equals(10)) {
						exerciseList.setMuscleCategory("Kettlebell");
					} else if (exerciseList.getRequiredEquipmemt().equals(7)) {
						exerciseList.setMuscleCategory("Bodyweight Exercise");
					} else if (exerciseList.getRequiredEquipmemt().equals(6)) {
						exerciseList.setMuscleCategory("Pull-up bar");
					} else if (exerciseList.getRequiredEquipmemt().equals(5)) {
						exerciseList.setMuscleCategory("Swiss Ball");
					} else if (exerciseList.getRequiredEquipmemt().equals(2)) {
						exerciseList.setMuscleCategory("SZ-Bar");
					}

					// System.out.println("NAME: "+ innerObj.get("name")+"\n
					// Description" + innerObj.get("description")+"\n");
				}
			}
		}

		catch (IOException | ParseException e) {
			e.printStackTrace();
		}

		return exerciseList;

	}// connectingToWgerAPI
} // class
