package Spring.AsetProject.Entities;


import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "position")
@Data //создает геттеры сеттеры и тд
@AllArgsConstructor  //создает конструкторы с аргументами
@NoArgsConstructor  // создает конструкторы без аргументов
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false, length = 3)
    private Long id;


    @Column(name= "position_name" , length = 30)
    private String positionName;

    }



