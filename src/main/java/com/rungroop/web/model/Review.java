package com.rungroop.web.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String content;
    private int stars;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "pokemon_id")
//    private Pokemon pokemon;

}
