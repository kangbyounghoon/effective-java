package com.study.effectivejava.chapter02.rule01;

public class AProviderImpl implements Provider {
    @Override
    public Service newService() {
        return new Service() {
            @Override
            public String preparedStatement() {
                return "select A-Service-Impl from dual;";
            }
        };
    }
}
