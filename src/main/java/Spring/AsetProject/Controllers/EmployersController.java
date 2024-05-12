package Spring.AsetProject.Controllers;


import Spring.AsetProject.Entities.Department;
import Spring.AsetProject.Entities.Employers;
import Spring.AsetProject.Entities.Position;
import Spring.AsetProject.Entities.Ranks;
import Spring.AsetProject.Service.ExcelGenerator;

import Spring.AsetProject.Service.PdfGenerator;
import Spring.AsetProject.Service.ServiceLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;


@Controller
@RequestMapping("/employers")
public class EmployersController {
    @Autowired
    private ServiceLog serviceLog;


    public static String photoPath = System.getProperty("user.dir") + "/src/main/resources/static";

    //получим список всех сотруднков с их данными
    @GetMapping
    public String getAllEmployers(Model model){
        List<Employers> employers = serviceLog.getAllEmployers();
        model.addAttribute("employers", employers);
        return "employers";
    }


    //Поиск сотрудников по реквизитам (отдельный запрос в репозиторий)
    @PostMapping("/search")
    public String search(@RequestParam(name = "key")String key,Model model){

        List<Employers> employers = serviceLog.searchEmployers(key);
        model.addAttribute("employers" ,employers);

        return "employers";
    }


    @GetMapping("/add")
    public String form(Model model){
        List<Department> departments = serviceLog.getAllDepartments();
        model.addAttribute("departments", departments);

        List<Position> positions = serviceLog.getAllPosition();
        model.addAttribute("positions", positions);

        List<Ranks> ranks = serviceLog.getAllRanks();
        model.addAttribute("rank", ranks);

        return "addEmployer";
    }

    @PostMapping("/add")
    public String addEmployer(@RequestParam(name="personal_number",defaultValue = "0") int personalNumber,
                              @RequestParam(name="surname",defaultValue = " ") String surname,
                              @RequestParam(name="name", defaultValue = " ") String name,
                              @RequestParam(name="second_name",defaultValue = " ") String secondName,
                              @RequestParam(name = "born_date")
                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate bornDate,
                              @RequestParam(name = "contract_start")
                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate contractStart,
                              @RequestParam(name="contract_time", defaultValue = "0") int contractTime,
                              @RequestParam(name = "position", defaultValue = " ") Long position_id,
                              @RequestParam(name = "ranks", defaultValue = " ") Long ranks_id,
                              @RequestParam(name = "department", defaultValue = " ") Long department_id,
                              @RequestParam(name = "photo")MultipartFile photo){


        Position position = serviceLog.getPosition(position_id);
        Ranks ranks = serviceLog.getRanks(ranks_id);
        Department department = serviceLog.getDepartment(department_id);





       if( position != null && ranks != null && department != null ){
           Employers employers = new Employers();

           employers.setPersonalNumber(personalNumber);
           employers.setSurname(surname);
           employers.setName(name);
           employers.setSecondName(secondName);
           employers.setBornDate(bornDate);
           employers.setContractStart(contractStart);
           employers.setContractTime(contractTime);
           employers.setContractEnd(contractStart.plusYears(contractTime));


           employers.setPosition(position);
           employers.setRanks(ranks);
           employers.setDepartment(department);

           Path fileNameAndPath = Paths.get(photoPath, photo.getOriginalFilename());
           employers.setPhoto(photo.getOriginalFilename());
           try {
               Files.write(fileNameAndPath, photo.getBytes());
           } catch (IOException e) {
               e.printStackTrace();
           }
           serviceLog.addEmployer(employers);
       }


       return "redirect:/employers";
    }


    @GetMapping("/create/{id}")
    public String create(Model model,@PathVariable(name = "id") Long id){


        Employers employers = serviceLog.getEmployer(id);
        model.addAttribute("employers", employers);

        List<Department> departments = serviceLog.getAllDepartments();
        model.addAttribute("departments", departments);

        List<Position> positions = serviceLog.getAllPosition();
        model.addAttribute("positions", positions);

        List<Ranks> ranks = serviceLog.getAllRanks();
        model.addAttribute("rank", ranks);
        return "createEmployer";
    }

    @PostMapping("/create")
    public String update(@RequestParam(name = "id", defaultValue = "0") Long id,
                         @RequestParam(name="personal_number",defaultValue = "0") int personalNumber,
                         @RequestParam(name="surname",defaultValue = " ") String surname,
                         @RequestParam(name="name", defaultValue = " ") String name,
                         @RequestParam(name="second_name",defaultValue = " ") String secondName,
                         @RequestParam(name = "born_date")
                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate bornDate,
                         @RequestParam(name = "contract_start")
                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate contractStart,
                         @RequestParam(name="contract_time", defaultValue = "0") int contractTime,
                         @RequestParam(name = "position", defaultValue = " ") Long position_id,
                         @RequestParam(name = "ranks", defaultValue = " ") Long ranks_id,
                         @RequestParam(name = "department", defaultValue = " ") Long department_id){

        Employers employers = serviceLog.getEmployer(id);
        Position position = serviceLog.getPosition(position_id);
        Ranks ranks = serviceLog.getRanks(ranks_id);
        Department department = serviceLog.getDepartment(department_id);


        if( position != null && ranks != null && department != null ){
            employers.setPersonalNumber(personalNumber);
            employers.setSurname(surname);
            employers.setName(name);
            employers.setSecondName(secondName);
            employers.setBornDate(bornDate);
            employers.setContractStart(contractStart);
            employers.setContractTime(contractTime);
            employers.setContractEnd(contractStart.plusYears(contractTime));


            employers.setPosition(position);
            employers.setRanks(ranks);
            employers.setDepartment(department);

            serviceLog.updateEmployer(employers);
        }
        return  "redirect:/employers";

    }

    @PostMapping("/delete")
    public String delete(@RequestParam(name = "id") Long id){
        Employers employers = serviceLog.getEmployer(id);
        if (employers != null){
            serviceLog.deleteEmployer(employers);
        }
        return "redirect:/employers";
    }


    @GetMapping("/download/employers.xlsx")
    public ResponseEntity<InputStreamResource> excelStudentReport() throws IOException
    {
        List<Employers> list = (List<Employers>)serviceLog.getAllEmployers();

        ByteArrayInputStream in = ExcelGenerator.studentToExcel(list);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=Employers.xlsx");
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(in));
    }


    @GetMapping(value = "/exportpdf")
    public ResponseEntity<InputStreamResource> employeeReports() throws IOException {

        List<Employers> allEmployees = serviceLog.getAllEmployers();

        ByteArrayInputStream bis = PdfGenerator.employeesReport(allEmployees);

        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Disposition", "attachment;filename=employees.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }




}
