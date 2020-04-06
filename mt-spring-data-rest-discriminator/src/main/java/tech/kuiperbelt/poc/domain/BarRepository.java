package tech.kuiperbelt.poc.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BarRepository extends JpaRepository<Bar, Long> {

    List<Bar> findByFooName(@Param("fooName")String fooName);
}
