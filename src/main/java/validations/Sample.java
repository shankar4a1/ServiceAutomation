package validations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scale.framework.utility.*;
import cucumber.api.Scenario;
import org.junit.Assert;
import pojo.SamplePOJO;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class Sample extends CommonValidations {
    public Sample(Scenario scenario, ScenarioContext scenarioContext) {
        super(scenario, scenarioContext);
        //this.scenario = scenario;
        // this.scenarioContext = scenarioContext;
    }

    public void getRequest_withBasicAuth() {
        //APIutil is initiated in the parent class and all the response validations are placed in common validation class
        apiUtil = new APIUtil();
        // As this is an API call , we are not setting up any base url but defining the individual calls
        apiUtil.setBaseURI(configurationReader.get("BaseURL"));
        //This is used to validate the get call protected with basic authentication mechanism
        basicAuthValidation();
    }

    public void getRequest_withHeader_QueryParms() {
        //APIutil is initiated in the parent class and all the response validations are placed in common validation class
        apiUtil = new APIUtil();
        // As this is an API call , we are not setting up any base url but defining the individual calls
        apiUtil.setBaseURI(configurationReader.get("BaseURL"));
        //This is used to validate the get call protected with basic authentication mechanism
        basicAuthValidation();
        scenario.write("Request query parameters are :-");
        String[] queryParameters = {"grant_type"};
        setQueryParameters(queryParameters);
        String[] headerParameters = {"content-type"};
        setHeaderParameters(headerParameters);
    }

    public void postRequest() {
        //APIutil is initiated in the parent class and all the response validations are placed in common validation class
        apiUtil = new APIUtil();
        // As this is an API call , we are not setting up any base url but defining the individual calls
        apiUtil.setBaseURI(configurationReader.get("BaseURL"));
        //This is used to validate the get call protected with basic authentication mechanism
        basicAuthValidation();
        scenario.write("Request body parameters are :-");
        SamplePOJO samplePOJO = new SamplePOJO();
        samplePOJO.setFirstName(scenarioContext.getContext("firstName"));
        samplePOJO.setLastName(scenarioContext.getContext("lastName"));

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            apiUtil.setRequestBody(objectMapper.writeValueAsString(samplePOJO));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void validateResponse_200(){
        scenario.write("Schema validation for response 200");
        response.then().assertThat().body(matchesJsonSchemaInClasspath("data/schema/valid200.json"));
        scenario.write("Asserting the presence of response message");
        scenario.write("Asserting the presence of response element "+"firstName");
        Assert.assertTrue(jsonObject.has("firstName"));
        scenario.write("Asserting the presence of response element "+"lastName");
        Assert.assertTrue(jsonObject.has("lastName"));
    }

    public void validateResponse_201(){
        scenario.write("Schema validation for response 201");
        response.then().assertThat().body(matchesJsonSchemaInClasspath("data/schema/valid201.json"));
    }

}
