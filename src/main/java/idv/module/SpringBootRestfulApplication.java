package idv.module;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * SpringBootRestfulApiApplication. 2020/8/18 上午 09:56
 *
 * @author sero
 * @version 1.0.0
 **/
@EnableOpenApi
@SpringBootApplication
public class SpringBootRestfulApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRestfulApplication.class, args);
    }

}
