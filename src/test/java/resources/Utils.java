package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {
	public static RequestSpecification req;
	ResponseSpecification res;
	
	public RequestSpecification requestSpec() throws IOException {
		if(req==null) {
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
			req =new RequestSpecBuilder()
					.setBaseUri(getGlobalValue("baseURL"))
					.setContentType(ContentType.JSON)
					.addQueryParam("key", "qaclick123")
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
					.build();
			return req;			
		}
		return req;

	}
	
	public ResponseSpecification responseSpec() {
		res = new ResponseSpecBuilder()
				.expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();
		return res;
	}
	
	public String getGlobalValue(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream filePath = new FileInputStream("C:\\Users\\Samson\\eclipse-workspace01\\RestAssureFrame2\\src\\test\\java\\resources\\globalInputData.properties");
		
		prop.load(filePath);
		return prop.getProperty(key);
	
		
	}
	
	public String getJsonPath(Response response, String key) {
		String resSpec = response.asString();
		JsonPath js = new JsonPath(resSpec);
		return js.getString(key);
	}

}
