package com.lds.models.db;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.reflect.FieldMapper;

import java.io.IOException;
import java.util.Optional;

public class StarterKitDao extends DaoBase {

    public StarterKitDao(Jdbi jdbi) {
        super(jdbi);
    }

    /**
     * Overridden to provide scope/path pre-fixes to the named query
     * @param queryFileName
     * @return
     * @throws IOException
     */

    @Override
    protected String getQueryText(String queryFileName) throws IOException {
        String actualFileName = "ResidentQueries/" + queryFileName + ".sql";
        return super.getQueryText(actualFileName);
    }

    /**
     * Get the resident details
     *
     * @param residentId Resident Id
     * @return Resident details
     **/
    public Optional<ResidentDetails> getResident(Long residentId) throws IOException {
        String query = getQueryText("residentDetails");
        Optional<ResidentDetails> residentRecord = _jdbi.withHandle(handle ->
                                                                            handle
                                                                                    .registerRowMapper(FieldMapper.factory(ResidentDetails.class))
                                                                                    .createQuery(query)
                                                                                    .bind("resident_id", residentId)
                                                                                    .mapTo(ResidentDetails.class)
                                                                                    .findOne());
        return residentRecord;
    }


}
