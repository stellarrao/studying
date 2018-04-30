package javaexercise.interview.generics;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by rxh on 2018/4/15.
 */
/*
class GenericCls<T>
{
    private Class<T> type;
    public GenericCls(Class<T> cls)
    {
        type= cls;
    }
    Class<T> getType(){return type;}
}*/

class BaseClass<T> {

    private Class<T> type;

    public BaseClass(Class<T> type) {
        this.type = type;
    }

    public Class<T> getType() {
        return type;
    }

    private Class<T> getTypeParameterClass() {
        Type type = this.getClass().getGenericSuperclass();

        if(!ParameterizedType.class.isAssignableFrom(type.getClass())) {
            return null;
        }

        ParameterizedType paramType = (ParameterizedType) type;
        return (Class<T>) paramType.getActualTypeArguments()[0];
    }

    public static boolean set(Object object, String fieldName, Object fieldValue) {
        Class<?> clazz = object.getClass();
        while (clazz != null) {
            try {
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                field.set(object, fieldValue);
                return true;
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
        return false;
    }

    public T produce(Map<String, Object> map) {

        Object o = null;
        try {
//            o = getTypeParameterClass().newInstance();
            o = getType().newInstance();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                set(o, entry.getKey(), entry.getValue());
            }


        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        if (o != null)
            return (T) o;
        else
            return null;
    }
}

class Business extends BaseClass<AssginValue>{
    public Business(Class<AssginValue> type) {
        super(type);
    }
}
public class PropertyAssignValue {

    private static void testAssginValue() {
        BaseClass<AssginValueOther> b = new BaseClass<>(AssginValueOther.class);
//        Business b = new Business();
        Map<String, Object> map = new HashMap<>();
        map.put("test","Hello");
        map.put("name","rxh");

        AssginValueOther a = b.produce(map);
        System.out.println(a.toString());
    }
    public static void main(String[] args) throws Exception {
//        String path = ClassLoader.getSystemClassLoader().getClass().getResource("/").toString();
/*
// 创建内部类实例
        Class<?> enclosingClass = Class.forName("javaexercise.interview.generics.PropertyAssignValue");
        Object enclosingInstance = enclosingClass.newInstance();


        Class<?> object = Class.forName("javaexercise.interview.generics.PropertyAssignValue$AssginValue");
        Object o = object.getDeclaredConstructor(enclosingClass).newInstance(enclosingInstance);
        System.out.println(o);


        Class<?> object = Class.forName("javaexercise.interview.generics.AssginValue");
        Object o = object.getDeclaredConstructor().newInstance();
        System.out.println(o);

        Field testField = object.getDeclaredField("test");
        Field nameField = object.getDeclaredField("name");

        testField.setAccessible(true);
        testField.set(o, "Hello");
        testField.setAccessible(false);

        nameField.setAccessible(true);
        nameField.set(o, "name");
        nameField.setAccessible(false);

        System.out.println(o);
*/
        testAssginValue();
    }

}


class AssginValue {
    private String test;
    private String name;

    /*public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    */

    @Override
    public String toString() {
        return "AssginValue{" +
                "test='" + (test == null ? "null" : test) + '\'' +
                ", name='" + (name == null ? "null" : name) + '\'' +
                '}';
    }
}

class AssginValueOther {
    private String test;
    private String name;

    /*public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    */

    @Override
    public String toString() {
        return "AssginValue{" +
                "test='" + (test == null ? "null" : test) + '\'' +
                ", name='" + (name == null ? "null" : name) + '\'' +
                '}';
    }
}
