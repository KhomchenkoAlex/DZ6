//import BeanComparator.*;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;


/**
 * Created by alex on 14.12.16.
 */
public class CloneCreator {
    public <T> T createClone(T object){
        T cloneObject = null;
        Class clazz = object.getClass();
        Field[] fieldArray = clazz.getDeclaredFields();
        try {
            cloneObject = (T)clazz.newInstance();
            for (Field field:fieldArray) {
                String name = field.getName();
                field.setAccessible(true);
                Field cloneObjectField = cloneObject.getClass().getDeclaredField(name);
                cloneObjectField.setAccessible(true);
                cloneObjectField.set(cloneObject, field.get(object));
            }
        } catch (IllegalAccessException | InstantiationException | NoSuchFieldException e){
            e.printStackTrace();
        }
        return cloneObject;
    }
}

class CloneCreatorTest {
    public static void main(String[] args){
        CloneCreator cloneCreator = new CloneCreator();
        //BeanComparator bp = new BeanComparator();
        TestClass2 testClass2 = new TestClass2();
        TestClass2 clone = cloneCreator.createClone(testClass2);
        System.out.println("Original: " + testClass2.toString());
        System.out.println("Clone: " + clone.toString());
        System.out.println(clone.equals(testClass2));
    }
}

class TestClass2 {
    protected boolean flag = true;
    public int a = (int) (Math.random() * 10);
    public int b = (int) (Math.random() * 10);
    public String name = "Some String";
    List list = Arrays.asList(this.a, this.b);

    @Override
    public String toString(){
        String nameOfClass = this.getClass().getSimpleName();
        String fields = "  a - "+this.a + "; b - " + this.b + "; name - " + this.name + "; list - " + this.list;
        return  nameOfClass + fields;
    }
}
