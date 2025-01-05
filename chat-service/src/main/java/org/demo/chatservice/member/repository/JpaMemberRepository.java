package org.demo.chatservice.member.repository;

import java.util.Optional;
import org.demo.chatservice.member.repository.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    Optional<Member> findByName(String name);

}
