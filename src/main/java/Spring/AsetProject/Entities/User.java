package Spring.AsetProject.Entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity //сущность (обьект) из реального мира по которому нужно собрать данные
@Table(name = "users")
@Data //создает геттеры сеттеры и тд
@AllArgsConstructor  //создает конструкторы с аргументами
@NoArgsConstructor  // создает конструкторы без аргументов
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", length = 3)
    private Long id;


    @Column(name= "user_name" , length = 45)
    private String userName;

    @Column(name= "password" , length = 100)
    private String password;

    @Column(name= "email" , length = 80)
    private String email;

    @ManyToMany
    @JoinTable(name = "users_roles",
    joinColumns = @JoinColumn(name = "users_id"),
    inverseJoinColumns = @JoinColumn(name = "roles_id"))
    private Collection<Role> roles;


}
