package Utils;

import static org.hamcrest.Matchers.lessThan;

import java.io.IOException;
import java.util.Optional;

import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import Utils.TestData;

public class ValidateTruck {
	
	public ValidateTruck() {
		
	}
	
	TestData testdata;
	JsonObject json = new JsonObject();
	JsonPath jsonresponse;
	
	public void newTruckPreValidate(String authToken, int step, String truckID, 
			String truckFitnessCertificatePhoto, 
			String truckFitnessExpiryDate, 
			String truckInsuranceExpiryDate, 
			String truckInsurancePhoto,
			String truckModel,
			String truckModelYear,
			String truckRegisteredOwnerAddress,
			String truckRegisteredOwnerPhoto,
			String truckRegisteredOwnerName,
			String truckRegistrationDate,
			String truckRegistrationNumber,
			String truckRegistrationPhoto,
			String truckRoutePermitExpiryDate,
			String truckRoutePermitPhoto,
			String truckTaxTokenExpiryDate,
			String truckTaxTokenPhoto,
			int masterTruckBrandId,
			int masterTruckSizeId,
			int masterTruckTypeId,
			int masterTruckTyreId,
			String truckBackWithNumberPlate, 
			String truckFrontWithNumberPlate, 
			String truckFull, 
			String truckInnerDashboard
			) throws IOException {
		
		testdata = new TestData();
		json.addProperty("step", step);
		json.addProperty("truckID", truckID);
		json.addProperty("truckFitnessCertificatePhoto", truckFitnessCertificatePhoto);
		json.addProperty("truckFitnessExpiryDate", truckFitnessExpiryDate);
		json.addProperty("truckInsuranceExpiryDate", truckInsuranceExpiryDate);
		json.addProperty("truckInsurancePhoto", truckInsurancePhoto);
		json.addProperty("truckModel", truckModel);
		json.addProperty("truckModelYear", truckModelYear);
		json.addProperty("truckRegisteredOwnerAddress", truckRegisteredOwnerAddress);
		json.addProperty("truckRegisteredOwnerPhoto", truckRegisteredOwnerPhoto);
		json.addProperty("truckRegisteredOwnerName", truckRegisteredOwnerName);
		json.addProperty("truckRegistrationDate", truckRegistrationDate);
		json.addProperty("truckRegistrationNumber", truckRegistrationNumber);
		json.addProperty("truckRegistrationPhoto", truckRegistrationPhoto);
		json.addProperty("truckRoutePermitExpiryDate", truckRoutePermitExpiryDate);
		json.addProperty("truckRoutePermitPhoto", truckRoutePermitPhoto);
		json.addProperty("truckTaxTokenExpiryDate", truckTaxTokenExpiryDate);
		json.addProperty("truckTaxTokenPhoto", truckTaxTokenPhoto);
		json.addProperty("masterTruckBrandId", masterTruckBrandId);
		json.addProperty("masterTruckSizeId", masterTruckSizeId);
		json.addProperty("masterTruckTypeId", masterTruckTypeId);
		json.addProperty("masterTruckTyreId", masterTruckTyreId);
		
		JsonObject truckimageinfo = new JsonObject();
		
		truckimageinfo.addProperty("truckBackWithNumberPlate",truckBackWithNumberPlate);
		truckimageinfo.addProperty("truckFrontWithNumberPlate",truckFrontWithNumberPlate);
		truckimageinfo.addProperty("truckFull",truckFull);
		truckimageinfo.addProperty("truckInnerDashboard",truckInnerDashboard);
		
		json.add("userTruckPhoto", truckimageinfo);
		
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/truck/validate";
		
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
	
	public void newTruckPreValidate_ResponseTime(Long Benchmarktime, String authToken, int step, String truckID, 
			String truckFitnessCertificatePhoto, 
			String truckFitnessExpiryDate, 
			String truckInsuranceExpiryDate, 
			String truckInsurancePhoto,
			String truckModel,
			String truckModelYear,
			String truckRegisteredOwnerAddress,
			String truckRegisteredOwnerPhoto,
			String truckRegisteredOwnerName,
			String truckRegistrationDate,
			String truckRegistrationNumber,
			String truckRegistrationPhoto,
			String truckRoutePermitExpiryDate,
			String truckRoutePermitPhoto,
			String truckTaxTokenExpiryDate,
			String truckTaxTokenPhoto,
			int masterTruckBrandId,
			int masterTruckSizeId,
			int masterTruckTypeId,
			int masterTruckTyreId,
			String truckBackWithNumberPlate, 
			String truckFrontWithNumberPlate, 
			String truckFull, 
			String truckInnerDashboard
			) throws IOException {
		
		testdata = new TestData();
		json.addProperty("step", step);
		json.addProperty("truckID", truckID);
		json.addProperty("truckFitnessCertificatePhoto", truckFitnessCertificatePhoto);
		json.addProperty("truckFitnessExpiryDate", truckFitnessExpiryDate);
		json.addProperty("truckInsuranceExpiryDate", truckInsuranceExpiryDate);
		json.addProperty("truckInsurancePhoto", truckInsurancePhoto);
		json.addProperty("truckModel", truckModel);
		json.addProperty("truckModelYear", truckModelYear);
		json.addProperty("truckRegisteredOwnerAddress", truckRegisteredOwnerAddress);
		json.addProperty("truckRegisteredOwnerPhoto", truckRegisteredOwnerPhoto);
		json.addProperty("truckRegisteredOwnerName", truckRegisteredOwnerName);
		json.addProperty("truckRegistrationDate", truckRegistrationDate);
		json.addProperty("truckRegistrationNumber", truckRegistrationNumber);
		json.addProperty("truckRegistrationPhoto", truckRegistrationPhoto);
		json.addProperty("truckRoutePermitExpiryDate", truckRoutePermitExpiryDate);
		json.addProperty("truckRoutePermitPhoto", truckRoutePermitPhoto);
		json.addProperty("truckTaxTokenExpiryDate", truckTaxTokenExpiryDate);
		json.addProperty("truckTaxTokenPhoto", truckTaxTokenPhoto);
		json.addProperty("masterTruckBrandId", masterTruckBrandId);
		json.addProperty("masterTruckSizeId", masterTruckSizeId);
		json.addProperty("masterTruckTypeId", masterTruckTypeId);
		json.addProperty("masterTruckTyreId", masterTruckTyreId);
		
		JsonObject truckimageinfo = new JsonObject();
		
		truckimageinfo.addProperty("truckBackWithNumberPlate",truckBackWithNumberPlate);
		truckimageinfo.addProperty("truckFrontWithNumberPlate",truckFrontWithNumberPlate);
		truckimageinfo.addProperty("truckFull",truckFull);
		truckimageinfo.addProperty("truckInnerDashboard",truckInnerDashboard);
		
		json.add("userTruckPhoto", truckimageinfo);
		
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/truck/validate";
		
		RestAssured
		.given()
		.header("authtoken", authToken)
		.contentType("application/json")
		.body(json.toString())
		.when()
		.post()
		.then()
		.and()
		.time(lessThan(Benchmarktime));
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
	
}
