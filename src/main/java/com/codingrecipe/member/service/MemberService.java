package com.codingrecipe.member.service;

import com.codingrecipe.member.dto.MemberDTO;
import com.codingrecipe.member.entity.MemberEntity;
import com.codingrecipe.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public void save(MemberDTO memberDTO) {
        // 1. dto -> entity 로 변환
        // 2. repository 의 save 메서드 호출
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        memberRepository.save(memberEntity);
        // repository save메서드 호출 (조건. entity 객체를 넘겨줘야 함)
    }

    public MemberDTO login(MemberDTO memberDTO) {
        // 1. 회원이 입력한 이메일로 DB 에서 조회
        // 2. DB 에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 판단
        Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberDTO.getMemberEmail());

        if (byMemberEmail.isPresent()) {
            // 조회 결과가 있다.
            MemberEntity memberEntity = byMemberEmail.get();

            if (memberEntity.getMemberPassword().equals(memberDTO.getMemberPassword())) {
                // 비밀번호 일치하는 경우
                // entity -> DTO 변환 후 리턴
                MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);

                return dto;
            } else {
                // 비밀번호 불일치하는 경우

                return null;
            }
        } else {
            // 조회 결과가 없다.

            return null;
        }
    }

    public List<MemberDTO> findAll() {
        List<MemberEntity> memberEntityList = memberRepository.findAll();
        List<MemberDTO> memberDTOList = new ArrayList<>();

        for (MemberEntity memberEntity : memberEntityList) {
            memberDTOList.add(MemberDTO.toMemberDTO(memberEntity));
        }

//        List<MemberDTO> memberDTOList = memberEntityList.stream()
//                .map(memberEntity -> {
//                    MemberDTO memberDTO = new MemberDTO();
//                    memberDTO.setId(memberEntity.getId());
//                    memberDTO.setMemberEmail(memberEntity.getMemberEmail());
//                    memberDTO.setMemberPassword(memberEntity.getMemberPassword());
//                    memberDTO.setMemberName(memberEntity.getMemberName());
//                    return memberDTO;
//                }).collect(Collectors.toList());

        return memberDTOList;
    }

    public MemberDTO findById(Long id) {
        Optional<MemberEntity> findMember = memberRepository.findById(id);

        if(findMember.isPresent()){
            MemberDTO memberDTO = MemberDTO.toMemberDTO(findMember.get());

            return memberDTO;
        } else {
            return null;
        }
    }

    public MemberDTO updateForm(String myEmail) {
        Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(myEmail);

        if (byMemberEmail.isPresent()) {
            return MemberDTO.toMemberDTO(byMemberEmail.get());
        } else {
            return null;
        }
    }

    public void update(MemberDTO memberDTO) {
        // JpaRepository 의 save() 메서드는 id 가 없으면 save, id가 있으면 update 가 된다.
        memberRepository.save(MemberEntity.toUpdateMemberEntity(memberDTO));
    }

    public void deleteById(Long id){
        memberRepository.deleteById(id);
    }
}
