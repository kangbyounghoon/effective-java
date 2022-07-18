package com.study.effectivejava.chapter02.rule01;

public class BasicProvider extends NonRegisteringProvider {
    static {
        try {
            Services.registerDefaultProvider(new BasicProvider());
        } catch (Exception ex) {
            throw new RuntimeException("등록할 제공자가 없습니다.");
        }
    }

    public BasicProvider() {

    }
}
