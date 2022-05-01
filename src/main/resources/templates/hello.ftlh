<#import "parts/navbar.ftlh" as navbar>
<#import 'user/parts/page.ftlh' as page>
<@page.page "Главная">
    <#if user.id == -1>
        <#assign userRole = false, adminRole = false>
    <#else>
        <#assign userRole = true, adminRole = user.isAdmin()>
    </#if>
    <@navbar.navbar 1,userRole,adminRole></@navbar.navbar>
    <div class="row">
        <div class="col">
            <div class="alert alert-success" role="alert" id="successInfo" style="display: none"></div>
            <div class="alert alert-danger" role="alert" id="ErrorInfo" style="display: none"></div>
            <div class="card text-center">
                <div class="card-header">
                    <h1>Свободные окошки</h1>
                </div>
                <div class="card-body">
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <label class="input-group-text" for="select1">Услуга</label>
                        </div>
                        <select class="custom-select" id="select1">
                            <#list services as service>
                                <option value="${service.id}">${service.servicesName}
                                    (${service.servicesPrice + " руб., "
                                    + service.duration})
                                </option>
                            </#list>
                        </select>
                    </div>
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <label class="input-group-text" for="select2">Мастер</label>
                        </div>
                        <select class="custom-select" id="select2">
                            <#list employee as employee>
                                <option value="${employee.id}">${employee.fullName} (${employee.services.servicesName})
                                </option>
                            </#list>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="DateMain">Дата</label>
                        <input name="airlineName" value="${dateNow}" type="date" class="form-control"
                               aria-describedby="AirLineName" placeholder="Дата" id="DateMain">
                    </div>
                    <button type="button" class="btn btn-success" onclick="searchRecords();">
                        <i class="fas fa-search" style="padding-right: 10px"></i>Поиск
                    </button>
                </div>
                <div class="card-footer text-muted">
                </div>
            </div>
        </div>
        <div class="col" style="padding: 100px 100px; display: none;" id="recordsMain">
        </div>
    </div>
    <script src="public/MainPage.js"></script>
</@page.page>