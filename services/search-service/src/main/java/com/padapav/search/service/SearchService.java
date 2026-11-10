package com.padapav.search.service;
import com.padapav.search.model.*;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Locale;

@Service
public class SearchService {
    private static final List<SearchResult> ITEMS = List.of(
        new SearchResult(1L, "Vada Pav", "Mumbai Street Food", "Potato fritter in a pav bun", 2500L, true),
        new SearchResult(2L, "Pav Bhaji", "Mumbai Street Food", "Buttery pav with spiced vegetable bhaji", 12000L, true),
        new SearchResult(3L, "Paneer Sandwich", "Sandwiches", "Grilled paneer and vegetable sandwich", 9000L, true),
        new SearchResult(4L, "Chicken Momos", "Momos", "Steamed chicken dumplings", 11000L, false),
        new SearchResult(5L, "Mumbai Chaap", "Chaap", "Tandoori soya chaap", 14000L, true)
    );
    public SearchResponse search(String query) {
        String term = query == null ? "" : query.trim().toLowerCase(Locale.ROOT);
        List<SearchResult> results = ITEMS.stream().filter(x -> term.isBlank()
            || x.name().toLowerCase(Locale.ROOT).contains(term)
            || x.category().toLowerCase(Locale.ROOT).contains(term)
            || x.description().toLowerCase(Locale.ROOT).contains(term)).toList();
        return new SearchResponse(query == null ? "" : query, "in-memory-demo", results);
    }
}
