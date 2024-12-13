public class MyString {
    public static void main(String[] args) {
        String hello = "hello";
        System.out.println(countChar(hello, 'h'));
        System.out.println(countChar(hello, 'l'));
    }

        public static boolean subsetOf(String str1, String str2) {
            StringBuilder str2Builder = new StringBuilder(str2);
            for (int i = 0; i < str1.length(); i++) {
                int index = str2Builder.indexOf(String.valueOf(str1.charAt(i)));
            if (index == -1) {
                return false;
            }
            str2Builder.setCharAt(index, '-');
        }
        return true;
    }

    public static String spacedString(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }
        StringBuilder spaced = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            spaced.append(str.charAt(i));
            if (i < str.length() - 1) {
                spaced.append(" ");
            }
        }
        return spaced.toString();
    }

    public static String randomStringOfLetters(int n) {
        StringBuilder randomLetters = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int index = (int) (Math.random() * 26);
            randomLetters.append("abcdefghijklmnopqrstuvwxyz".charAt(index));
        }
        return randomLetters.toString();
    }

    public static String remove(String str1, String str2) {
        StringBuilder result = new StringBuilder(str1);
        for (int i = 0; i < str2.length(); i++) {
            char ch = str2.charAt(i);
            int index = result.indexOf(String.valueOf(ch));
            if (index != -1) {
                result.deleteCharAt(index);
            }
        }
        return result.toString();
    }

    public static String insertRandomly(char ch, String str) {
        int randomIndex = (int) (Math.random() * (str.length() + 1));
        return str.substring(0, randomIndex) + ch + str.substring(randomIndex);
    }
}