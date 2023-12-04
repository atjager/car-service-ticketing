package com.aj.carserviceticketing.domain.users;

import com.aj.carserviceticketing.domain.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table
public class AppUser extends BaseEntity {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 32)
    @NotNull(message = "Role must be specified.")
    private Role role;

    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @Column(name = "username", nullable = false, unique = true, length = 32)
    private String username;

    @Column(name = "password")
    private String password;

    @Email
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "verified")
    private Boolean verified;

//    @OneToMany(cascade = CascadeType.ALL)
//    private List<Ticket> tickets;

}
