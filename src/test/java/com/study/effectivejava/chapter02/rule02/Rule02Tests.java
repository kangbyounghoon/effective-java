package com.study.effectivejava.chapter02.rule02;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Rule02Tests {

    @Test
    void 빌더패턴_Test() {

        NutritionFacts cocaCola = new NutritionFacts.Builder(240, 8)
                .calories(100)
                .sodium(35)
                .carbohydrate(27)
                .build();
        System.out.println(cocaCola.toString());
    }
}
