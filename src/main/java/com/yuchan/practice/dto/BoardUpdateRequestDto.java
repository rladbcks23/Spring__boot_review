package com.yuchan.practice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
// 수정 요청 DTO
public class BoardUpdateRequestDto {
    private String title;
    private String content;
}
