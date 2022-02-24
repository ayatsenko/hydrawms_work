package com.idltd.hydramob.controller.wms_sale_orders;

import com.idltd.hydramob.entity.*;
import com.idltd.hydramob.entity.list.ActionPlanTypeList;
import com.idltd.hydramob.entity.wms_clients_exchange.ExchangeServiceTypeFile;
import com.idltd.hydramob.entity.wms_sale_orders.MenuWMSSaleOrdersMainTemp;
import com.idltd.hydramob.entity.wms_sale_orders.files.WMSSaleOrdersHittradeFileTemp;
import com.idltd.hydramob.repository.Contragent_ListRepository;
import com.idltd.hydramob.repository.User_ListRepository;
import com.idltd.hydramob.repository.User_Role_ListRepository;
import com.idltd.hydramob.repository.wms_clients_exchange.ExchangeServiceTypeFileRepository;
import com.idltd.hydramob.repository.wms_list.WMSContragentWEBServiceListRepository;
import com.idltd.hydramob.repository.wms_sale_orders.MenuWMSSaleOrdersMainRepository;
import com.idltd.hydramob.repository.wms_sale_orders.MenuWMSSaleOrdersMainTempRepository;
import com.idltd.hydramob.repository.wms_sale_orders.MenuWMSSaleOrdersPositionRepository;
import com.idltd.hydramob.repository.wms_sale_orders.MenuWMSSaleOrdersPostRepository;
import com.idltd.hydramob.repository.wms_sale_orders.files.WMSSaleOrdersHittradeFileRepository;
import com.idltd.hydramob.utils.JSONDatatable;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.sql.rowset.serial.SerialException;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/wms_sale_orders")
public class WMSSaleOrdersController {

    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;

    private MenuWMSSaleOrdersMainRepository menuWMSSaleOrdersMainRepository;

    private MenuWMSSaleOrdersPositionRepository menuWMSSaleOrdersPositionRepository;

    private MenuWMSSaleOrdersPostRepository menuWMSSaleOrdersPostRepository;
    private Contragent_ListRepository contragent_ListRepository;
    private WMSContragentWEBServiceListRepository wmsContragentWEBServiceListRepository;
    private WMSSaleOrdersHittradeFileRepository wmsSaleOrdersHittradeFileRepository;
    private ExchangeServiceTypeFileRepository exchangeServiceTypeFileRepository;

    private MenuWMSSaleOrdersMainTempRepository menuWMSSaleOrdersMainTempRepository;

    public WMSSaleOrdersController(
            User_ListRepository user_ListRepository,
            User_Role_ListRepository user_Role_ListRepository,

            MenuWMSSaleOrdersMainRepository menuWMSSaleOrdersMainRepository,

            MenuWMSSaleOrdersPositionRepository menuWMSSaleOrdersPositionRepository,

            MenuWMSSaleOrdersPostRepository menuWMSSaleOrdersPostRepository,
            Contragent_ListRepository contragent_ListRepository,
            WMSContragentWEBServiceListRepository wmsContragentWEBServiceListRepository,
            WMSSaleOrdersHittradeFileRepository wmsSaleOrdersHittradeFileRepository,
            ExchangeServiceTypeFileRepository exchangeServiceTypeFileRepository,

            MenuWMSSaleOrdersMainTempRepository menuWMSSaleOrdersMainTempRepository
    ) {
        this.user_ListRepository = user_ListRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;

        this.menuWMSSaleOrdersMainRepository = menuWMSSaleOrdersMainRepository;

        this.menuWMSSaleOrdersPositionRepository = menuWMSSaleOrdersPositionRepository;

        this.menuWMSSaleOrdersPostRepository = menuWMSSaleOrdersPostRepository;
        this.contragent_ListRepository = contragent_ListRepository;
        this.wmsContragentWEBServiceListRepository = wmsContragentWEBServiceListRepository;
        this.wmsSaleOrdersHittradeFileRepository = wmsSaleOrdersHittradeFileRepository;
        this.exchangeServiceTypeFileRepository = exchangeServiceTypeFileRepository;

        this.menuWMSSaleOrdersMainTempRepository = menuWMSSaleOrdersMainTempRepository;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @RequestMapping
    public ModelAndView index(ModelAndView model,
                              @RequestParam(name = "act_id", required = false) Long act_id,

                              @RequestParam(name = "table_order_column", required = false) Long table_order_column,
                              @RequestParam(name = "table_order_type", required = false) String table_order_type,
                              @RequestParam(name = "table_search", required = false) String table_search,
                              @RequestParam(name = "table_pagelen", required = false) Long table_pagelen,
                              @RequestParam(name = "table_page", required = false) Long table_page
    ){
        List<User_Report_List> user_Report_List;
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        List<Client_Report_List> client_Report_List;
        List<Action_Type_List> action_Type_List;
        List<Action_Status_List> action_Status_List;
        List<Action_Result_List> action_Result_List;
        List<ActionPlanTypeList> actionPlanType;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        user_List = user_ListRepository.queryUserByName(name);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        model.addObject("act_id", act_id);

        model.addObject("table_order_column", table_order_column);
        model.addObject("table_order_type", table_order_type);
        model.addObject("table_search", table_search);
        model.addObject("table_pagelen", table_pagelen);
        model.addObject("table_page", table_page);

        model.setViewName("wms_sale_orders/cover");
        return model;
    }

    @PostMapping("/get_menu")
    public JSONDatatable gettable(
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_Role_List> user_Role_List;
        List<User_List> user_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();

        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        result.setData(menuWMSSaleOrdersMainRepository.queryWMSSaleOrdersMainByID(
                user_List.get(0).id,
                user_Role_List.get(0).role_id
        ));

        return result;
    }

    @PostMapping("/edit_main_post")
    public ModelAndView edit_main_post(
            @RequestParam(name = "client_id") String client_id,
            @RequestParam(name = "exch_sale_order_post_id") String exch_sale_order_post_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery DelActQuery = entityManager
                    .createStoredProcedureQuery("PKG_WMS.pr_EditExchangeSaleOrderPostLink")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, client_id)
                    .setParameter(4, exch_sale_order_post_id)
                    ;
            DelActQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/get_position_menu")
    public JSONDatatable get_position_menu(
            @RequestParam(name = "req_exch_ser_file_id", required = false) Long req_exch_ser_file_id,
            @RequestParam(name = "req_exch_ser_file_row_id", required = false) Long req_exch_ser_file_row_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_Role_List> user_Role_List;
        List<User_List> user_List;

        if(req_exch_ser_file_id != null && req_exch_ser_file_id > 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuWMSSaleOrdersPositionRepository.queryWMSSaleOrdersPositionsByID(
                    user_List.get(0).id,
                    user_Role_List.get(0).role_id,
                    req_exch_ser_file_id,
                    req_exch_ser_file_row_id
            ));
        }
        return result;
    }

    @PostMapping("/get_post_menu")
    public JSONDatatable get_post_menu(
            @RequestParam(name = "exch_sale_order_id", required = false) Long exch_sale_order_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_Role_List> user_Role_List;
        List<User_List> user_List;

        if(exch_sale_order_id != null && exch_sale_order_id > 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuWMSSaleOrdersPostRepository.queryWMSSaleOrdersPostByID(
                    user_List.get(0).id,
                    user_Role_List.get(0).role_id,
                    exch_sale_order_id
            ));
        }
        return result;
    }

    @GetMapping("/get_client_list")
    public JSONDatatable get_client_list(
            @RequestParam(value="mode") Long mode
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(mode != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(contragent_ListRepository.queryCntWMSServiceList(
                    user_List.get(0).id, user_Role_List.get(0).role_id));
        }
        return result;
    }

    @GetMapping("/get_client_web_service_list")
    public JSONDatatable get_client_web_service_list(
            @RequestParam(value="mode") Long mode,
            @RequestParam(name = "cnt_id", required = false) Long cnt_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(mode != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(wmsContragentWEBServiceListRepository.queryCntWMSWEBServiceList(
                    user_List.get(0).id, user_Role_List.get(0).role_id, cnt_id));
        }
        return result;
    }

    //Add Documents File
    @PostMapping("/add_main_file")
    public ModelAndView add_main_file(
            @RequestParam(name = "cnt_id", required = false) Long cnt_id,
            @RequestParam(name = "exch_ser_type_id", required = false) Long exch_ser_type_id,
            @RequestParam("file") MultipartFile file
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);
        try (InputStream inputStream = file.getInputStream()) {
            try {
/*Hit Trade*/
                if (exch_ser_type_id == 6) {
                    ExchangeServiceTypeFile clients_documents_file = new ExchangeServiceTypeFile();

                    clients_documents_file.cnt_id = cnt_id;
                    clients_documents_file.user_id = user_List.get(0).id;
                    clients_documents_file.role_id = user_Role_List.get(0).role_id;
                    clients_documents_file.exch_ser_file_name = file.getOriginalFilename();
                    clients_documents_file.exch_ser_file_contenttype = file.getContentType();
                    clients_documents_file.exch_ser_type_id = exch_ser_type_id;

                    try (InputStream fileInputStream = file.getInputStream()) {
                        byte[] obj_in = IOUtils.toByteArray(fileInputStream);
                        clients_documents_file.exch_ser_file_body = new javax.sql.rowset.serial.SerialBlob(obj_in);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (SerialException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    clients_documents_file = exchangeServiceTypeFileRepository.save(clients_documents_file);
                    Long exch_ser_file_id = clients_documents_file.getExch_ser_file_id();

                    InputStream Hittrade_excelFile = new BufferedInputStream(file.getInputStream());

                    Workbook workbook = new XSSFWorkbook(Hittrade_excelFile);
                    Sheet datatypeSheet = workbook.getSheetAt(0);
                    Iterator<Row> Hittrade_it = datatypeSheet.iterator();

                    //пропускаем 1-ю строку
                    if (Hittrade_it.hasNext()) {
                        Row row = Hittrade_it.next();
                    }

                    //пропускаем 2-ю строку
                    if (Hittrade_it.hasNext()) {
                        Row row = Hittrade_it.next();
                    }

                    //пропускаем 3-ю строку
                    if (Hittrade_it.hasNext()) {
                        Row row = Hittrade_it.next();
                    }

                    while (Hittrade_it.hasNext()) {
                        try {
                            Row row = Hittrade_it.next();
                            WMSSaleOrdersHittradeFileTemp wmsSaleOrdersHittradeFileTemp = new WMSSaleOrdersHittradeFileTemp();

                            wmsSaleOrdersHittradeFileTemp.setExch_ser_file_id(exch_ser_file_id);
                            wmsSaleOrdersHittradeFileTemp.setExch_ser_file_row_id((long) row.getRowNum());
                            wmsSaleOrdersHittradeFileTemp.setDb_user_id(user_List.get(0).id);
                            wmsSaleOrdersHittradeFileTemp.setDb_role_id(user_Role_List.get(0).role_id);

                            DataFormatter dataFormatter = new DataFormatter();

                            if (row.getCell(0) != null && !row.getCell(0).toString().equals("")) {
                                wmsSaleOrdersHittradeFileTemp.setPosition_date(dataFormatter.formatCellValue(row.getCell(0)));
                            } else {
                                wmsSaleOrdersHittradeFileTemp.setPosition_date("");
                            }

                            if (row.getCell(1) != null && !row.getCell(1).toString().equals(""))
                                wmsSaleOrdersHittradeFileTemp.setOrder_num(row.getCell(1).toString());
                            else {
                                wmsSaleOrdersHittradeFileTemp.setOrder_num("");
                            }

                            if (row.getCell(2) != null && !row.getCell(2).toString().equals("")) {
                                row.getCell(2).setCellType(CellType.STRING);
                                wmsSaleOrdersHittradeFileTemp.setSystem_num(row.getCell(2).toString());
                            } else {
                                wmsSaleOrdersHittradeFileTemp.setSystem_num("");
                            }

                            if (row.getCell(3) != null && !row.getCell(3).toString().equals("")) {
                                row.getCell(3).setCellType(CellType.STRING);
                                wmsSaleOrdersHittradeFileTemp.setOut_order_num(row.getCell(3).toString());
                            } else {
                                wmsSaleOrdersHittradeFileTemp.setOut_order_num("");
                            }

                            if (row.getCell(4) != null && !row.getCell(4).toString().equals("")) {
                                wmsSaleOrdersHittradeFileTemp.setPaid_type(row.getCell(4).toString());
                            } else {
                                wmsSaleOrdersHittradeFileTemp.setPaid_type("");
                            }

                            if (row.getCell(5) != null && !row.getCell(5).toString().equals("")) {
                                row.getCell(5).setCellType(CellType.STRING);
                                wmsSaleOrdersHittradeFileTemp.setNomenclature_id(row.getCell(5).toString());
                            } else {
                                wmsSaleOrdersHittradeFileTemp.setNomenclature_id("");
                            }

                            if (row.getCell(6) != null && !row.getCell(6).toString().equals("")) {
                                wmsSaleOrdersHittradeFileTemp.setNomenclature_name(row.getCell(6).toString());
                            } else {
                                wmsSaleOrdersHittradeFileTemp.setNomenclature_name("");
                            }

                            if (row.getCell(7) != null && !row.getCell(7).toString().equals("")) {
                                row.getCell(7).setCellType(CellType.STRING);
                                wmsSaleOrdersHittradeFileTemp.setPosition_count(row.getCell(7).toString());
                            } else {
                                wmsSaleOrdersHittradeFileTemp.setPosition_count("");
                            }

                            if (row.getCell(8) != null && !row.getCell(8).toString().equals("")) {
                                row.getCell(8).setCellType(CellType.STRING);
                                wmsSaleOrdersHittradeFileTemp.setPrice(row.getCell(8).toString());
                            } else {
                                wmsSaleOrdersHittradeFileTemp.setPrice("");
                            }

                            if (row.getCell(9) != null && !row.getCell(9).toString().equals("")) {
                                row.getCell(9).setCellType(CellType.STRING);
                                wmsSaleOrdersHittradeFileTemp.setUnit(row.getCell(9).toString());
                            } else {
                                wmsSaleOrdersHittradeFileTemp.setUnit("");
                            }

                            if (row.getCell(10) != null && !row.getCell(10).toString().equals("")) {
                                row.getCell(10).setCellType(CellType.STRING);
                                wmsSaleOrdersHittradeFileTemp.setTtn(row.getCell(10).toString());
                            } else {
                                wmsSaleOrdersHittradeFileTemp.setTtn("");
                            }

                            wmsSaleOrdersHittradeFileRepository.save(wmsSaleOrdersHittradeFileTemp);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    StoredProcedureQuery DelActQuery = entityManager
                            .createStoredProcedureQuery("PKG_WMS.pr_addPostExchangeArchDetail")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, exch_ser_file_id)
                            .setParameter(4, cnt_id)
                            ;
                    DelActQuery.execute();
                }
                else {
                    InputStream excelFile = new BufferedInputStream(file.getInputStream());

                    MenuWMSSaleOrdersMainTemp clientsLoaderMainTemp = new MenuWMSSaleOrdersMainTemp();
                    Workbook workbook = new XSSFWorkbook(excelFile);
                    Sheet datatypeSheet = workbook.getSheetAt(0);
                    Iterator<Row> it = datatypeSheet.iterator();

                    //пропускаем 1-ю строку
                    if (it.hasNext()) {
                        Row row = it.next();
                    }
                    while (it.hasNext()) {
                        Row row = it.next();
                        clientsLoaderMainTemp.setRow_id((long) row.getRowNum());
                        clientsLoaderMainTemp.setDb_user_id(user_List.get(0).id);
                        clientsLoaderMainTemp.setDb_role_id(user_Role_List.get(0).role_id);

                        if (row.getCell(1) != null && row.getCell(1).toString() != "")
                            clientsLoaderMainTemp.setSender(row.getCell(1).toString());
                        else {
                            clientsLoaderMainTemp.setSender("");
                        }

                        if (row.getCell(2) != null && row.getCell(2).toString() != "")
                            clientsLoaderMainTemp.setSender_city(row.getCell(2).toString());
                        else {
                            clientsLoaderMainTemp.setSender_city("");
                        }

                        if (row.getCell(3) != null && row.getCell(3).toString() != "")
                            clientsLoaderMainTemp.setSender_branch(row.getCell(3).toString());
                        else {
                            clientsLoaderMainTemp.setSender_branch("");
                        }

                        if (row.getCell(4) != null && row.getCell(4).toString() != "") {
                            row.getCell(4).setCellType(CellType.STRING);
                            clientsLoaderMainTemp.setSender_phone(row.getCell(4).toString());
                        } else {
                            clientsLoaderMainTemp.setSender_branch("");
                        }

                        if (row.getCell(5) != null && row.getCell(5).toString() != "")
                            clientsLoaderMainTemp.setReciver(row.getCell(5).toString());
                        else {
                            clientsLoaderMainTemp.setReciver("");
                        }

                        if (row.getCell(6) != null && row.getCell(6).toString() != "")
                            clientsLoaderMainTemp.setReciver_city(row.getCell(6).toString());
                        else {
                            clientsLoaderMainTemp.setReciver_city("");
                        }

                        if (row.getCell(7) != null && row.getCell(7).toString() != "")
                            clientsLoaderMainTemp.setReciver_branch(row.getCell(7).toString());
                        else {
                            clientsLoaderMainTemp.setReciver_branch("");
                        }

                        if (row.getCell(8) != null && row.getCell(8).toString() != "") {
                            row.getCell(8).setCellType(CellType.STRING);
                            clientsLoaderMainTemp.setReciver_phone(row.getCell(8).toString());
                        } else {
                            clientsLoaderMainTemp.setReciver_phone("");
                        }

                        if (row.getCell(9) != null && row.getCell(9).toString() != "") {
                            row.getCell(9).setCellType(CellType.STRING);
                            clientsLoaderMainTemp.setTtn(row.getCell(9).toString());
                        } else {
                            clientsLoaderMainTemp.setTtn("");
                        }

                        if (row.getCell(10) != null && row.getCell(10).toString() != "")
                            clientsLoaderMainTemp.setItems(row.getCell(10).toString());
                        else {
                            clientsLoaderMainTemp.setItems("");
                        }

                        if (row.getCell(11) != null && row.getCell(11).toString() != "") {
                            row.getCell(11).setCellType(CellType.STRING);
                            clientsLoaderMainTemp.setSku(row.getCell(11).toString());
                        } else {
                            clientsLoaderMainTemp.setSku("");
                        }

                        if (row.getCell(12) != null && row.getCell(12).toString() != "")
                            clientsLoaderMainTemp.setCounts(row.getCell(12).toString());
                        else {
                            clientsLoaderMainTemp.setCounts("");
                        }

                        if (row.getCell(13) != null && row.getCell(13).toString() != "")
                            clientsLoaderMainTemp.setOrder_id(row.getCell(13).toString());
                        else {
                            clientsLoaderMainTemp.setOrder_id("");
                        }

                        menuWMSSaleOrdersMainTempRepository.save(clientsLoaderMainTemp);
                    }

                    StoredProcedureQuery DelActQuery = entityManager
                            .createStoredProcedureQuery("PKG_WMS.pr_addPostExchangeSaleOrderFromTemp")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id);
                    DelActQuery.execute();
                }
            } catch(IOException e){
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
