# 생성자 인자가 많을 때는 Builder 패턴 적용을 고려하라.

### 점증적 생성자 패턴
````
 정적 팩터리나 생성자는 같은 문제를 갖고 있다. 선택전 인자가 많은 상황에 잘 적응하지 못한다는 것.
 가령 인자의 수가 20개인 경우 보통 프로그래머들은 이런 상황에 '점층적 생성자 패턴'을 적용한다.
 
 #점증적 생성자 패턴 - 더 많은 인자 개수에 잘 적응하지 못한다.
 public NutritionFacts(int servingSize, int servings)..
 public NutritionFacts(int servingSize, int servings, int calories)..
 public NutritionFacts(int servingSize, int servings, int calories, int fat, int sodium)..
 public NutritionFacts(int servingSize, int servings, int calories, int fat, int sodium, int carbohydrate)..
 
 이 클래스로 객체를 생성할 때는 설정하려는 인자 개수에 맞는 생성자를 골라 호출하면 된다.
 NutritionFacts cocaCola = new NutritionFacts(240, 8, 100, 3, 35, 27);
 
````
## 점층적 생성자 패턴은 잘 동작하지만 인자 수가 늘어나면 클라이언트 코드를 작성하기가 어려워지고, 무엇보다 읽기 어려운 코드가 되고만다.

---

### 자바빈 패턴
````
생성자에 전달되는 인자 수가 많을 때 적용 가능한 두 번째 대안은 자바빈 패턴이다. 인자 없는 생성자를 호출하여 객체부터 
만든 다음, 설정 메서드(setter method)들을 호출하여 필수 필드뿐 아니라 선택적 필드의 값들까지 채우는 것이다.

#자바빈 패턴 - 일관성 훼손이 가능하고, 항상 변경 가능하다.
public class NutritionFacts {
    public NutritionFacts(){}
    
    //설정자(setter)
    public void setServingSize(int val)..
    public void setServings(int val)..
    public void setCalories(int val)..
    public void setFat(int val)..
    public void setSodium(int val)..
    public void setCarbohydrate(int val)...
}

이 패턴에는 점층적 생성자 패턴에 있던 문제는 없다. 작성해야 하는 코드이 양이 조금 많아질 수는 있지만 객체를
생성하기도 쉬우며, 읽기도 좋다.

NutritionFacts cocaCola = new NutritionFacts();
cocaCola.setServingSize(240)
cocaCola.setServings(8)
cocaCola.setCalories(100)
cocaCola.setFat(35)....
````
## 단점
 - 1회의 함수 호출로 객체 생성을 끝낼 수 없으므로, 객체 일관성(consistency)이 일시적으로 깨질 수 있다는 것이다. 
   - 생성자의 인자가 유효한지 검사하여 일관성을 보장하는 단순한 방법을 여기서는 사용할 수 없다.
 - 변경 불가능(immutable) 클래스를 만들 수 없다는 것이다.


# 대안 (Builder 패턴)
 ### 점층적 생성자 패턴의 안전성에 자바빈 패턴의 가독성을 결합한 세 번째 대안이 있다는 것이다.

- 필요한 객체를 직접 생성하는 대신, 클라이언트는 먼저 필수 인자들을 생성자에(또는 정적 팩터리 메서드에) 전부 전달하여 빌더 객체를 만든다.
- 그리고 아무런 인자 없이 build 메서드를 호출하여 변경 불가능(immutable) 객체를 만드는 것이다.
- 빌더 클래스는 빌더가 만드는 객체 클래스의 정적 멤버 클래스로 정의한다.

```
NutritionFacts Class 참조 (Rule02Tests.java)

NutritionFacts 객체가 변경 불가능하다는 사실, 그리고 모든 인자의 기본값(default value)이 한곳에 모여 있다는 것에 유의하기 바란다.
빌더에 정의된 설정 메서드는 빌더 객체 자신을 반환하므로, 설정 메서드를 호출하는 코드는 죽이어서 쓸 수 있다.
아래 코드를 보자.

 NutritionFacts cocaCola = new NutritionFacts.Builder(240, 8)
                .calories(100)
                .sodium(35)
                .carbohydrate(27)
                .build();

생성자와 마찬가지로, 빌더 패턴을 사용하면 인자에 불변식을 적용할 수 있다.
build 메서드 안에서 해당 불변식이 위반되었는지 검사할 수 있는 것이다.
빌더 객체에서 실제 객체로 인자가 복사된 다음에 불변식들을 검사 할 수 있다는 것, 
그리고 그 불변식을 빌더 객체의 필드가 아니라 실제 객체의 필드를 두고 검사할 수 있다는 것은 중요하다(규칙39).
불변식을 위반한 경우, build 메서드는 IllegalStateException을 던저야 한다(규칙60).
이 예외 객체를 살펴보면 어떤 불변식을 위반했는지 알아낼 수도 있어야 한다(규칙63).
이렇게 하면 build가 실제로 호출되기 전에 불변식을 깨뜨리는 인자가 전달 되었다는 사실을 신속하게 알 수 있다는 장점이 있다.                
```
### 빌더 패턴은 유연하다.
- 하나의 빌더 객체로 여러 객체를 만들 수 있다.
  다른 객체를 생성해야 할 때마다 빌더 객체의 설정 메서드를 호출하면 다음에 생성될 객체를 바꿀 수 있다.
- 또한 빌더 객체는 어떤 필드의 값은 자동으로 채울 수 도 있다. 객체가 만들어질 때마다 자동적으로 증가되는 일련번호 같은 것은 그 좋은 예다.
- 인자가 설정된 빌더는 훌륭한 `추상적 팩터리`다. 다시 말해서, 클라이언트는 그런 빌더를 어떤 메서드에 넘겨서, 해당 메서드가 클라이언트에게
  하나 이상의 객체를 만들어 주도록 할 수 있다.
```
 //자료형이 T인 객체에 대한 빌더
 publiic interface Builder<T> {
   public T build();
 }
 
 이런 인터페이스가 있으면, NutritionFacts.Builder 클래스는 Builder<NutritionFacts>를 implement 하도록 선언할 수 있음에 유의하자.
```
- 빌더 객체를 인자로 받는 메서드는 보통 `한정적 와일드카드 자료형`을 통해 인자의 자료형을 제한한다(규칙28).
  예를 들어, 아래의 메서드는 클라이언트가 전달한 Builder 객체를 사용하여 트리의 노드(node)를 만든다.
```
   Tree buildTree(Builder<? extends Node> nodeBuilder) { ... }
```
## 요약
빌더 패턴은 인자가 많은 생성자나 정적 팩터리가 필요한 클래스를 설계할 때, 특히 대부분의 인자가 선택적 인자인 상황에 유용하다.
클라이언트 코드 가독성은 전통적인 점층적 생성자 패턴을 따를 때보다 훨씬 좋아질 것이며, 그 결과물은 자바빈을 사용할 때보다 훨씬 안전할 것이다.