/**   
* @Title: HelloWordController.java 
* @Package com.shtouyun.etrace.web 
* @author 
* @version V1.0   
*/
package database.mongodb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import database.mongodb.entity.UserDTO;
import database.mongodb.service.UserService;

@RestController
@EnableAutoConfiguration
public class HelloController {

    @Autowired
    UserService userService;

    @RequestMapping("/hello")
    public UserDTO hello() throws Exception {
        System.out.println("hello");
        return userService.saveUser();
    }
}
