package com.automation.backend.data;

import java.util.List;
import java.util.Map;


/*
 * This class read all the Data from YAML
 */

public class AllTestCaseData {

    private Map<String, List<TestData>> allTestCaseDataMap;

    public Map<String, List<TestData>> getAllTestCaseDataMap() {
        return allTestCaseDataMap;
    }

    public void setAllTestCaseDataMap(Map<String, List<TestData>> allTestCaseDataMap) {
        this.allTestCaseDataMap = allTestCaseDataMap;
    }
}
