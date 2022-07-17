package com.study.effectivejava.chapter02.rule01;

public class BProviderImpl implements Provider {
    @Override
    public Service newService() {
        return new Service() {
            @Override
            public String preparedStatement() {
                return "select B-Service-Impl from dual;";
            }
        };
    }
}
