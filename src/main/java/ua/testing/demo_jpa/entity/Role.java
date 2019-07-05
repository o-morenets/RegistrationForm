package ua.testing.demo_jpa.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "role",
        uniqueConstraints = {@UniqueConstraint(name = "APP_ROLE_UK", columnNames = "role_name")})
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id", nullable = false)
    private Long roleId;

    @Column(name = "role_name", length = 30, nullable = false)
    private String roleName;

    @Override
    public String getAuthority() {
        return roleName;
    }

    public static Role of(String roleName) {
        return Role.builder()
                .roleName(roleName)
                .build();
    }
}