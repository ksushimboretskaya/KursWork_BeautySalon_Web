<#import "../parts/page.ftlh" as page>
<#import "parts/navbar.ftlh" as navbar>
<#import "parts/modal.ftlh" as modal>
<@page.page "Админ-Панель">
    <@navbar.navbar 3></@navbar.navbar>
    <@modal.addmodal 'modalAdd', "услуги">
        <div class="modal-body" id="readyToAdd">
            <div class="form-group">
                <label for="ServicesNameAdd">Название</label>
                <input name="airlineName" type="text" class="form-control" aria-describedby="AirLineName" placeholder="Название" id="ServicesNameAdd">
                <small id="AirLineName" class="form-text text-muted">Название услуги.</small>
            </div>
            <div class="form-group">
                <label for="ServicesCostAdd">Стоимость</label>
                <input name="airlineName" type="text" class="form-control" aria-describedby="AirLineName" placeholder="Стоимость" id="ServicesCostAdd">
                <small id="AirLineName" class="form-text text-muted">Стоимость услуги.</small>
            </div>
            <div class="form-group">
                <label for="DurationAdd">Длительность</label>
                <input name="airlineName" type="text" class="form-control" aria-describedby="AirLineName" placeholder="Длительность" id="DurationAdd">
                <small id="AirLineName" class="form-text text-muted">Длительность услуги.</small>
            </div>
        </div>
        <@modal.addmodalFooter "addService()"></@modal.addmodalFooter>
    </@modal.addmodal>
    <@modal.modal 'modalEdit', "услуги">
        <div class="modal-body" id="readyToEdit">
            <div class="form-group">
                <label for="idEdit">ID</label>
                <input name="id" type="text" class="form-control" id="idEdit" aria-describedby="idHelp" placeholder="ID" disabled="disabled">
                <small id="idHelp" class="form-text text-muted">ID услуги позволяет идентифицировать ее в БД, его смена невозможна.</small>
            </div>
            <div class="form-group">
                <label for="ServicesNameEdit">Название</label>
                <input name="login" type="text" class="form-control" aria-describedby="AirLineName" placeholder="Название" id="ServicesNameEdit">
                <small id="AirLineName" class="form-text text-muted">Название авиакомпании.</small>
            </div>
            <div class="form-group">
                <label for="ServicesCostEdit">Стоимость</label>
                <input name="airlineName" type="text" class="form-control" aria-describedby="AirLineCost" placeholder="Стоимость" id="ServicesCostEdit">
                <small id="AirLineName" class="form-text text-muted">Стоимость услуги.</small>
            </div>
            <div class="form-group">
                <label for="DurationEdit">Длительность</label>
                <input name="duration" type="text" class="form-control" aria-describedby="Duration" placeholder="Длительность" id="DurationEdit">
                <small id="Duration" class="form-text text-muted">Длительность услуги.</small>
            </div>
        </div>
        <@modal.modalFooter "saveServices()"></@modal.modalFooter>
    </@modal.modal>
    <div class="grey-bg container-fluid">
        <div class="row">
            <div class="col-12 mt-3 mb-1">
                <h4 class="text-uppercase">Услуги.</h4>
                <div class="alert alert-success" role="alert" id="successInfo" style="display: none"></div>
                <div class="alert alert-success" role="alert" id="ErrorInfo" style="display: none"></div>
                <button type="button" class="btn btn-success" onclick="getServices();"><i class="fas fa-sync-alt"></i></button>
                <button type="button" class="btn btn-success" onclick="addDialog();"><i class="fas fa-plus"></i></button>
                <@modal.updatePanel "услуг"></@modal.updatePanel>
            </div>
        </div>
        <div class="row justify-content-center">
            <table class="table" style="text-align: center">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Название</th>
                    <th scope="col">Стоимость</th>
                    <th scope="col">Длительность</th>
                    <th scope="col">Управление</th>
                </tr>
                </thead>
                <tbody id="ServicesList">
                <#list services as service>
                    <tr>
                        <th scope="row">${service.id}</th>
                        <td>${service.servicesName}</td>
                        <td>${service.servicesPrice}</td>
                        <td>${service.duration}</td>
                        <td>
                            <button type="button" class="btn btn-primary btn-sm" onclick="editServices('${service.id}');"><i class="fas fa-pencil-alt"></i></button>
                            <button type="button" class="btn btn-danger btn-sm" onclick="removeServices('${service.id}');"><i class="fas fa-trash-alt"></i></button>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
    <script src="../adminPanel.js"></script>
</@page.page>