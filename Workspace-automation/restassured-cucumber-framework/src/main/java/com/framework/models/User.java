package com.framework.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * POJO representing a reqres.in "user" payload.
 * Implements the Builder design pattern for readable, immutable-style object creation
 * (e.g. User.builder().name("Ram").job("Tester").build()).
 *
 * Demonstrates: Encapsulation (private fields + getters only), Builder pattern.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    private String id;
    private String name;
    private String job;
    private String createdAt;
    private String updatedAt;

    private User() {
        // instantiate only via Builder
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", job='" + job + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }

    /**
     * Static nested Builder class - classic Builder design pattern.
     */
    public static class Builder {
        private final User user;

        private Builder() {
            user = new User();
        }

        public Builder id(String id) {
            user.id = id;
            return this;
        }

        public Builder name(String name) {
            user.name = name;
            return this;
        }

        public Builder job(String job) {
            user.job = job;
            return this;
        }

        public Builder createdAt(String createdAt) {
            user.createdAt = createdAt;
            return this;
        }

        public Builder updatedAt(String updatedAt) {
            user.updatedAt = updatedAt;
            return this;
        }

        public User build() {
            return user;
        }
    }
}
