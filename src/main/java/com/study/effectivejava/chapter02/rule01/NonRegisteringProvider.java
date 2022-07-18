package com.study.effectivejava.chapter02.rule01;

public class NonRegisteringProvider implements Provider {
    @Override
    public Service newService() {
        return new Service() {
            @Override
            public String preparedStatement() {
                return "select basic-provider from dual;";
            }
        };
    }
}
