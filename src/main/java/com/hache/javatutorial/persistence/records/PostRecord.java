package com.hache.javatutorial.persistence.records;

public record PostRecord(
        Long postId,
        String title,
        String msgPost
) {}
