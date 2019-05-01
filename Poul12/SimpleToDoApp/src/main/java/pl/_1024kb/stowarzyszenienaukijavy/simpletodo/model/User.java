package pl._1024kb.stowarzyszenienaukijavy.simpletodo.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
@NamedQueries(
        {
            @NamedQuery(name = "User.getUserByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
            @NamedQuery(name = "User.getAll", query = "SELECT u FROM User u")
        }
)
public class User implements Serializable
{
    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false, unique = true)
    private Long userId;
    @Column(nullable = false, length = 35, unique = true)
    private String username;
    @Column(nullable = false, length = 128)
    private String password;
    @Transient
    private String repeatedPassword;
    @Column(nullable = false, length = 45, unique = true)
    private String email;
    @OneToMany(mappedBy = "user")
    private List<Task> tasks;

    /*public User() {

    }*/

    @Builder
    public User(Long userId, String username, String password, String repeatedPassword, String email)
    {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.repeatedPassword = repeatedPassword;
        this.email = email;
    }
}
