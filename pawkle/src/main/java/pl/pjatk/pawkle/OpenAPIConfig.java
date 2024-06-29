package pl.pjatk.pawkle;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("NBP Application API")
                        .version("1.0")
                        .description("API for currency using data from NBP. Project made by the (best) java student s27118"));
    }
}
