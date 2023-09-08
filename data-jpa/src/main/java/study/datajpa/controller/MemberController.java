package study.datajpa.controller;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;
import study.datajpa.repository.MemberRepository;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/members/{id}")        //도메인 클래스 컨버터 사용 전
    public String findMember(@PathVariable("id") Long id) {
        Member member = memberRepository.findById(id).get();    //spring이 convertor해주고 findMember2에서 바로 injection해줌
        return member.getUsername();
    }

    @GetMapping("/members2/{id}")       //도메인 클래스 컨버터 사용 후
    public String findMember2(@PathVariable("id") Member member) {
        return member.getUsername();
    }

    @GetMapping("/members")
    public Page<MemberDto> list(@PageableDefault(size = 5) Pageable pageable) {
        Page<Member> page = memberRepository.findAll(pageable);        //인라인 가능, 실무에선 인라인으로 씀, 원래 엔티티<Member>쓰면 안됨, 따라서 dto로 바꿔서
        Page<MemberDto> map = page.map(member -> new MemberDto(member)); //메서드 레퍼런스로 줄이기 가능해짐
        return map;
    }

//    @PostConstruct
    public void init() {
        for (int i = 0; i < 100; i++) {
            memberRepository.save(new Member("user" + i, i));
        }
    }
}
