package Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.JsonObject;

import groovy.json.internal.Value;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.EncoderConfig;
import io.restassured.config.MultiPartConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.internal.util.IOUtils;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import Utils.Randomgenerator;
import Utils.TestData;
import Utils.Timing;

public class TruckCertificates {

	public TruckCertificates() {
		
	}
	
	TestData testdata;
	Randomgenerator rand = new Randomgenerator();
	JsonObject json = new JsonObject();
	JsonPath jsonresponse;
	Timing timing = new Timing();
	
	
	public void Add_TruckCertificatesAPI(String authToken, int truckId, boolean editDocuments) throws IOException {

		testdata = new TestData();
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/truck/truckCertificates";

		String file = testdata.properties.getProperty("imgfile");

		jsonresponse = new JsonPath(RestAssured
				.given()
				.header("authtoken", authToken)
				.multiPart("truckRegistrationPhoto.image", new File(file))
				.queryParam("truckRegistrationPhoto.type", 1)
				.multiPart("truckRegistrationPhotoBackSide.image", new File(file))
				.queryParam("truckRegistrationPhotoBackSide.type", 1)
				.multiPart("truckInsurancePhoto.image", new File(file))
				.queryParam("truckInsurancePhototype", 1)
				.multiPart("truckFitnessCertificatePhoto.image", new File(file))
				.queryParam("truckFitnessCertificatePhoto.type", 1)
				.multiPart("truckRoutePermitPhoto.image", new File(file))
				.queryParam("truckRoutePermitPhoto.type", 1)
				.multiPart("truckTaxTokenPhoto.image", new File(file))
				.queryParam("truckTaxTokenPhoto.type", 1)
				.multiPart("truckNocCertificatePhoto.image", new File(file))
				.queryParam("truckNocCertificatePhoto.type", 1)
				.multiPart("truckBankDocumentPhoto.image", new File(file))
				.queryParam("truckBankDocumentPhoto.type", 1)
				.multiPart("truckCaseDocumentPhoto.image", new File(file))
				.queryParam("truckCaseDocumentPhoto.type", 1)
				.queryParam("edit", editDocuments)
				.queryParam("truckId", truckId)
				.queryParam("truckRegisteredOwnerName", rand.generateRandomName())
				.queryParam("truckRegisteredOwnerAddress", rand.generateRandomName())
				.queryParam("truckInsuranceExpiryDate", new Timing().FutureDate(rand.getRandomNumberInRange(1, 6940)))
				.queryParam("truckFitnessExpiryDate", new Timing().FutureDate(rand.getRandomNumberInRange(1, 6940)))
				.queryParam("truckRoutePermitExpiryDate", new Timing().FutureDate(rand.getRandomNumberInRange(1, 6940)))
				.queryParam("truckTaxTokenExpiryDate", new Timing().FutureDate(rand.getRandomNumberInRange(1, 6940)))
				.contentType("multipart/form-data").log().all()
				.post()
				.asString());

		jsonresponse.prettyPrint();
	}
	
	public void MobileApp_AddTruckRegistrationPhotoOnly(String authToken, int truckId, boolean editDocuments, String file) throws IOException {

		testdata = new TestData();
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/truck/truckCertificates";

		//String file = testdata.properties.getProperty("imgfile");

		try {
		jsonresponse = new JsonPath(RestAssured
				.given()
				.header("authtoken", authToken)
				.multiPart("truckRegistrationPhoto.image", new File(file))
				.queryParam("truckRegistrationPhoto.type", 1)
				.queryParam("edit", editDocuments)
				.queryParam("truckId", truckId)
				.contentType("multipart/form-data")
				.and()
				.post()
				.asString());

		jsonresponse.prettyPrint();
		}
		catch(Exception e) {
			
		}
	}
	
	public void MobileApp_AddTruckInsurancePhotoOnly(String authToken, int truckId, boolean editDocuments, String file) throws IOException {

		testdata = new TestData();
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/truck/truckCertificates";

		//String file = testdata.properties.getProperty("imgfile");

		try {
		jsonresponse = new JsonPath(RestAssured
				.given()
				.header("authtoken", authToken)
				.multiPart("truckInsurancePhoto.image", new File(file))
				.queryParam("truckInsurancePhototype", 1)
				.queryParam("edit", editDocuments)
				.queryParam("truckId", truckId)
				.contentType("multipart/form-data")
				.and()
				.post()
				.asString());

		jsonresponse.prettyPrint();
		}
		catch(Exception e) {
			
		}
	}
	
	public void MobileApp_AddtruckFitnessCertificatePhotoOnly(String authToken, int truckId, boolean editDocuments, String file) throws IOException {

		testdata = new TestData();
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/truck/truckCertificates";

		//String file = testdata.properties.getProperty("imgfile");

		try {
		jsonresponse = new JsonPath(RestAssured
				.given()
				.header("authtoken", authToken)
				.multiPart("truckFitnessCertificatePhoto.image", new File(file))
				.queryParam("truckFitnessCertificatePhoto.type", 1)
				.queryParam("edit", editDocuments)
				.queryParam("truckId", truckId)
				.contentType("multipart/form-data")
				.and()
				.post()
				.asString());

		jsonresponse.prettyPrint();
		}
		catch(Exception e) {
			
		}
	}
	
	public void MobileApp_AddtruckRoutePermitPhotoOnly(String authToken, int truckId, boolean editDocuments, String file) throws IOException {

		testdata = new TestData();
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/truck/truckCertificates";

		//String file = testdata.properties.getProperty("imgfile");

		jsonresponse = new JsonPath(RestAssured
				.given()
				.header("authtoken", authToken)
				.multiPart("truckRoutePermitPhoto.image", new File(file))
				.queryParam("edit", editDocuments)
				.queryParam("truckId", truckId)
				.contentType("multipart/form-data")
				.and()
				.post()
				.asString());

		jsonresponse.prettyPrint();
	}
	
	public void MobileApp_AddTruckTaxTokenPhotoOnly(String authToken, int truckId, boolean editDocuments, String file) throws IOException {

		testdata = new TestData();
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/truck/truckCertificates";

		//String file = testdata.properties.getProperty("imgfile");

		jsonresponse = new JsonPath(RestAssured
				.given()
				.header("authtoken", authToken)
				.multiPart("truckTaxTokenPhoto.image", new File(file))
				.queryParam("truckTaxTokenPhoto.type", 1)
				.queryParam("edit", editDocuments)
				.queryParam("truckId", truckId)
				.contentType("multipart/form-data").log().all()
				.and()
				.post()
				.asString());

		jsonresponse.prettyPrint();
	}
	
	public void MobileApp_AddTruckNOCCertificatePhotoOnly(String authToken, int truckId, boolean editDocuments, String file) throws IOException {

		testdata = new TestData();
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/truck/truckCertificates";

		//String file = testdata.properties.getProperty("imgfile");

		jsonresponse = new JsonPath(RestAssured
				.given()
				.header("authtoken", authToken)
				.multiPart("truckNocCertificatePhoto.image", new File(file))
				.queryParam("truckNocCertificatePhoto.type", 1)
				.queryParam("edit", editDocuments)
				.queryParam("truckId", truckId)
				.contentType("multipart/form-data")
				.and()
				.post()
				.asString());

		jsonresponse.prettyPrint();
	}
	
	public void MobileApp_AddTruckBankDocumentPhotoOnly(String authToken, int truckId, boolean editDocuments, String file) throws IOException {

		testdata = new TestData();
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/truck/truckCertificates";

		//String file = testdata.properties.getProperty("imgfile");

		jsonresponse = new JsonPath(RestAssured
				.given()
				.header("authtoken", authToken)
				.multiPart("truckBankDocumentPhoto.image", new File(file))
				.queryParam("truckBankDocumentPhoto.type", 1)
				.queryParam("edit", editDocuments)
				.queryParam("truckId", truckId)
				.contentType("multipart/form-data")
				.and()
				.post()
				.asString());

		jsonresponse.prettyPrint();
	}
	
	public void MobileApp_AddTruckCaseDocumentPhotoOnly(String authToken, int truckId, boolean editDocuments, String file) throws IOException {

		testdata = new TestData();
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/truck/truckCertificates";

		//String file = testdata.properties.getProperty("imgfile");

		jsonresponse = new JsonPath(RestAssured
				.given()
				.header("authtoken", authToken)
				.multiPart("truckCaseDocumentPhoto.image", new File(file))
				.queryParam("truckCaseDocumentPhoto.type", 1)
				.queryParam("edit", editDocuments)
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
