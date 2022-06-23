package pl.polsl.hrservice.raport.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.polsl.hrservice.raport.query.ReportQuery;
import pl.polsl.hrservice.raport.service.IGenerateReport;
import pl.polsl.hrservice.raport.util.HttpHeadersUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by piotrswierzy on 21.06.2022
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/reports")
public class ReportController {
    private final IGenerateReport generateReport;

    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasAuthority(T(pl.polsl.hrservice.user.enumerator.Role).ADMIN)")
    @GetMapping
    public void download(ReportQuery query,
            HttpServletResponse response) throws IOException {

        try(
                final var result = generateReport.generateReport(query)
        ) {


            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", HttpHeadersUtil.getContentDisposition(result.getFileName()));
            result.getInputStream().transferTo(response.getOutputStream());
            result.getInputStream().close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
