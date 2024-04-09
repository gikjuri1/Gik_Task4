package ru.gik.task4;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    public User(String username, String fio){
        this.username = username;
        this.fio = fio;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Getter
    @Setter
    @Column(name = "username")
    String username;
    @Getter
    @Setter
    @Column(name = "fio")
    String fio;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @Getter //!!!!
    @Setter //!!!!
    Set<Login> logins;

    @Override
    public String toString()
    {
        return username+ " "+fio;
    }

}
