package study.datajpa.dto;

import lombok.Data;
import study.datajpa.entity.Member;

@Data       //단순 dto는 쓰지만 원래 쓰지 않는다. getter, setter가 들어가 있다.
public class MemberDto {

    private Long id;
    private String username;
    private String teamName;

    public MemberDto(Long id, String username, String teamName) {
        this.id = id;
        this.username = username;
        this.teamName = teamName;
    }

    public MemberDto(Member member) {
        this.id = member.getId();
        this.username = member.getUsername();
    }
}
