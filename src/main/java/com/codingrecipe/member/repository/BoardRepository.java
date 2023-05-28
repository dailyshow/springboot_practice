package com.codingrecipe.member.repository;

import com.codingrecipe.member.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    @Modifying // update, delete 같은 쿼리를 실행해야 할 때는 @Modifying 을 붙여줘야 함
    @Query(value = "update BoardEntity b set b.boardHits = b.boardHits + 1 where b.id=:id")
    void updateHits(@Param("id") Long id);
}
