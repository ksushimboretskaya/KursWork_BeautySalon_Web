$(function () {
    console.log("ready!");
    $('#successInfo').hide();
    //getGeneralInfo();
});

function getGraph() {
    const config = {
        type: 'pie',
        data: data,
        options: {
            responsive: true,
            plugins: {
                legend: {
                    position: 'top',
                },
                title: {
                    display: true,
                    text: 'Chart.js Pie Chart'
                }
            }
        },
    };

}

function getGeneralInfo() {
    var url = 'http://localhost:8080/api/admin/info';
    $('#services').text("Загрузка..");
    $('#employee').text("Загрузка..");
    $('#bookedRecords').text("Загрузка..");
    $('#freeRecords').text("Загрузка..");
    $('#roles').text("Загрузка..");
    $('#users').text("Загрузка..");
    console.log(url)
    $.ajax({
        url: url,
        dataType: "json",
        data: null,
        type: "GET",
        success: function (data) {
            console.log(data);
            $('#services').text(data.services);
            $('#employee').text(data.employee);
            $('#bookedRecords').text(data.bookedRecords);
            $('#freeRecords').text(data.freeRecords);
            $('#roles').text(data.roles);
            $('#users').text(data.users);
            $('#successInfo').show().text("Данные успешно обновлены!").hide(500);
        },
        error: function (data) {
            $('#ErrorInfo').show().text(data.message);
        },
    });
}

function getUsers() {
    $('#UsersList').text("")
    $('#tableLoad').show();
    var url = 'http://localhost:8080/api/admin/users';
    var table = "<tr>"
    $.ajax({
        url: url,
        dataType: "json",
        data: null,
        type: "GET",
        success: function (data) {
            for (var user in data) {
                table += "<th scope=\"row\">" + data[user].id + "</th>";
                table += "<td>" + data[user].login + "</td>";
                table += "<td>" + data[user].fullName + "</td>";
                table += "<td>" + data[user].email + "</td>";
                if (data[user].active === true) {
                    table += "<td>Да</td>";
                } else {
                    table += "<td>Нет</td>";
                }
                var roles = data[user].roles;
                table += "<td>";
                for (var role in roles) {
                    table += roles[role].name + " ";
                }
                table += "</td>";
                table += "<td>\n" +
                    "        <button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"editUser(" + data[user].id + ");\"><i class=\"fas fa-user-edit\"></i></button>\n" +
                    "        <button type=\"button\" class=\"btn btn-danger btn-sm\" onclick=\"removeUser(" + data[user].id + ");\"><i class=\"fas fa-user-slash\"></i></button>\n" +
                    "    </td></tr>";
                $('#tableLoad').hide();
                $('#UsersList').html(table);
                console.log(data);
            }
            $('#successInfo').show().text("Данные успешно обновлены!");
        },
        error: function (data) {
            $('#ErrorInfo').show().text(data.message);
        },
    });
}

function getServices() {
    $('#ServicesList').text("")
    $('#tableLoad').show();
    var url = 'http://localhost:8080/api/admin/services';
    var table = "<tr>"
    $.ajax({
        url: url,
        dataType: "json",
        data: null,
        type: "GET",
        success: function (data) {
            for (var field in data) {
                table += "<th scope=\"row\">" + data[field].id + "</th>";
                table += "<td>" + data[field].servicesName + "</td>";
                table += "<td>" + data[field].servicesPrice + "</td>";
                table += "<td>" + data[field].duration + "</td>";
                table += "<td>\n" +
                    "        <button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"editServices(" + data[field].id + ");\"><i class=\"fas fa-pencil-alt\"></i></button>\n" +
                    "        <button type=\"button\" class=\"btn btn-danger btn-sm\" onclick=\"removeServices(" + data[field].id + ");\"><i class=\"fas fa-trash-alt\"></i></button>\n" +
                    "    </td></tr>";
                $('#tableLoad').hide();
                $('#ServicesList').html(table);
                console.log(data);
            }
            $('#successInfo').show().text("Данные успешно обновлены!");
        },
        error: function (data) {
            $('#ErrorInfo').show().text(data.message);
        },
    });
}

function getEmployee() {
    $('#EmployeeList').text("")
    $('#tableLoad').show();
    var url = 'http://localhost:8080/api/admin/employee';
    var table = "<tr>"
    $.ajax({
        url: url,
        dataType: "json",
        data: null,
        type: "GET",
        success: function (data) {
            for (var field in data) {
                table += "<th scope=\"row\">" + data[field].id + "</th>";
                table += "<td>" + data[field].fullName + "</td>";
                table += "<td>" + data[field].services_id + "</td>";
                table += "<td>\n" +
                    "        <button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"editEmployee(" + data[field].id + ");\"><i class=\"fas fa-pencil-alt\"></i></button>\n" +
                    "        <button type=\"button\" class=\"btn btn-danger btn-sm\" onclick=\"editEmployee(" + data[field].id + ");\"><i class=\"fas fa-trash-alt\"></i></button>\n" +
                    "    </td></tr>";
                $('#tableLoad').hide();
                $('#EmployeeList').html(table);
                console.log(data);
            }
            $('#successInfo').show().text("Данные успешно обновлены!");
        },
        error: function (data) {
            $('#ErrorInfo').show().text(data.message);
        },
    });
}

function getBRecords() {
    $('#BRecordsList').text("")
    $('#tableLoad').show();
    var url = 'http://localhost:8080/api/admin/bookedRecords';
    var table = "<tr>"
    $.ajax({
        url: url,
        dataType: "json",
        data: null,
        type: "GET",
        success: function(data) {
            for(var field in data) {
                table += "<th scope=\"row\">" + data[field].id + "</th>";
                table += "<td>" + data[field].freeRecords.date + "</td>";
                table += "<td>" + data[field].user.fullName + "</td>";
                table += "<td>" + data[field].employee.services.servicesName + "</td>";
                table += "<td>" + data[field].employee.services.servicesCost + "</td>";
                table += "<td>\n" +
                    "        <button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"editBTicket(" + data[field].id + ");\"><i class=\"fas fa-pencil-alt\"></i></button>\n" +
                    "        <button type=\"button\" class=\"btn btn-danger btn-sm\" onclick=\"removeBTicket(" + data[field].id + ");\"><i class=\"fas fa-trash-alt\"></i></button>\n" +
                    "    </td></tr>";
                $('#tableLoad').hide();
                $('#BRecordsList').html(table);
                console.log(data);
            }
            $('#successInfo').show().text("Данные успешно обновлены!");
        },
        error: function (data) {
            $('#ErrorInfo').show().text(data.message);
        },
    });
}

// function getCountries() {
//     $('#CountriesList').text("")
//     $('#tableLoad').show();
//     var url = 'http://localhost:8080/api/admin/countries';
//     var table = "<tr>"
//     $.ajax({
//         url: url,
//         dataType: "json",
//         data: null,
//         type: "GET",
//         success: function(data) {
//             for(var field in data) {
//                 table += "<th scope=\"row\">" + data[field].id + "</th>";
//                 table += "<td>" + data[field].name + "</td>";
//                 table += "<td>\n" +
//                     "        <button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"editCountry(" + data[field].id + ");\"><i class=\"fas fa-pencil-alt\"></i></button>\n" +
//                     "        <button type=\"button\" class=\"btn btn-danger btn-sm\" onclick=\"removeCountry(" + data[field].id + ");\"><i class=\"fas fa-trash-alt\"></i></button>\n" +
//                     "    </td></tr>";
//                 $('#tableLoad').hide();
//                 $('#CountriesList').html(table);
//                 console.log(data);
//             }
//             $('#successInfo').show().text("Данные успешно обновлены!");
//         },
//         error: function (data) {
//             $('#ErrorInfo').show().text(data.message);
//         },
//     });
// }

// function getLuggage() {
//     $('#LuggageList').text("")
//     $('#tableLoad').show();
//     var url = 'http://localhost:8080/api/admin/luggage';
//     var table = "<tr>"
//     $.ajax({
//         url: url,
//         dataType: "json",
//         data: null,
//         type: "GET",
//         success: function(data) {
//             for(var field in data) {
//                 table += "<th scope=\"row\">" + data[field].id + "</th>";
//                 table += "<td>" + data[field].name + "</td>";
//                 table += "<td>\n" +
//                     "        <button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"editLuggage(" + data[field].id + ");\"><i class=\"fas fa-pencil-alt\"></i></button>\n" +
//                     "        <button type=\"button\" class=\"btn btn-danger btn-sm\" onclick=\"removeLuggage(" + data[field].id + ");\"><i class=\"fas fa-trash-alt\"></i></button>\n" +
//                     "    </td></tr>";
//                 $('#tableLoad').hide();
//                 $('#LuggageList').html(table);
//                 console.log(data);
//             }
//             $('#successInfo').show().text("Данные успешно обновлены!");
//         },
//         error: function (data) {
//             $('#ErrorInfo').show().text(data.message);
//         },
//     });
// }
// function getPlanes() {
//     $('#PlanesList').text("")
//     $('#tableLoad').show();
//     var url = 'http://localhost:8080/api/admin/planes';
//     var table = "<tr>"
//     $.ajax({
//         url: url,
//         dataType: "json",
//         data: null,
//         type: "GET",
//         success: function(data) {
//             for(var field in data) {
//                 table += "<th scope=\"row\">" + data[field].id + "</th>";
//                 table += "<td>" + data[field].name + "</td>";
//                 table += "<td>\n" +
//                     "        <button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"editPlane(" + data[field].id + ");\"><i class=\"fas fa-pencil-alt\"></i></button>\n" +
//                     "        <button type=\"button\" class=\"btn btn-danger btn-sm\" onclick=\"removePlane(" + data[field].id + ");\"><i class=\"fas fa-trash-alt\"></i></button>\n" +
//                     "    </td></tr>";
//                 $('#tableLoad').hide();
//                 $('#PlanesList').html(table);
//                 console.log(data);
//             }
//             $('#successInfo').show().text("Данные успешно обновлены!");
//         },
//         error: function (data) {
//             $('#ErrorInfo').show().text(data.message);
//         },
//     });
// }

function getRoles() {
    $('#rolesList').text("")
    $('#tableLoad').show();
    var url = 'http://localhost:8080/api/admin/roles';
    var table = "<tr>"
    $.ajax({
        url: url,
        dataType: "json",
        data: null,
        type: "GET",
        success: function (data) {
            for (var field in data) {
                table += "<th scope=\"row\">" + data[field].id + "</th>";
                table += "<td>" + data[field].name + "</td>";
                table += "<td>\n" +
                    "        <button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"editRole(" + data[field].id + ");\"><i class=\"fas fa-pencil-alt\"></i></button>\n" +
                    "        <button type=\"button\" class=\"btn btn-danger btn-sm\" onclick=\"removeRole(" + data[field].id + ");\"><i class=\"fas fa-trash-alt\"></i></button>\n" +
                    "    </td></tr>";
                $('#tableLoad').hide();
                $('#rolesList').html(table);
                console.log(data);
            }
            $('#successInfo').show().text("Данные успешно обновлены!");
        },
        error: function (data) {
            $('#ErrorInfo').show().text(data.message);
        },
    });
}

function getFreeRecords() {
    $('#RecordsList').text("")
    $('#tableLoad').show();
    var url = 'http://localhost:8080/api/admin/records';
    var table = "<tr>"
    $.ajax({
        url: url,
        dataType: "json",
        data: null,
        type: "GET",
        success: function(data) {
            for(var field in data) {
                table += "<th scope=\"row\">" + data[field].id + "</th>";
                table += "<td>" + data[field].date + "</td>";
                table += "<td>" + data[field].employee.fullName + "</td>";
                table += "<td>" + data[field].employee.services.servicesName + "</td>";
                table += "<td>" + data[field].employee.servicesCost + "</td>";
                table += "<td>\n" +
                    "        <button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"editRecords(" + data[field].id + ");\"><i class=\"fas fa-pencil-alt\"></i></button>\n" +
                    "        <button type=\"button\" class=\"btn btn-danger btn-sm\" onclick=\"removeRecords(" + data[field].id + ");\"><i class=\"fas fa-trash-alt\"></i></button>\n" +
                    "    </td></tr>";
                $('#tableLoad').hide();
                $('#RecordsList').html(table);
                console.log(data);
            }
            $('#successInfo').show().text("Данные успешно обновлены!");
        },
        error: function (data) {
            $('#ErrorInfo').show().text(data.message);
        },
    });
}

function editUser(id) {
    $('#readyToEdit').hide();
    $('#waitingToEdit').show();
    $('#userEdit').modal();
    var url = 'http://localhost:8080/api/admin/users/' + id;
    $('#idEdit').val(id);
    $.ajax({
        url: url,
        dataType: "json",
        data: null,
        type: "GET",
        success: function (data) {
            console.log(data);
            $('#loginEdit').val(data.login);
            $('#fullNameEdit').val(data.fullName);
            $('#emailEdit').val(data.email);
            if (data.active === true) {
                $('#activeEdit').val("1").change();
            } else {
                $('#activeEdit').val("0").change();
            }
            $('#waitingToEdit').hide();
            $('#readyToEdit').show();
        },
        error: function (data) {
            $('#ErrorInfo').show().text(data.message);
        },
    });
}

function saveUser() {
    var act;
    act = $('#activeEdit').val() === "1";
    var rolesq = [];
    var arr = $('#rolesEditU').val();
    for (var role in arr) {
        var trueRole = {
            id: arr[role]
        }
        rolesq.push(trueRole);
    }
    var user = {
        id: $('#idEdit').val(),
        login: $('#loginEdit').val(),
        fullName: $('#fullNameEdit').val(),
        email: $('#emailEdit').val(),
        roles: rolesq,
        active: act,
        password: "nope"
    }
    var url = 'http://localhost:8080/api/admin/users/' + user.id;
    console.log(user);
    $.ajax({
        url: url,
        type: 'post',
        dataType: 'json',
        contentType: 'application/json',
        success: function (data) {
            console.log(data.msg);
            getUsers();
            $('#successInfo').show().text(data.message);
        },
        error: function (data) {
            $('#ErrorInfo').show().text(data.message);
        },
        data: JSON.stringify(user),
    });
}

function removeUser(id) {
    var url = 'http://localhost:8080/api/admin/users/' + id;
    $.ajax({
        url: url,
        dataType: "json",
        data: null,
        type: "DELETE",
        success: function (data) {
            getUsers();
            $('#successInfo').show().text(data.message);
        }
    });
}

function addDialog() {
    $('#readyToAdd').show();
    $('#waitingToEdit').hide();
    $('#modalAdd').modal();
}

function addUser() {
    var act = $('#activeEdit').val() === "1";
    var rolesq = [];
    var arr = $('#rolesAddU').val();
    for (var role in arr) {
        var trueRole = {
            id: arr[role]
        }
        rolesq.push(trueRole);
    }
    var user = {
        login: $('#loginAdd').val(),
        fullName: $('#fullNameAdd').val(),
        email: $('#emailAdd').val(),
        roles: rolesq,
        active: act,
        password: $('#passwordUAdd').val()
    }
    var url = 'http://localhost:8080/api/admin/users/';
    $.ajax({
        url: url,
        dataType: "json",
        type: "PUT",
        contentType: 'application/json',
        success: function (data) {
            getUsers();
            $('#successInfo').show().text(data.message);
        },
        error: function (data) {
            $('#ErrorInfo').show().text(data.message);
        },
        data: JSON.stringify(user),
    });
}

function addServices() {
    var services = {
        servicesName: $('#ServicesNameAdd').val(),
        servicesCost: $('#ServicesCostAdd').val(),
        duration: $('#DurationAdd').val()
    }
    var url = 'http://localhost:8080/api/admin/services/';
    $.ajax({
        url: url,
        dataType: "json",
        type: "PUT",
        contentType: 'application/json',
        success: function(data) {
            getServices();
            $('#successInfo').show().text(data.message);
        },
        error: function (data) {
            $('#ErrorInfo').show().text(data.message);
        },
        data: JSON.stringify(services),
    });
}

function editServices(id) {
    $('#saveB').prop('disabled', true);
    $('#readyToEdit').hide();
    $('#waitingToEdit').show();
    $('#modalEdit').modal();
    var url = 'http://localhost:8080/api/admin/services/' + id;
    $('#idEdit').val(id);
    $.ajax({
        url: url,
        dataType: "json",
        data: null,
        type: "GET",
        success: function(data) {
            console.log(data);
            $('#ServicesNameEdit').val(data.servicesName);
            $('#ServicesCostEdit').val(data.servicesCost);
            $('#DurationEdit').val(data.duration);
            $('#waitingToEdit').hide();
            $('#readyToEdit').show();
            $('#saveB').prop('disabled', false);
        },
        error: function (data) {
            $('#ErrorInfo').show().text(data.message);
        },
    });
}

function saveServices() {
    var Services = {
        id: $('#idEdit').val(),
        servicesName: $('#ServicesNameEdit').val(),
        servicesCost: $('#ServicesCostEdit').val(),
        duration: $('#DurationEdit').val()
    }
    var url = 'http://localhost:8080/api/admin/services/' + Services.id;
    console.log(Services);
    $.ajax({
        url: url,
        type: 'post',
        dataType: 'json',
        contentType: 'application/json',
        success: function (data) {
            console.log(data.msg);
            getServices();
            $('#successInfo').show().text(data.message);
        },
        error: function (data) {
            $('#ErrorInfo').show().text(data.message);
        },
        data: JSON.stringify(Services),
    });
}

function removeServices(id) {
    var url = 'http://localhost:8080/api/admin/services/' + id;
    $.ajax({
        url: url,
        dataType: "json",
        data: null,
        type: "DELETE",
        success: function(data) {
            getServices();
            $('#successInfo').show().text(data.message);
        },
        error: function (data) {
            $('#ErrorInfo').show().text(data.message);
        },
    });
}

function addRole() {
    var role = {
        role: $('#roleAddName').val()
    }
    var url = 'http://localhost:8080/api/admin/roles/';
    $.ajax({
        url: url,
        dataType: "json",
        type: "PUT",
        contentType: 'application/json',
        success: function (data) {
            getRoles();
            $('#successInfo').show().text(data.message);
        },
        error: function (data) {
            $('#ErrorInfo').show().text(data.message);
        },
        data: JSON.stringify(role),
    });
}

function editRole(id) {
    $('#saveB').prop('disabled', true);
    $('#readyToEdit').hide();
    $('#waitingToEdit').show();
    $('#modalEdit').modal();
    var url = 'http://localhost:8080/api/admin/roles/' + id;
    $('#idEdit').val(id);
    $.ajax({
        url: url,
        dataType: "json",
        data: null,
        type: "GET",
        success: function (data) {
            console.log(data);
            $('#roleEditName').val(data.role);
            $('#waitingToEdit').hide();
            $('#readyToEdit').show();
            $('#saveB').prop('disabled', false);
        },
        error: function (data) {
            $('#ErrorInfo').show().text(data.message);
        },
    });
}

function saveRole() {
    var Role = {
        id: $('#idEdit').val(),
        role: $('#roleEditName').val()
    }
    var url = 'http://localhost:8080/api/admin/roles/' + Role.id;
    console.log(Role);
    $.ajax({
        url: url,
        type: 'post',
        dataType: 'json',
        contentType: 'application/json',
        success: function (data) {
            getRoles();
            $('#successInfo').show().text(data.message);
        },
        error: function (data) {
            $('#ErrorInfo').show().text(data.message);
        },
        data: JSON.stringify(Role),
    });
}

function removeRole(id) {
    var url = 'http://localhost:8080/api/admin/roles/' + id;
    $.ajax({
        url: url,
        dataType: "json",
        data: null,
        type: "DELETE",
        success: function (data) {
            getRoles();
            $('#successInfo').show().text(data.message);
        },
        error: function (data) {
            $('#ErrorInfo').show().text(data.message);
        },
    });
}

function addEmployee() {
    var employee = {
        name: $('#EmployeeNameAdd').val(),
        serv:$('#ServicesNameAdd').val()
    }
    var url = 'http://localhost:8080/api/admin/employee/';
    $.ajax({
        url: url,
        dataType: "json",
        type: "PUT",
        contentType: 'application/json',
        success: function(data) {
            getEmployee();
            $('#successInfo').show().text(data.message);
        },
        error: function (data) {
            $('#ErrorInfo').show().text(data.message);
        },
        data: JSON.stringify(employee),
    });
}

// function editCountry(id) {
//     $('#saveB').prop('disabled', true);
//     $('#readyToEdit').hide();
//     $('#waitingToEdit').show();
//     $('#modalEdit').modal();
//     var url = 'http://localhost:8080/api/admin/countries/' + id;
//     $('#idEdit').val(id);
//     $.ajax({
//         url: url,
//         dataType: "json",
//         data: null,
//         type: "GET",
//         success: function(data) {
//             console.log(data);
//             $('#CountryNameEdit').val(data.name);
//             $('#waitingToEdit').hide();
//             $('#readyToEdit').show();
//             $('#saveB').prop('disabled', false);
//         },
//         error: function (data) {
//             $('#ErrorInfo').show().text(data.message);
//         },
//     });
// }
// function saveCountry() {
//     var Country = {
//         id: $('#idEdit').val(),
//         name: $('#CountryNameEdit').val()
//     }
//     var url = 'http://localhost:8080/api/admin/countries/' + Country.id;
//     console.log(Country);
//     $.ajax({
//         url: url,
//         type: 'post',
//         dataType: 'json',
//         contentType: 'application/json',
//         success: function (data) {
//             getCountries();
//             $('#successInfo').show().text(data.message);
//         },
//         error: function (data) {
//             $('#ErrorInfo').show().text(data.message);
//         },
//         data: JSON.stringify(Country),
//     });
// }
// function removeCountry(id) {
//     var url = 'http://localhost:8080/api/admin/countries/' + id;
//     $.ajax({
//         url: url,
//         dataType: "json",
//         data: null,
//         type: "DELETE",
//         success: function(data) {
//             getCountries();
//             $('#successInfo').show().text(data.message);
//         },
//         error: function (data) {
//             $('#ErrorInfo').show().text(data.message);
//         },
//     });
// }

// function addTicket() {
//     var departure_airport = {
//         id: $('#departureAirAdd').val()
//     }
//     var destination_airport = {
//         id: $('#destinationAirAdd').val()
//     }
//     var luggage_type = {
//         id: $('#LuggageTAdd').val()
//     }
//     var plane = {
//         id: $('#plainTAdd').val()
//     }
//     var airline = {
//         id: $('#AirCompanyTAdd').val()
//     }
//     var ticket = {
//         airline: airline,
//         plane: plane,
//         departureAirport: departure_airport,
//         destinationAirport: destination_airport,
//         luggage: luggage_type,
//         price: $('#TicketPriceAdd').val(),
//         departureTime: $('#FromTimeTAdd').val(),
//         arrivalTime: $('#ToTimeTAdd').val(),
//         ticketsAmount: $('#AmountOfTAdd').val()
//     }
//     console.log(ticket);
//     var url = 'http://localhost:8080/api/admin/tickets/';
//     $.ajax({
//         url: url,
//         dataType: "json",
//         type: "PUT",
//         contentType: 'application/json',
//         success: function(data) {
//             getTickets();
//             $('#successInfo').show().text(data.message);
//         },
//         error: function (data) {
//             $('#ErrorInfo').show().text(data.message);
//         },
//         data: JSON.stringify(ticket),
//     });
// }
// function editTicket(id) {
//     $('#saveB').prop('disabled', true);
//     $('#readyToEdit').hide();
//     $('#waitingToEdit').show();
//     $('#modalEdit').modal();
//     var url = 'http://localhost:8080/api/admin/tickets/' + id;
//     $('#idEdit').val(id);
//     $.ajax({
//         url: url,
//         dataType: "json",
//         data: null,
//         type: "GET",
//         success: function(data) {
//             console.log(data);
//             $('#departureAirEdit').val(data.departureAirport.id).text(data.departureAirport.name);
//             $('#destinationAirEdit').val(data.destinationAirport.id).text(data.destinationAirport.name);
//             $('#LuggageTEdit').val(data.luggage.id).text(data.luggage.name);
//             $('#plainTEdit').val(data.plane.id).text(data.plane.name);
//             $('#AirCompanyTEdit').val(data.airline.id).text(data.airline.name);
//             $('#TicketPriceEdit').val(data.price);
//             $('#FromTimeTEdit').val(data.departureTime);
//             $('#ToTimeTEdit').val(data.arrivalTime);
//             $('#AmountOfTEdit').val(data.ticketsAmount);
//             $('#waitingToEdit').hide();
//             $('#readyToEdit').show();
//             $('#saveB').prop('disabled', false);
//         },
//         error: function (data) {
//             $('#ErrorInfo').show().text(data.message);
//         },
//     });
// }
// function saveTicket() {
//     var departure_airport = {
//         id: $('#departureAirEdit').val()
//     }
//     var destination_airport = {
//         id: $('#destinationAirEdit').val()
//     }
//     var luggage_type = {
//         id: $('#LuggageTEdit').val()
//     }
//     var plane = {
//         id: $('#plainTEdit').val()
//     }
//     var airline = {
//         id: $('#AirCompanyTEdit').val()
//     }
//     var ticket = {
//         id: $('#idEdit').val(),
//         airline: airline,
//         plane: plane,
//         departureAirport: departure_airport,
//         destinationAirport: destination_airport,
//         luggage: luggage_type,
//         price: $('#TicketPriceEdit').val(),
//         departureTime: $('#FromTimeTEdit').val(),
//         arrivalTime: $('#ToTimeTEdit').val(),
//         ticketsAmount: $('#AmountOfTEdit').val()
//     }
//
//     var url = 'http://localhost:8080/api/admin/tickets/' + ticket.id;
//     console.log(ticket);
//     $.ajax({
//         url: url,
//         type: 'post',
//         dataType: 'json',
//         contentType: 'application/json',
//         success: function (data) {
//             getTickets();
//             $('#successInfo').show().text(data.message);
//         },
//         error: function (data) {
//             $('#ErrorInfo').show().text(data.message);
//         },
//         data: JSON.stringify(ticket),
//     });
// }
// function removeTicket(id) {
//     var url = 'http://localhost:8080/api/admin/tickets/' + id;
//     $.ajax({
//         url: url,
//         dataType: "json",
//         data: null,
//         type: "DELETE",
//         success: function(data) {
//             getTickets();
//             $('#successInfo').show().text(data.message);
//         },
//         error: function (data) {
//             $('#ErrorInfo').show().text(data.message);
//         },
//     });
// }
//
// function addBTicket() {
//     var user = {
//         id: $('#userBAdd').val()
//     }
//     var ticket = {
//         id: $('#ticketBAdd').val()
//     }
//     var Bticket = {
//         user: user,
//         ticket: ticket,
//         price: $('#priceBAdd').val()
//     }
//     console.log(Bticket);
//     var url = 'http://localhost:8080/api/admin/bookedtickets/';
//     $.ajax({
//         url: url,
//         dataType: "json",
//         type: "PUT",
//         contentType: 'application/json',
//         success: function(data) {
//             getBTickets();
//             $('#successInfo').show().text(data.message);
//         },
//         error: function (data) {
//             $('#ErrorInfo').show().text(data.message);
//         },
//         data: JSON.stringify(Bticket),
//     });
// }
// function editBTicket(id) {
//     $('#saveB').prop('disabled', true);
//     $('#readyToEdit').hide();
//     $('#waitingToEdit').show();
//     $('#modalEdit').modal();
//     var url = 'http://localhost:8080/api/admin/bookedtickets/' + id;
//     $('#idEdit').val(id);
//     $.ajax({
//         url: url,
//         dataType: "json",
//         data: null,
//         type: "GET",
//         success: function(data) {
//             console.log(data);
//             $('#ticketBEdit').val(data.ticket.id);
//             $('#userBEdit').val(data.user.id);
//             $('#priceBEdit').val(data.price);
//             $('#boughtTimeB').val(data.time);
//             $('#waitingToEdit').hide();
//             $('#readyToEdit').show();
//             $('#saveB').prop('disabled', false);
//         },
//         error: function (data) {
//             $('#ErrorInfo').show().text(data.message);
//         },
//     });
// }
// function saveBTicket() {
//     var user = {
//         id: $('#userBEdit').val()
//     }
//     var ticket = {
//         id: $('#ticketBEdit').val()
//     }
//     var Bticket = {
//         id: $('#idEdit').val(),
//         user: user,
//         ticket: ticket,
//         price: $('#priceBEdit').val()
//     }
//
//     var url = 'http://localhost:8080/api/admin/bookedtickets/' + Bticket.id;
//     console.log(Bticket);
//     $.ajax({
//         url: url,
//         type: 'post',
//         dataType: 'json',
//         contentType: 'application/json',
//         success: function (data) {
//             console.log(data.msg);
//             getBTickets();
//             $('#successInfo').show().text(data.message);
//         },
//         error: function (data) {
//             $('#ErrorInfo').show().text(data.message);
//         },
//         data: JSON.stringify(Bticket),
//     });
// }
// function removeBTicket(id) {
//     var url = 'http://localhost:8080/api/admin/bookedtickets/' + id;
//     $.ajax({
//         url: url,
//         dataType: "json",
//         data: null,
//         type: "DELETE",
//         success: function(data) {
//             getBTickets();
//             $('#successInfo').show().text(data.message);
//         },
//         error: function (data) {
//             $('#ErrorInfo').show().text(data.message);
//         },
//     });
//}