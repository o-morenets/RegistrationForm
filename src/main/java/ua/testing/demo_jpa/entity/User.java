package ua.testing.demo_jpa.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(name = "APP_USER_UK", columnNames = "email")})
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "role_name")
    private Collection<Role> authorities;

    @Column(length = 128, nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String username;

    @Transient
    private boolean accountNonExpired;

    @Transient
    private boolean accountNonLocked;

    @Transient
    private boolean credentialsNonExpired;

    @Column(length = 1, nullable = false)
    private boolean enabled;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;
}
