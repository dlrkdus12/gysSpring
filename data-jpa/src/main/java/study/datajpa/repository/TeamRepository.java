package study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.datajpa.entity.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {    //<타입, 매핑된 pk>
        }

        //jpa가 알아서 구현체를 만듬.JpaRepository에 다 있음. save, find....등