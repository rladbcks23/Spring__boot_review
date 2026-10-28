package com.yuchan.practice.service;

import com.yuchan.practice.domain.entity.Board;
import com.yuchan.practice.domain.repository.BoardRepository;
import com.yuchan.practice.dto.BoardResponseDto;
import com.yuchan.practice.dto.BoardSaveRequestDto;
import com.yuchan.practice.dto.BoardUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
// 핵심 비즈니스 로직과 트랜잭션을 관리합니다.
public class BoardService {
    private final BoardRepository boardRepository;

    // 1. 글 등록
    @Transactional
    public Long save(BoardSaveRequestDto requestDto){
        return boardRepository.save(requestDto.toEntity()).getId();
    }

    // 2. 글 수정
    public Long update(Long id, BoardUpdateRequestDto requestDto){
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
        // Dirty Checking : 트랜잭션 안에서 값만 변경하여 자동으로 update 쿼리가 실행됨
        board.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    // 3. 글 조회
    public BoardResponseDto findById(Long id){
        Board entity = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니더. id = " + id));
        return new BoardResponseDto(entity);
    }
}
