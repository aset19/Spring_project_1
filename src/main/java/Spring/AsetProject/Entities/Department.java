package Spring.AsetProject.Entities;


import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "department")
@Data //создает геттеры сеттеры и тд
@AllArgsConstructor //создает конструкторы с аргументами
@NoArgsConstructor // создает конструкторы без аргументов
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", length = 3)
    private Long id;


    @Column(name= "department_name" , length = 30)
    private String departmentName;

}
