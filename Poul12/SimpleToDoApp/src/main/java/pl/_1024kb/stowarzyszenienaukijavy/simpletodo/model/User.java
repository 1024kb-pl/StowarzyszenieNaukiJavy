package pl._1024kb.stowarzyszenienaukijavy.simpletodo.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.constraint.Match;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
@Match(firstField = "password", secondField = "repeatedPassword", message = "{pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.User.repeatedPassword.Match}")
public class User implements Serializable
{
    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false, unique = true)
    private Long id;
    @Size(min = 3, message = "{pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.User.username.Size}")
    @Column(nullable = false, length = 35, unique = true)
    private String username;
    @Size(min = 6, message = "{pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.User.password.Size}")
    @Column(nullable = false, length = 128)
    private String password;
    @Size(min = 6, message = "{pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.User.repeatedPassword.Size}")
    @Transient
    private String repeatedPassword;
    @Email(message = "{pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.User.email.Email}")
    @Column(nullable = false, length = 45, unique = true)
    private String email;
    @OneToMany(mappedBy = "user")
    private List<Task> tasks;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<UserRole> roles = new HashSet<>();

    @Builder
    public User(Long id, String username, String password, String repeatedPassword, String email)
    {
        this.id = id;
        this.username = username;
        this.password = password;
        this.repeatedPassword = repeatedPassword;
        this.email = email;
    }
}
