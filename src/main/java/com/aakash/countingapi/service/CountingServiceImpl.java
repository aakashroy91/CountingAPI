package com.aakash.countingapi.service;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@Component
public class CountingServiceImpl implements CountingService{
    @Override
    public int countWordsStartingWithChar(List<String> list,char character) {
        Predicate<String> charCheck = x->x.toLowerCase().startsWith(String.valueOf(character).toLowerCase());
        List<String> count = list.stream().flatMap(x -> Arrays.stream(x.split(" "))).filter(charCheck).toList();
        return count.size();
    }

    @Override
    public List<String> getWordsBasedOnLength(List<String> wordsList,int length) {
        Predicate<String> lengthCheck = x->x.length()>=length;
        return  wordsList.stream().flatMap(x -> Arrays.stream(x.split(" "))).filter(lengthCheck).toList();
    }
}
