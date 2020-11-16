/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.questionario.rest.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Home redirection to swagger api documentation
 */
@Controller
public class HomeController {
    @RequestMapping(value = "/")
    public String index() {
        return "redirect:swagger-ui.html";
    }
}
