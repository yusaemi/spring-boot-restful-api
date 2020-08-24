package idv.module.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Response;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.List;

import static java.util.Collections.singletonList;


/**
 * SwaggerConfig. 2020/8/15 5:49 下午
 *
 * @author sero
 * @version 1.0.0
 **/
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .globalResponses(HttpMethod.GET, getCustomizedResponseMessages())
                .globalResponses(HttpMethod.POST, getCustomizedResponseMessages())
                .globalResponses(HttpMethod.PUT, getCustomizedResponseMessages())
                .globalResponses(HttpMethod.DELETE, getCustomizedResponseMessages())
                .forCodeGeneration(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("idv.module"))
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/");
    }

    //API訊息
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot搭建RestFul Api Server")
                .description("建議一個基礎的RestFul服務架構")
                .version("1.0.0")
                .build();
    }

    private List<Response> getCustomizedResponseMessages() {
        List<Response> responses = singletonList(new ResponseBuilder().code("400").description("bad Request").build());
        return responses;
    }

}
