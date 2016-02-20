var ajaxUrl = 'ajax/profile/meals/';
var oTable;

function updateTable() {
    $.ajax({
        type: "POST",
        url: ajaxUrl + 'filter',
        data: $('#filter').serialize(),
        success: function (data) {
            updateTableByData(data);
        }
    });
    return false;
}

$(function () {
    oTable = $('#datatable').DataTable({
        ajax: {
            url: ajaxUrl,
            dataSrc: ""
        },
        lengthChange: false,
        search: false,
        columns: [
            {
                data: "dateTime",
                render: function (date, type, row) {
                    return date.replace('T', ' ');
                }
            },
            {
                data: "description"
            },
            {
                data: "calories"
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
        order: [
            [0, "desc"]
        ],
        createdRow: function (row, data, dataIndex) {
            $(row).addClass(data.exceed ? 'exceeded' : 'normal');
        },
        initComplete: function(settings, json){
            $('#filter').submit(function () {
                updateTable();
                return false;
            });

            /* datetime picker */

            var startDate = $('#startDate');
            var endDate = $('#endDate');
            startDate.datetimepicker({
                timepicker: false,
                format: 'Y-m-d',
                lang: 'ru',
                formatDate: 'Y-m-d',
                onShow: function (ct) {
                    this.setOptions({
                        maxDate: endDate.val() ? endDate.val() : false
                    })
                }
            });
            endDate.datetimepicker({
                timepicker: false,
                format: 'Y-m-d',
                lang: 'ru',
                formatDate: 'Y-m-d',
                onShow: function (ct) {
                    this.setOptions({
                        minDate: startDate.val() ? startDate.val() : false
                    })
                }
            });

            $('.time-picker').datetimepicker({
                datepicker: false,
                format: 'H:i',
                lang: 'ru'
            });

            $('#dateTime').datetimepicker({
                format: 'Y-m-d\\TH:i',
                lang: 'ru'
            });

            makeEditable();
        }
    });

});