package ru.gik.task4;

import jakarta.persistence.*;
import jakarta.persistence.criteria.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.util.Date;

@Entity
@Table(name = "logins")
@AllArgsConstructor
@NoArgsConstructor
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Getter
    @Setter
    @Column(name = "access_date")
    Date access_date;

    @Getter
    @Setter
    @Column(name = "application")
    String application;

    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "user_id")
    @Getter
    @Setter
    private User user;


    public Login (String application)
    {
        this.application = application;
        this.access_date = new Date();
        //this.user.id=user_id;
    }
    public Login (String application, Date access_date)
    {
        this.application = application;
        this.access_date = access_date;
        //this.user.id=user_id;
    }

}
