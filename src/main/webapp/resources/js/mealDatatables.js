var ajaxUrl = "ajax/profile/meals/";
var datatableApi;

// $(document).ready(function () {
function clearFilter() {
    $("#filter")[0].reset();
    $.get(ajaxUrl, updateTableByData);
}

function updateTable() {
    $.ajax({
        type: "POST",
        url: ajaxUrl + "filter",
        data: $("#filter").serialize(),
        success: updateTableByData
    });
}

$(function () {
    $('#startDate').datetimepicker({format: 'YYYY-MM-DD'});
    $('#endDate').datetimepicker({format: 'YYYY-MM-DD'});
    $('#startTime').datetimepicker({format:'HH:mm'});
    $('#endTime').datetimepicker({format:'HH:mm'});
    $('#dateTime').datetimepicker({format:'YYY  Y-MM-DD\\THH:mm',sideBySide: true});

    datatableApi = $("#datatable").DataTable({
        "ajax": {
            "url": ajaxUrl,
            "dataSrc": ""
        },
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "dateTime",
                "render": function (data, type, row) {
                    return (moment(data).format("DD.MM.YYYY HH:mm"));
                }
            },
            {
                "data": "description"
            },
            {
                "data": "calories"
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderEditBtn
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderDeleteBtn
            }
        ],
        "order": [
            [
                0,
                "desc"
            ]
        ],
        "createdRow": function( row, data, dataIndex ) {
            if ( data["exceed"] == true ) {
                $(row).addClass( 'exceeded' );
            } else $(row).addClass( 'normal' );
        },
        "initComplete": makeEditable
    });
});
