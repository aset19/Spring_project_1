package Spring.AsetProject.Entities;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity //сущность (обьект) из реального мира по которому нужно собрать данные
@Table(name = "employers")
@Data //создает геттеры сеттеры и тд
@AllArgsConstructor  //создает конструкторы с аргументами
@NoArgsConstructor  // создает конструкторы без аргументов
public class Employers {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", length = 3)
    private Long id;

    @Column(name = "num", length = 5)
    private int personalNumber;

    @Column(name = "surname", length = 15)
    private String surname;

    @Column(name = "name",nullable = false, length = 15)
    private String name;

    @Column(name = "second_name", nullable = false,length = 15)
    private String secondName;

    @Column(name = "photo")
    private String photo;

    @Column(name = "born_date",nullable = false)
    private LocalDate bornDate;

    @Column(name = "contract_start",nullable = false)
    private LocalDate contractStart;

    @Column(name = "contract_time",nullable = false, length = 2)
    private int contractTime;

    @Column(name = "contract_end", nullable = false)
    private LocalDate contractEnd;

    @ManyToOne(fetch = FetchType.EAGER)
    private Position position;

    @ManyToOne(fetch = FetchType.EAGER)
    private Ranks ranks;

    @ManyToOne(fetch = FetchType.EAGER)
    private Department department;

}
