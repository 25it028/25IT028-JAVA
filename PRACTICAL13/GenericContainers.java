package PRACTICAL13;

class Box<T> {
    private T value;

    void set(T value) {
        this.value = value;
    }

    T get() {
        return value;
    }
}

class Pair<A, B> {
    private A first;
    private B second;

    Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    A getFirst() {
        return first;
    }

    B getSecond() {
        return second;
    }
}

public class GenericContainers {

    static <T> T firstElement(T[] array) {
        return array[0];
    }

    public static void main(String[] args) {

        Box<String> b1 = new Box<>();
        b1.set("Hello Java");

        Box<Integer> b2 = new Box<>();
        b2.set(100);

        System.out.println("Box String: " + b1.get());
        System.out.println("Box Integer: " + b2.get());

        Pair<String, Integer> p =
            new Pair<>("Tanvi", 18);

        System.out.println("Pair First: " + p.getFirst());
        System.out.println("Pair Second: " + p.getSecond());

        String[] names = {"Tanvi", "Happy", "Jash"};

        System.out.println("First Element: " +
            firstElement(names));

        Integer[] numbers = {10, 20, 30};

        System.out.println("First Number: " +
            firstElement(numbers));
    }
}

