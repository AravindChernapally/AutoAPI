package com.lds.scripts;

import com.rp.assertions.HardAssert;
import com.rp.base.ApiBase;
import com.lds.models.db.ResidentDetails;
import com.lds.models.db.StarterKitDao;
import io.qameta.allure.Description;
import org.apache.juneau.serializer.SerializeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

public class ResidentDatabaseTest extends ApiBase {

    private @Autowired StarterKitDao _starterKitDao;

    @Description ("Verify database test")
    @Test (dataProvider = "SampleTestData")
    public void verifyResidentRecordInDatabase(Map<String, String> data) throws IOException, SerializeException {

        Long residentId = Long.valueOf(data.get("residentId"));
        Optional<ResidentDetails> residentDetailRecord = _starterKitDao.getResident(residentId);

        HardAssert.assertIsTrue(
                residentDetailRecord.isPresent(),
                "Resident Detail Record",
                "Verify resident detail record in DB is not null");

        HardAssert.assertEquals(
                residentDetailRecord.get().getResidentId(),
                residentId,
                "Resident Id in database",
                "Verify resident id is equal to the test data");

        HardAssert.assertEquals(residentDetailRecord.get().isActive,
                                true,
                                "Resident is active flag",
                                "Verify resident is active");

    }
}
