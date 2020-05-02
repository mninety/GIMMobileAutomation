package Utils;

import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import Utils.Randomgenerator;
import Utils.TestData;
import static org.hamcrest.Matchers.lessThan;
import java.io.IOException;
import Utils.Timing;

public class GIMSignUp {
	
	public GIMSignUp() {
		
	}
	TestData testdata;
	Randomgenerator rand = new Randomgenerator();
	JsonPath jsonresponse;
	
	//public String mobileno = rand.generateRandomNumber(11);
	public String password = "pass01";

public void byroleId(int roleID) throws IOException {
	testdata = new TestData();
	JsonObject json = new JsonObject();
    
	if(roleID!=2) {	  
		json.addProperty("deviceToken", rand.generateRandomNumber(6));
		json.addProperty("deviceType", "ANDROID");
		json.addProperty("dob", new Timing().PastDate(rand.getRandomNumberInRange(20000, 21000)));
		json.addProperty("password", password);
		json.addProperty("email", rand.generateRandomemailID());
		json.addProperty("name", rand.generateRandomName());
		json.addProperty("mobileNumber", rand.generateRandomNumber(11));	
		json.addProperty("location", "Dhaka");
		json.addProperty("nationalId", rand.generateRandomNID(10));
		json.addProperty("nationalIdBackPhoto", testdata.properties.getProperty("image"));
		json.addProperty("nationalIdFrontPhoto", testdata.properties.getProperty("image"));
		json.addProperty("referrelCode", rand.generateRandomNumber(11));
		json.addProperty("masterDistrictId", 1);
		json.addProperty("roleId", roleID);
		json.addProperty("fleetOwnerDetails", 0);
		json.addProperty("driverBackPhoto", "");
		json.addProperty("driverFrontPhoto", "");
		json.addProperty("driverLicenseNumber", "");
		json.addProperty("driverLicenseExpiryDate", "");
		json.addProperty("partnerAndDriver",  false);
	}
	else {
		json.addProperty("deviceToken", rand.generateRandomNumber(6));
		json.addProperty("deviceType", "ANDROID");
		json.addProperty("dob", new Timing().PastDate(rand.getRandomNumberInRange(20000, 21000)));
		json.addProperty("password", password);
		json.addProperty("email", rand.generateRandomemailID());
		json.addProperty("name", rand.generateRandomName());
		json.addProperty("mobileNumber", rand.generateRandomNumber(11));	
		json.addProperty("location", "Dhaka");
		json.addProperty("nationalId", rand.generateRandomNID(10));
		json.addProperty("nationalIdBackPhoto", testdata.properties.getProperty("image"));
		json.addProperty("nationalIdFrontPhoto", testdata.properties.getProperty("image"));
		json.addProperty("referrelCode", rand.generateRandomNumber(11));
		json.addProperty("masterDistrictId", 1);
		json.addProperty("roleId", roleID);
		json.addProperty("fleetOwnerDetails", 0);
		json.addProperty("driverBackPhoto", testdata.properties.getProperty("image"));
		json.addProperty("driverFrontPhoto", testdata.properties.getProperty("image"));
		json.addProperty("driverLicenseNumber", rand.generateRandomNumber(11));
		json.addProperty("driverLicenseExpiryDate", new Timing().FutureDate(rand.getRandomNumberInRange(1, 10000)));
		json.addProperty("partnerAndDriver",  false);
	
	}	
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/auth/signUp";
		
		jsonresponse = new JsonPath(RestAssured
				.given()
				.contentType("application/json")
				.body(json.toString())
				.when()
				.post()
				.asString());
		
		jsonresponse.prettyPrint();
					
}

public void byroleId_andMobileno(int roleID, String mobileno) throws IOException {
	testdata = new TestData();
	JsonObject json = new JsonObject();
    
	if(roleID!=2) {	  
		json.addProperty("deviceToken", rand.generateRandomNumber(6));
		json.addProperty("deviceType", "ANDROID");
		json.addProperty("dob", new Timing().PastDate(rand.getRandomNumberInRange(20000, 21000)));
		json.addProperty("password", password);
		json.addProperty("email", rand.generateRandomemailID());
		json.addProperty("name", rand.generateRandomName());
		json.addProperty("mobileNumber", mobileno);	
		json.addProperty("location", "Dhaka");
		json.addProperty("nationalId", rand.generateRandomNID(10));
		json.addProperty("nationalIdBackPhoto", testdata.properties.getProperty("image"));
		json.addProperty("nationalIdFrontPhoto", testdata.properties.getProperty("image"));
		json.addProperty("referrelCode", rand.generateRandomNumber(11));
		json.addProperty("masterDistrictId", 1);
		json.addProperty("roleId", roleID);
		json.addProperty("fleetOwnerDetails", 0);
		json.addProperty("driverBackPhoto", "");
		json.addProperty("driverFrontPhoto", "");
		json.addProperty("driverLicenseNumber", "");
		json.addProperty("driverLicenseExpiryDate", "");
		json.addProperty("partnerAndDriver",  false);
	}
	else {
		json.addProperty("deviceToken", rand.generateRandomNumber(6));
		json.addProperty("deviceType", "ANDROID");
		json.addProperty("dob", new Timing().PastDate(rand.getRandomNumberInRange(20000, 21000)));
		json.addProperty("password", password);
		json.addProperty("email", rand.generateRandomemailID());
		json.addProperty("name", rand.generateRandomName());
		json.addProperty("mobileNumber", rand.generateRandomNumber(11));	
		json.addProperty("location", "Dhaka");
		json.addProperty("nationalId", rand.generateRandomNID(10));
		json.addProperty("nationalIdBackPhoto", testdata.properties.getProperty("image"));
		json.addProperty("nationalIdFrontPhoto", testdata.properties.getProperty("image"));
		json.addProperty("referrelCode", rand.generateRandomNumber(11));
		json.addProperty("masterDistrictId", 1);
		json.addProperty("roleId", roleID);
		json.addProperty("fleetOwnerDetails", 0);
		json.addProperty("driverBackPhoto", testdata.properties.getProperty("image"));
		json.addProperty("driverFrontPhoto", testdata.properties.getProperty("image"));
		json.addProperty("driverLicenseNumber", rand.generateRandomNumber(11));
		json.addProperty("driverLicenseExpiryDate", new Timing().FutureDate(rand.getRandomNumberInRange(1, 10000)));
		json.addProperty("partnerAndDriver",  false);
	
	}	
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/auth/signUp";
		
		jsonresponse = new JsonPath(RestAssured
				.given()
				.contentType("application/json")
				.body(json.toString())
				.when()
				.post()
				.asString());
		
		jsonresponse.prettyPrint();
					
}


public void UserSignUp(int DeviceToken, String deviceType, String DateofBirth, 
		String Password, String email, String username, String mobileno, String location, 
		String nidno, String nidfrontphoto, String nidbackphoto, String referrelcode, 
		int masterdistrictid, int roleid, int fleetownerdetails, String driverfrontphoto, 
		String driverbackphoto, String driverlicenseno, String driverlicenseexpirydate) throws IOException {
	
	testdata = new TestData();
		JsonObject json = new JsonObject();
    
		json.addProperty("deviceToken", DeviceToken);
		json.addProperty("deviceType", deviceType);
		json.addProperty("dob", DateofBirth);
		json.addProperty("password", Password);
		json.addProperty("email", email);
		json.addProperty("name", username);
		json.addProperty("mobileNumber", mobileno);	
		json.addProperty("location", location);
		json.addProperty("nationalId", nidno);
		json.addProperty("nationalIdBackPhoto", nidbackphoto);
		json.addProperty("nationalIdFrontPhoto", nidfrontphoto);
		json.addProperty("referrelCode", referrelcode);
		json.addProperty("masterDistrictId", masterdistrictid);
		json.addProperty("roleId", roleid);
		json.addProperty("fleetOwnerDetails", fleetownerdetails);
		json.addProperty("driverBackPhoto", driverbackphoto);
		json.addProperty("driverFrontPhoto", driverfrontphoto);
		json.addProperty("driverLicenseNumber", driverlicenseno);
		json.addProperty("driverLicenseExpiryDate", driverlicenseexpirydate);
		json.addProperty("partnerAndDriver",  false);
	
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/auth/signUp";
		
		jsonresponse = new JsonPath(RestAssured.
				given()
				.contentType("application/json")
				.body(json.toString())
				.when()
				.post()
				.asString());
		
		jsonresponse.prettyPrint();
					
}


public void byroleId_ResponseTime(int roleID, Long Benchmarktime) throws IOException {
	
	testdata = new TestData();
	JsonObject json = new JsonObject();
    
	if(roleID!=2) {	  
		json.addProperty("deviceToken", rand.generateRandomNumber(6));
		json.addProperty("deviceType", "ANDROID");
		json.addProperty("dob", new Timing().PastDate(rand.getRandomNumberInRange(20000, 21000)));
		json.addProperty("password", rand.generateRandomPassword());
		json.addProperty("email", rand.generateRandomemailID());
		json.addProperty("name", rand.generateRandomName());
		json.addProperty("mobileNumber", rand.generateRandomNumber(11));	
		json.addProperty("location", "Dhaka");
		json.addProperty("nationalId", rand.generateRandomNID(10));
		json.addProperty("nationalIdBackPhoto", testdata.properties.getProperty("image"));
		json.addProperty("nationalIdFrontPhoto", testdata.properties.getProperty("image"));
		json.addProperty("referrelCode", rand.generateRandomNumber(11));
		json.addProperty("masterDistrictId", 1);
		json.addProperty("roleId", roleID);
		json.addProperty("fleetOwnerDetails", 0);
		json.addProperty("driverBackPhoto", "");
		json.addProperty("driverFrontPhoto", "");
		json.addProperty("driverLicenseNumber", "");
		json.addProperty("driverLicenseExpiryDate", "");
		json.addProperty("partnerAndDriver",  false);
	}
	else {
		json.addProperty("deviceToken", rand.generateRandomNumber(6));
		json.addProperty("deviceType", "ANDROID");
		json.addProperty("dob", new Timing().PastDate(rand.getRandomNumberInRange(20000, 21000)));
		json.addProperty("password", rand.generateRandomPassword());
		json.addProperty("email", rand.generateRandomemailID());
		json.addProperty("name", rand.generateRandomName());
		json.addProperty("mobileNumber", rand.generateRandomNumber(11));	
		json.addProperty("location", "Dhaka");
		json.addProperty("nationalId", rand.generateRandomNID(10));
		json.addProperty("nationalIdBackPhoto", testdata.properties.getProperty("image"));
		json.addProperty("nationalIdFrontPhoto", testdata.properties.getProperty("image"));
		json.addProperty("referrelCode", rand.generateRandomNumber(11));
		json.addProperty("masterDistrictId", 1);
		json.addProperty("roleId", roleID);
		json.addProperty("fleetOwnerDetails", 0);
		json.addProperty("driverBackPhoto", testdata.properties.getProperty("image"));
		json.addProperty("driverFrontPhoto", testdata.properties.getProperty("image"));
		json.addProperty("driverLicenseNumber", rand.generateRandomNumber(11));
		json.addProperty("driverLicenseExpiryDate", new Timing().FutureDate(rand.getRandomNumberInRange(1, 10000)));
		json.addProperty("partnerAndDriver",  false);
	
	}	
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/auth/signUp";
		
		RestAssured
				.given()
				.contentType("application/json")
				.body(json.toString())
				.when()
				.post()
				.then()
				.and()
				.time(lessThan(Benchmarktime));
		
		//jsonresponse.prettyPrint();
					
}


public void PartnerandDriver(int roleID) throws IOException {
	testdata = new TestData();
	JsonObject json = new JsonObject();
    
		json.addProperty("deviceToken", rand.generateRandomNumber(6));
		json.addProperty("deviceType", "ANDROID");
		json.addProperty("dob", new Timing().PastDate(rand.getRandomNumberInRange(20000, 21000)));
		json.addProperty("password", password);
		json.addProperty("email", rand.generateRandomemailID());
		json.addProperty("name", rand.generateRandomName());
		json.addProperty("mobileNumber", rand.generateRandomNumber(11));	
		json.addProperty("location", "Dhaka");
		json.addProperty("nationalId", rand.generateRandomNID(10));
		json.addProperty("nationalIdBackPhoto", testdata.properties.getProperty("image"));
		json.addProperty("nationalIdFrontPhoto", testdata.properties.getProperty("image"));
		json.addProperty("referrelCode", rand.generateRandomNumber(11));
		json.addProperty("masterDistrictId", 1);
		json.addProperty("roleId", roleID);
		json.addProperty("fleetOwnerDetails", 0);
		json.addProperty("driverBackPhoto", testdata.properties.getProperty("image"));
		json.addProperty("driverFrontPhoto", testdata.properties.getProperty("image"));
		json.addProperty("driverLicenseNumber", rand.generateRandomNumber(11));
		json.addProperty("driverLicenseExpiryDate", new Timing().FutureDate(rand.getRandomNumberInRange(1, 10000)));
		json.addProperty("partnerAndDriver",  true);
	
		
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/auth/signUp";
		
		jsonresponse = new JsonPath(RestAssured
				.given()
				.contentType("application/json")
				.body(json.toString())
				.when()
				.post()
				.asString());
		
		jsonresponse.prettyPrint();
					
}

public void NewDriver_ForExistingFleetOwner(int FleetOwnerId) throws IOException {
	testdata = new TestData();
	JsonObject json = new JsonObject();
    
		json.addProperty("deviceToken", rand.generateRandomNumber(6));
		json.addProperty("deviceType", "ANDROID");
		json.addProperty("dob", new Timing().PastDate(rand.getRandomNumberInRange(20000, 21000)));
		json.addProperty("password", password);
		json.addProperty("email", rand.generateRandomemailID());
		json.addProperty("name", rand.generateRandomName());
		json.addProperty("mobileNumber", rand.generateRandomNumber(11));	
		json.addProperty("location", "Dhaka");
		json.addProperty("nationalId", rand.generateRandomNID(10));
		json.addProperty("nationalIdBackPhoto", testdata.properties.getProperty("image"));
		json.addProperty("nationalIdFrontPhoto", testdata.properties.getProperty("image"));
		json.addProperty("referrelCode", rand.generateRandomNumber(11));
		json.addProperty("masterDistrictId", 1);
		json.addProperty("roleId", 2);
		json.addProperty("fleetOwnerDetails", FleetOwnerId);
		json.addProperty("driverBackPhoto", testdata.properties.getProperty("image"));
		json.addProperty("driverFrontPhoto", testdata.properties.getProperty("image"));
		json.addProperty("profilePhoto", testdata.properties.getProperty("image"));
		json.addProperty("driverLicenseNumber", rand.generateRandomNumber(11));
		json.addProperty("driverLicenseExpiryDate", new Timing().FutureDate(rand.getRandomNumberInRange(1, 10000)));
		json.addProperty("partnerAndDriver",  false);
	
		
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/auth/signUp";
		
		jsonresponse = new JsonPath(RestAssured
				.given()
				.contentType("application/json")
				.body(json.toString()).log().all()
				.when()
				.post()
				.asString());
		
		jsonresponse.prettyPrint();
					
}

public void NewDriver_ForExistingFleetOwner(int FleetOwnerId, String drivername) throws IOException {
	testdata = new TestData();
	JsonObject json = new JsonObject();
    
		json.addProperty("deviceToken", rand.generateRandomNumber(6));
		json.addProperty("deviceType", "ANDROID");
		json.addProperty("dob", new Timing().PastDate(rand.getRandomNumberInRange(20000, 21000)));
		json.addProperty("password", password);
		json.addProperty("email", rand.generateRandomemailID());
		json.addProperty("name", drivername);
		json.addProperty("mobileNumber", rand.generateRandomNumber(11));	
		json.addProperty("location", "Dhaka");
		json.addProperty("nationalId", rand.generateRandomNID(10));
		json.addProperty("nationalIdBackPhoto", testdata.properties.getProperty("image"));
		json.addProperty("nationalIdFrontPhoto", testdata.properties.getProperty("image"));
		json.addProperty("referrelCode", "");
		json.addProperty("masterDistrictId", 1);
		json.addProperty("roleId", 2);
		json.addProperty("fleetOwnerDetails", FleetOwnerId);
		json.addProperty("profilePhoto", testdata.properties.getProperty("image"));
		json.addProperty("driverBackPhoto", testdata.properties.getProperty("image"));
		json.addProperty("driverFrontPhoto", testdata.properties.getProperty("image"));
		json.addProperty("driverLicenseNumber", rand.generateRandomNumber(11));
		json.addProperty("driverLicenseExpiryDate", new Timing().FutureDate(rand.getRandomNumberInRange(1, 10000)));
		json.addProperty("partnerAndDriver",  false);
	
		
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/auth/signUp";
		
		jsonresponse = new JsonPath(RestAssured
				.given()
				.contentType("application/json")
				.body(json.toString()).log().all()
				.when()
				.post()
				.asString());
		
		jsonresponse.prettyPrint();
					
}


public String getResponseMessage() {
	return jsonresponse.getString("message");
}

public String getResponseCode() {
	return jsonresponse.getString("responseCode");
}

public String getSuccessValue() {
	return jsonresponse.getString("success");
}

public String getUserRoleName() {
	return jsonresponse.getString("data.userRoles[0].name");
}

public String getApplicationStatus() {
	return jsonresponse.getString("data.userRoles[0].applicationStatus");
}

public String getroleID() {
	return jsonresponse.getString("data.roleId");
}

public String getotpVerifiedStatus() {
	return jsonresponse.getString("data.otpVerified");
}

public String getuserName() {
	return jsonresponse.getString("data.name");
}

public String getauthtoken() {
	return jsonresponse.getString("data.token");
}

public String getNIDBackPhoto() {
	return jsonresponse.getString("data.nationalIdBackPhoto");
}

public String getNIDFrontPhoto() {
	return jsonresponse.getString("data.nationalIdFrontPhoto");
}

public String getuserId() {
	return jsonresponse.getString("data.id");
}

public String getNIDnumber() {
	return jsonresponse.getString("data.nationalId");
}

public String getuserRole() {
	return jsonresponse.getString("data.userRoles[0].name");
}

public String getDistrict() {
	return jsonresponse.getString("data.district");
}

public String getDriverFrontPhoto() {
	return jsonresponse.getString("data.driverFrontPhoto");
}

public String getDriverBackPhoto() {
	return jsonresponse.getString("data.driverBackPhoto");
}

public String getDriverLicenseNumber() {
	return jsonresponse.getString("data.driverLicenseNumber");
}

public String getDriverLicenseExpiry() {
	return jsonresponse.getString("data.driverLicenseExpiryDate");
}

public String getuseremailid() {
	return jsonresponse.getString("data.email");
}
 




}

