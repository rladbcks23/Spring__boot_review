package com.yuchan.practice.dto;

import com.yuchan.practice.domain.entity.Board;
import lombok.Getter;

@Getter
// 응답 조회 DTO
public class BoardResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;

    public BoardResponseDto(Board entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
