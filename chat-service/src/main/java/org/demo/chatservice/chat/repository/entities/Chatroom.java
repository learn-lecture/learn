package org.demo.chatservice.chat.repository.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.demo.chatservice.oauth.repository.entities.Member;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Chatroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chatroom_id")
    private Long id;

    private String title;

    @OneToMany(mappedBy = "chatroom")
    private Set<MemberChatroomMapping> memberChatroomMappings;

    @Transient
    @Setter
    private Boolean hasNewMessage;

    private LocalDateTime createdAt;

    public MemberChatroomMapping addMember(Member member) {
        if (memberChatroomMappings == null) {
            memberChatroomMappings = new HashSet<>();
        }

        MemberChatroomMapping memberChatroomMapping = MemberChatroomMapping.builder()
                .member(member)
                .chatroom(this)
                .lastCheckedAt(LocalDateTime.now())
                .build();

        memberChatroomMappings.add(memberChatroomMapping);
        return memberChatroomMapping;
    }
}
