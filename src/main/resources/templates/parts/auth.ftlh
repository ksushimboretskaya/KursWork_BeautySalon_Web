<#macro login>
    <form action="/login" method="post">
        <div><label> Login : <input type="text" name="username"/> </label></div>
        <div><label> Password: <input type="password" name="password"/> </label></div>
        <div><input type="submit" value="Sign In"/></div>
    </form>
</#macro>
<#macro register>
    <form action="/register" method="post">
        <div><label> UserName : <input type="text" name="login"/> </label></div>
        <div><label> FullName : <input type="text" name="fullName"/> </label></div>
        <div><label> Email : <input type="email" name="email"/> </label></div>
        <div><label> Password : <input type="password" name="password"/> </label></div>
        <div><input type="submit" value="Register"/></div>
    </form>
</#macro>
<#macro logout>
</#macro>