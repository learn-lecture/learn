package org.demo.jpashop.repository;

import java.util.List;
import org.demo.jpashop.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByName(String name);

}
