package com.padapav.search.model;
import java.util.List;
public record SearchResponse(String query, String source, List<SearchResult> results) {}
