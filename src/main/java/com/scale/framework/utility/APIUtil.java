package com.scale.framework.utility;

import com.github.dzieciou.testing.curl.CurlCommand;
import com.github.dzieciou.testing.curl.CurlLoggingRestAssuredConfigFactory;
import com.github.dzieciou.testing.curl.Options;
import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import static io.restassured.RestAssured.given;
//import static io.restassured.RestAssured.module.jsv.JsonSchemaValidator.matchesjsonSchemaInClasspath;
//import com.github.dzieciou.testing.curl.CurlLoggingRestAssuredConfigFactory;


public class APIUtil {

    private Response response;
    private RequestSpecification request;
    private RestAssuredConfig config = CurlLoggingRestAssuredConfigFactory.createConfig(Options.builder().updateCurl(this::reportCurlCommand).build());

    private String curl = "No request has been made yet";

    private void reportCurlCommand(CurlCommand curlCommand){
        {
            curl = curlCommand.toString();
        }
    }

    public String getCurl() {
        return curl;
    }

    public void setBaseURI(String baseURI) {
        RestAssured.baseURI = baseURI;
        RestAssured.useRelaxedHTTPSValidation();
    }

//    public void compareJSONSchema(Response response, String fileName) {
//        response.then().assertThat().body(matchesJsonSchemaInClass(fileName + ".json"));
//    }

    public void setBasicAuth(String username, String password) {
        if (request == null)
            request = given().auth().preemptive().basic(username, password);
        else {
            request.auth().preemptive().basic(username, password);
        }
    }

    public void setOauth2(String accessToken) {
        if (request == null)
            request = given().auth().preemptive().oauth2(accessToken);
        else {
            request.auth().preemptive().oauth2(accessToken);
        }
    }

    public void setRequestBody(String resBody) {
        if (request == null)
            request = given().body(resBody);
        else {
            request.body(resBody);
        }
    }

    public void setHeader(String headerName, String headerValue) {
        if (request == null)
            request = given().header(headerName,headerValue);
        else {
            request.header(headerName,headerValue);
        }
    }

    public void setContentType(String contentType) {
        if (request == null)
            request = given().accept(contentType);
        else {
            request.accept(contentType);
        }
    }

    public void setQueryParam(String key, String value) {
        if (request == null)
            request = given().queryParam(key,value);
        else {
            request.queryParam(key,value);
        }
    }

    public Response getRequest(String path) {
       response = request.config(config).get(path);
       return response;
    }

    public Response postRequest(String path) {
        response = request.config(config).request("post", path);
        return response;
    }

    public Response postRequest() {
        response = request.config(config).request("post");
        return response;
    }

    public Response deleteRequest(String path) {
        response = request.config(config).delete(path);
        return response;
    }

    public void verifyStatusCode(Response response,int expectedStatusCode){
        Assert.assertEquals(response.statusCode(),expectedStatusCode);

    }

    public String getToken(){
        JsonPath jsonPath = response.jsonPath();
        String token = jsonPath.get("accesstoken");
        return token;
    }

    public String getRequestBody(){
        return toString();
    }


}
