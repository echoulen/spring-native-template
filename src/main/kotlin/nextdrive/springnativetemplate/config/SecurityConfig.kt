package nextdrive.springnativetemplate.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.SecurityWebFiltersOrder
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.Authentication
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.authentication.AuthenticationWebFilter
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import java.security.InvalidKeyException

@Configuration
@EnableWebFluxSecurity
class SecurityConfig {
    @Bean
    fun securityWebFilterChain(
        http: ServerHttpSecurity,
        rootAuthFilter: AuthenticationWebFilter
    ): SecurityWebFilterChain {
        return http
            .addFilterAt(rootAuthFilter, SecurityWebFiltersOrder.AUTHORIZATION)
            .authorizeExchange()
            .pathMatchers("/hello")
            .authenticated()
            .anyExchange()
            .permitAll()
            .and()
            .httpBasic()
            .and()
            .csrf().disable()
            .formLogin().disable()
            .logout().disable()
            .cors().disable()
            .build()
    }

    @Bean
    fun rootAuthFilter(manager: RootAuthenticationManager): AuthenticationWebFilter {
        return AuthenticationWebFilter(manager)
            .apply {
                setServerAuthenticationConverter(AuthorizationUsernamePasswordAuthenticationConverter())
                setRequiresAuthenticationMatcher(
                    ServerWebExchangeMatchers.pathMatchers(
                        "/hello"
                    )
                )
            }
    }
}

@Component
class RootAuthenticationManager : ReactiveAuthenticationManager {
    override fun authenticate(authentication: Authentication): Mono<Authentication> {
        return Mono.just(authentication)
            .onErrorResume { Mono.error(InvalidKeyException("TOKEN INVALID")) }
            .map {
                UsernamePasswordAuthenticationToken(
                   "user",
                    authentication.credentials as String,
                    listOf()
                )
            }
    }
}

class AuthorizationUsernamePasswordAuthenticationConverter(
    private val header: String = HttpHeaders.AUTHORIZATION,
    private val prefix: String = "Bearer "
) : ServerAuthenticationConverter {
    override fun convert(exchange: ServerWebExchange?): Mono<Authentication> {
        return Mono.justOrEmpty(exchange)
            .map { it?.request?.headers?.getFirst(header) ?: "" }
            .filter { it.isNotEmpty() && it.startsWith(prefix) }
            .map { it.replaceFirst(prefix, "") }
            .map { UsernamePasswordAuthenticationToken(it, it) }
    }
}