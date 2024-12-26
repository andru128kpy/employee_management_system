package com.example.springemployees.components;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


@Component
public class EmployeeValidationService {
    public void validateSearchParams(Map<String, String> params) {
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            if (!List.of("name", "email", "status", "department").contains(key)) {
                throw new IllegalArgumentException("Invalid search parameter: " + key);
            }


            if (value == null || value.trim().isEmpty()) {
                throw new IllegalArgumentException("Search parameter '" + key + "' cannot be empty.");
            }

            if ("email".equals(key) && !value.matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
                throw new IllegalArgumentException("Invalid email format in search parameter.");
            }
        }
    }
}
