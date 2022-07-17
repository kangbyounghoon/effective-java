package com.study.effectivejava.chapter02.rule01;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//서비스 등록과 접근에 사용되는 객체 생성 불가능 클래스
public class Services {
    private Services() {    // 객체 생성 방지
        
    }

    // 서비스 이름과 서비스 간 대응관계 보관
    private static final Map<String, Provider> providers = new ConcurrentHashMap<>();
    public static final String DEFAULT_PROVIDER_NAME = "<def>";

    // 제공자 등록 API
    public static void registerDefaultProvider(Provider provider) {
        registerProvider(DEFAULT_PROVIDER_NAME, provider);
    }
    public static void registerProvider(String name, Provider provider) {
        providers.put(name, provider);
    }


    // 서비스 접근 API
    public static Service newInstance() {
        return newInstance(DEFAULT_PROVIDER_NAME);
    }

    public static Service newInstance(String name) {
        Provider provider = providers.get(name);
        if (provider == null) {
            throw new IllegalArgumentException("No provider registered with name : " + name);
        }
        return provider.newService();
    }
}
