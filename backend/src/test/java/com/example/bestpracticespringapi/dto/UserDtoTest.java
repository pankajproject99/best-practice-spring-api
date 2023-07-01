package com.example.bestpracticespringapi.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserDtoTest {

    @Test
    void testEquals() {
        UserDto userDto1 = new UserDto("xyz", "xyzp");
        UserDto userDto2 = new UserDto("xyz", "xyzp");

        assertThat(userDto1).isEqualTo(userDto2);
    }

    @Test
    void testHashCode() {
        UserDto userDto1 = new UserDto("xyz", "xyzp");
        UserDto userDto2 = new UserDto("xyz", "xyzp");

        assertThat(userDto1.hashCode()).isEqualTo(userDto2.hashCode());
    }

    @Test
    void testToString() {
        UserDto userDto1 = new UserDto("xyz", "xyzp");

        assertThat(userDto1.toString()).contains("username=xyz", "password=xyzp");
    }
}