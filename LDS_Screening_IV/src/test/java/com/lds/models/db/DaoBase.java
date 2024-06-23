package com.lds.models.db;

import com.rp.util.FileReaderUtil;
import org.jdbi.v3.core.Jdbi;

import java.io.IOException;

/*
Base class for data access object. This allows dependency injection via spring framework
 */
public abstract class DaoBase {

    final String BASE_PATH = "src/test/resources/queries/";

    protected Jdbi _jdbi;

    public DaoBase(Jdbi jdbi) {
        _jdbi = jdbi;
    }

    protected String getQueryText(String queryFileName) throws IOException {
        String filePath = BASE_PATH + queryFileName;
        System.out.println("Reading query from: " + filePath);

        FileReaderUtil fileReader = new FileReaderUtil();
        String fileContent = fileReader.readAllFileContentAsString(filePath);
        return fileContent;
    }



}
