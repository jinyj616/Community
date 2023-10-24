package com.example.comunity.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data // @Getter @Setter
@Table(name = "board")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id, title"})
public class Board {

    @Id @GeneratedValue
    private Long id;
    private String title;
    private String content;
    private Long hit;

    public Board(Long id, String title, String content,Long hit) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.hit = hit;
    }
}