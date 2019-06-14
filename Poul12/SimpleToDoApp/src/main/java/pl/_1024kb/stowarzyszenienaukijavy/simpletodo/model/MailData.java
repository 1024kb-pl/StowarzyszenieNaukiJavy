package pl._1024kb.stowarzyszenienaukijavy.simpletodo.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@Table(name = "mail_data")
public class MailData implements Serializable
{
    private static final long serialVersionUID = 3L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mail_id", nullable = false)
    private Long id;
    @Column(nullable = false, length = 25)
    private String addressName;
    @Column(nullable = false, length = 128)
    private String password;

    @Builder
    public MailData(Long id, String addressName, String password)
    {
        this.id = id;
        this.addressName = addressName;
        this.password = password;
    }
}
