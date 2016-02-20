var ajaxUrl = 'ajax/admin/users/';
var oTable;

function updateTable() {
    $.get(ajaxUrl, function (data) {
        updateTableByData(data);
    });
}

$(function () {
    oTable = $('#datatable').DataTable({
        ajax: {
            url: ajaxUrl,
            dataSrc: ""
        },
        lengthChange: false,
        /*"sSearch": false,*/
        columns: [
            {
                data: "name"
            },
            {
                data: "email",
                render: function (data, type) {
                    if (type == 'display') {
                        return '<a href="mailto:' + data + '">' + data + '</a>';
                    }
                    return data;
                }
            },
            {
                data: "roles",
                searchable: false
            },
            {
                data: "enabled",
                render: function (data, type, row) {
                    if (type == 'display') {
                        return '<input type="checkbox" ' + (data ? 'checked' : '') + ' onclick="enable($(this),' + row.id + ');"/>';
                    }
                    return data;
                }
            },
            {
                data: "registered",
                render: function (date, type) {
                    if (type == 'display') {
                        var dateObject = new Date(date);
                        return '<span>' + dateObject.toISOString().substring(0, 10) + '</span>';
                    }
                    return date;
                }
            },
            {
                defaultContent: "",
                orderable: false,
                render: renderEditBtn
            },
            {
                defaultContent: "",
                orderable: false,
                render: renderDeleteBtn
            }
        ],
        "order": [
            [0, "asc"]
        ],
        "createdRow": function (row, data, dataIndex) {
            if (!data.enabled) {
                $(row).css("text-decoration", "line-through");
            }
        },
        "initComplete": makeEditable
    });
    /*makeEditable();*/
    /*init();*/
});

/*
 function init() {
 $(':checkbox').each(function () {
 if (!$(this).is(":checked")) {
 $(this).closest('tr').css("text-decoration", "line-through");
 }
 });
 }*/
