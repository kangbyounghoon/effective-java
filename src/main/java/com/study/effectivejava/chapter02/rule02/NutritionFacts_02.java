package com.study.effectivejava.chapter02.rule02;

public class NutritionFacts_02 {
    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int sodium;
    private final int carbohydrate;

    private NutritionFacts_02(NutritionFactsBuilder builder) {
        servingSize = builder.servingSize;
        servings = builder.servings;
        calories = builder.calories;
        fat = builder.fat;
        sodium = builder.sodium;
        carbohydrate = builder.carbohydrate;

    }

    public static class NutritionFactsBuilder implements com.study.effectivejava.chapter02.rule02.Builder<NutritionFacts_02> {
        //필수 인자
        private final int servingSize;
        private final int servings;

        //선택적 인자 - 기본값으로 초기화
        private int calories = 0;
        private int fat = 0;
        private int sodium = 0;
        private int carbohydrate = 0;

        public NutritionFactsBuilder(int servingSize, int servings) {
            this.servingSize = servingSize;
            this.servings = servings;
        }

        @Override
        public NutritionFacts_02 build() {
            return new NutritionFacts_02(this);
        }


        public NutritionFactsBuilder calories(int val) {
            calories = val;
            return this;
        }

        public NutritionFactsBuilder fat(int val) {
            fat = val;
            return this;
        }

        public NutritionFactsBuilder carbohydrate(int val) {
            carbohydrate = val;
            return this;
        }

        public NutritionFactsBuilder sodium(int val) {
            sodium = val;
            return this;
        }

    }
}
