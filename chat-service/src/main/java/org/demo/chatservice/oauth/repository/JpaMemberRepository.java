package org.demo.chatservice.oauth.repository;

import java.util.Optional;
import org.demo.chatservice.oauth.repository.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

}
