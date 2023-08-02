package br.com.lucianoneves.tarefas;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PrincipalController {

    @GetMapping("")
    public String index() {
        return "index";
    }

}
