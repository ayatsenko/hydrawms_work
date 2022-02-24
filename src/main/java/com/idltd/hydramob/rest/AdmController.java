package com.idltd.hydramob.rest;

import com.idltd.hydramob.entity.*;
import com.idltd.hydramob.repository.*;
import com.idltd.hydramob.utils.JSONLocationDatatable;
import com.idltd.hydramob.utils.JSONUsersDatatable;
import com.idltd.hydramob.utils.JSONWorkplaceDatatable;
import com.idltd.hydramob.utils.JSONWraper;
import com.idltd.hydramob.utils.security.Jwt_token_util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;

@RestController
//@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/adm")
public class AdmController {

    final UsersRepository usersRepository;
    final RoleRepository roleRepository;
    final LocationRepository locationRepository;
    final User_locationRepository user_locationRepository;
    final WorkplaceRepository workplaceRepository;
    final User_workplaceRepository user_workplaceRepository;

    public AdmController(UsersRepository usersRepository, RoleRepository roleRepository, LocationRepository locationRepository, User_locationRepository user_locationRepository, WorkplaceRepository workplaceRepository, User_workplaceRepository user_workplaceRepository) {
        this.usersRepository = usersRepository;
        this.roleRepository = roleRepository;
        this.locationRepository = locationRepository;
        this.user_locationRepository = user_locationRepository;
        this.workplaceRepository = workplaceRepository;
        this.user_workplaceRepository = user_workplaceRepository;
    }

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/add_user")
    public User add_user(@RequestParam(value="username", required=true) String username,
                         @RequestParam(value="pass", required=true) String pass,
                         @RequestParam(value="roleid", required=true) Long roleid,
                         @RequestParam(value="email", required=false) String email,
                         @RequestParam(value="telegram_chatid", required=false) Long telegram_chatid
    ) {

        User user = new User(username,bCryptPasswordEncoder.encode(pass));
        HashSet roles = new HashSet();
        roles.add(roleRepository.queryRoleByID(roleid));
        user.setRoles(roles);
        user.user_email=email;
        user.telegram_chatid=telegram_chatid;
        user.setJwt(Jwt_token_util.createJWT(user,"ООО Рога и Копыта"));
        usersRepository.save(user);
        return user;
    }

    @PostMapping("/update_user")
    public User update_user(
                        @RequestParam(value="id") long id,
                        @RequestParam(value="roleid") Long roleid,
                        @RequestParam(value="email", required=false) String email,
                        @RequestParam(value="telegram_chatid", required=false) Long telegram_chatid
    ) {

        User user = usersRepository.queryCurrUserByID(id);
        HashSet roles = new HashSet();
        roles.add(roleRepository.queryRoleByID(roleid));
        user.setRoles(roles);
        user.user_email=email;
        user.telegram_chatid=telegram_chatid;
        usersRepository.save(user);
        return user;
    }

    @PostMapping("/get_users")
    public JSONUsersDatatable get_users(@RequestParam(value="username", required=false, defaultValue = "%") String username){
        JSONUsersDatatable result = new JSONUsersDatatable(new ArrayList<>());
        result.setData(usersRepository.queryByLikeUsername(username));
        return result;
    }

    @PostMapping("/del_user")
    public JSONWraper del_user(@RequestParam(value="id", required=true) long id){
        JSONWraper result = new JSONWraper();
        usersRepository.delete(usersRepository.queryCurrUserByID(id));
        result.result=true;
        return result;

    }

    @PostMapping("/change_pass")
    public JSONWraper change_pass(@RequestParam(value="id") long id,
                                  @RequestParam(value="password") String password){
        JSONWraper result = new JSONWraper();
        User u=usersRepository.queryCurrUserByID(id);
        u.setPassword(bCryptPasswordEncoder.encode(password));
        u=usersRepository.save(u);
        result.result=true;
        result.data=u.getPassword();
        return result;

    }

    @PostMapping("/createtoken")
    public JSONWraper createtoken(@RequestParam(value="id") long id){
        JSONWraper result = new JSONWraper();
        User user = usersRepository.queryCurrUserByID(id);
        user.setJwt(Jwt_token_util.createJWT(user,"ООО Рога и Копыта"));
        user = usersRepository.save(user);
        result.result=true;
        result.data=user.getJwt();
        return result;

    }

    @PostMapping("/cleartoken")
    public String cleartoken(@RequestParam(value="id", required=true) long id){
        User user = usersRepository.queryCurrUserByID(id);
        user.setJwt(null);
        user = usersRepository.save(user);
        return user.getJwt();

    }

    @PostMapping("/add_user_location")
    public User_location add_user_location( @RequestParam(value="user_id") long user_id,
                                            @RequestParam(value="loc_id") long loc_id) {

        User_location user_location = new User_location(user_id,loc_id);
        user_locationRepository.save(user_location);
        return user_location;
    }

    @PostMapping("/del_user_location")
    public void del_user_location(@RequestParam(value="user_id") long user_id,
                                  @RequestParam(value="loc_id") long loc_id) {
        User_location user_location=user_locationRepository.queryByUser_idAndAndLoc_id(user_id,loc_id);
        user_locationRepository.delete(user_location);
    }

    @PostMapping("/add_userworkplace")
    public User_workplace add_userworkplace( @RequestParam(value="user_id") long user_id,
                                            @RequestParam(value="wp_id") long wp_id) {
        User_workplace user_workplace = new User_workplace(user_id,wp_id);
        user_workplaceRepository.save(user_workplace);
        return user_workplace;
    }

    @PostMapping("/del_userworkplace")
    public void del_userworkplace( @RequestParam(value="user_id") long user_id,
                                   @RequestParam(value="wp_id") long wp_id) {
        user_workplaceRepository.delete(user_workplaceRepository.queryByUser_idandLoc_id(user_id,wp_id));
    }

    @PostMapping("/get_locations_table")
    public JSONLocationDatatable get_locations(@RequestParam(value="partnership", required=false, defaultValue = "%") String partnership){
        JSONLocationDatatable result = new JSONLocationDatatable(new ArrayList<>());
        result.setData(locationRepository.queryByLikePartnership(partnership));
        return result;
    }

    @PostMapping("/get_user_locations_table")
    public JSONLocationDatatable get_user_locations_table(@RequestParam(value="user_id", required=false, defaultValue = "-1") long user_id){
        JSONLocationDatatable result = new JSONLocationDatatable(new ArrayList<>());
        if (user_id != -1) {
            result.setData(locationRepository.queryByUserId(user_id));
        }
        return result;
    }

    @PostMapping("/add_location")
    public Location add_location(
            @RequestParam(value="loc_location", required=true) String loc_location,
            @RequestParam(value="loc_partnership", required=true) String loc_partnership,
            @RequestParam(value="loc_address", required=true) String loc_address,
            @RequestParam(value="loc_city", required=true) String loc_city,
            @RequestParam(value="loc_zipcode", required=true) String loc_zipcode,
            @RequestParam(value="loc_country_iso2", required=true) String loc_country_iso2,
            @RequestParam(value="loc_phone", required=true) String loc_phone
    ){
        Location location = new Location();
        location.loc_location=loc_location;
        location.loc_partnership=loc_partnership;
        location.loc_address=loc_address;
        location.loc_city=loc_city;
        location.loc_zipcode=loc_zipcode;
        location.loc_country_iso2=loc_country_iso2;
        location.loc_phone=loc_phone;
        locationRepository.save(location);

        return location;
    }

    @PostMapping("/set_location")
    public Location set_location(
            @RequestParam(value="loc_id", required=true) long loc_id,
            @RequestParam(value="loc_location", required=true) String loc_location,
            @RequestParam(value="loc_partnership", required=true) String loc_partnership,
            @RequestParam(value="loc_address", required=true) String loc_address,
            @RequestParam(value="loc_city", required=true) String loc_city,
            @RequestParam(value="loc_zipcode", required=true) String loc_zipcode,
            @RequestParam(value="loc_country_iso2", required=true) String loc_country_iso2,
            @RequestParam(value="loc_phone", required=true) String loc_phone
    ){
        Location location = new Location();
        location.loc_id=loc_id;
        location.loc_location=loc_location;
        location.loc_partnership=loc_partnership;
        location.loc_address=loc_address;
        location.loc_city=loc_city;
        location.loc_zipcode=loc_zipcode;
        location.loc_country_iso2=loc_country_iso2;
        location.loc_phone=loc_phone;
        locationRepository.save(location);

        return location;
    }

    @PostMapping("/del_location")
    public void del_location(
            @RequestParam(value="loc_id", required=true) long loc_id
    ){
        locationRepository.delete(locationRepository.queryLocByID(loc_id));
    }

    @PostMapping("/get_workplace_table")
    public JSONWorkplaceDatatable get_location_workplace_table(){
        JSONWorkplaceDatatable result = new JSONWorkplaceDatatable(workplaceRepository.findAll());
        return result;
    }
    @PostMapping("/get_location_workplace_table")
    public JSONWorkplaceDatatable get_location_workplace_table(@RequestParam(value="loc_id", required=false, defaultValue = "-1") Long loc_id){
        JSONWorkplaceDatatable result = new JSONWorkplaceDatatable(new ArrayList<>());
        if (loc_id!=-1) {
            result.setData(workplaceRepository.queryByLoc_id(loc_id));
        }
        return result;
    }
    @PostMapping("/get_user_location_workplace_table")
    public JSONWorkplaceDatatable get_user_location_workplace_table(@RequestParam(value="loc_id", required=false, defaultValue = "-1") Long loc_id,
                                                                    @RequestParam(value="user_id", required=false, defaultValue = "-1") Long user_id
    ){
        JSONWorkplaceDatatable result = new JSONWorkplaceDatatable(new ArrayList<>());
        if (loc_id!=-1) {
            result.setData(workplaceRepository.queryByUser_idandLoc_id(user_id,loc_id));
        }
        return result;
    }

    @PostMapping("/add_workplace")
    public Workplace add_workplace(
            @RequestParam(value="loc_id", required=true) long loc_id,
            @RequestParam(value="wp_name", required=true) String wp_name,
            @RequestParam(value="wp_texttemplate", required=true) String wp_texttemplate
    ){
        Workplace workplace = new Workplace();
        workplace.loc_id=loc_id;
        workplace.wp_name=wp_name;
        workplace.wp_texttemplate=wp_texttemplate;
        workplaceRepository.save(workplace);
        return workplace;
    }

    @PostMapping("/update_workplace")
    public Workplace add_workplace(
            @RequestParam(value="loc_id", required=true) long loc_id,
            @RequestParam(value="wp_id", required=true) long wp_id,
            @RequestParam(value="wp_name", required=true) String wp_name,
            @RequestParam(value="wp_texttemplate", required=true) String wp_texttemplate
    ){
        Workplace workplace = new Workplace();
        workplace.loc_id=loc_id;
        workplace.wp_id=wp_id;
        workplace.wp_name=wp_name;
        workplace.wp_texttemplate=wp_texttemplate;
        workplaceRepository.save(workplace);
        return workplace;
    }

    @PostMapping("/del_workplace")
    public Workplace add_workplace(
            @RequestParam(value="wp_id", required=true) long wp_id
    ){
        Workplace workplace = new Workplace();
        workplace.wp_id=wp_id;
        workplaceRepository.delete(workplace);
        return workplace;
    }
}
