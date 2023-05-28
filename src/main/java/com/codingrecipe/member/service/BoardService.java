package com.codingrecipe.member.service;

import com.codingrecipe.member.dto.BoardDTO;
import com.codingrecipe.member.entity.BoardEntity;
import com.codingrecipe.member.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// service 에서 해줘야 하는 내용
// DTO -> Entity 로 변환 (Entity 클래스에서 생성)
// Repository 로 넘겨줄 때는 Entity 로 넘겨줘야함

// Entity -> DTO 로 변환 (DTO 클래스에서 생성)
// database 에서 데이터를 넘겨받을때는 entity 형태이므로 DTO 로 변환시켜줘야함
// controller 로 넘겨줄 때는 DTO 로 넘겨줘야함

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public void save(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);


        boardRepository.save(boardEntity);
    }

    public List<BoardDTO> findAll() {
        List<BoardEntity> boardEntityList = boardRepository.findAll();

        List<BoardDTO> boardDTOList = new ArrayList<>();

        for (BoardEntity boardEntity : boardEntityList) {
            boardDTOList.add(BoardDTO.toBoardDTO(boardEntity));
        }

        return boardDTOList;
    }

    @Transactional // 스프링부트 영속성 컨텍스트 처리를 위해 사용
    public void updateHits(Long id) {
        boardRepository.updateHits(id);
    }

    public BoardDTO findById(Long id) {
        BoardDTO boardDTO = new BoardDTO();
        Optional<BoardEntity> repositoryById = boardRepository.findById(id);

        if(repositoryById.isPresent()) {
            BoardEntity boardEntity = repositoryById.get();
            boardDTO.setId(boardEntity.getId());

            return boardDTO;
        } else {

            return null;
        }
    }
}
