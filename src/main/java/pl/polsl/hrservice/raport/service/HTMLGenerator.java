package pl.polsl.hrservice.raport.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.IContext;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

/**
 * Created by piotrswierzy on 21.06.2022
 */
@Component
@RequiredArgsConstructor
public class HTMLGenerator {
    public String generate(String path, IContext context) {
        return getTemplateEngine().process(path, context);
    }

    private SpringTemplateEngine getTemplateEngine() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setPrefix("");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.addDialect(new Java8TimeDialect());
        templateEngine.setTemplateResolver(templateResolver);
        return templateEngine;
    }
}
