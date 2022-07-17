package com.study.effectivejava;

import com.study.effectivejava.chapter02.rule01.AProviderImpl;
import com.study.effectivejava.chapter02.rule01.BProviderImpl;
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

        //서비스 제공자 프레임워크 패턴 예

        //1. 서비스 제공자 인터페이스의 구현체가 제공자 등록 API 에 등록될 시점에 서비스 제공자 인터페이스의 구현체에 정의된 서비스 인터페이스 구현체로 제한된다.
        Services.registerProvider("A", new AProviderImpl());
        Services.registerProvider("B", new BProviderImpl());


        //2. 서비스 접근 API 로 원하는 서비스를 취한다.
        Service aService = Services.newInstance("A");
        System.out.println(aService.preparedStatement());

        Service bService = Services.newInstance("B");
        System.out.println(bService.preparedStatement());

    }

}
