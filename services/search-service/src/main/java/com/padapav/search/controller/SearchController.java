package com.padapav.search.controller;
import com.padapav.search.model.SearchResponse;
import com.padapav.search.service.SearchService;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/v1/search")
public class SearchController {
    private final SearchService service;
    public SearchController(SearchService service) { this.service = service; }
    @GetMapping
    public SearchResponse search(@RequestParam(defaultValue = "") String q) { return service.search(q); }
}
