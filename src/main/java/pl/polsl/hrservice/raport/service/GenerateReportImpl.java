package pl.polsl.hrservice.raport.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import pl.polsl.hrservice.raport.domain.Report;
import pl.polsl.hrservice.raport.domain.ReportResult;
import pl.polsl.hrservice.raport.query.ReportQuery;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by piotrswierzy on 21.06.2022
 */
@Service
@RequiredArgsConstructor
public class GenerateReportImpl implements IGenerateReport {
    private final HTMLGenerator htmlGenerator;
    private final IReportReadService reportReadService;
    private final PdfGenerator pdfGenerator;

    @Override
    public ReportResult generateReport(final ReportQuery query) {
        final var report = reportReadService.read(query);
        final var html = getHTML(report);
        return ReportResult.builder()
                .inputStream(generatePDF(html))
                .fileName(String.format("Report_%s.pdf",report.generationTime().toString()))
                .build();
    }

    private String getHTML(final Report report){
        Context context = new Context();
        context.setVariable("report", report);
        return htmlGenerator.generate("templates/report.html", context);
    }

    private InputStream generatePDF(final String html){
        try(final var outputStream = new ByteArrayOutputStream()){
            pdfGenerator.generatePdfFromHtml(html, outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


}
