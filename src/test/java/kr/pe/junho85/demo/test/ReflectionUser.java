package kr.pe.junho85.demo.test;

import java.lang.reflect.Field;

public class ReflectionUser {
    public void appendText(Object object, String text) {
        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);

            if (field.getType().equals(String.class)) {
                try {
                    String s = (String) field.get(object);
                    if (s == null) {
                        field.set(object, text);
                    } else {
                        field.set(object, s + text);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Class<?> clazz = RefObject.class;

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName());
            System.out.println(field.getType());
        }

        Field field = clazz.getDeclaredField("numId");

        // 필드값을 다루기 위해서, 객체를 생성함!
        RefObject refObject = new RefObject();
        field.setAccessible(true);
        int numId = (int) field.get(refObject);
        System.out.println("numId는: " + numId);

        field.set(refObject, 3);

        numId = (int) field.get(refObject);
        System.out.println("numId는: " + numId);

        // string인 필드 내용에 텍스트 붙이기
        System.out.println("name: " + refObject.getName());

        ReflectionUser reflectionUser = new ReflectionUser();
        reflectionUser.appendText(refObject, " hello world!");

        System.out.println("name: " + refObject.getName());
    }
}
