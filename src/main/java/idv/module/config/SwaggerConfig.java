package idv.module.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * SwaggerConfig. 2020/8/15 5:49 下午
 *
 * @author sero
 * @version 1.0.0
 **/
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI createRestApi() {
        return new OpenAPI()
                .info(new Info().title("Spring Boot搭建RestFul Api Server")
                        .description("建議一個基礎的RestFul服務架構")
                        .version("1.0.0"));
    }

}
