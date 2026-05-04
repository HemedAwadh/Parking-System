import java.util.*;

public class Reversal {
    public static void main(String[] args) {
        /// Given string "Hello World" reverse the characters

        String name = "strops";
        String rev = new StringBuilder(name).reverse().toString();
        System.out.println("Reversed String is "+ rev);

        String a = "Hello world";
        String[] items = a.split("");

        String[] reversed = new String[items.length];

        int initial = 0;

        for (int i = items.length - 1; i >= 0; i--) {
            System.out.println(items[i]);
            reversed[initial] = items[i];

            initial++;
        }

        System.out.println(Arrays.toString(reversed));

        String reversedString = "";

        for (String item : reversed) {
            reversedString += item;
        }

//        for (int j = 0; j <= reversed.length - 1; j++) {
//            reversedString += reversed[j];
//        }

        System.out.println(reversedString);

        /// option 2
        String input = "Hello Herrera";
        String reversed1 = input.chars()
                .mapToObj(c -> String.valueOf((char) c))
                .reduce("", (d, b) -> b + d);
        System.out.println(reversed1);


        String java = "Java#programming";
        java.length();
        System.out.println(java.length());
        java.charAt(0);
        System.out.println(java.charAt(0));
        java.substring(0, 4);
        System.out.println(java.substring(0, 4));
        java.contains("Java");
        System.out.println(java.contains("V"));
        java.equals("java");
        System.out.println(java.equals("Java programming"));
        java.equalsIgnoreCase("javax");
        System.out.println(java.equalsIgnoreCase("JAVA PROGRAMMIN"));
        System.out.println(Arrays.toString(java.split("#")));


        int[] numbers = {1, 2, 3};
        int length = numbers.length;
        System.out.println(length);
        int index = numbers[0];
        System.out.println(index);


        List<String> num = new ArrayList<>();
        num.add("Herrera");
        System.out.println(num);
        num.add("Hemed");
        System.out.println(num);
        num.contains("Hemed");
        System.out.println(num);
        num.getFirst();
        System.out.println(num);

        for (String list : num) {
            System.out.println(list);
        }

        num.forEach(System.out::println);

        Map<String, Object> map = new HashMap<>();
        map.put("Java", 21);
        map.put("Springboot", 4);

        System.out.println(map);
        System.out.println(map.get("Java"));
        System.out.println(map.containsKey("Spring"));
        map.remove("Java");
        System.out.println(map);
        System.out.println("---------------------------");

        /// Given String "omsoososoooo" get the character that appears most and how many times it appears in the string

        String input1 = "omsoososoooo";

        Map<Character, Integer> freqMap = new HashMap<>();

        //Count frequency
        for (char c : input1.toCharArray()) {
            System.out.println(c);
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
            System.out.println(freqMap);
        }

        char frequentCharacter = 0;
        int maxCount = 0;

        //Find max
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                frequentCharacter = entry.getKey();

            }
            System.out.println(frequentCharacter + " : appears" + " " + maxCount + "times");
        }


        ///Given a String "ABBA", determine if the string is a palendrom

        String x = "ABBA";
        String reversedPalendrom = new StringBuilder(x).reverse().toString();
        if (x.equals(reversedPalendrom)) {
            System.out.println("String is a Palendrom");
        } else {
            System.out.println("String is not Palendrom");
        }
        var result = NumberPalindrome.isPalindrom(121);
        System.out.println(result);

        var res = DecimalComparator.areEqualByThreeDecimalPlaces(12, 13);
        System.out.println(res);

        var y = PlayingCat.isCatPlaying(true, 25);
        System.out.println(y);

        NumberInWord.printNumberInWord(2);
    }

    public static class NumberPalindrome {
        public static boolean isPalindrom(int number) {
            if (number < 0) {
                return false;
            }
            int original = number;
            int reversed = 0;
            while (number != 0) {
                int digit = number % 10;
                reversed = reversed * 10 + digit;
                number = number / 10;

            }
            return original == reversed;

        }

    }

    public class DecimalComparator {
        public static boolean areEqualByThreeDecimalPlaces(double first, double second) {

            long firstRounded = (long) (first * 1000);
            double secondRounded = second * 1000;
            return firstRounded == secondRounded;

        }
    }

    public class PlayingCat {
        public static boolean isCatPlaying(boolean summer, int temperature) {
            if (temperature >= 25 && temperature <= 35) {
                return true;
            } else if (temperature >= 25 && temperature <= 45) {
                return true;
            } else {
                return false;


            }

        }
    }

    public class NumberInWord {
        public static void printNumberInWord(int number) {
            String numberInWord;
            switch (number) {
                case 0 -> numberInWord = "ZERO";
                case 1 -> numberInWord = "ONE";
                case 2 -> numberInWord = "TWO";
                case 3 -> numberInWord = "THREE";
                case 4 -> numberInWord = "FOUR";
                case 5 -> numberInWord = "FIVE";
                case 6 -> numberInWord = "SIX";
                case 7 -> numberInWord = "SEVEN";
                case 8 -> numberInWord = "EIGHT";
                case 9 -> numberInWord = "NINE";
                default -> numberInWord = "OTHER";

            }
            System.out.println(numberInWord);
        }


    }


}
