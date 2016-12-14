import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 * Created by alex on 13.12.16.
 */
public class BeanComparator {
    public <T> void comparateBean(T inObject1, T inObject2) {

        Class clazz1 = inObject1.getClass();
        Class clazz2 = inObject2.getClass();
        Field[] fieldArray1 = clazz1.getDeclaredFields();
        Field[] fieldArray2 = clazz2.getDeclaredFields();
        int i = 0;
        while (i < fieldArray1.length) {
            StringBuilder result = new StringBuilder("Field ");
            fieldArray1[i].setAccessible(true);
            fieldArray2[i].setAccessible(true);
            try {
                if (fieldArray1[i].get(inObject1).equals(fieldArray2[i].get(inObject2))) {
                    result.append(fieldArray1[i].getName()).append(" - ").append(fieldArray1[i].get(inObject1));
                    result.append("    ");
                    result.append(fieldArray2[i].getName()).append(" - ").append(fieldArray2[i].get(inObject2));
                    result.append("  - match!");
                } else {
                    result.append(fieldArray1[i].getName()).append(" - ").append(fieldArray1[i].get(inObject1));
                    result.append("    ");
                    result.append(fieldArray2[i].getName()).append(" - ").append(fieldArray2[i].get(inObject2));
                    result.append("  - no match(");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            i++;
            System.out.println(result);
        }

    }
}

class TestClass1 {
    protected boolean flag = true;
    public int a = (int) (Math.random() * 10);
    public int b = (int) (Math.random() * 10);
    private double c = 1.5;
    public String name = "Some String";
    public String color = "Other string";
    List list = Arrays.asList(10, 20, 30);

    TestClass1() {
        flag = true;
        a = (int) (Math.random() * 10);
        b = (int) (Math.random() * 10);
        c = 1.5;
        name = "Some String";
        color = "Other string";
        list = Arrays.asList(10, 20, 30);
    }

    TestClass1(boolean flag, int a, int b, double c, String name, String color) {
        this.flag = flag;
        this.a = a;
        this.b = b;
        this.c = c;
        this.name = name;
        this.color = color;
    }
}

class BeanComparatorTest {
    public static void main(String[] argv) {
        TestClass1 o1 = new TestClass1();
        TestClass1 o2 = new TestClass1();
        TestClass1 o3 = new TestClass1(false, 2, 3, 5.8, "Boom", "black");
        BeanComparator bc = new BeanComparator();
        bc.comparateBean(o1, o2);
        System.out.println();
        bc.comparateBean(o3, o1);
    }
}

