package com.api.test_localiza.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.builders.ResponseMessageBuilder
import springfox.documentation.schema.ModelRef
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.ResponseMessage
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket


@Configuration
class SwaggerConfiguration {

    @Bean
    fun api(): Docket? {
        return Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build()
    }
    private fun getApiInfo(): ApiInfo {
        return ApiInfoBuilder()
            .title("Localiza Teste API")
            .description("Api Definition")
            .version("1.0.0")
            .license("Creative Commons Zero v1.0 Universal")
            .licenseUrl("https://choosealicense.com/licenses/cc0-1.0/")
            .build()
    }
}