package yzl.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import yzl.com.pojo.User;
import yzl.com.service.UserService;

@ControllerAdvice
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/find/{id}")
    public User findById(@PathVariable Integer id){
        User user = userService.selectByPrimaryKey(id);
        return user;
    }
    @GetMapping("/findall")
    public String findAll(Model model ){
     model.addAttribute("users",userService.findAll());
        return "selectAll";
    }
    @ResponseBody
    @PostMapping("/add")
    public User saveUser(User user){
        User user1 = userService.selectByPrimaryKey(user.getId());
        if(user1 == null)
            userService.insertSelective(user);
        else
            userService.updateByPrimaryKey(user);
        return user;
    }
    @GetMapping("/deleteById/{id}")
    public String deleteById(@PathVariable Integer id){
        userService.deleteByPrimaryKey(id);
        return "forward:/user/findall";
    }
    @GetMapping("/updataById/{id}")
    public String updataById(@PathVariable Integer id,Model model){
        User user = userService.selectByPrimaryKey(id);
        System.out.println(user);
        model.addAttribute("user",user);
        return "page";
    }
    @GetMapping("/page")
    public String page(){
        return "page";
    }
}
