function saveUser() {
    var user = {
        login: $('#loginEdit').val(),
        fullName: $('#fullNameEdit').val(),
        email: $('#emailEdit').val(),
        password: $('#passwordEdit').val()
    }
    var url = 'http://localhost:8080/api/user';
    console.log(user);
    $.ajax({
        url: url,
        type: 'post',
        dataType: 'json',
        contentType: 'application/json',
        success: function (data) {
            console.log(data.msg);
            $('#successInfo').show().text(data.message);
        },
        error: function (data) {
            $('#ErrorInfo').show().text(data.message);
        },
        data: JSON.stringify(user),
    });
}

function changePass() {
    $('#passwChange').val("Пароль").show();
}

function sellTicket(id) {
    var url = 'http://localhost:8080/api/user/records/sell?id='+id;
    $.ajax({
        url: url,
        type: 'get',
        dataType: 'json',
        contentType: 'application/json',
        success: function (data) {
            console.log(data);
            $('#successInfo').show().text("Запись отменена.");
        },
        error: function (data) {
            $('#ErrorInfo').show().text("Произошла ошибка при отмене =(");
        },
        data: null,
    });
}