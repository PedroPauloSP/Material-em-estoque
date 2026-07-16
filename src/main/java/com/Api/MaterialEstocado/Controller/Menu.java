
package com.Api.MaterialEstocado.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author pedro
 */
@Controller
public class Menu {
    @GetMapping("/Menu") public String menu() {
        
        return "Menu"; 
    }
}
