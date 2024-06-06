package com.Dasanayaka.Backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Contactinformation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ContactId;


    private String email;
    private String mobile;
    private String X;
    private String faceBook;

}
