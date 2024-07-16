package org.delivery.db.user;

import java.time.LocalDateTime;

import org.delivery.db.BaseEntity;
import org.delivery.db.user.vo.UserStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "user")
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User extends BaseEntity {

	@Column(length = 50, nullable = false)
	private String name;

	@Column(length = 100, nullable = false)
	private String email;

	@Column(length = 100, nullable = false)
	private String password;

	@Column(length = 50, nullable = false)
	@Enumerated(EnumType.STRING)
	@Setter
	private UserStatus status;

	@Column(length = 150, nullable = false)
	private String address;

	@Setter
	private LocalDateTime registeredAt;

	@Setter
	private LocalDateTime unregisteredAt;

	@Setter
	private LocalDateTime lastLoginAt;

}
