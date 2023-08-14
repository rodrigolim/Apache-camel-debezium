package com.example.consumer.model;

import lombok.*;

import java.io.Serializable;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Person implements Serializable {

    @Id
    private Long id;
    
    private String name;
    private Integer age;

}
