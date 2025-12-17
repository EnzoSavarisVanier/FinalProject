package util;

public class Util {
    /**
     * turns a string into titlecase
     * @param str string input
     * @return titlecase string
     */
    public static String toTitleCase(String str) {
        if (str.isEmpty()) {
            return str;
        }

        str = str.toLowerCase();
        StringBuilder result = new StringBuilder();
        int start = 0;

        for (;;) {   // intentional infinite loop, we break manually
            int spaceIndex = str.indexOf(' ', start);

            // no more spaces → last word
            if (spaceIndex == -1) {
                result.append(str.substring(start, start + 1).toUpperCase());
                result.append(str.substring(start + 1));
                break;
            }

            // word before space
            result.append(str.substring(start, start + 1).toUpperCase());
            result.append(str, start + 1, spaceIndex);
            result.append(" ");

            start = spaceIndex + 1;
        }

        return result.toString();
    }
}
