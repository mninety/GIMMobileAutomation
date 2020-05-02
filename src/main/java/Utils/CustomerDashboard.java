package Utils;

import java.io.IOException;
import java.time.Instant;

import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import Utils.TestData;

public class CustomerDashboard {
	
	public CustomerDashboard() {
		
	}
	
	TestData testdata;
	JsonObject json = new JsonObject();
	JsonPath jsonresponse;
	
	public void CustomerDashboardAPI_FilterByGoodsType(String authToken, int page, int size, int goodsType) throws IOException {
		
		testdata = new TestData();
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/customer/dashboard/trips";
		
		jsonresponse = new JsonPath(RestAssured.
				given().
				header("authtoken", authToken).
				queryParam("page", page).
				queryParam("size", size).
				queryParam("goodsType", goodsType).
				contentType("application/json").
				body(json.toString()).
				when().
				get().
				asString());
		
		jsonresponse.prettyPrint();
			
	}

public void CustomerDashboardAPI(String authToken, int page, int size) throws IOException {
		
		testdata = new TestData();
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/customer/dashboard/trips";
		
		jsonresponse = new JsonPath(RestAssured.
				given().
				header("authtoken", authToken).
				queryParam("page", page).
				queryParam("size", size).
				contentType("application/json").
				body(json.toString()).
				when().
				get().
				asString());
		
		jsonresponse.prettyPrint();
			
	}

public void CustomerDashboardAPI_FilterByFromPickupDate(String authToken, int page, int size, String frompickupdatetime) throws IOException {
	
	testdata = new TestData();
	RestAssured.baseURI = testdata.properties.getProperty("env");
	RestAssured.basePath = "/api/v1/customer/dashboard/trips";
	
	jsonresponse = new JsonPath(RestAssured.
			given().
			header("authtoken", authToken).
			queryParam("page", page).
			queryParam("size", size).
			queryParam("fromPickupDate", frompickupdatetime).
			contentType("application/json").
			body(json.toString()).log().all().
			when().
			get().
			asString());
	
	jsonresponse.prettyPrint();
		
}

public void CustomerDashboardAPI_FilterByToPickupDate(String authToken, int page, int size, String topickupdatetime) throws IOException {
	
	testdata = new TestData();
	RestAssured.baseURI = testdata.properties.getProperty("env");
	RestAssured.basePath = "/api/v1/customer/dashboard/trips";
	
	jsonresponse = new JsonPath(RestAssured.
			given().
			header("authtoken", authToken).
			queryParam("page", page).
			queryParam("size", size).
			queryParam("toPickupDate", topickupdatetime).
			contentType("application/json").
			body(json.toString()).
			when().
			get().
			asString());
	
	jsonresponse.prettyPrint();
		
}

public void CustomerDashboardAPI_FilterByPickupDates(String authToken, int page, int size, String frompickupdate, String topickupdatetime) throws IOException {
	
	testdata = new TestData();
	RestAssured.baseURI = testdata.properties.getProperty("env");
	RestAssured.basePath = "/api/v1/customer/dashboard/trips";
	
	jsonresponse = new JsonPath(RestAssured.
			given().
			header("authtoken", authToken).
			queryParam("page", page).
			queryParam("size", size).
			queryParam("fromPickupDate", frompickupdate).
			queryParam("toPickupDate", topickupdatetime).
			contentType("application/json").
			body(json.toString()).
			when().
			get().
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
	
	public int getNumberofResults() {
		return Integer.parseInt(jsonresponse.getString("data.numberOfResults"));
	}
	
	public int getTripID() {
		return Integer.parseInt(jsonresponse.getString("data.contentList[0].dashboardTripModels[0].tripId"));
	}
	

}
