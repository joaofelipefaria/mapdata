package lu.joaofaria.java.samples.mapdata.api.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class GreetingsController {

	@GetMapping("/greetings")
	public String sayHi() {
		return "Hello, API!";
	}
}
