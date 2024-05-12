package Spring.AsetProject.Entities;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "role_name" , length = 45)
    private String roleName;

}
