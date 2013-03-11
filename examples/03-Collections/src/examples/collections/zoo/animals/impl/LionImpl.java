package examples.collections.zoo.animals.impl;

import examples.collections.zoo.animals.Lion;

/**
 * User: Liron Blecher
 * Date: 3/4/11
 */
public class LionImpl implements Lion {
    private int age;
    private String name;

    public LionImpl(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        //this option works if we want to compare one instance class (this) to another instance class (o)
        //if (o == null || getClass() != o.getClass()) return false;

        //this option works when we want to compare one instance class (o) without having another instance class
        //so we use the actual Class name (LionImpl)
        if (o instanceof LionImpl) {
            LionImpl lion = (LionImpl) o;
            //todo: need to check if name (both for this and lion) are nulls...
            return lion.age == this.age && lion.name.equals(this.name);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int result = age;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public void feedMe() {
        System.out.println("Wroar !!! eating... like a king!");
    }
}