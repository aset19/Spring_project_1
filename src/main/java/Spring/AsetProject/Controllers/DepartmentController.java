package Spring.AsetProject.Controllers;


import Spring.AsetProject.Entities.Department;
import Spring.AsetProject.Service.ServiceLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Коньроллеры обрабатывают запросы и обычно возвращают названия шаблонов.

@Controller
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private ServiceLog serviceLog;


    @GetMapping
    public String getAllDepartment(Model model) {
        List<Department> allDepartments = serviceLog.getAllDepartments();
        model.addAttribute("allDepartments", allDepartments);
        return "department";
    }

   @GetMapping("/add")
    public String add(){
        return "addDepartment";
   }

   @PostMapping("/add")
    public String addDepartment(@RequestParam("department_name") String departmentName){
        if(departmentName != null){
            Department department = new Department();
            department.setDepartmentName(departmentName);
            serviceLog.addDepartment(department);
        }
        return "redirect:/department";
   }

   @GetMapping("/create/{id}")
    public String create(@PathVariable(name="id") Long id, Model model){
        Department department = serviceLog.getDepartment(id);
        model.addAttribute("department", department);
        return "createDepartment";
   }

   @PostMapping("/create")
    public String update(@RequestParam(name = "id") Long id,
                         @RequestParam(name = "department") String departmentName){
         Department department = serviceLog.getDepartment(id);
         if (departmentName != null && department != null && !departmentName.equals("")){
             department.setDepartmentName(departmentName);
             serviceLog.updateDepartment(department);
         }
         return  "redirect:/department";

   }

   @PostMapping("/delete")
    public String delete(@RequestParam(name = "id") Long id){
        Department departments = serviceLog.getDepartment(id);
       if (departments != null){
           serviceLog.deleteDepartment(departments);
       }
       return "redirect:/department";
   }







}
