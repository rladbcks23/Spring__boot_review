package com.yuchan.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.yuchan.practice.domain.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{
    // DB에 접근하여 데이터를 저장하고 조회하는 역할을 합니다. MyBatis의 DAO와 비슷합니다.
}
