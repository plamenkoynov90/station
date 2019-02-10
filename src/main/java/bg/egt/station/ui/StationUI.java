package bg.egt.station.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import bg.egt.station.enums.Action;
import bg.egt.station.service.StationService;

@Controller
public class StationUI {

	@Autowired
	private StationService stationService;

	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("doors", this.stationService.getStationDoors());
		return "home/home";
	}

	@PostMapping(value = "/addDoor")
	public String addDoor() {
		this.stationService.addDoor();
		return "redirect:/";
	}

	@PostMapping(value = "/processAction/{index}", params = "removeDoor")
	public String removeDoor(@PathVariable int index) {
		this.stationService.removeDoor(index);
		return "redirect:/";
	}

	@PostMapping(value = "/processAction/{index}", params = "putCoin")
	public String putCoin(@PathVariable int index, RedirectAttributes redirectAttributes) {
		String actionMsg = this.stationService.processAction(index, Action.PUT_COIN);
		redirectAttributes.addFlashAttribute("msg", actionMsg);
		return "redirect:/";
	}

	@PostMapping(value = "/processAction/{index}", params = "go")
	public String goThrough(@PathVariable int index, RedirectAttributes redirectAttributes) {
		String actionMsg = this.stationService.processAction(index, Action.GO_THROUGH);
		redirectAttributes.addFlashAttribute("msg", actionMsg);
		return "redirect:/";
	}

	@PostMapping(value = "/processAction/{index}", params = "key")
	public String useKey(@PathVariable int index, RedirectAttributes redirectAttributes) {
		String actionMsg = this.stationService.processAction(index, Action.USE_KEY);
		redirectAttributes.addFlashAttribute("msg", actionMsg);
		return "redirect:/";
	}

}
