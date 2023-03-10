package com.reanima.business.repository.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users_registration")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "user_email", nullable = false, unique = true)
    private String userEmail;

    @Column(name = "user_password", nullable = false, updatable = false)
    private String userPassword;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_surname")
    private String userSurname;

    @Column(name = "user_enabled")
    private boolean enabled = true;

    @CreationTimestamp
    @Column(name = "user_created", nullable = false, updatable = false)
    private LocalDateTime userCreated;

    @UpdateTimestamp
    @Column(name = "user_updated")
    private LocalDateTime userUpdated;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
    joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))

    private Set<RoleEntity> roles = new HashSet<>();

    public void addRole(RoleEntity roleEntity) {
        this.roles.add(roleEntity);
    }

}
