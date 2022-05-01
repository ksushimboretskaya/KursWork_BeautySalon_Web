<#import "../parts/page.ftlh" as page>
<#import "parts/navbar.ftlh" as navbar>
<#import "parts/modal.ftlh" as modal>
<@page.page "Админ-Панель">
    <@navbar.navbar 4></@navbar.navbar>
    <@modal.addmodal 'modalAdd', "мастера">
        <div class="modal-body" id="readyToAdd">
            <div class="form-group">
                <label for="EmployeeNameAdd">ФИО мастера</label>
                <input name="airlineName" type="text" class="form-control" aria-describedby="AirLineName"
                       placeholder="Название" id="EmployeeNameAdd">
                <small id="AirLineName" class="form-text text-muted">ФИО мастера.</small>
            </div>
            <div class="form-group">
                <label for="servicesAdd">Услуга</label>
                <select class="selectpicker" data-live-search="true" id="servicesAdd">
                    <#list services as service>
                        <option value="${service.id}">${service.servicesName + " " + service.servicesPrice}</option>
                    </#list>
                </select>
            </div>
        </div>
        <@modal.addmodalFooter "addEmployee()"></@modal.addmodalFooter>
    </@modal.addmodal>
    <@modal.modal 'modalEdit', "мастера">
        <div class="modal-body" id="readyToEdit">
            <div class="form-group">
                <label for="idEdit">ID</label>
                <input name="id" type="text" class="form-control" id="idEdit" aria-describedby="idHelp" placeholder="ID"
                       disabled="disabled">
                <small id="idHelp" class="form-text text-muted">ID мастера позволяет идентифицировать его в БД, его
                    смена невозможна.</small>
            </div>
            <div class="form-group">
                <label for="EmployeeNameEdit">ФИО мастера</label>
                <input name="login" type="text" class="form-control" aria-describedby="AirLineName"
                       placeholder="Название" id="EmployeeNameEdit">
                <small id="AirLineName" class="form-text text-muted">ФИО мастера.</small>
            </div>
            <div class="form-group">
                <label for="servicesEdit">Услуга</label>
                <select class="selectpicker" data-live-search="true" id="servicesEdit">
                    <#list services as service>
                        <option value="${service.id}">${service.servicesName}</option>
                    </#list>
                </select>
            </div>
        </div>
        <@modal.modalFooter "saveEmployee()"></@modal.modalFooter>
    </@modal.modal>
    <div class="grey-bg container-fluid">
        <div class="row">
            <div class="col-12 mt-3 mb-1">
                <h4 class="text-uppercase">Мастера.</h4>
                <div class="alert alert-success" role="alert" id="successInfo" style="display: none"></div>
                <div class="alert alert-success" role="alert" id="ErrorInfo" style="display: none"></div>
                <button type="button" class="btn btn-success" onclick="getEmployee();"><i class="fas fa-sync-alt"></i>
                </button>
                <button type="button" class="btn btn-success" onclick="addDialog();"><i class="fas fa-plus"></i>
                </button>
                <@modal.updatePanel "услуг"></@modal.updatePanel>
            </div>
        </div>
        <div class="row justify-content-center">
            <table class="table" style="text-align: center">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">ФИО мастера</th>
                    <th scope="col">Название услуги</th>
                    <th scope="col">Управление</th>
                </tr>
                </thead>
                <tbody id="EmployeeList">
                <#list employees as employee>
                    <tr>
                        <th scope="row">${employee.id}</th>
                        <td>${employee.fullName}</td>
                        <td>${employee.services.servicesName}</td>
                        <td>
                            <button type="button" class="btn btn-primary btn-sm"
                                    onclick="editEmployee('${employee.id}');"><i class="fas fa-pencil-alt"></i></button>
                            <button type="button" class="btn btn-danger btn-sm"
                                    onclick="removeEmployee('${employee.id}');"><i class="fas fa-trash-alt"></i></button>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
    <script src="../adminPanel.js"></script>
</@page.page>