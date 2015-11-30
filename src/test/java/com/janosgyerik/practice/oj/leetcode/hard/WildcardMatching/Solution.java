package com.janosgyerik.practice.oj.leetcode.hard.WildcardMatching;

public class Solution {
    public boolean isMatch(String text, String pattern) {
        String[] segments = split(pattern);
        if (segments.length == 0) {
            return true;
        }
        if (segments.length == 1) {
            return startsWith(text, segments[0]) && text.length() == segments[0].length();
        }

        // if first is not "" then must match from start
        int textIndex = 0;
        int segmentsIndex = 0;
        if (!segments[0].isEmpty()) {
            if (!startsWith(text, segments[0])) {
                return false;
            }
            segmentsIndex = 1;
            textIndex = segments[0].length();
        }

        // if first is "" then can find anywhere, return index
        while (textIndex < text.length() && segmentsIndex < segments.length) {
            String segment = segments[segmentsIndex];
            if (segment.isEmpty()) {
                ++segmentsIndex;
                continue;
            }
            // for each next pattern segment, find index
            textIndex = indexOf(text, segment, textIndex) + segment.length();
            if (textIndex < 0) {
                return false;
            }
            ++segmentsIndex;
        }

        if (segmentsIndex >= segments.length - 1 && segments[segments.length - 1].isEmpty()) {
            return true;
        }
        return textIndex == text.length() && segmentsIndex == segments.length;
    }

    public int indexOf(String text, String pattern) {
        return indexOf(text, pattern, 0);
    }

    public int indexOf(String text, String pattern, int start) {
        for (int pos = start; pos <= text.length() - pattern.length(); ++pos) {
            int i = 0;
            for (; i < pattern.length(); ++i) {
                char c = text.charAt(pos + i);
                char p = pattern.charAt(i);
                if (c != p && p != '?') {
                    break;
                }
            }
            if (i == pattern.length()) {
                return pos;
            }
        }
        return -1;
    }

    private boolean startsWith(String text, String pattern) {
        if (text.length() < pattern.length()) {
            return false;
        }
        for (int i = 0; i < pattern.length(); ++i) {
            if (pattern.charAt(i) != text.charAt(i) && pattern.charAt(i) != '?') {
                return false;
            }
        }
        return true;
    }

    private String[] split(String text) {
        return text.split("\\*", -1);
    }
}
