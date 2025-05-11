package com.aakash.countingapi.controllers;

import com.aakash.countingapi.model.WordListDTO;
import com.aakash.countingapi.service.CountingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CountingAPIControllerV1.class)
class CountingAPIControllerV1Test {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CountingService countingService;

    @Test
    void countWordsStartingWithCharTest() throws Exception {
        List<String> words = List.of("apple", "banana", "apricot", "avocado", "berry");
        WordListDTO requestData = new WordListDTO();
        requestData.setWordData(words);
        char character = 'a';
        int expectedCount = 3;
        when(countingService.countWordsStartingWithChar(words, character)).thenReturn(expectedCount);
        mockMvc.perform(post("/api/v1/counting/matched-count/{character}", character)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestData)))
                .andExpect(status().isOk())
                .andExpect(content().string("3"));
    }

    @Test
    void getWordsBasedOnLengthTest() throws Exception {
        List<String> words = List.of("apple", "banana", "apricot", "avocado", "berry");
        WordListDTO requestData = new WordListDTO();
        requestData.setWordData(words);
        int length = 7;
        List<String> outputWords = List.of("apricot", "avocado");
        when(countingService.getWordsBasedOnLength(words,length)).thenReturn(outputWords);
        mockMvc.perform(post("/api/v1/counting/matched-words/{length}",length)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requestData)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0]", is("apricot")))
                .andExpect(jsonPath("$[1]", is("avocado")));
    }

    @Test
    void getWordsBasedOnLengthTest_returnEmpty() throws Exception {
        List<String> words = List.of("apple", "banana", "apricot", "avocado", "berry");
        WordListDTO requestData = new WordListDTO();
        requestData.setWordData(words);
        int length = 10;
        List<String> outputWords = Collections.emptyList();
        when(countingService.getWordsBasedOnLength(words,length)).thenReturn(outputWords);
        mockMvc.perform(post("/api/v1/counting/matched-words/{length}",length)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestData)))
                .andExpect(status().isNoContent());
    }

    @Test
    void testValidationErrorMessages() throws Exception {
        List<String> words = List.of("", "banana", "apricot", "avocado", "berry");
        WordListDTO requestData = new WordListDTO();
        requestData.setWordData(words);
        char character = 'a';
        int expectedCount = 3;
        when(countingService.countWordsStartingWithChar(words, character)).thenReturn(expectedCount);
        mockMvc.perform(post("/api/v1/counting/matched-count/{character}", character)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestData)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$['error message']").value("Word Data elements must not be blank"));
    }

    @Test
    void testValidInputforCharacter() throws Exception {
        List<String> words = List.of("apple", "banana", "apricot", "avocado", "berry");
        WordListDTO requestData = new WordListDTO();
        requestData.setWordData(words);
        char character = '1';
        int expectedCount = 3;
        when(countingService.countWordsStartingWithChar(words, character)).thenReturn(expectedCount);
        mockMvc.perform(post("/api/v1/counting/matched-count/{character}", character)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestData)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$['error message']").value("Please enter a valid alphabet character in API URI to compare"));
    }

    @Test
    void testHelloEndpoint() throws Exception {
        mockMvc.perform(get("/api/v1/counting/test"))
                .andExpect(status().isOk())
                .andExpect(content().string("Api working and Up"));
    }
}