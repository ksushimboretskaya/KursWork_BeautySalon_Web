<#import "parts/page.ftlh" as page>
<#import "parts/auth.ftlh" as auth>
<#import 'parts/navbar.ftlh' as navbar>
<@page.page "Регистрация">
    <@navbar.navbar 6,false,false></@navbar.navbar>
    <form action="/register" method="post">
        <div class="modal-body" id="readyToEdit" style="padding-right: 30%; padding-left: 30%;">
            <div class="form-group">
                <label for="login">Логин</label>
                <input name="login" type="text" class="form-control" id="login" aria-describedby="loginHelp" placeholder="Логин">
                <small id="loginHelp" class="form-text text-muted">Логин для идентификации акккаунта.</small>
            </div>
            <div class="form-group">
                <label for="fullName">ФИО</label>
                <input name="fullName" type="text" class="form-control" id="fullName" aria-describedby="fullNameHelp" placeholder="ФИО">
                <small id="fullNameHelp" class="form-text text-muted">Фамилия, имя, отчество.</small>
            </div>
            <div class="form-group">
                <label for="email">Почта</label>
                <input name="email" type="email" class="form-control" id="email" aria-describedby="emailHelp" placeholder="Почта">
                <small id="emailHelp" class="form-text text-muted">Почта для взаимодействия с сервисом.</small>
            </div>
            <div class="form-group">
                <label for="password">Пароль</label>
                <input name="password" type="password" class="form-control" id="password" aria-describedby="passwordHelp" placeholder="Пароль">
                <small id="passwordHelp" class="form-text text-muted">Пароль для входа.</small>
            </div>
        </div>
        <div class="modal-footer" style="padding-right: 30%; padding-left: 30%;">
            <button type="submit" class="btn btn-primary" data-dismiss="modal" id="register">Регистрация</button>
        </div>
    </form>
</@page.page>