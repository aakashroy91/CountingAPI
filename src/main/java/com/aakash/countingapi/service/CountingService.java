package com.aakash.countingapi.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CountingService {
    public int countWordsStartingWithChar(List<String> list,char ch);
    public List<String> getWordsBasedOnLength(List<String> list,int length);
}
