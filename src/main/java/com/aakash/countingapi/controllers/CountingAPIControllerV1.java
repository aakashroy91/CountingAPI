package com.aakash.countingapi.controllers;

import com.aakash.countingapi.exceptions.InvalidInputException;
import com.aakash.countingapi.model.WordListDTO;
import com.aakash.countingapi.service.CountingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Generated;
import java.util.List;

@RestController
@RequestMapping("api/v1/counting")
public class CountingAPIControllerV1 {

    private static final Logger logger = LoggerFactory.getLogger(CountingAPIControllerV1.class);

    private final CountingService countingService;

    public CountingAPIControllerV1(CountingService countingService) {
        this.countingService = countingService;
    }

    @Operation(
            summary = "To get count of words based on starting character",
            description = "Returns the count of words that have starting character equal to passed character"
    )
    @Parameter(name = "character", description = "Character to match", required = true)
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "List of String as JSON",
            required = true
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully returned word count"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/matched-count/{character}")
    public ResponseEntity<Integer> countWordsStartingWithChar(@PathVariable char character, @RequestBody @Valid WordListDTO wordData) {
       logger.info("Received request to count words starting with character: {}", character);
        if (!Character.isAlphabetic(character)) {
            throw new InvalidInputException("Please enter a valid alphabet character in API URI to compare");
        }
        return ResponseEntity.ok(countingService.countWordsStartingWithChar(wordData.getWordData(), character));
    }

    @Operation(
            summary = "To get Words based on passed length",
            description = "Returns the list of words that have length greater or equal to passed length"
    )
    @Parameter(name = "length", description = "length of string to match", required = true)
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "List of String as JSON",
            required = true
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully returned word list"),
            @ApiResponse(responseCode = "204", description = "No matching words found"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/matched-words/{length}")
    public ResponseEntity<List<String>> getWordsBasedOnLength(@PathVariable int length, @RequestBody @Valid WordListDTO wordData) {
        logger.info("Received request to get words having length greater or equal to : {}", length);
        List<String> wordsBasedOnLength = countingService.getWordsBasedOnLength(wordData.getWordData(), length);
        if(wordsBasedOnLength.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(wordsBasedOnLength);
    }

    @Operation(
            summary = "To check if API service is running",
            description = "Returns a simple message to verify the API is working."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully Up"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })

    @GetMapping("/test")
    public ResponseEntity<String> testHello() {
        return new ResponseEntity<>("Api working and Up", HttpStatus.OK);
    }
}
