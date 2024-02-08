package org.esg.assesment.calculator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: NUWAN
 * Date: 2024-01-30
 * Description:
 * calculator test class
 */
public class StringCalculatorTest {

    static StringCalculator calculator;

    @BeforeAll
    public static void init() {
        calculator = new StringCalculator();
    }

    @Test
    public void whenGivenEmptyValue_ThenAdding_ReturnZeroAsOutput() {
        assertEquals(0, calculator.add(""),
                "For the given empty string, expected result is zero, but the actual result was incorrect.");
    }

    @Test
    public void whenGivenSingleNumber_ThenAdding_ReturnSameNumberAsOutput() {
        assertEquals(1, calculator.add("1"),
                "For the given single number, expected result is same number, but the actual result was incorrect.");
    }

    @Test
    public void whenGivenTwoNumbers_ThenAdding_ReturnSumOfTwoNumbersAsOutput() {
        assertEquals(3, calculator.add("1,2"),
                "For the given two numbers, expected result is sum of the given numbers, but the actual result was incorrect.");
    }

    @Test
    public void whenGivenUnknownNumbers_ThenAdding_ReturnSumOfUnknownNumbersAsOutput() {
        assertEquals(10, calculator.add("1,2,3,4"),
                "For the given multiple numbers, expected result is sum of the given numbers, but the actual result was incorrect.");
    }

    @Test
    public void whenGivenNewLineAndCommaBetweenNumbers_ThenAdding_ReturnSumOfAllNumbersAsOutput() {
        assertEquals(6, calculator.add("1\n2,3"),
                "For the given numbers with comma and new line, expected result is sum of the given numbers, but the actual result was incorrect.");
    }

    @Test
    public void whenGivenNewLineAndCommaWihSingleNumber_ThenAdding_ReturnSumOfNumbersAsOutput() {
        assertEquals(1, calculator.add("1,\n"),
                "For the given number with comma and new line, expected result is sum of the given number, but the actual result was incorrect.");
    }

    @Test
    public void whenGivenNewLineAndCommaAndSemiColonWihMultipleNumber_ThenAdding_ReturnSumOfNumbersAsOutput() {
        assertEquals(3, calculator.add("//;\n1;2"),
                "For the given number with comma, semicolon and new line, expected result is sum of the given number, but the actual result was incorrect.");
    }

    @Test
    public void whenGivenNewLineAndCommaAndSemiColonWihNoNumber_ThenAdding_ReturnSumOfNumbersAsOutput() {
        assertEquals(0, calculator.add("//;\n;"),
                "For the given number with comma, semicolon and new line, expected result is sum of the given number, but the actual result was incorrect.");
    }

    @Test
    public void whenGivenNegativeNumber_ThenAdding_ReturnException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> calculator.add("-1,2"));
        assertEquals("Negatives not allowed: -1", exception.getMessage());
    }

    @Test
    public void whenGivenMultipleNegativeNumber_ThenAdding_ReturnException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> calculator.add("2,-4,3,-5"));
        assertEquals("Negatives not allowed: -4,-5", exception.getMessage());
    }

    @Test
    public void whenGivenNumberMoreThan1000_ThenAdding_ReturnSumOfNumbersAfterIgnoreMoreThen1000AsOutput() {
        assertEquals(2, calculator.add("1001,2"),
                "For the given number more then 1000, expected result is sum of the given number, but the actual result was incorrect.");
    }

    @Test
    public void whenGivenNumbersAndAnyLengthOfDelimiter_ThenAdding_ReturnSumOfNumbersAsOutput() {
        assertEquals(6, calculator.add("//[|||]\n1|||2|||3"),
                "For the given numbers, expected result is sum of the given number, but the actual result was incorrect.");
    }

    @Test
    public void whenGivenNumbersAndAnyDelimiterAndAnyNumber_ThenAdding_ReturnSumOfNumbersAsOutput() {
        assertEquals(6, calculator.add("//[|][%]\n1|2%3"),
                "For the given numbers, expected result is sum of the given number, but the actual result was incorrect.");
    }

    @Test
    public void whenGivenNumbersAndAnyLengthOfDelimiterAndAnyNumber_ThenAdding_ReturnSumOfNumbersAsOutput() {
        assertEquals(6, calculator.add("//[||][%%%]\n1||2%%%3"),
                "For the given numbers, expected result is sum of the given number, but the actual result was incorrect.");
    }

    @Test
    public void whenGivenNumbersAndAnyLengthOfAnyDelimiterAndAnyNumber_ThenAdding_ReturnSumOfNumbersAsOutput() {
        assertEquals(10, calculator.add("//[||][%%%][@@@@]\n1||2%%%3@@@@4"),
                "For the given numbers, expected result is sum of the given number, but the actual result was incorrect.");
    }

}
