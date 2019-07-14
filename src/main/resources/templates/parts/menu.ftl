<#import "/spring.ftl" as s/>
<div class="row">
    <div class="col-md-12">
        <ul class="nav justify-content-center">
            <li class="nav-item">
                <a class="nav-link active" href="/">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link active" href="/login">Login</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/allUsers">All users (ADMIN)</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/userInfo">User Info (USER)</a>
            </li>
            <li class="nav-item">
                <form action="/logout" method="post">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <button class="btn btn-secondary" type="submit">
                        <@s.message "menu.logout" />
                    </button>
                </form>
            </li>
        </ul>
    </div>
</div>
