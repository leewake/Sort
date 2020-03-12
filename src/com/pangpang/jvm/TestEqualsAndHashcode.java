package com.pangpang.jvm;

import java.util.HashMap;
import java.util.Objects;

/**
 * @description:
 * @author: leewake
 * @create: 2020-02-19 14:01
 **/

public class TestEqualsAndHashcode {

    public static void main(String[] args) {
        Student student1 = new Student(1, "张三");
        Student student2 = new Student(1, "张三");

        HashMap<Student, String> map = new HashMap<>();
        map.put(student1, "student1");
        map.put(student2, "student2");

        System.out.println(map.toString());
    }


    public static class Student {
        private int id;
        private String name;

        public Student(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Student student = (Student) o;
            return id == student.id &&
                    Objects.equals(name, student.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }

}
