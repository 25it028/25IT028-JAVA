import java.lang.annotation.*;
import java.lang.reflect.*;
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Run {
}
class MyTests {
    @Run
    public void testAdd() {
        System.out.println("Test Add Passed");
    }
    @Run
    public void testSub() {
        System.out.println("Test Sub Passed");
    }
    public void normalMethod() {
        System.out.println("Normal Method");
    }
    @Run
    public void testMultiply() {
        System.out.println("Test Multiply Passed");
    }
}
public class MiniTestRunner {
    public static void main(String[] args) throws Exception {
        MyTests obj = new MyTests();
        Method[] methods = MyTests.class.getDeclaredMethods();
        int count = 0;
        for (Method method : methods) {
            if (method.isAnnotationPresent(Run.class)
                    && method.getParameterCount() == 0) {
                method.invoke(obj);
                count++;
            }
        }

        System.out.println("Total tests executed: " + count);
    }
}