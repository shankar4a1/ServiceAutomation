package com.scale.framework.utility;

import cucumber.api.Scenario;
import org.openqa.selenium.WebDriver;
import pojo.FTSEPojo;
import validations.FTSE;

public class SingletonObjectManager {
    private Scenario scenario;
    private ScenarioContext scenarioContext;
    //  API Classes
    private FTSEPojo ftsePojo;
    private FTSE ftse;

    public SingletonObjectManager(Scenario scenario, ScenarioContext scenarioContext) {
        this.scenario = scenario;
        this.scenarioContext = scenarioContext;
    }
    public FTSEPojo getFTSEPOJO() {
        return ftsePojo == null ? ftsePojo = new FTSEPojo() : ftsePojo;
    }

    public FTSE getFTSEObj() { return ftse == null ? ftse = new FTSE(scenario, scenarioContext) : ftse;
    }
}