package com.ltw.bt06.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "videos")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String url;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
