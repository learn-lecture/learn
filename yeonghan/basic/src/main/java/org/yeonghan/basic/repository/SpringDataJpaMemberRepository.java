package org.yeonghan.basic.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.yeonghan.basic.domain.Member;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    @Override
    Optional<Member> findByName(String name);

}
