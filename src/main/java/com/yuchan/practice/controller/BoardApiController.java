package com.yuchan.practice.controller;

import com.yuchan.practice.dto.BoardResponseDto;
import com.yuchan.practice.dto.BoardSaveRequestDto;
import com.yuchan.practice.dto.BoardUpdateRequestDto;
import com.yuchan.practice.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class BoardApiController {
    private final BoardService boardService;

    // 등록 API
    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody BoardSaveRequestDto requestDto){
        return boardService.save(requestDto);
    }

    // 수정 API
    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody BoardUpdateRequestDto requestDto){
        return boardService.update(id, requestDto);
    }

    // 조회 API
    @GetMapping("/api/v1/posts/{id}")
    public BoardResponseDto findById(@PathVariable Long id){
        return boardService.findById(id);
    }
}
