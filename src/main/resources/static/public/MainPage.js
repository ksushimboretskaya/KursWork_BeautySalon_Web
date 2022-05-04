function searchRecords() {
    $("#recordsMain").html("    <div class=\"modal-body\" id=\"tableLoad\">\n" +
        "        <div class=\"d-flex justify-content-center\">\n" +
        "            Идет загрузка данных, пожалуйста, ожидайте...\n" +
        "            <div class=\"spinner-border\" role=\"status\">\n" +
        "                <span class=\"sr-only\">Загрузка...</span>\n" +
        "            </div>\n" +
        "        </div>\n" +
        "    </div>").show();
    var date = $('#DateMain').val();
    var services = $('#select1').val();
    var employee = $('#select2').val();
    var url = 'http://localhost:8080/api/public/freeRecords?date=' +
        date + "&services=" + services + "&employee=" +
        employee;
    console.log(url)
    var html = "";
    $.ajax({
        url: url,
        dataType: "json",
        data: null,
        type: "GET",
        success: function (data) {
            for (var t in data) {
                if (data[t].freeRecords === undefined) {
                    $('#ErrorInfo').text("Поиск невозможен на дату в прошлом!").show().delay(5000).fadeOut();
                } else {
                    html += "            <article class=\"card\">\n" +
                        "                <section class=\"date\">\n" +
                        "                    <time datetime=\"23th feb\">\n" +
                        "                        <span>Запись</span>\n" +
                        "                        <span> <i class=\"fas fa-ruble-sign\"></i> " + data[t].freeRecords[t].employee.services.servicesPrice + "</span>\n" +
                        "                        <button onclick='buy(" + data[t].freeRecords[t].id + ")'" +
                        " type=\"button\" class=\"btn btn-success\"><i class=\"fas fa-shopping-cart\"></i> Записаться</button>\n" +
                        "                    </time>\n" +
                        "                </section>\n" +
                        "                <section class=\"card-cont\">\n";

                    html += "<small>Услуга</small>\n" +
                        "<h2><i class=\"fas fa-grin-hearts\" style=\"padding-right: 5px\"></i>"
                        + data[t].freeRecords[t].employee.services.servicesName + "</h2>";
                    html += "<small>Мастер</small>\n" +
                        "<h2><i class=\"fas fa-grin-hearts\" style=\"padding-right: 5px\"></i>"
                        + data[t].freeRecords[t].employee.fullName + "</h2>";
                    html += "                    </section>\n" +
                        "            </article><br><br>";
                }
            }
                $('#recordsMain').html(html).show();
                console.log("data", data);
                if (data[0] === undefined) {
                    $('#successInfo').text("Свободных окошек нет =(").show().delay(5000).fadeOut();
                }
                else if(data.error_code === 400) {
                    $('#successInfo').text("Попробуйте новый поиск =(").show().delay(5000).fadeOut();
                }else {
                    $('#successInfo').text("Свободные окошки найдены!").show().delay(5000).fadeOut();
                }

        },
        error: function (data) {
            $('#ErrorInfo').text("Ошибка. Проверьте, указали ли вы все поля?").show().delay(5000).fadeOut();
        },
    });
}

function buy(id) {
    var url = 'http://localhost:8080/api/public/records/buy?id=' + id;
    console.log(url)
    $.ajax({
        url: url,
        dataType: "json",
        data: null,
        type: "GET",
        success: function (data) {
            console.log(data);
            $('#successInfo').text("Вы записались!").show().delay(5000).fadeOut();
        },
        error: function (data) {
            $('#ErrorInfo').text("Для записи на услугу нужна авторизация!").show().delay(5000).fadeOut();
        },
    });
}