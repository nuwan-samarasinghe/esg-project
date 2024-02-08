package org.esg.assesment.calculator;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author: NUWAN
 * Date: 2024-01-30
 * Description:
 * calculator implementation
 */
public class StringCalculator {

    public static final String REGEX_MATCH_DELIMITER_AND_NUMBER = "//\\[(.*?)\\]\\n(.*)";
    public static final String REGEX_SPLIT_DELIMITER = "\\]\\[";
    public static final String DEFAULT_DELIMETERS = ",\n";
    public static final String DELIMITER_IDENTIFIER = "//";
    public static final String DELIMITER_REGEX = "[%s]";
    public static final String ERROR_MSG_NEGATIVE = "Negatives not allowed: %s";

    public int add(String numbers) {
        if (!numbers.isEmpty()) {
            StringBuilder delimiterSb = new StringBuilder();
            StringBuilder negativeBuilder = new StringBuilder();
            Matcher matcher = Pattern.compile(REGEX_MATCH_DELIMITER_AND_NUMBER).matcher(numbers);
            if (matcher.find()) {
                String delimitersGroup = matcher.group(1);
                String numberVal = matcher.group(2);
                for (String delimiter : delimitersGroup.split(REGEX_SPLIT_DELIMITER)) {
                    delimiterSb.append(delimiter);
                }
                return performSum(numberVal, delimiterSb, negativeBuilder);
            } else {
                delimiterSb.append(DEFAULT_DELIMETERS);
                if (numbers.startsWith(DELIMITER_IDENTIFIER)) {
                    delimiterSb.append(numbers.charAt(2));
                    numbers = numbers.substring(3);
                }
                return performSum(numbers, delimiterSb, negativeBuilder);
            }
        }
        return 0;
    }

    private static int performSum(String numbers, StringBuilder delimiter, StringBuilder negativeBuilder) {
        int sum = Arrays.stream(numbers.split(String.format(DELIMITER_REGEX, delimiter)))
                .filter(s -> !s.isEmpty())
                .peek(s -> {
                    if (Integer.parseInt(s) < 0) {
                        if (!negativeBuilder.isEmpty()) {
                            negativeBuilder.append(",");
                        }
                        negativeBuilder.append(s);
                    }
                })
                .filter(s -> Integer.parseInt(s) < 1000)
                .mapToInt(Integer::parseInt).sum();
        if (!negativeBuilder.isEmpty()) {
            throw new IllegalArgumentException(String.format(ERROR_MSG_NEGATIVE, negativeBuilder));
        }
        return sum;
    }

}
