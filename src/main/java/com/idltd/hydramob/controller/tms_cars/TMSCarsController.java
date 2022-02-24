package com.idltd.hydramob.controller.tms_cars;

import com.idltd.hydramob.entity.User_List;
import com.idltd.hydramob.entity.User_Role_List;
import com.idltd.hydramob.entity.tms_cars.DetailTMSCars;
import com.idltd.hydramob.repository.User_ListRepository;
import com.idltd.hydramob.repository.User_Role_ListRepository;
import com.idltd.hydramob.repository.tms_cars.DetailTMSCarsRepository;
import com.idltd.hydramob.repository.tms_cars.MenuTMSCarsRepository;
import com.idltd.hydramob.utils.JSONDatatable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@RestController
@RequestMapping("/tms_cars")
public class TMSCarsController {

    private MenuTMSCarsRepository menuTMSCarsRepository;
    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;
    private DetailTMSCarsRepository detailTMSCarsRepository;

    public TMSCarsController(
            MenuTMSCarsRepository menuTMSCarsRepository,
            User_ListRepository user_ListRepository,
            User_Role_ListRepository user_Role_ListRepository,
            DetailTMSCarsRepository detailTMSCarsRepository
    ) {
        this.menuTMSCarsRepository = menuTMSCarsRepository;
        this.user_ListRepository = user_ListRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;
        this.detailTMSCarsRepository = detailTMSCarsRepository;
    }

    @RequestMapping
    public ModelAndView index(ModelAndView model,
                              @RequestParam(name = "tms_car_id", required = false) Long tms_car_id,

                              @RequestParam(name = "tms_cars_table_order_column", required = false) Long tms_cars_table_order_column,
                              @RequestParam(name = "tms_cars_table_order_type", required = false) String tms_cars_table_order_type,
                              @RequestParam(name = "tms_cars_table_search", required = false) String tms_cars_table_search,
                              @RequestParam(name = "tms_cars_table_pagelen", required = false) Long tms_cars_table_pagelen,
                              @RequestParam(name = "tms_cars_table_page", required = false) Long tms_cars_table_page

    ){
        model.addObject("tms_car_id", tms_car_id);

        model.addObject("tms_cars_table_order_column", tms_cars_table_order_column);
        model.addObject("tms_cars_table_order_type", tms_cars_table_order_type);
        model.addObject("tms_cars_table_search", tms_cars_table_search);
        model.addObject("tms_cars_table_pagelen", tms_cars_table_pagelen);
        model.addObject("tms_cars_table_page", tms_cars_table_page);

        model.setViewName("tms_cars/cover");
        return model;
    }

    @PostMapping("/gettable")
    public JSONDatatable gettable(
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_Role_List> user_Role_List;
        List<User_List> user_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();

        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        result.setData(menuTMSCarsRepository.queryMenuTMSCarsByID(user_List.get(0).id,user_Role_List.get(0).role_id));

        return result;
    }

    @RequestMapping("/detail")
    public ModelAndView detailview(@RequestParam(value="mode") Long mode,
                                   @RequestParam(value="tms_car_id") Long tms_car_id,

                                   @RequestParam(value="tms_cars_table_order_column") Long order_column,
                                   @RequestParam(value="tms_cars_table_order_type") String order_type,
                                   @RequestParam(value="tms_cars_table_search") String table_search,
                                   @RequestParam(value="tms_cars_table_pagelen") Long pagelen,
                                   @RequestParam(value="tms_cars_table_page") Long page
    ) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("mode", mode);
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        List<DetailTMSCars> detailTMSCars;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        if(mode == 0){
        }
        else{
            detailTMSCars = detailTMSCarsRepository.queryDetailTMSCarsByID(user_List.get(0).id,user_Role_List.get(0).role_id, tms_car_id);

            mav.addObject("tms_car_id", detailTMSCars.get(0).tms_car_id);
            mav.addObject("tms_car_name", detailTMSCars.get(0).tms_car_name);
            mav.addObject("tms_car_number", detailTMSCars.get(0).tms_car_number);
            mav.addObject("tms_car_phone", detailTMSCars.get(0).tms_car_phone);
            mav.addObject("tms_car_telegram", detailTMSCars.get(0).tms_car_telegram);
        }

        mav.addObject("tms_cars_table_order_column", order_column);
        mav.addObject("tms_cars_table_order_type", order_type);
        mav.addObject("tms_cars_table_search", table_search);
        mav.addObject("tms_cars_table_pagelen", pagelen);
        mav.addObject("tms_cars_table_page", page);

        mav.setViewName("/tms_cars/detail");
        return mav;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/model")
    public ModelAndView model(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="tms_car_id") Long tms_car_id,
            @RequestParam(value="tms_car_name", required = false) String tms_car_name,
            @RequestParam(value="tms_car_number", required = false) String tms_car_number,
            @RequestParam(value="tms_car_phone", required = false) String tms_car_phone,
            @RequestParam(value="tms_car_telegram", required = false) Long tms_car_telegram,

            @RequestParam(value="tms_cars_table_order_column", required = false) Long order_column,
            @RequestParam(value="tms_cars_table_order_type", required = false) String order_type,
            @RequestParam(value="tms_cars_table_search", required = false) String table_search,
            @RequestParam(value="tms_cars_table_pagelen", required = false) Long pagelen,
            @RequestParam(value="tms_cars_table_page", required = false) Long page
    ) {
        ModelAndView mav = new ModelAndView();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        user_List = user_ListRepository.queryUserByName(name);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try {
            switch (mode.intValue()) {
                case 0:
                    StoredProcedureQuery AddTMSCarTypeQuery = entityManager
                            .createStoredProcedureQuery("PKG_TMS_POLAND.pr_AddTmsCars")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, tms_car_number)
                            .setParameter(4, tms_car_name)
                            .setParameter(5, tms_car_phone)
                            .setParameter(6, tms_car_telegram);
                    AddTMSCarTypeQuery.execute();
                    break;
                case 1:
                    StoredProcedureQuery EditTMSCarTypeQuery = entityManager
                            .createStoredProcedureQuery("PKG_TMS_POLAND.pr_EditTmsCars")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(6, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(7, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, tms_car_id)
                            .setParameter(4, tms_car_number)
                            .setParameter(5, tms_car_name)
                            .setParameter(6, tms_car_phone)
                            .setParameter(7, tms_car_telegram);
                    EditTMSCarTypeQuery.execute();
                    break;
                case 2:
                    StoredProcedureQuery DelTMSCarTypeQuery = entityManager
                            .createStoredProcedureQuery("PKG_TMS_POLAND.pr_DelTmsCars")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, tms_car_id);
                    DelTMSCarTypeQuery.execute();
                    break;
                default:
                    throw new Exception("Mode:"+mode.toString()+" undefined");
            }
            mav.addObject("error_code",0);
        } catch (Exception e) {
            mav.addObject("error_code",500);
            mav.addObject("exception",e.getLocalizedMessage());
        }

        //Параметры на форме
        mav.addObject("mode", mode);

        mav.addObject("tms_car_id", tms_car_id);
        mav.addObject("tms_car_name", tms_car_name);
        mav.addObject("tms_car_number", tms_car_number);
        mav.addObject("tms_car_phone", tms_car_phone);
        mav.addObject("tms_car_telegram", tms_car_telegram);

        mav.addObject("tms_cars_table_order_column", order_column);
        mav.addObject("tms_cars_table_order_type", order_type);
        mav.addObject("tms_cars_table_search", table_search);
        mav.addObject("tms_cars_table_pagelen", pagelen);
        mav.addObject("tms_cars_table_page", page);

        mav.setViewName("/tms_cars/detail");
        return mav;
    }
}
