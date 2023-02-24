package nextdrive.springnativetemplate.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaRepositories(basePackages = ["nextdrive.springnativetemplate.data"])
@EntityScan("nextdrive.springnativetemplate.data")
class RepositoryConfig
