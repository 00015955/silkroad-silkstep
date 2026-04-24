package uz.silkStep.project.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Silk Step API")
                        .version("1.0.0")
                        .description("Silk Step System Backend API Documentation")
                        .contact(new Contact()
                                .name("Development Team")
                                .email("dev@example.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")))
                .components(new Components()
                        .addSecuritySchemes("access_token", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .bearerFormat("JWT")
                                .scheme("bearer"))
                )
                .security(Collections.singletonList(new SecurityRequirement().addList("access_token")));
    }
}

//OpenApiConfiguration → Swagger docs. This class configures the OpenAPI documentation for the Silk Step API using the Springdoc OpenAPI library. It defines a bean that creates an OpenAPI instance with detailed information about the API, including its title, version, description, contact information, and license. Additionally, it sets up a security scheme for JWT authentication, specifying that the API uses HTTP Bearer tokens in the Authorization header. This configuration allows for automatic generation of API documentation that can be accessed through Swagger UI or other OpenAPI-compatible tools.