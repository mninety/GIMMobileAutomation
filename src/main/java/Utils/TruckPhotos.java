package Utils;

import java.io.File;
import java.io.IOException;

import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import Utils.Randomgenerator;
import Utils.TestData;
import Utils.Timing;

public class TruckPhotos {
	
	public TruckPhotos() {
		
	}
	
	TestData testdata;
	Randomgenerator rand = new Randomgenerator();
	JsonObject json = new JsonObject();
	JsonPath jsonresponse;
	Timing timing = new Timing();
	
	public void addAllTruckPhotos(String authToken, int truckId, String file) throws IOException {

		testdata = new TestData();
		
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/truck/truckPhotos";

		jsonresponse = new JsonPath(RestAssured.given().header("authtoken", authToken)
				.multiPart("truckBackWithNumberPlate.image", new File(file))
				.queryParam("truckBackWithNumberPlate.type", 1)
				.multiPart("truckFrontWithNumberPlate.image", new File(file))
				.queryParam("truckFrontWithNumberPlate.type", 1)
				.multiPart("truckFull.image", new File(file))
				.queryParam("truckFull.type", 1)
				.multiPart("truckInnerDashboard.image", new File(file))
				.queryParam("truckInnerDashboard.type", 1)
				.queryParam("edit", false)
				.queryParam("submit", false)
				.queryParam("truckId", truckId)
				.contentType("multipart/form-data").log().all()
				.and()
				.post()
				.asString());

		 jsonresponse.prettyPrint();

	}
	
	public void addtruckFrontWithNumberPlate(String authToken, int truckId, String file) throws IOException {

		testdata = new TestData();
		
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/truck/truckPhotos";

		jsonresponse = new JsonPath(RestAssured.given().header("authtoken", authToken)
				.multiPart("truckFrontWithNumberPlate.image", new File(file))
				.queryParam("truckFrontWithNumberPlate.type", 1)
				.queryParam("edit", false)
				.queryParam("submit", false)
				.queryParam("truckId", truckId)
				.contentType("multipart/form-data")
				.and()
				.post()
				.asString());

		 jsonresponse.prettyPrint();

	}
	
	public void addtruckBackWithNumberPlate(String authToken, int truckId, String file) throws IOException {

		testdata = new TestData();
		
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/truck/truckPhotos";

		jsonresponse = new JsonPath(RestAssured.given().header("authtoken", authToken)
				.multiPart("truckBackWithNumberPlate.image", new File(file))
				.queryParam("truckBackWithNumberPlate.type", 1)
				.queryParam("edit", false)
				.queryParam("submit", false)
				.queryParam("truckId", truckId)
				.contentType("multipart/form-data")
				.and()
				.post()
				.asString());

		 jsonresponse.prettyPrint();

	}
	
	public void addTruckFullPhoto(String authToken, int truckId, String file) throws IOException {

		testdata = new TestData();
		
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/truck/truckPhotos";

		jsonresponse = new JsonPath(RestAssured.given().header("authtoken", authToken)
				.multiPart("truckFull.image", new File(file))
				.queryParam("truckFull.type", 1)
				.queryParam("edit", false)
				.queryParam("submit", false)
				.queryParam("truckId", truckId)
				.contentType("multipart/form-data")
				.and()
				.post()
				.asString());

		 jsonresponse.prettyPrint();

	}
	
	public void addTruckPhotoWithInnerDashboard(String authToken, int truckId, String file) throws IOException {

		testdata = new TestData();
		
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/truck/truckPhotos";

		jsonresponse = new JsonPath(RestAssured.given().header("authtoken", authToken)
				.multiPart("truckInnerDashboard.image", new File(file))
				.queryParam("truckInnerDashboard.type", 1)
				.queryParam("edit", false)
				.queryParam("submit", false)
				.queryParam("truckId", truckId)
				.contentType("multipart/form-data")
				.and()
				.post()
				.asString());

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
	
	public String getDataMessage() {
		return jsonresponse.getString("data.message");
	}
	
	public String getTruckStatus() {
		return jsonresponse.getString("data.status");
	}
	


}
