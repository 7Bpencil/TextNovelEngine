package com.urfu.textnovelengine;

import com.urfu.chadnovelengine.BackendUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BackendUtilitiesTest {
    private final int questionsAmount = 3;

    @Test
    void isValidAnswerNotANumber() {
        Assertions.assertFalse(BackendUtilities.isValidAnswer("ABVC", questionsAmount));
        Assertions.assertFalse(BackendUtilities.isValidAnswer("12efqef", questionsAmount));
        Assertions.assertFalse(BackendUtilities.isValidAnswer("9iq", questionsAmount));
        Assertions.assertFalse(BackendUtilities.isValidAnswer("    ", questionsAmount));
    }

    @Test
    void isValidAnswerEmptyString() {
        Assertions.assertFalse(BackendUtilities.isValidAnswer("", questionsAmount));
    }

    @Test
    void isValidAnswerNull() {
        Assertions.assertFalse(BackendUtilities.isValidAnswer(null, questionsAmount));
    }

    @Test
    void isValidAnswerLessThanOne() {
        Assertions.assertFalse(BackendUtilities.isValidAnswer("-100", questionsAmount));
        Assertions.assertFalse(BackendUtilities.isValidAnswer("-1", questionsAmount));
        Assertions.assertFalse(BackendUtilities.isValidAnswer("0", questionsAmount));
    }

    @Test
    void isValidAnswerMoreThanQuestionsAmount() {
        Assertions.assertFalse(BackendUtilities.isValidAnswer(String.valueOf(questionsAmount + 1), questionsAmount));
        Assertions.assertFalse(BackendUtilities.isValidAnswer(String.valueOf(questionsAmount + 100), questionsAmount));
    }

    @Test
    void isValidAnswerCorrect() {
        for (var i = 1; i <= questionsAmount; ++i) {
            Assertions.assertTrue(BackendUtilities.isValidAnswer(String.valueOf(i), questionsAmount));
        }
    }

    @Test
    void isValidAnswerNotAnInt() {
        Assertions.assertFalse(BackendUtilities.isValidAnswer(String.valueOf(0.9999f), questionsAmount));
        for (var i = 1; i <= questionsAmount; ++i) {
            Assertions.assertFalse(BackendUtilities.isValidAnswer(String.valueOf(i + 0.01f), questionsAmount));
        }
    }

    @Test
    void isIntegerCorrect() {
        Assertions.assertTrue(BackendUtilities.isInteger("12"));
        Assertions.assertTrue(BackendUtilities.isInteger("-12"));
        Assertions.assertTrue(BackendUtilities.isInteger("0"));
    }

    @Test
    void isIntegerNotANumber() {
        Assertions.assertFalse(BackendUtilities.isInteger("ABVC"));
        Assertions.assertFalse(BackendUtilities.isInteger("12efqef"));
        Assertions.assertFalse(BackendUtilities.isInteger("9iq"));
        Assertions.assertFalse(BackendUtilities.isInteger("    "));
    }

    @Test
    void isIntegerEmptyString() {
        Assertions.assertFalse(BackendUtilities.isInteger(""));
    }

    @Test
    void isIntegerNull() {
        Assertions.assertFalse(BackendUtilities.isInteger(null));
    }

    @Test
    void isIntegerNotAnInteger() {
        Assertions.assertFalse(BackendUtilities.isInteger("0.5"));
        Assertions.assertFalse(BackendUtilities.isInteger("-0.5"));
        Assertions.assertFalse(BackendUtilities.isInteger("-0.0"));
    }
}
