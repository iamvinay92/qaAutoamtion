package com.automation.backend.utils.DataUtils;

import com.automation.backend.data.AllTestCaseData;
import com.automation.backend.data.TestData;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.DataProvider;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class DataProviders {

//    static Faker faker = new Faker();

    @DataProvider(name = "service1_data_provider")
    public Object[][] getUser(Method method) {
        Yaml yaml = new Yaml();
        AllTestCaseData allTestCaseData = null;
        String testDataFilePath = "/Users/300069695/Documents/other-repos/qaAutoamtion/src/test/resources/testdata/all_test_data.yaml";

        try {
            allTestCaseData = yaml.loadAs(new FileReader(new File(testDataFilePath)), AllTestCaseData.class);
        } catch (FileNotFoundException e) {
            log.error("Exception while reading a yaml ", e);
            e.printStackTrace();
        }

        String testCaseName = method.getName();
        List<TestData> dataSet = allTestCaseData.getAllTestCaseDataMap().get(testCaseName);

        Object[][] data = new Object[dataSet.size()][1];

        for (int i = 0; i < dataSet.size(); i++) {
            data[i][0] = dataSet.get(i);
        }

        return data;
    }
}
