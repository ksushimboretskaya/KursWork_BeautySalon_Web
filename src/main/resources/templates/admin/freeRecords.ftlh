<#import "../parts/page.ftlh" as page>
<#import "parts/navbar.ftlh" as navbar>
<#import "parts/modal.ftlh" as modal>
<@page.page "Админ-Панель">
    <@navbar.navbar 6></@navbar.navbar>
    <@modal.addmodal 'modalAdd', "записи">
        <div class="modal-body" id="readyToAdd">
            <div class="form-group">
                <label for="servicesAdd">Услуга</label>
                <select class="selectpicker" data-live-search="true" id="servicesAdd">
                        <#list services as service>
                        <option value="${service.id}">${service.servicesName + " " + service.servicesPrice}</option>
                        </#list>
                </select>
            </div>
                <div class="form-group">
                    <label for="employeeAdd">Мастер</label>
                    <select class="selectpicker" data-live-search="true" id="employeeAdd">
                        <#list employees as employee>
                            <option value="${employee.id}">${employee.fullName}</option>
                        </#list>
                    </select>
                </div>
<#--            <div class="form-group">-->
<#--                <label for="AirCompanyTAdd">Авиакомпания</label>-->
<#--                <select class="selectpicker" data-live-search="true" id="AirCompanyTAdd">-->
<#--                    <#list airlines as airline>-->
<#--                        <option value="${airline.id}">${airline.name}</option>-->
<#--                    </#list>-->
<#--                </select>-->
<#--            </div>-->
<#--            <div class="form-group">-->
<#--                <label for="LuggageTAdd">Тип багажа</label>-->
<#--                <select class="selectpicker" data-live-search="true" id="LuggageTAdd">-->
<#--                    <#list luggage as lug>-->
<#--                        <option value="${lug.id}">${lug.name}</option>-->
<#--                    </#list>-->
<#--                </select>-->
<#--            </div>-->
<#--            <div class="form-group">-->
<#--                <label for="FromTimeTAdd">Время отбытия</label>-->
<#--                <input name="id" type="datetime-local" class="form-control" id="FromTimeTAdd" aria-describedby="idHelp" placeholder="Время вылета">-->
<#--                <small id="idHelp" class="form-text text-muted">Запланированное время вылета.</small>-->
<#--            </div>-->
<#--            <div class="form-group">-->
<#--                <label for="ToTimeTAdd">Время прибытия</label>-->
<#--                <input name="id" type="datetime-local" class="form-control" id="ToTimeTAdd" aria-describedby="idHelp" placeholder="Время прилета">-->
<#--                <small id="idHelp" class="form-text text-muted">Запланированное время прилета.</small>-->
<#--            </div>-->
<#--            <div class="form-group">-->
<#--                <label for="TicketPriceAdd">Цена билета</label>-->
<#--                <input name="id" type="number" class="form-control" id="TicketPriceAdd" aria-describedby="idHelp" placeholder="Цена в рублях">-->
<#--                <small id="idHelp" class="form-text text-muted">Цена билета.</small>-->
<#--            </div>-->
<#--            <div class="form-group">-->
<#--                <label for="AmountOfTAdd">Количество билетов</label>-->
<#--                <input name="id" type="number" class="form-control" id="AmountOfTAdd" aria-describedby="idHelp" placeholder="Количество билетов">-->
<#--                <small id="idHelp" class="form-text text-muted">Максимальное количество билетов для продажи.</small>-->
<#--            </div>-->
        </div>
        <@modal.addmodalFooter "addFreeRecords()"></@modal.addmodalFooter>
    </@modal.addmodal>
    <@modal.modal 'modalEdit', "услуги">
        <div class="modal-body" id="readyToEdit">
            <div class="form-group">
                <label for="idEdit">ID</label>
                <input name="id" type="text" class="form-control" id="idEdit" aria-describedby="idHelp" placeholder="ID" disabled="disabled">
                <small id="idHelp" class="form-text text-muted">ID услуги позволяет идентифицировать ee в БД, его смена невозможна.</small>
            </div>
            <div class="form-group">
                <label for="servicesEdit">Услуга</label>
                <select class="selectpicker" data-live-search="true" id="servicesEdit">
                    <#list services as service>
                        <option value="${service.id}">${service.servicesName}</option>
                    </#list>
                </select>
            </div>
            <div class="form-group">
                <label for="employeeEdit">Мастер</label>
                <select class="selectpicker" data-live-search="true" id="destinationAirEdit">
                    <#list employees as employee>
                        <option value="${employee.id}">${employee.fullName}</option>
                    </#list>
                </select>
            </div>
<#--            <div class="form-group">-->
<#--                <label for="plainTEdit">Самолет</label>-->
<#--                <select class="selectpicker" data-live-search="true" id="plainTEdit">-->
<#--                    <#list planes as plane>-->
<#--                        <option value="${plane.id}">${plane.name}</option>-->
<#--                    </#list>-->
<#--                </select>-->
<#--            </div>-->
<#--            <div class="form-group">-->
<#--                <label for="AirCompanyTEdit">Авиакомпания</label>-->
<#--                <select class="selectpicker" data-live-search="true" id="AirCompanyTEdit">-->
<#--                    <#list airlines as airline>-->
<#--                        <option value="${airline.id}">${airline.name}</option>-->
<#--                    </#list>-->
<#--                </select>-->
<#--            </div>-->
<#--            <div class="form-group">-->
<#--                <label for="LuggageTEdit">Тип багажа</label>-->
<#--                <select class="selectpicker" data-live-search="true" id="LuggageTEdit">-->
<#--                    <#list luggage as lug>-->
<#--                        <option value="${lug.id}">${lug.name}</option>-->
<#--                    </#list>-->
<#--                </select>-->
<#--            </div>-->
<#--            <div class="form-group">-->
<#--                <label for="FromTimeTEdit">Время отбытия</label>-->
<#--                <input name="id" type="datetime-local" class="form-control" id="FromTimeTEdit" aria-describedby="idHelp" placeholder="Запланированное время вылета">-->
<#--                <small id="idHelp" class="form-text text-muted">Запланированное время вылета.</small>-->
<#--            </div>-->
<#--            <div class="form-group">-->
<#--                <label for="ToTimeTEdit">Время прибытия</label>-->
<#--                <input name="id" type="datetime-local" class="form-control" id="ToTimeTEdit" aria-describedby="idHelp" placeholder="Запланированное время прилета">-->
<#--                <small id="idHelp" class="form-text text-muted">Запланированное время прилета.</small>-->
<#--            </div>-->
<#--            <div class="form-group">-->
<#--                <label for="TicketPriceEdit">Цена билета</label>-->
<#--                <input name="id" type="number" class="form-control" id="TicketPriceEdit" aria-describedby="idHelp" placeholder="Цена в рублях">-->
<#--                <small id="idHelp" class="form-text text-muted">Цена билета.</small>-->
<#--            </div>-->
<#--            <div class="form-group">-->
<#--                <label for="AmountOfTEdit">Количество билетов</label>-->
<#--                <input name="id" type="number" class="form-control" id="AmountOfTEdit" aria-describedby="idHelp" placeholder="Количество билетов">-->
<#--                <small id="idHelp" class="form-text text-muted">Максимальное количество предметов для продажи.</small>-->
<#--            </div>-->
        </div>
        <@modal.modalFooter "saveFreeRecord()"></@modal.modalFooter>
    </@modal.modal>
    <div class="grey-bg container-fluid">
        <div class="row">
            <div class="col-12 mt-3 mb-1">
                <h4 class="text-uppercase">Записи.</h4>
                <div class="alert alert-success" role="alert" id="successInfo" style="display: none"></div>
                <div class="alert alert-success" role="alert" id="ErrorInfo" style="display: none"></div>
                <button type="button" class="btn btn-success" onclick="getFreeRecords();"><i class="fas fa-sync-alt"></i></button>
                <button type="button" class="btn btn-success" onclick="addDialog();"><i class="fas fa-plus"></i></button>
                <@modal.updatePanel "билетов"></@modal.updatePanel>
            </div>
        </div>
        <div class="row justify-content-center">
            <table class="table" style="text-align: center">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Дата</th>
                    <th scope="col">Услуга</th>
                    <th scope="col">Мастер</th>
                    <th scope="col">Управление</th>
                </tr>
                </thead>
                <tbody id="TicketsList">
                <#list freeRecords as freerec>
                    <tr>
                        <th scope="row">${freerec.id}</th>
                        <td>${freerec.date}</td>
                        <td>${freerec.employee.services.servicesName}</td>
                        <td>${freerec.employee.fullName}</td>
                        <td>
                            <button type="button" class="btn btn-primary btn-sm" onclick="editFreeRecords('${freerec.id}');"><i class="fas fa-pencil-alt"></i></button>
                            <button type="button" class="btn btn-danger btn-sm" onclick="removeFreeRecords('${freerec.id}');"><i class="fas fa-trash-alt"></i></button>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
    <script src="../adminPanel.js"></script>
</@page.page>