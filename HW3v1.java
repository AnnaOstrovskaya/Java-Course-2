import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class HW3v1 {

    public static void main(String[] args) {
        Apple apple1 = new Apple(1);
        Apple apple2 = new Apple(1);
        Apple apple3 = new Apple(1);
        Apple apple4 = new Apple(1);

        ArrayList<Apple> apples = new ArrayList<Apple>();
        apples.add(apple1);
        apples.add(apple2);
        apples.add(apple3);
        apples.add(apple4);

        BoxOfFruit$ boxOfFruit1 = new BoxOfFruit$<>(apples);

        Orange orange1 = new Orange(2);
        Orange orange2 = new Orange(2);

        ArrayList<Orange> oranges = new ArrayList<Orange>();
        oranges.add(orange1);
        oranges.add(orange2);

        BoxOfFruit$ boxOfFruit2 = new BoxOfFruit$<>(oranges);

        boolean result = boxOfFruit1.compare(boxOfFruit2);

        BoxOfFruit$ boxOfFruit3 = new BoxOfFruit$<>(apples);

        try {
            ArrayList<Apple> oneAppleList = new ArrayList<Apple>();
            oneAppleList.add(apple1);
            boxOfFruit3.addFruitToBox(oneAppleList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static abstract class Fruit {

        private final int weight;

        public Fruit(int weight) {
            this.weight = weight;
        }

        public int getWeight() {
            return this.weight;
        }
    }

    public static class Apple extends Fruit {
        public Apple(int weight) {
            super(weight);
        }
    }

    public static class Orange extends Fruit {
        public Orange(int weight) {
            super(weight);
        }
    }

    public static class BoxOfFruit$<F extends Fruit> {
        private ArrayList<F> fruits;
        private int weight;

        public BoxOfFruit$(ArrayList<F> incomingFruits) {
            this.fruits = incomingFruits;
            this.weight = 0;
            for (F currentFruit : this.fruits) {
                this.weight = this.weight + currentFruit.getWeight();
            }
        }

        public void addFruitToBox(ArrayList<F> incomingFruits) throws Exception {
            if (this.fruits.size() > 0) {
                if ( //let's check if the class of the incoming fruit matches the class of existing fruit
                        this.fruits.get(0).getClass().equals(
                                incomingFruits.get(0).getClass()
                        )
                ) { // if matches
                    this.fruits.addAll(incomingFruits);
                    this.weight = 0;
                    for (F currentFruit : this.fruits) {
                        this.weight = this.weight + currentFruit.getWeight();
                    }
                } else {
                    throw new Exception("Incompatible fruit supplied");
                }
            }
        }

        public boolean compare(BoxOfFruit$ boxToCompareWith) {
            if (this.weight == boxToCompareWith.weight) {
                return true;
            } else {
                return false;
            }
        }
    }

}




