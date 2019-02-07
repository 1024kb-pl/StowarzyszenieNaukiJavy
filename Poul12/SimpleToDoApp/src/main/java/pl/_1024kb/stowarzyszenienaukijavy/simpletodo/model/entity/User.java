package pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.entity;

public class User
{
    private Long id;
    private String username;
    private String password;
    private String repeatedPassword;
    private String email;

    public User(Long id, String username, String password, String email)
    {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }
    
    public User(String username, String password, String email)
    {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String repeatedPassword, String email)
    {
        this.username = username;
        this.password = password;
        this.repeatedPassword = repeatedPassword;
        this.email = email;
    }

    public Long getId()
    {
        return id;
    }
    
    public void setId(Long id)
    {
        this.id = id;
    }
        
    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getRepeatedPassword() {
        return repeatedPassword;
    }

    public void setRepeatedPassword(String repeatedPassword) {
        this.repeatedPassword = repeatedPassword;
    }
}
