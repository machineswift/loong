package com.machine.server.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Slf4j
@Configuration
public class LoongGatewayConfig {

    @Bean
    public CorsWebFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("http://localhost:4200");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new CorsWebFilter(source);
    }


//    @Bean
//    public GlobalFilter postGlobalFilter() {
//        return (exchange, chain) -> {
//            return chain.filter(exchange)
//                    .then(Mono.fromRunnable(() -> {
//                        ServerHttpRequest request = exchange.getRequest();
//                        ServerHttpResponse response = exchange.getResponse();
//                        MultiValueMap<String, HttpCookie> cookies = request.getCookies();
//                        for (Map.Entry<String, List<HttpCookie>> entry : cookies.entrySet()) {
//                            List<HttpCookie> cookieList = entry.getValue();
//                            for (HttpCookie cookie : entry.getValue()) {
//
////                                ResponseCookie cookieNew = ResponseCookie.from(cookie.getName())
////                                        .value(cookie.getValue())
////                                        .maxAge(cookie.getMaxAge())
////                                        .domain(cookie.getDomain())
////                                        .path("/")
////                                        .secure(cookie.isSecure())
////                                        .httpOnly(cookie.isHttpOnly())
////                                        .sameSite(cookie.getSameSite())
////                                        .build();
////                                cookieList.add(cookieNew);
//                                String ss="";
//                            }
//                            //entry.setValue(cookieList);
//                        }
//                    }));
//        };
//    }

}
