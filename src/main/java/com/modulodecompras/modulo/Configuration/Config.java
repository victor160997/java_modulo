package com.modulodecompras.modulo.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.client.SimpleClientHttpRequestFactory;


@Configuration
public class Config {
    @Bean
    public RestTemplate getRestTemplate(){
        RestTemplate rt = new RestTemplate();
        SimpleClientHttpRequestFactory reqFactory = new SimpleClientHttpRequestFactory();
        reqFactory.setConnectTimeout(5000);
        reqFactory.setReadTimeout(5000);
        rt.setRequestFactory(reqFactory);
        return rt;
    }

}
