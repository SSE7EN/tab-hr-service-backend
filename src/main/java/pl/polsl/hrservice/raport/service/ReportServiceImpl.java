package pl.polsl.hrservice.raport.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.polsl.hrservice.position.domain.Position;
import pl.polsl.hrservice.position.query.PositionQuery;
import pl.polsl.hrservice.position.service.IPositionReadService;
import pl.polsl.hrservice.raport.domain.PositionInfo;
import pl.polsl.hrservice.raport.domain.Report;
import pl.polsl.hrservice.raport.query.ReportQuery;

import java.time.ZonedDateTime;

/**
 * Created by piotrswierzy on 21.06.2022
 */
@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements IReportReadService {
    private final IPositionReadService positionReadService;

    @Override
    @Transactional(readOnly = true)
    public Report read(ReportQuery query) {
        final var positions = positionReadService.readAll(PositionQuery.builder()
                .build(), Pageable.unpaged());

        return Report.builder()
                .generationTime(ZonedDateTime.now())
                .positionInfo(
                        PositionInfo.builder()
                                .positions(positions.getContent())
                                .build()
                ).build();
    }
}
