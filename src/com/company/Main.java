package com.company;

import org.w3c.dom.css.Counter;

import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


class CheckPrime implements Runnable {

    int start;
    int end;
    List<Integer> primeNumbers;



    public CheckPrime(int start, int end, List<Integer> primeNumbers) {
        this.start = start;
        this.end = end;
        this.primeNumbers = primeNumbers;

    }

    @Override
    public void run() {

        for(int i = start; i <= end; i++){
            int checker = 0;

            for(int j = 2; j < i; j++){

                if(i % j == 0){
                    checker++;
                    break;
                }
            }
            if(checker == 0 && i > 1){
                primeNumbers.add(i);
            }
        }
    }
}


    public class Main {

        public static void main(String[] args) throws InterruptedException {

            List<Person> people = getPeople();

            // 1.1 - snittlönen för kvinnorna respektive männen i listan.
            System.out.println("Kvinnornas medelinkomst: " +
                    people
                            .stream()
                            .filter(person -> person.getGender().equals(Gender.FEMALE))
                            .mapToDouble(Person::getSalary)
                            .summaryStatistics()
                            .getAverage()
                    + "Kr."
            );
            System.out.println("-------------");

            System.out.println("Männens medelinkomst: " +
                    people
                            .stream()
                            .filter(person -> person.getGender().equals(Gender.MALE))
                            .mapToDouble(Person::getSalary)
                            .summaryStatistics()
                            .getAverage()
                    + "Kr."
            );
            System.out.println("-------------");

            // 1.2 - Vem har högst lön totalt.

            System.out.println("Personen med högst inkomst: " +
                    people
                            .stream()
                            .sorted(Comparator.comparing(Person::getSalary).reversed())
                            .limit(1)
                            .toList()
            );
            System.out.println("-------------");

            // 1.3 - Vem som har lägst lön totalt.
            System.out.println("Personen med lägst inkomst: " +
                    people
                            .stream()
                            .sorted(Comparator.comparing(Person::getSalary))
                            .limit(1)
                            .toList()
            );

            // 2. Skapa en bilfabrik, med hjälp av factory pattern.

            CarFactory carFactory = new CarFactory();
            Car volvo = carFactory.createCar("Volvo");
            Car bmw = carFactory.createCar("BMW");
            Car vw = carFactory.createCar("Volkswagen");
            Car tesla = carFactory.createCar("tesla");


            System.out.println("-------------");

            System.out.println(tesla.getBrand() + " got a range of " + tesla.getMaximumRange() + " miles");
            System.out.println("-------------");

            System.out.println(volvo.getBrand() + " got a range of " + volvo.getMaximumRange() + " miles");
            System.out.println("-------------");

            System.out.println(bmw.getBrand() + " got a range of " + bmw.getMaximumRange() + " miles");
            System.out.println("-------------");

            System.out.println(vw.getBrand() + " got a range of " + vw.getMaximumRange() + " miles");


            // 3. Skapa en lista av ord. Använd reguljära uttryck för att plocka ut endast
            // de ord som innehåller 2 eller fler engelska vokaler(a, e, i, o, u, y)
            System.out.println("-------------");
            Pattern pattern1 = Pattern.compile("[aeiouy].*[aeiouy]", Pattern.CASE_INSENSITIVE);
            System.out.println("The words that contains 2 or more vowels are:");
            List.of("Car", "Stamp", "Tree","Computer", "House", "Shh", "Transcendence")
                    .stream()
                    .filter(word -> pattern1.matcher(word).find())
                    .forEach(System.out::println);





            // 4. Räkna ut antalet primtal inom intervallet 0 till 500'000.
            // Dela upp intervallet på 2 eller flera trådar,
            // som var för sig räknar på antalet primtal inom sin tilldelade del parallellt.
            // Du kan t.ex. låta en tråd ta en första del (typ 0 till 350'000)
            // och en annan tråd resten 350'001 till 500'000.
            // Det är dock givetvis tillåtet med något eget mer avancerat och effektivt upplägg också.
            System.out.println("-------------");

            List<Integer> primeNumbers = Collections.synchronizedList(new ArrayList<Integer>());
            Thread primeChecker1 = new Thread(new CheckPrime(0, 250000, primeNumbers));
            Thread primeChecker2 = new Thread(new CheckPrime(250001, 500000, primeNumbers));
            primeChecker1.start();
            primeChecker2.start();
            primeChecker1.join();
            primeChecker2.join();
            System.out.println("There is: " + primeNumbers.size() + " Prime numbers.");




        }

        private static List<Person> getPeople() {
            return List.of(
                    new Person("Jonas", Gender.MALE, 30_000),
                    new Person("Hans", Gender.MALE, 25_000),
                    new Person("Hilda", Gender.FEMALE, 28_000),
                    new Person("Svante", Gender.MALE, 36_000),
                    new Person("Arvid", Gender.MALE, 35_000),
                    new Person("Sofia", Gender.FEMALE, 48_000),
                    new Person("Hanna", Gender.FEMALE, 24_000),
                    new Person("Göran", Gender.MALE, 56_000),
                    new Person("Gunnar", Gender.MALE, 38_000),
                    new Person("Johanna", Gender.FEMALE, 72_000)
            );
        }

    }
