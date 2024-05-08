package org.example.services.user;

public record ProfileDTO(Boolean publicEmail,
                         String title,
                         String bio) {
}
