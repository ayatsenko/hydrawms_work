package com.idltd.hydramob.controller.user_interface;

import com.idltd.hydramob.repository.User_ListRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/user_interface")
public class UserInterfaceController {
    private final User_ListRepository user_ListRepository;

    public UserInterfaceController(
            User_ListRepository user_ListRepository
                                ) {
        this.user_ListRepository = user_ListRepository;
    }

    @RequestMapping(value = {"","/", "/index"}, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView user_interface(
    ){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/user_interface/cover");
        return mav;
    }
}
