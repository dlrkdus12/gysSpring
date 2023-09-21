package study.querydsl.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)      //생성자 써주는것보다 어노테이션을 이용함, @NoArgsConstructor 파라미터가 없는 기본 생성자.
@ToString(of = {"id", "name"})      //@ToString 은 변수 값들을 리턴해주는 메소드를 자동생성해준다.
@Getter @Setter
public class Team {

    @Id @GeneratedValue
    private Long id;
    private String name;

    @OneToMany(mappedBy = "team")               //참조값
    private List<Member> members = new ArrayList<>();

    public Team(String name) {
        this.name = name;
    }
}
