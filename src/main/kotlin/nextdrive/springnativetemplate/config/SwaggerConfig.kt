package nextdrive.springnativetemplate.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.servers.Server
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {
    @Bean
    fun openapi(): OpenAPI {
        return OpenAPI()
            .addServersItem(Server().url("/"))
    }
}
