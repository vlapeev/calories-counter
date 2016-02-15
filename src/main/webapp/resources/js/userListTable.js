var ajaxUrl = 'ajax/admin/users/';
var oTable;

function updateTable() {
    $.get(ajaxUrl, function (data) {
        updateTableByData(data);
    });
}

$(function () {
    oTable = $('#datatable').DataTable({
        "sAjaxSource": ajaxUrl,
        "sAjaxDataProp": "",
        "bLengthChange": false,
        /*"sSearch": false,*/
        "aoColumns": [
            {"mData": "name"},
            {
                "mData": "email",
                "mRender": function (data, type) {
                    if (type == 'display') {
                        return '<a href="mailto:' + data + '">' + data + '</a>';
                    }
                    return data;
                }
            },
            {"mData": "roles", "bSearchable": false},
            {
                "mData": "enabled",
                "mRender": function (data, type, row) {
                    debugger;
                    if (type == 'display') {
                        return '<input type="checkbox" ' + (data ? 'checked' : '') + ' onclick="enable($(this),' + row.id + ');"/>';
                    }
                    return data;
                }
            },
            {
                "mData": "registered",
                "mRender": function (date, type) {
                    if (type == 'display') {
                        var dateObject = new Date(date);
                        return '<span>' + dateObject.toISOString().substring(0, 10) + '</span>';
                    }
                    return date;
                }
            },
            {"sDefaultContent": "", "bSortable": false, "mRender": renderEditBtn},
            {"sDefaultContent": "", "bSortable": false, "mRender": renderDeleteBtn}
        ],
        "aaSorting": [
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
