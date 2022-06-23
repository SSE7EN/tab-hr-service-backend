package pl.polsl.hrservice.raport.service;

import com.lowagie.text.DocumentException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.springframework.stereotype.Component;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileSystems;

import static com.lowagie.text.pdf.BaseFont.EMBEDDED;
import static com.lowagie.text.pdf.BaseFont.IDENTITY_H;

@Slf4j
@Component
@RequiredArgsConstructor
public class PdfGenerator {

    public void generatePdfFromHtml(String html, OutputStream outputStream) throws IOException, DocumentException {
        long start = System.currentTimeMillis();
        ITextRenderer renderer = new ITextRenderer();
        renderer.getFontResolver().addFont("templates/ArialUnicodeMS.ttf", IDENTITY_H, EMBEDDED);
        String baseUrl = FileSystems
                .getDefault()
                .getPath("src", "java", "resources")
                .toUri()
                .toURL()
                .toString();
        renderer.setDocumentFromString(html, baseUrl);

        renderer.layout();
        renderer.createPDF(outputStream);
        renderer.finishPDF();
        log.info("generatePdfFromHtml: " + (System.currentTimeMillis() - start) + " ms.");
    }

}
