package com.study.effectivejava.chapter02.rule01;

public class ServiceImpl implements Service {
    @Override
    public String preparedStatement(String query) {
        return query;
    }
}
