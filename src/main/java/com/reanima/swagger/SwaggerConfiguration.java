package com.reanima.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.reanima.swagger.SwaggerTags.*;

//endpoint URL: http://localhost:9191/swagger-ui/#/
@EnableSwagger2
@Configuration
public class SwaggerConfiguration {

    @Bean
    public Docket swaggerConfig() {
         Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.ant("/api/**"))
                .apis(RequestHandlerSelectors.basePackage("com.reanima"))
                .build()
                .apiInfo(apiInfo());
        docket.useDefaultResponseMessages(false);
        return appendTags(docket);
    }

    private Docket appendTags(Docket docket) {
        return docket.tags(
                new Tag(INGREDIENT_CONTROLLER_TAG_NAME, INGREDIENT_CONTROLLER_TAG_DESCRIPTION),
                new Tag(RECIPE_CONTROLLER_TAG_NAME, RECIPE_CONTROLLER_TAG_DESCRIPTION),
                new Tag(LOGIN_CONTROLLER_TAG_NAME, LOGIN_CONTROLLER_TAG_DESCRIPTION)
        );
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(TITLE)
                .description(DESCRIPTION)
                .contact(DEFAULT_CONTACT)
                .version(VERSION)
                .build();
    }
}
