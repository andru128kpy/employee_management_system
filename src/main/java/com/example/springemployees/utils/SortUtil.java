package com.example.springemployees.utils;

import org.springframework.data.domain.Sort;

public class SortUtil {

    private SortUtil() {
    }

    public static Sort.Direction parseSortDirection(String sortDir) {
        if (sortDir == null || sortDir.equalsIgnoreCase("ASC")) {
            return Sort.Direction.ASC;
        } else if (sortDir.equalsIgnoreCase("DESC")) {
            return Sort.Direction.DESC;
        } else {
            throw new IllegalArgumentException("Invalid sort direction: " + sortDir);
        }
    }

    public static Sort buildSort(String sortBy, String sortDir) {
        Sort.Direction direction = parseSortDirection(sortDir);
        return Sort.by(direction, sortBy == null ? "id" : sortBy);
    }
}
