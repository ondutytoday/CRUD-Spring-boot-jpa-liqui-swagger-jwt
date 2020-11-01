package org.vasileva.crud.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "username", length = 100, nullable = false, unique = true)
    private String username;

    @Column(name = "password", length = 255, nullable = false, unique = true)
    private String password;

    @Column(name = "email", length = 255, nullable = false, unique = true)
    private String email;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", foreignKey = @ForeignKey(name = "FK_ROLE"))
    private Roles role;

    public Users(String username, String password, String email, Roles role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }
}
