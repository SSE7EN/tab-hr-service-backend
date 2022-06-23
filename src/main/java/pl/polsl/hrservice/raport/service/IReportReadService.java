package pl.polsl.hrservice.raport.service;

import pl.polsl.hrservice.raport.domain.Report;
import pl.polsl.hrservice.raport.query.ReportQuery;

/**
 * Created by piotrswierzy on 21.06.2022
 */
public interface IReportReadService {
    Report read(ReportQuery query);
}
