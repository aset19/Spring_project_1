package Spring.AsetProject.Service;


import Spring.AsetProject.Entities.*;
import Spring.AsetProject.Repository.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;


@org.springframework.stereotype.Service
@Transactional
public class Service implements ServiceLog, UserDetailsService {

    private final EmployersRepo employersRepo;

    private final DepartmentRepo departmentRepo;

    private final PositionRepo positionRepo;

    private final RanksRepo ranksRepo;

    private final UserRepo userRepo;

    private final RoleRepo roleRepo;



    public Service(EmployersRepo employersRepo, DepartmentRepo departmentRepo,
                   PositionRepo positionRepo, RanksRepo ranksRepo, UserRepo userRepo, RoleRepo roleRepo) {
        this.employersRepo = employersRepo;
        this.departmentRepo = departmentRepo;
        this.positionRepo = positionRepo;
        this.ranksRepo = ranksRepo;
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }




    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public Employers addEmployer(Employers employers) {
        return employersRepo.save(employers);
    }

    @Override
    public Employers getEmployer(Long id) {
        return employersRepo.getById(id);
    }

    @Override
    public List<Employers> getAllEmployers() {
        return employersRepo.findAll();
    }

    @Override
    public Employers updateEmployer(Employers employers) {
        return employersRepo.save(employers);
    }

    @Override
    public void deleteEmployer(Employers employers) {
        employersRepo.delete(employers);
    }
    @Override
    public List<Employers> searchEmployers(String key){return employersRepo.findByKey(key);}





///////////////////////////////////////////////////////////////////////////////////
    @Override
    public Department addDepartment(Department department) {
        return departmentRepo.save(department);
    }

    @Override
    public Department getDepartment(Long id) {
        return departmentRepo.getById(id);
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepo.findAll();
    }

    @Override
    public Department updateDepartment(Department department) {
        return departmentRepo.save(department);
    }

    @Override
    public void deleteDepartment(Department department) {
        departmentRepo.delete(department);
    }




//////////////////////////////////////////////////////////////////////////////////
    @Override
    public Position addPosition(Position position) {
        return positionRepo.save(position);
    }

    @Override
    public Position getPosition(Long id) {
        return positionRepo.getById(id);
    }

    @Override
    public List<Position> getAllPosition() {
        return positionRepo.findAll();
    }

    @Override
    public Position updatePosition(Position position) {
        return positionRepo.save(position);
    }

    @Override
    public void deletePosition(Position position) {
        positionRepo.delete(position);
    }



///////////////////////////////////////////////////////////////////////////////////
    @Override
    public Ranks addRanks(Ranks ranks) {
        return ranksRepo.save(ranks);
    }

    @Override
    public Ranks getRanks(Long id) {
        return ranksRepo.getById(id);
    }

    @Override
    public List<Ranks> getAllRanks() {
        return ranksRepo.findAll();
    }

    @Override
    public Ranks updateRanks(Ranks ranks){
        return ranksRepo.save(ranks);
    }

    @Override
    public void deleteRanks(Ranks ranks) {
        ranksRepo.delete(ranks);
    }



 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //в этом методе мы попытаемся достать юзера по username из нашей базы
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUserName(username); //попытаемся достать юзера по пришедшему username (есть ли такой в базе)
        if (user == null){
            throw new UsernameNotFoundException(String.format("don't have such user"));
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(),
                Collections.emptyList());
    }

    public List<Role> roleList(){
        return roleRepo.findAll();
    }

    public User findByUserName(String username){
        return userRepo.findByUserName(username);
    }


    @Transactional
    public void register(User user){
        userRepo.save(user);
    }

}
