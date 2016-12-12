import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by alex on 12.12.16.
 */
public class BeanRepresenter {

    TreeMap representObject(Object inputObject) throws IllegalAccessException {
        TreeMap<String, Object> fieldMap = new TreeMap<String, Object>();
        String key = null;
        Object value = null;
        Class clazz = inputObject.getClass();
        Field[] fieldArray = clazz.getDeclaredFields();
        for (Field field : fieldArray) {
            field.setAccessible(true);
            value = field.get(inputObject);
            key = field.getName();
            fieldMap.put(key, value);
        }
        return fieldMap;
    }
}

class TestClass {
    protected boolean flag = true;
    public int a = 10;
    double b = 1.5;
    public String name = "Some name";
    int[] array = {1, 2, 3, 4, 5, 6};
    List list = Arrays.asList(10, 20, 30);
}

class BeanRepresenterTest {
    public static void main(String[] argv) {
        TreeMap<String, Object> treemap = new TreeMap<>();
        TestClass test = new TestClass();
        BeanRepresenter beanRepresenter = new BeanRepresenter();

        try {
            treemap = beanRepresenter.representObject(test);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        for (String key : treemap.keySet()) {
            Object value = treemap.get(key);
            System.out.println(key + " " + value.toString());

        }
    }
}

