package com.example.tech4good_server.global.component.data;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class DataProcessComponent {
    public String convertListToString(List<String> list) {
        return list != null ? String.join(", ", list) : null;
    }

    public List<String> convertStringToList(String str) {
        return str != null ? Arrays.asList(str.split(", ")) : null;
    }

    public String addHashToEachWord(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }

        String[] words = str.split(", ");

        List<String> taggedWords = new ArrayList<>();
        for (String word : words) {
            taggedWords.add("#" + word);
        }

        return String.join(", ", taggedWords);
    }

}
