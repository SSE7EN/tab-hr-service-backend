package pl.polsl.hrservice.raport.domain;

import lombok.Builder;
import lombok.Value;

import java.io.InputStream;

/**
 * Created by piotrswierzy on 21.06.2022
 */
@Value
@Builder(toBuilder = true)
public class ReportResult implements AutoCloseable{
    InputStream inputStream;
    String fileName;

    @Override
    public void close() throws Exception {
        inputStream.close();
    }
}
