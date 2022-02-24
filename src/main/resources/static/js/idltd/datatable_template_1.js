function recreateDataTable(vinput_values) {
    moment.locale('ru'); // default the locale to English
    moment.defaultFormat = "DD.MM.YYYY HH:mm";

    let vtable = $(vinput_values.table);
    let vdata_table;

    let startIndex = vinput_values.dom.indexOf("f");
    let vtable_dom = vinput_values.dom === undefined ? 'frtip' :
        vinput_values.dom.slice(0, startIndex) + '<"'+vinput_values.table.substring(1)+' toolbar">' + vinput_values.dom.slice(startIndex);
    let responsive_detail_list;
    if(vinput_values.responsive_details === 'undefined' || vinput_values.responsive_details === false){
        responsive_detail_list = '"details": false;';
    }

    /*var vtable_dom = vinput_values.dom === undefined ? 'frtip' : '<"'+vinput_values.table.substring(1)+' toolbar">'+vinput_values.dom;*/
    /*  l - length changing input control
        f - filtering input
        t - The table!
        i - Table information summary
        p - pagination control
        r - processing display element
        B - Buttons
        S - Select  */

    if ($.fn.dataTable.isDataTable($(vinput_values.table))) {
        $(vinput_values.table).DataTable().destroy();
    }

    if (window.screen.width < 1024 && window.screen.height < 768) {
        vinput_values.pagingType = "simple";
        vinput_values.pageLength = 10;
    }

    datatable_option = {
        // "ajax": vinput_values.ajax,
        // "serverSide" : true,
        "ajax": function (data, callback, settings) {
            var jsonData = JSON.parse(JSON.stringify(vinput_values.ajax, function (key, value) {
                if (key == 'data') {
                    if (typeof value === "function") {
                        var d = {};
                        value(d);
                        return d;
                    } else {
                        return value;
                    }
                } else {
                    return value;
                }
            }));

            function afterReload(donedata) {
                var rowIndexes = [];
                var rowNum = 0;
                $(vinput_values.table).DataTable().rows(function (idx, data, node) {
                    if (data[vinput_values.key] === vinput_values.rowdata[vinput_values.key]) {
                        rowIndexes.push(idx);
                        rowNum = node.rowIndex;
                    }
                    return false;
                });
                $(vinput_values.table).DataTable().rows(rowIndexes).select();
                if (typeof vinput_values.switchControl !== 'undefined') {
                    vinput_values.switchControl();
                }
            }

            $.ajax(jsonData).done(function (donedata) {
                callback(donedata);
                afterReload(donedata);
            });

            //этот костыль надо убрать
            setTimeout(function () {
                $(vinput_values.table).DataTable().columns.adjust().draw();
            }, 300);
        },
        "initComplete": function (settings, json) {
            if (typeof vinput_values.initComplete !== 'undefined') {
                vinput_values.initComplete(vdata_table);
            }

            if (vinput_values.rowdata.table_order_column != null) {
                var search_param = '';
                if (vinput_values.rowdata.table_search != null && vinput_values.rowdata.table_search !== '') {
                    search_param = vinput_values.rowdata.table_search;
                }
                $(vinput_values.table).DataTable().page.len(vinput_values.rowdata.table_pagelen).search(search_param).order([vinput_values.rowdata.table_order_column, vinput_values.rowdata.table_order_type]);
                $(vinput_values.table ).DataTable().page(vinput_values.rowdata.table_page).draw(false);
            }

            if(typeof vinput_values.button_form !== 'undefined'){
                $(vinput_values.button_form).removeAttr('hidden').addClass('ml-2');
                $("."+vinput_values.table.substring(1)).prop('id', vinput_values.table.substring(1) + '_toolbar').append($(vinput_values.button_form));
            }

            var hideColumnNumber = 0;

            if ($(vinput_values.table).DataTable() != null && $(vinput_values.table).DataTable().rows().count() > 0 && vinput_values.columnSearch) {
                $(vinput_values.table + " thead tr:first").after('<tr role="row"></tr>');

                $(vinput_values.table + " thead tr:eq(1)").empty();
                this.api().columns().every(function () {
                    var column = this;
                    if(column.visible() !== true)
                    {
                        hideColumnNumber = hideColumnNumber + 1;
                    }

                    if ($(vinput_values.table + " thead tr:eq(0) th").eq(column.index()).css('display') !== 'none'
                        &&
                        $(vinput_values.table + " thead tr:eq(0) th").eq(column.index()).css('display') !== undefined
                    ) {
                        $(vinput_values.table + " thead tr:eq(1)").append('<th></th>');
                        if (vinput_values.columnSearchSelect.includes(column.index())
                        ){
                            var select = $('<select class="form-control select2" data-toggle="select2"><option value=""></option></select>')
                                .appendTo(
                                    $(vinput_values.table + " thead tr:eq(1) th").eq(column.index()).empty()
                                )
                                .on('change', function () {
                                    var val = $.fn.DataTable.util.escapeRegex($(this).val());
                                    if (val === "null") {
                                        column.search('^$', true, false).draw(false);
                                    } else {
                                        column.search(val ? '^' + val + '$' : '', true, false).draw(false);
                                    }
                                });

                            column.data().unique().sort().each(
                                function (d, j) {
                                    var name;
                                    if(d === null){ name = 'пусто'}
                                    else{ name = d }
                                    select.addClass("form-control").append('<option value="' + d + '">' + name + '</option>');
                                });
                        }
                    }
                });
            }
        },
        "rowCallback": vinput_values.rowCallback,
        "fnRowCallback": vinput_values.fnRowCallback,
        "orderCellsTop": typeof vinput_values.orderCellsTop === 'undefined' ? true : vinput_values.orderCellsTop,
        "fixedHeader": typeof vinput_values.fixedHeader === 'undefined' ? false : vinput_values.fixedHeader,
        "processing" : typeof vinput_values.processing === 'undefined' ? true : vinput_values.processing,
        "searching" : typeof vinput_values.searching === 'undefined' ? true : vinput_values.searching,
        "ordering": vinput_values.ordering,
        "order": vinput_values.order,
        "select": {
            style: typeof vinput_values.select_style === 'undefined' ? 'single' : vinput_values.select_style,
        },
        "keys": vinput_values.keys,
        "LengthChange": typeof vinput_values.LengthChange === 'undefined' ? true : vinput_values.LengthChange ,
        "lengthMenu": [
            [5, 10, 25, 50, 100, -1],
            [5, 10, 25, 50, 100, "All"]
        ],
        "pageLength": vinput_values.pageLength,
        "paging": typeof vinput_values.paging === 'undefined' ? true : vinput_values.paging,
        "pagingType": typeof vinput_values.pagingType === 'undefined' ? 'simple_numbers' : vinput_values.pagingType,
        "autoWidth": typeof vinput_values.autoWidth === 'undefined' ? true : vinput_values.autoWidth,
        //"scrollY": typeof vinput_values.scrollY === 'undefined' ? false : vinput_values.scrollY,
        //"scrollX": typeof vinput_values.scrollX === 'undefined' ? false : vinput_values.scrollX,
        //"scrollCollapse": typeof vinput_values.scrollCollapse === 'undefined' ? false : vinput_values.scrollCollapse,
        "responsive": typeof vinput_values.responsive === 'undefined' ? true : vinput_values.responsive,
        "createdRow": vinput_values.createdRow,
        "language":
            {
                "processing": '<i class="fa fa-spinner text-primary fa-spin fa-3x fa-fw"></i>',
                "search": "Поиск:",
                "lengthMenu": "Показать _MENU_ записей",
                "info": "Записи с _START_ до _END_ из (_TOTAL_)",
                "infoEmpty": "Записи с 0 до 0 из 0",
                "infoFiltered": "(_MAX_)",
                "infoPostFix": "",
                "loadingRecords": "Загрузка записей...",
                "zeroRecords": "Записи отсутствуют.",
                "emptyTable": "В таблице отсутствуют данные",
                "paginate": {
                    "first": "Первая",
                    "previous": "Предыдущая",
                    "next": "Следующая",
                    "last": "Последняя"
                },
                "aria": {
                    "sortAscending": ": активировать для сортировки столбца по возрастанию",
                    "sortDescending": ": активировать для сортировки столбца по убыванию"
                },
                "select": {
                    "rows": {
                        "_": " Выбрано записей: %d",
                        "0": " Выберите строку",
                        "1": " Выбрана одна запись"
                    }
                }
            },
        "columns": vinput_values.columns,
        "columnDefs": vinput_values.columnDefs,
        "fixedColumns" :  typeof vinput_values.fixedColumns === 'undefined' ? false : vinput_values.fixedColumns,
        "footerCallback": vinput_values.footerCallback,
        dom: vtable_dom,
        buttons: [
            {extend: 'copy', text: '<u>C</u>opy', key: { key: 'c', altKey: true}, className: 'copyButton',
                exportOptions: { columns: ':visible'}
            },
            {extend: 'excel', text: 'E<u>x</u>cel', key: { key: 'x', altKey: true},
                autoFilter: true, className: 'excelButton', footer: true,
                exportOptions: {
                    columns: "thead th:not(.noExport)",
                    orthogonal: 'export'
                }
            },
            {extend: 'pdf', text: '<u>P</u>df', key: { key: 'p', altKey: true}, className: 'pdfButton',
                exportOptions: { columns: ':visible'},
                customize : function(doc){
                    var colCount = new Array();
                    vtable.find('tbody tr:first-child td').each(function(){
                        if($(this).attr('colspan')){
                            for(var i=1;i<=$(this).attr('colspan');$i++){
                                colCount.push('*');
                            }
                        }else{ colCount.push('*'); }
                    });
                    doc.content[1].table.widths = colCount;
                }, footer: true, orientation: 'landscape'
            },
            {extend: 'print', text: 'P<u>r</u>int', key: { key: 'r', altKey: true}, className: 'printButton',
                exportOptions: { columns: ':visible'}, footer: true
            }
            //{extend: 'colvis', text: 'Column Vis', key: { key: 'v', altKey: true}, className: 'colvisButton'
            //}
        ]
    };

    if(vinput_values.scrollX){
        datatable_option.scrollX = true;
    }
    if(vinput_values.scrollY !== undefined){
        datatable_option.scrollY = vinput_values.scrollY;
    }
    if(vinput_values.scrollCollapse){
        datatable_option.scrollCollapse = true;
    }
    vdata_table = $(vinput_values.table).DataTable(datatable_option);

    $('.copyButton').removeClass('btn-secondary').addClass('btn btn-sm btn-outline-dark mb-1 ml-1');
    $('.excelButton').removeClass('btn-secondary').addClass('btn btn-sm btn-outline-dark mb-1');
    $('.pdfButton').removeClass('btn-secondary').addClass('btn btn-sm btn-outline-dark mb-1');
    $('.printButton').removeClass('btn-secondary').addClass('btn btn-sm btn-outline-dark mb-1');

    if(!vinput_values.copy_button) { vdata_table.buttons( '.copyButton' ).remove();}
    if(!vinput_values.excel_button) { vdata_table.buttons( '.excelButton' ).remove();}
    if(!vinput_values.pdf_button) { vdata_table.buttons( '.pdfButton' ).remove();}
    if(!vinput_values.print_button) { vdata_table.buttons( '.printButton' ).remove();}
    //if(!vinput_values.colvis_button) { vdata_table.buttons( '.colvisButton' ).remove();}

    vdata_table
        .off('select')
        .off('deselect')
        .on( 'select', function ( e, dt, type, indexes ) {
            var rowData = vdata_table.row( indexes ).data();
            Object.keys(vinput_values.rowdata).forEach(function(index) {
                vinput_values.rowdata[index] = rowData[index]
            });
            if (typeof vinput_values.switchControl !== 'undefined' ){ vinput_values.switchControl(); }
        })
        .on( 'deselect', function (e, dt, type, indexes) {
            Object.keys(vinput_values.rowdata).forEach(function(index) {
                vinput_values.rowdata[index] = null
            });
            if (typeof vinput_values.switchControl !== 'undefined' ){ vinput_values.switchControl(); }
        })
        .on( 'responsive-resize', function ( e, datatable, columns ) {
        });
}