package examples.collections.zoo;

import examples.collections.zoo.animals.Animal;
import examples.collections.zoo.animals.Butterfly;
import examples.collections.zoo.animals.Lion;
import examples.collections.zoo.animals.impl.LionImpl;
import examples.collections.zoo.cage.Cage;


/**
 * User: Liron Blecher
 * Date: 3/4/11
 */
public class MyZoo {

    private Cage<Lion> lionsCage;
    private Cage<Butterfly> butterfliesCage;

    //let's assume someone creates and initializes this class somewhere...
    public MyZoo(Cage<Lion> lionsCage, Cage<Butterfly> butterfliesCage) {
        this.lionsCage = lionsCage;
        this.butterfliesCage = butterfliesCage;

        //generics helps us avoid adding wrong data into classes
//        Lion lionKing = new LionImpl(3, "Simba");
//        lionsCage.add(lionKing); //Good!
//        butterfliesCage.add(lionKing) //Bad - Won't compile

        feedAllAnimalsInTheZoo();
    }


    private void feedAllAnimalsInTheZoo() {
        //the problem is this:
        //even though Lion and Butterfly extends Animal
        //a cage of Lions (or Butterflies) DOES NOT extends a cage of Animals
        //only the Generics extends each other, not the classes themselves

//        feedAnimalsInCageBAD(lionsCage); //compile time error
//        feedAnimalsInCageBAD(butterfliesCage); //compile time error

        feedAnimalsInCageGood(lionsCage); //OK!
        feedAnimalsInCageGood(butterfliesCage); //OK!
    }

    //this is a general method the feeds all the animals in a cage
    //this implementation accepts a Cage of Animal - and nothing else! (cage of Lion is no good here)
    private void feedAnimalsInCageBAD(Cage<Animal> cage) {
        for (Animal animal : cage.getAnimals()) {
            animal.feedMe();
        }
    }

    //this is a general method the feed all the animals in a cage
    //this implementation accepts a Cage of Something (?) that extends Animal
    private void feedAnimalsInCageGood(Cage<? extends Animal> cage) {
        for (Animal animal : cage.getAnimals()) {
            animal.feedMe();
        }
    }
}