package com.study.effectivejava.chapter02.rule01;

public class ProviderImpl implements Provider {
    @Override
    public Service newService() {
        return new ServiceImpl();
    }
}
