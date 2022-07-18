package com.study.effectivejava.chapter02.rule01;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Rule01Tests {

    @Test
    void 서비스_제공자_프레임워크_기본() {

        //1. 제공자 등록 API에 서비스 제공자 인터페이스 구현체 등록
        Services.registerProvider("A", new AProviderImpl());
        Services.registerProvider("B", new BProviderImpl());

        //2. 서비스 접근 API로 원하는 서비스를 가져온다.
        Service aService = Services.newInstance("A");
        Service bService = Services.newInstance("B");

        //출력
        System.out.println(aService.preparedStatement());
        System.out.println(bService.preparedStatement());
    }

    @Test
    void 서비스_제공자_프레임워크_Basic_Provider() throws ClassNotFoundException {

        //1. 제공자 등록 API에 서비스 제공자 인터페이스 구현체 등록
        Class.forName("com.study.effectivejava.chapter02.rule01.BasicProvider");

        //2. 서비스 접근 API로 원하는 서비스를 가져온다.
        Service defaultService = Services.newInstance();

        //출력
        System.out.println(defaultService.preparedStatement());
    }
}
