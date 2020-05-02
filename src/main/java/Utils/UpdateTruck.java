package Utils;

import java.io.IOException;

import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import Utils.TestData;

public class UpdateTruck {
	
	public UpdateTruck() {
		
	}
	
	TestData testdata;
	JsonObject json = new JsonObject();
	JsonPath jsonresponse;
	
	public void TruckDetailsUpdateAPI(String authToken, int truckId,
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
			String truckRegistrationPhotoBackSide,
			String truckRoutePermitExpiryDate,
			String truckRoutePermitPhoto,
			String truckTaxTokenExpiryDate,
			String truckTaxTokenPhoto,
			String truckNocCertificatePhoto,
			String truckBankDocumentPhoto,
			String truckCaseDocumentPhoto,
			int masterTruckBrandId,
			int masterTruckSizeId,
			int masterTruckTypeId,
			int masterTruckTyreId,
			String truckBackWithNumberPlate, 
			String truckFrontWithNumberPlate, 
			String truckFull, 
			String truckInnerDashboard,
			String modelName,
			String truckBaseStand,
			int truckDimensionLength,
			int truckDimensionWidth,
			int truckDimensionHeight,
			String truckOwnerNameInNOC,
			String truckOwnerAddressInNOC,
			String truckOwnerMobileNoInNOC) throws IOException {
		
		testdata = new TestData();
		json.addProperty("truckId", truckId);
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
		json.addProperty("truckRegistrationPhotoBackSide", truckRegistrationPhotoBackSide);
		json.addProperty("truckRoutePermitExpiryDate", truckRoutePermitExpiryDate);
		json.addProperty("truckRoutePermitPhoto", truckRoutePermitPhoto);
		json.addProperty("truckTaxTokenExpiryDate", truckTaxTokenExpiryDate);
		json.addProperty("truckTaxTokenPhoto", truckTaxTokenPhoto);
		json.addProperty("truckNocCertificatePhoto", truckNocCertificatePhoto);
		json.addProperty("truckBankDocumentPhoto", truckBankDocumentPhoto);
		json.addProperty("truckCaseDocumentPhoto", truckCaseDocumentPhoto);
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
		json.addProperty("modelName", modelName);
		json.addProperty("truckBaseStand", truckBaseStand);
		json.addProperty("truckDimensionLength", truckDimensionLength);
		json.addProperty("truckDimensionWidth", truckDimensionWidth);
		json.addProperty("truckDimensionHeight", truckDimensionHeight);
		json.addProperty("truckOwnerNameInNOC", truckOwnerNameInNOC);
		json.addProperty("truckOwnerAddressInNOC", truckOwnerAddressInNOC);
		json.addProperty("truckOwnerMobileNoInNOC", truckOwnerMobileNoInNOC);
		
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/truck/update";
		
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
	
	public int getTruckID() {
		return Integer.parseInt(jsonresponse.getString("data.id").toString());
	}
	
	public int getTruckStatus() {
		return Integer.parseInt(jsonresponse.getString("data.truckStatus").toString());
	}
	


}

