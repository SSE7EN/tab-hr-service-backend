package pl.polsl.hrservice.raport.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.io.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.polsl.hrservice.position.domain.Position;
import pl.polsl.hrservice.position.enumerated.ProgrammingLanguage;
import pl.polsl.hrservice.raport.domain.PositionInfo;
import pl.polsl.hrservice.raport.domain.Report;
import pl.polsl.hrservice.raport.query.ReportQuery;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Created by piotrswierzy on 21.06.2022
 */
@Slf4j
class IGenerateReportTest {
    private PdfGenerator pdfGenerator;
    private GenerateReportImpl generateReport;
    private IReportReadService reportReadService = mock(IReportReadService.class);

    @BeforeEach
    public void init() {
        final var htmlGenerator = new HTMLGenerator();
        pdfGenerator = new PdfGenerator();

        generateReport = new GenerateReportImpl(htmlGenerator, reportReadService, pdfGenerator);

    }

    @Test
    void generateReport() throws Exception {
        when(reportReadService.read(any(ReportQuery.class)))
                .thenReturn(
                        ReportFixtures.getReportFixtures());

        try (var result = generateReport.generateReport(ReportQuery.builder()
                .build())) {

            final var outputFile = new File("report.pdf");
            try (var outStream = new FileOutputStream(outputFile)) {
                outStream.write(result.getInputStream().readAllBytes());
            }
        }

    }


    static class ReportFixtures {
        static Report getReportFixtures(){
            return Report.builder()
                    .generationTime(ZonedDateTime.now())
                    .positionInfo(
                            PositionInfo.builder()
                                    .positions(
                                            List.of(
                                                    Position.builder()
                                                            .id(1L)
                                                            .name("Postion 1")
                                                            .description("Description")
                                                            .programmingLanguages(Set.of(ProgrammingLanguage.JAVA, ProgrammingLanguage.PYTHON))
                                                            .build(),
                                                    Position.builder()
                                                            .id(2L)
                                                            .name("Postion 2")
                                                            .description("Description")
                                                            .programmingLanguages(Set.of(ProgrammingLanguage.PYTHON))
                                                            .build(),
                                                    Position.builder()
                                                            .id(3L)
                                                            .name("Postion 3")
                                                            .description("Description")
                                                            .programmingLanguages(Set.of(ProgrammingLanguage.C_PLUS_PLUS))
                                                            .build(),
                                                    Position.builder()
                                                            .id(4L)
                                                            .name("Postion 4")
                                                            .description("Description")
                                                            .programmingLanguages(Set.of(ProgrammingLanguage.C_SHARP, ProgrammingLanguage.JAVA))
                                                            .build()
                                            )
                                    )
                                    .build()
                    ).build();
        }
    }
}