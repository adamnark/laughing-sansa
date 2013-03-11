package examples.collections.zoo.cage;

import examples.collections.zoo.animals.Animal;
import java.util.Collection;

/**
 * A cage is a collection of things, with bars to keep them in.
 */
public interface Cage<T extends Animal>{

    public Collection<T> getAnimals();

    public void add(T t);
}
