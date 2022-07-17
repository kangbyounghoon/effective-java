# 생성자 대신 정적 팩터리 메서드를 사용할 수 없는지 생각해 보라

### 정적 팩터리 메서드(static factory method) 예:
````
public static Boolean valueOf(boolean a) { 
 return b ? Boolean.TRUE : Boolean.FALSE;
}
````

### 클래스를 정의할 때 생성자 대신 정적 팩터리 메서드를 제공할 수 있다.
#### 장점
 - 생성자와는 달리 정적 팩터리 메서드에는 이름이 있다.
   - 생성자에 전달되는 인자들은 어떤 객체가 생성되는지를 설명하지 못하지만, 정적 팩터리 메서드는 이름을 잘 짓기만 한다면 사용하기도 쉽고, 클라이언트 코드이 가독성도 높아진다.
 - 생성자와는 달리 호출할 때마다 새로운 객체를 생성할 필요는 없다는 것이다.
   - 앞서 살펴본 Boolean.valueOf(Boolean) 메서드는 이 기법을 활용한 좋은 사례다. 결코 객체를 생성하지 않는 것이다. 동일한 객체가 요청되는 일이 잦고, 특히 객체를 만드는 비용이 클 때 적용하면 성능을 크게 개선할 수 있다.
 - 생성자와는 달리 반환값 자료형의 하위 자료형 객체를 반환할 수 있다는 것이다. 따라서 반환되는 객체의 클래스를 훨씬 유연하게 결정할 수 있다. 
    - 정적 팩터리 메서드가 반환하는 객체의 클래스는 정적 팩터리 메서드가 정의된 클래스 코드가 작성되는 순간에 존재하지 않아도 무방하다.
    - 서비스 제공자 프레임워크의 근간을 이루는 것이 바로 그런 유연한 정적 팩터리 메서드들이다.
    - 서비스 제공자 프레임워크는 다양한 서비스 제공자들이 하나의 서비스를 구성하는 시스템인데, 클라이언트가 실제 구현된 서비스를 이용할 수 있도록 한다. 물론 그 세부적인 구현 내용은 몰라도 이용할 수 있다.
      - JDBC 경우
        - Connection이 서비스 인터페이스
        - DriverManager.registerDriver가 제공자 등록 API
        - DriverManager.getConnection이 서비스 접근 API 역할
        - Driver가 서비스 제공자 인터페이스 역할
        ```
        public class Driver extends NonRegisteringDriver implements java.sql.Driver {
        static {
         try {
            //서비스 제공자 인터페이스를 제공자 등록 API에 등록한다.
             java.sql.DriverManager.registerDriver(new Driver());
         } catch (SQLException E) {
             throw new RuntimeException("Can't register driver!");
         }
        }
        
        public Driver() throws SQLException {
        // Required for Class.forName().newInstance()
        }
        ```
      - 예제소스
        - Service가 서비스 인터페이스
        - Services.registerProvider가 제공자 등록 API
        - Services.newInstance가 서비스 접근 API 역할
        - Provider가 서비스 제공자 인터페이스 역할
 - 형인자  자료형(parameterized type) 객체를 만들 때 편하다는 점이다.
   ```
   public static <K,V> HashMap<K,V> newInstance() {
    return new HashMap<K,V>();
   }
   
   Map<String, List<String>> m = HashMap.newInstance();
   
   자료형 유츄는 jdk1.7에 생성자에도 가능해졌다. 아래 예)
   Map<String, List<String>> m = new HashMap<>();
   ```
### 단점
 - public 이나 protected로 선언된 생성자가 없으므로 하위 클래스를 만들 수 없다는 것.
 - 정적 팩터리 메서드가 다른 정적 메서드와 확연히 구분되지 않는다는 것.