package Spring.AsetProject.Entities;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "ranks")
@Data //создает геттеры сеттеры и тд
@AllArgsConstructor  //создает конструкторы с аргументами
@NoArgsConstructor  // создает конструкторы без аргументов
public class Ranks {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", length = 3)
    private Long id;


    @Column(name= "ranks_name" , length = 30)
    private String ranksName;


}
