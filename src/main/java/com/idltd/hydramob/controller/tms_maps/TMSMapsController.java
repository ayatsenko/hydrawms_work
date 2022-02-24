package com.idltd.hydramob.controller.tms_maps;

import com.idltd.hydramob.repository.User_ListRepository;
import com.idltd.hydramob.repository.User_Role_ListRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/tms_maps")
public class TMSMapsController {

    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;

     public TMSMapsController(
            User_ListRepository user_ListRepository,
            User_Role_ListRepository user_Role_ListRepository

    ) {
        this.user_ListRepository = user_ListRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;

    }

    @RequestMapping
    public ModelAndView index(ModelAndView model
    ){
        model.setViewName("tms_maps/cover");
        return model;
    }
}
