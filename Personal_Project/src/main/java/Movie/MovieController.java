package Movie;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("movie")
public class MovieController {
	
	@Autowired
	MovieService svc;

	@RequestMapping(value="search", method=RequestMethod.POST)
	public String resulit (@RequestParam("keyword")String keyword, Model model){
		 ArrayList<MovieVO> arr = svc.search(keyword);
		 model.addAttribute("list", arr);
		return "movie/result";
	}
	
}
