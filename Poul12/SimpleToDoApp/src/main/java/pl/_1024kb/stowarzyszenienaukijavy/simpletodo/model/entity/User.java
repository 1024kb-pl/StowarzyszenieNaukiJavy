package pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class User
{
    private Long userId;
    private String username;
    private String password;
    private String repeatedPassword;
    private String email;

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
