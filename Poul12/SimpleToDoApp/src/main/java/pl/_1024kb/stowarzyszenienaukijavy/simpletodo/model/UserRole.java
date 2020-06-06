package pl._1024kb.stowarzyszenienaukijavy.simpletodo.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@Table(name = "roles")
public class UserRole implements Serializable
{
    private static final long serialVersionUID = 3L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false, unique = true)
    private Long id;
    @Column(nullable = false, length = 15)
    private String role;
    @Column(nullable = false, length = 150)
    private String description;

    @Builder
    public UserRole(Long id, String role, String description)
    {
        this.id = id;
        this.role = role;
        this.description = description;
    }
}
