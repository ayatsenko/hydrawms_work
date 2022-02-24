package com.idltd.hydramob.controller.client_docs;

import com.idltd.hydramob.entity.User_List;
import com.idltd.hydramob.entity.User_Role_List;
import com.idltd.hydramob.repository.User_ListRepository;
import com.idltd.hydramob.repository.User_Role_ListRepository;
import com.idltd.hydramob.repository.client_docs.MenuClientBillsFinanceListRepository;
import com.idltd.hydramob.repository.client_docs.MenuClientBillsListRepository;
import com.idltd.hydramob.repository.client_docs.MenuClientBillsPaymentsListRepository;
import com.idltd.hydramob.repository.clients.MenuClientDocumentsListRepository;
import com.idltd.hydramob.utils.JSONDatatable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/client_docs")
public class ClientDocsController {

    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;
    private MenuClientDocumentsListRepository menuClientDocumentsListRepository;
    private MenuClientBillsListRepository menuClientBillsListRepository;
    private MenuClientBillsPaymentsListRepository menuClientBillsPaymentsListRepository;
    private MenuClientBillsFinanceListRepository menuClientBillsFinanceListRepository;

    public ClientDocsController(
            User_ListRepository user_ListRepository,
            User_Role_ListRepository user_Role_ListRepository,
            MenuClientDocumentsListRepository menuClientDocumentsListRepository,
            MenuClientBillsListRepository menuClientBillsListRepository,
            MenuClientBillsPaymentsListRepository menuClientBillsPaymentsListRepository,
            MenuClientBillsFinanceListRepository menuClientBillsFinanceListRepository
    ) {
        this.user_ListRepository = user_ListRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;
        this.menuClientDocumentsListRepository = menuClientDocumentsListRepository;
        this.menuClientBillsListRepository = menuClientBillsListRepository;
        this.menuClientBillsPaymentsListRepository = menuClientBillsPaymentsListRepository;
        this.menuClientBillsFinanceListRepository = menuClientBillsFinanceListRepository;
    }

    @RequestMapping
    public ModelAndView index(ModelAndView model,
                              @RequestParam(name = "fin_id", required = false) Long fin_id,
                              @RequestParam(name = "cnt_id", required = false) Long cnt_id,

                              @RequestParam(name = "cnt_name", required = false) String cnt_name,
                              @RequestParam(name = "cnt_user_access", required = false) Long cnt_user_access,

                              @RequestParam(name = "clients_list_table_order_column", required = false) Long clients_list_table_order_column,
                              @RequestParam(name = "clients_list_table_order_type", required = false) String clients_list_table_order_type,
                              @RequestParam(name = "clients_list_table_search", required = false) String clients_list_table_search,
                              @RequestParam(name = "clients_list_table_pagelen", required = false) Long clients_list_table_pagelen,
                              @RequestParam(name = "clients_list_table_page", required = false) Long clients_list_table_page,

                              @RequestParam(name = "client_docs_list_table_order_column", required = false) Long client_docs_list_table_order_column,
                              @RequestParam(name = "client_docs_list_table_order_type", required = false) String client_docs_list_table_order_type,
                              @RequestParam(name = "client_docs_list_table_search", required = false) String client_docs_list_table_search,
                              @RequestParam(name = "client_docs_list_table_pagelen", required = false) Long client_docs_list_table_pagelen,
                              @RequestParam(name = "client_docs_list_table_page", required = false) Long client_docs_list_table_page,

                              @RequestParam(name = "client_docs_filter_id", required = false) Long client_docs_filter_id,
                              @RequestParam(name = "client_docs_filter_start_date", required = false) String client_docs_filter_start_date,
                              @RequestParam(name = "client_docs_filter_end_date", required = false) String client_docs_filter_end_date
    ){
        model.addObject("fin_id", fin_id);
        model.addObject("cnt_id", cnt_id);

        model.addObject("cnt_name", cnt_name);
        model.addObject("cnt_user_access", cnt_user_access);

        model.addObject("client_docs_list_table_order_column", client_docs_list_table_order_column);
        model.addObject("client_docs_list_table_order_type", client_docs_list_table_order_type);
        model.addObject("client_docs_list_table_search", client_docs_list_table_search);
        model.addObject("client_docs_list_table_pagelen", client_docs_list_table_pagelen);
        model.addObject("client_docs_list_table_page", client_docs_list_table_page);

        model.addObject("clients_list_table_order_column", clients_list_table_order_column);
        model.addObject("clients_list_table_order_type", clients_list_table_order_type);
        model.addObject("clients_list_table_search", clients_list_table_search);
        model.addObject("clients_list_table_pagelen", clients_list_table_pagelen);
        model.addObject("clients_list_table_page", clients_list_table_page);

        model.addObject("client_docs_filter_id", client_docs_filter_id);
        model.addObject("client_docs_filter_start_date", client_docs_filter_start_date);
        model.addObject("client_docs_filter_end_date", client_docs_filter_end_date);

        model.setViewName("client_docs/cover");
        return model;
    }

    @PostMapping("/get_client_doc_table")
    public JSONDatatable get_client_doc_table(
            @RequestParam(name = "cnt_id", required = false) Long cnt_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if (cnt_id != null && cnt_id > 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuClientDocumentsListRepository.queryClientDocumentsMenuListByUserID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, cnt_id
            ));
        }
        return result;
    }

    @PostMapping("/get_client_bills_table")
    public JSONDatatable get_client_bills_table(
            @RequestParam(name = "cnt_doc_id", required = false) Long cnt_doc_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if (cnt_doc_id != null && cnt_doc_id > 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuClientBillsListRepository.queryClientBillsMenuListByUserID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, cnt_doc_id
            ));
        }
        return result;
    }

    @PostMapping("/get_client_bills_payments_table")
    public JSONDatatable get_client_bills_payments_table(
            @RequestParam(name = "cnt_bill_id", required = false) Long cnt_bill_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if (cnt_bill_id != null && cnt_bill_id > 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuClientBillsPaymentsListRepository.queryClientBillsPaymentsMenuListByUserID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, cnt_bill_id
            ));
        }
        return result;
    }

    @PostMapping("/get_client_bills_finance_table")
    public JSONDatatable get_client_bills_finance_table(
            @RequestParam(name = "cnt_bill_id", required = false) Long cnt_bill_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if (cnt_bill_id != null && cnt_bill_id > 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuClientBillsFinanceListRepository.queryClientBillsFinanceMenuListByUserID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, cnt_bill_id
            ));
        }
        return result;
    }
}
