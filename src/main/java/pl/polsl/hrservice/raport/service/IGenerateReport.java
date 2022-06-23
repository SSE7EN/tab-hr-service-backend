package pl.polsl.hrservice.raport.service;

import pl.polsl.hrservice.raport.domain.ReportResult;
import pl.polsl.hrservice.raport.query.ReportQuery;

import java.io.OutputStream;

/**
 * Created by piotrswierzy on 21.06.2022
 */
public interface IGenerateReport {

    ReportResult generateReport(ReportQuery query);
}
