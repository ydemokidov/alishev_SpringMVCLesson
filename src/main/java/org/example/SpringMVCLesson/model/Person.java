package org.example.SpringMVCLesson.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
public class Person {
    int id;
    @NotEmpty(message = "Name should not be empty!")
    @Size(min=2,max = 30, message="Name should be between 2 and 30 characters")
    String name;
    @NotEmpty
    @Min(value = 1,message = "Age should be greated than 0")
    int age;
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    String email;

    public Person() {
    }
}
