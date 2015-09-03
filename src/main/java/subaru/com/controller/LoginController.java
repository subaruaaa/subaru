package subaru.com.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import static subaru.com.types.functions.map;
import static subaru.com.utils.JsonHelper.jsonpEntity;

@Controller
public class LoginController {
	@RequestMapping("/add_h5collection.php")
	public ResponseEntity<String> addCollection(Integer gid, String callback, HttpServletResponse response) {

		return jsonpEntity(
				map("终于起来了","哈哈哈哈"), callback);
	}
}
