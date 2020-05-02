package Utils;

import java.io.IOException;

import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import Utils.Randomgenerator;
import Utils.TestData;
import Utils.Timing;

public class TruckInfo {
	
	public TruckInfo(){
		
	}
	
	TestData testdata;
	Randomgenerator rand = new Randomgenerator();
	JsonObject json = new JsonObject();
	JsonPath jsonresponse;
	Timing timing = new Timing();

	public void AddTruckInfoAPI(String authToken) throws IOException {

		testdata = new TestData();
		json.addProperty("edit", false);
		json.addProperty("truckId", 0);
		//json.addProperty("truckRegistrationNumber","");

		JsonObject truckregdetails = new JsonObject();

		truckregdetails.addProperty("regNumDistrictCode", rand.getRandomNumberInRange(1, 76));
		truckregdetails.addProperty("regNumVehicleSeries", rand.getRandomNumberInRange(1, 33));
		truckregdetails.addProperty("regNumTextOne", rand.generateRandomNumber(2));
		truckregdetails.addProperty("regNumTextTwo", rand.generateRandomNumber(4));

		json.add("truckRegistrationNumberApp", truckregdetails);

		json.addProperty("truckRegistrationDate", new Timing().PastDate(rand.getRandomNumberInRange(1, 10000)));
		json.addProperty("masterTruckTypeId", rand.getRandomNumberInRange(1, 7));
		json.addProperty("masterTruckSizeId", rand.getRandomNumberInRange(1, 79));
		json.addProperty("truckLength", rand.getRandomNumberInRange(11, 15));
		json.addProperty("truckWidth", rand.getRandomNumberInRange(11, 15));
		json.addProperty("truckHeight", rand.getRandomNumberInRange(11, 15));
		json.addProperty("masterTruckTyreId", rand.getRandomNumberInRange(1, 4));
		json.addProperty("masterTruckBrandId", rand.getRandomNumberInRange(1, 30));
		json.addProperty("truckModelName", rand.generateRandomName());
		json.addProperty("truckModelYear", "2000");

		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/truck/truckInfo";

		jsonresponse = new JsonPath(RestAssured.
				given().
				header("authtoken", authToken).
				contentType("application/json").
				body(json.toString()).
				when().log().all().
				post().
				asString());

		 jsonresponse.prettyPrint();

	}
	
	public void EditTruckInfoAPI(String authToken, int truckid) throws IOException {

		testdata = new TestData();
		json.addProperty("edit", true);
		json.addProperty("truckId", truckid);
		//json.addProperty("truckRegistrationNumber","");

		JsonObject truckregdetails = new JsonObject();

		truckregdetails.addProperty("regNumDistrictCode", rand.getRandomNumberInRange(1, 76));
		truckregdetails.addProperty("regNumVehicleSeries", rand.getRandomNumberInRange(1, 33));
		truckregdetails.addProperty("regNumTextOne", rand.generateRandomNumber(2));
		truckregdetails.addProperty("regNumTextTwo", rand.generateRandomNumber(4));

		json.add("truckRegistrationNumberApp", truckregdetails);

		json.addProperty("truckRegistrationDate", timing.PastDate(rand.getRandomNumberInRange(1, 10000)));
		json.addProperty("masterTruckTypeId", rand.getRandomNumberInRange(1, 7));
		json.addProperty("masterTruckSizeId", rand.getRandomNumberInRange(1, 79));
		json.addProperty("truckLength", rand.getRandomNumberInRange(11, 15));
		json.addProperty("truckWidth", rand.getRandomNumberInRange(11, 15));
		json.addProperty("truckHeight", rand.getRandomNumberInRange(11, 15));
		json.addProperty("masterTruckTyreId", rand.getRandomNumberInRange(1, 4));
		json.addProperty("masterTruckBrandId", rand.getRandomNumberInRange(1, 30));
		json.addProperty("truckModelName", rand.generateRandomName());
		json.addProperty("truckModelYear", rand.getRandomNumberInRange(2000, 2018));

		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/truck/truckInfo";

		jsonresponse = new JsonPath(RestAssured.
				given().
				header("authtoken", authToken).
				contentType("application/json").
				body(json.toString()).
				when().
				post().
				asString());

		 jsonresponse.prettyPrint();

	}
	
	public void AddTruckInfoAPI_FieldValidation1(String authToken, int regNumDistrictCode,
			int regNumVehicleSeries, int regNumTextOne, int regNumTextTwo, 
			String truckRegistrationDate, int masterTruckTypeId, int masterTruckSizeId, int truckLength,
			int truckWidth, int truckHeight, int masterTruckTyreId, int masterTruckBrandId,
			String truckModelName, int truckModelYear) throws IOException {

		testdata = new TestData();
		json.addProperty("edit", false);
		json.addProperty("truckId", 0);
		//json.addProperty("truckRegistrationNumber","");

		JsonObject truckregdetails = new JsonObject();

		truckregdetails.addProperty("regNumDistrictCode", regNumDistrictCode);
		truckregdetails.addProperty("regNumVehicleSeries", regNumVehicleSeries);
		truckregdetails.addProperty("regNumTextOne", regNumTextOne);
		truckregdetails.addProperty("regNumTextTwo", regNumTextTwo);

		json.add("truckRegistrationNumberApp", truckregdetails);

		json.addProperty("truckRegistrationDate", truckRegistrationDate);
		json.addProperty("masterTruckTypeId", masterTruckTypeId);
		json.addProperty("masterTruckSizeId", masterTruckSizeId);
		json.addProperty("truckLength", truckLength);
		json.addProperty("truckWidth", truckWidth);
		json.addProperty("truckHeight", truckHeight);
		json.addProperty("masterTruckTyreId", masterTruckTyreId);
		json.addProperty("masterTruckBrandId", masterTruckBrandId);
		json.addProperty("truckModelName", truckModelName);
		json.addProperty("truckModelYear", truckModelYear);

		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/truck/truckInfo";

		jsonresponse = new JsonPath(RestAssured.
				given().
				header("authtoken", authToken).
				contentType("application/json").
				body(json.toString()).
				when().
				post().
				asString());

		 jsonresponse.prettyPrint();

	}
	
	public void AddTruckInfoAPI_FieldValidation(String authToken, String truckRegistrationDate, int masterTruckTypeId, int masterTruckSizeId, int truckLength,
			int truckWidth, int truckHeight, int masterTruckTyreId, int masterTruckBrandId,
			String truckModelName, int truckModelYear) throws IOException {

		testdata = new TestData();
		json.addProperty("edit", false);
		json.addProperty("truckId", 0);
		//json.addProperty("truckRegistrationNumber","");
		json.addProperty("truckRegistrationNumber", "DHAKA METRO-TA-"+rand.generateRandomNumber(2)+"-"+rand.generateRandomNumber(4));

		/*JsonObject truckregdetails = new JsonObject();

		truckregdetails.addProperty("regNumDistrictCode", regNumDistrictCode);
		truckregdetails.addProperty("regNumVehicleSeries", regNumVehicleSeries);
		truckregdetails.addProperty("regNumTextOne", regNumTextOne);
		truckregdetails.addProperty("regNumTextTwo", regNumTextTwo);

		json.add("truckRegistrationNumberApp", truckregdetails);
		*/
		json.addProperty("truckRegistrationDate", truckRegistrationDate);
		json.addProperty("masterTruckTypeId", masterTruckTypeId);
		json.addProperty("masterTruckSizeId", masterTruckSizeId);
		json.addProperty("truckLength", truckLength);
		json.addProperty("truckWidth", truckWidth);
		json.addProperty("truckHeight", truckHeight);
		json.addProperty("masterTruckTyreId", masterTruckTyreId);
		json.addProperty("masterTruckBrandId", masterTruckBrandId);
		json.addProperty("truckModelName", truckModelName);
		json.addProperty("truckModelYear", truckModelYear);

		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/truck/truckInfo";

		jsonresponse = new JsonPath(RestAssured.
				given().
				header("authtoken", authToken).
				contentType("application/json").
				body(json.toString()).
				when().
				post().
				asString());

		 jsonresponse.prettyPrint();

	}
	
	
	public String getDataValue() {
		return jsonresponse.getString("data");
	}
	
	public String getSuccessValue() {
		return jsonresponse.getString("success");
	}
	
	public String getMessage() {
		return jsonresponse.getString("message").toString();
	}
	
	public String getResponseCode() {
		return jsonresponse.getString("responseCode").toString();
	}
	
	public int getTruckId() {
		return jsonresponse.getInt("data.truckId");
	}
	
	public String getDataMessage() {
		return jsonresponse.getString("data.message");
	}
	
	public String getTruckStatus() {
		return jsonresponse.getString("data.status");
	}

}
