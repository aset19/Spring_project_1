package Spring.AsetProject.Controllers;


import Spring.AsetProject.Entities.Position;
import Spring.AsetProject.Service.ServiceLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@Controller
@RequestMapping("/positions")
public class PositionController {

    @Autowired
    private ServiceLog serviceLog;

    @GetMapping
    public String getAllPositions(Model model){
        List<Position> Positions = serviceLog.getAllPosition();
        model.addAttribute("positions", Positions);
        return "positions";
    }

    @GetMapping("/add")
    public String add(){return "addPosition";}


    @PostMapping("/add")
    public String addPosition(@RequestParam(name="position_name") String positionName){
        if(positionName != null){
            Position position = new Position();
            position.setPositionName(positionName);
            serviceLog.addPosition(position);
        }
        return "redirect:/positions";
    }

    @GetMapping("/create/{id}")
    public String create(@PathVariable(name = "id") Long id, Model model){
        Position position = serviceLog.getPosition(id);
        model.addAttribute("position", position);
        return "createPosition";
    }

    @PostMapping("/create")
    public String update(@RequestParam(name = "id") Long id,
                         @RequestParam(name = "position") String positionName){
        Position position = serviceLog.getPosition(id);
        if (positionName != null && position != null){
            position.setPositionName(positionName);
            serviceLog.updatePosition(position);
        }
        return  "redirect:/positions";

    }

    @PostMapping("/delete")
    public String delete(@RequestParam(name = "id") Long id){
        Position positions = serviceLog.getPosition(id);
        if (positions != null){
            serviceLog.deletePosition(positions);
        }
        return "redirect:/positions";
    }






}
