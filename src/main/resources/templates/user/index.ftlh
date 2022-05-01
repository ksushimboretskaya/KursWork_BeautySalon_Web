<#import "parts/page.ftlh" as page>
<#import "../parts/navbar.ftlh" as navbar>
<#import "parts/userPart.ftlh" as userP>
<@page.page "Личный кабинет">
    <@navbar.navbar 2,user.id != -1,user.isAdmin()></@navbar.navbar>
    <div class="grey-bg container-fluid">
        <section id="minimal-statistics">
            <div class="row">
                <div class="col-sm">
                    <@userP.editUserPart></@userP.editUserPart></div>
                <div class="col-sm">
                    <div class="modal-body" id="userTickets">
                        <div class="form-group" style="text-align: center">
                            <h1>Ваши записи:</h1>
                            <#list bookedRecords as ticket>
                                <article class="card">
                                    <section class="date">
                                        <time datetime="23th feb">
                                            <i class="fas fa-ruble-sign"></i>
                                            <small>Цена:</small>
                                            <p>${ticket.freeRecords.employee.services.servicesPrice}</p>
                                            <button onclick='sellTicket(${ticket.id})' type="button"
                                                    class="btn btn-danger">
                                                <i class="fas fa-trash-alt"></i> Отменить запись
                                            </button>
                                        </time>
                                    </section>
                                    <section>
                                        <div class=" card-cont">
                                            <h2>Название</h2>
                                            <span>${ticket.freeRecords.employee.services.servicesName}</span>
                                            <h2>Мастер</h2>
                                            <span>${ticket.freeRecords.employee.fullName}</span>
                                            <h2>Длительность</h2>
                                            <span>${ticket.freeRecords.employee.services.duration}</span>
                                        </div>
                                    </section>
                                </article><br><br>
                            </#list>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
    <script src="userPanel.js"></script>
</@page.page>