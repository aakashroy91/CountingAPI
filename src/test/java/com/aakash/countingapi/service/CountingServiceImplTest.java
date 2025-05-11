package com.aakash.countingapi.service;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CountingServiceImplTest {

    private final CountingServiceImpl countingService=new CountingServiceImpl();

    @Test
    void countWordsStartingWithChar_ShouldReturnCorrectCount() {
        List<String> input = List.of("Apple apricot", "Banana Avocado", "Almond berry");
        int result = countingService.countWordsStartingWithChar(input, 'a');
        // Apple, apricot, Avocado, Almond → 4 words starting with 'a' or 'A'
        assertEquals(4, result);
    }

    @Test
    void countWordsStartingWithChar_ShouldBeCaseInsensitive() {
        List<String> input = List.of("apple", "Apple", "APPLE");
        int result = countingService.countWordsStartingWithChar(input, 'A');
        assertEquals(3, result);
    }

    @Test
    void countWordsStartingWithChar_ShouldReturnZeroIfNoMatch() {
        List<String> input = List.of("banana", "cherry", "date");
        int result = countingService.countWordsStartingWithChar(input, 'x');
        assertEquals(0, result);
    }

    @Test
    void countWordsStartingWithChar_ShouldHandleEmptyList() {
        List<String> input = List.of();
        int result = countingService.countWordsStartingWithChar(input, 'a');
        assertEquals(0, result);
    }

    @Test
    void countWordsStartingWithChar_ShouldHandleEmptyStrings() {
        List<String> input = List.of("", " ", "  ");
        int result = countingService.countWordsStartingWithChar(input, 'a');
        assertEquals(0, result);
    }


    /// //////////////////////////
    @Test
    void testGetWordsBasedOnLength_NormalCase() {
        List<String> input = List.of("hello world", "this is a test", "Java code");
        int length = 5;

        List<String> result = countingService.getWordsBasedOnLength(input, length);

        List<String> expected = List.of("hello", "world");

        assertEquals(expected, result);
    }

    @Test
    void testGetWordsBasedOnLength_EmptyList() {
        List<String> input = Collections.emptyList();
        int length = 3;

        List<String> result = countingService.getWordsBasedOnLength(input, length);

        assertTrue(result.isEmpty());
    }

    @Test
    void testGetWordsBasedOnLength_ListWithEmptyStrings() {
        List<String> input = List.of("", "   ");
        int length = 1;

        List<String> result = countingService.getWordsBasedOnLength(input, length);

        assertTrue(result.isEmpty());
    }

    @Test
    void testGetWordsBasedOnLength_AllWordsTooShort() {
        List<String> input = List.of("a bb cc", "dd ee");
        int length = 3;

        List<String> result = countingService.getWordsBasedOnLength(input, length);

        assertTrue(result.isEmpty());
    }

    @Test
    void testGetWordsBasedOnLength_LengthZero() {
        List<String> input = List.of("a bb ccc");
        int length = 0;

        List<String> result = countingService.getWordsBasedOnLength(input, length);

        List<String> expected = List.of("a", "bb", "ccc");
        assertEquals(expected, result);
    }

    @Test
    void testGetWordsBasedOnLength_NullInput() {
        assertThrows(NullPointerException.class, () ->
                countingService.getWordsBasedOnLength(null, 3)
        );
    }
}