package study.querydsl.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "username", "age"})       //team 을 써주면 무한루프에 빠짐
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)      //참조값, FK
    @JoinColumn(name = "team_id")
    private Team team;


    //기본 생성자 3개
    public Member(String username) {        //나중 예제
        this(username, 0);
    }

    public Member(String username, int age) {   //다른 생성자를 호출하여 코드의 중복을 피하고 초기화하여 생성자를 오버로딩을 사용할 수 있다. 코드의 중복을 피하여 코드 재사용 향상. 3개를 2개로
        this(username, age, null);
    }

    public Member(String username, int age, Team team) {    //(안에있는 파라미터)를 받는 생성자를 만든다~....
        this.username = username;
        this.age = age;
        if (team != null) {
            changeTeam(team);
        }
    }

    private void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}





