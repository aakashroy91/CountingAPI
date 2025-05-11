package com.aakash.countingapi.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public class WordListDTO {

    @NotNull(message = "Word Data cannot be null")
    @Size(min = 1, message = "Word Data must contain at least one string")
    private List<@NotBlank(message = "Word Data elements must not be blank")String> wordData;

    public List<String> getWordData() {
        return wordData;
    }

    public void setWordData(List<String> wordData) {
        this.wordData = wordData;
    }
}
