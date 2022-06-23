package pl.polsl.hrservice.raport.query;

import lombok.Builder;

/**
 * Created by piotrswierzy on 21.06.2022
 */
public record ReportQuery(

) {
    @Builder(toBuilder = true) public ReportQuery{}
}
