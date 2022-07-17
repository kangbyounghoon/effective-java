package com.study.effectivejava;

import com.study.effectivejava.chapter02.rule01.ProviderImpl;
import com.study.effectivejava.chapter02.rule01.Service;
import com.study.effectivejava.chapter02.rule01.Services;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EffectiveJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EffectiveJavaApplication.class, args);
//        Boolean flag = new Boolean(true);
//        System.out.println(flag);

        Services.registerDefaultProvider(new ProviderImpl());
        Service service = Services.newInstance();
        System.out.println(service.preparedStatement("select * from dual;"));
    }

}
