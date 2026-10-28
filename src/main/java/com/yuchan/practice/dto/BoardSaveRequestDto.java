package com.yuchan.practice.dto;

import com.yuchan.practice.domain.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
// 등록 요청 DTO
public class BoardSaveRequestDto {
    // "Entity를 컨트롤러에서 바로 쓰면 안 되나요?"
    // 네, 안 됩니다. Entity는 DB와 밀접한 핵심 클래스이므로,
    // 화면이나 API 요청 스펙에 맞춰 자주 변경되는 뷰 로직과 분리해야 합니다.
    private String title;
    private String content;
    private String author;

    public Board toEntity(){
        return Board.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
