package Spring.AsetProject.Controllers;


import Spring.AsetProject.Entities.Ranks;
import Spring.AsetProject.Service.ServiceLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/ranks")
public class RankController {

    @Autowired
    private ServiceLog serviceLog;

    @GetMapping
    public String form(Model model){
        List<Ranks> Ranks = serviceLog.getAllRanks();
        model.addAttribute("ranks", Ranks);
        return "ranks";
    }

    @GetMapping("/add")
    public String add(){return "addRanks";}


    @PostMapping("/add")
    public String addRang(@RequestParam(name="ranks_name") String ranksName){
        if(ranksName != null){
            Ranks ranks = new Ranks();
            ranks.setRanksName(ranksName);
            serviceLog.addRanks(ranks);
        }
        return "redirect:/ranks";

    }

    @GetMapping("/create/{id}")
    public String create(@PathVariable(name = "id") Long id, Model model){
        Ranks ranks = serviceLog.getRanks(id);
        model.addAttribute("rank", ranks);
        return "createRank";
    }

    @PostMapping("/create")
    public String update(@RequestParam(name = "id") Long id,
                         @RequestParam(name = "ranks") String ranksName){
        Ranks ranks = serviceLog.getRanks(id);
        if (ranksName != null && ranks != null){
            ranks.setRanksName(ranksName);
            serviceLog.updateRanks(ranks);
        }
        return  "redirect:/ranks";

    }

    @PostMapping("/delete")
    public String delete(@RequestParam(name = "id") Long id){
        Ranks ranks = serviceLog.getRanks(id);
        if (ranks != null){
            serviceLog.deleteRanks(ranks);
        }
        return "redirect:/ranks";
    }


}
