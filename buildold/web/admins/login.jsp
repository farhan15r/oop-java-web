<%-- 
    Document   : login
    Created on : Sep 26, 2022, 9:48:04 PM
    Author     : nakro
--%>
<!doctype html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
        <meta name="generator" content="Hugo 0.88.1">
        <title>Sign in</title>

        <link rel="canonical" href="https://getbootstrap.com/docs/5.1/examples/sign-in/">



        <!-- Bootstrap core CSS -->
        <link href="../assets/dist/css/bootstrap.min.css" rel="stylesheet">

        <style>
            .bd-placeholder-img {
                font-size: 1.125rem;
                text-anchor: middle;
                -webkit-user-select: none;
                -moz-user-select: none;
                user-select: none;
            }

            @media (min-width: 768px) {
                .bd-placeholder-img-lg {
                    font-size: 3.5rem;
                }
            }
        </style>


        <!-- Custom styles for this template -->
        <link href="../assets/dist/css/signin.css" rel="stylesheet">
    </head>
    <body class="text-center">

        <main class="form-signin">
            <c:set var="login" value='${sessionScope.login}'/>
            <c:choose>
                <c:when test="${login == 'failed'}">
                    <div class="alert alert-danger" role="alert">
                        Login failed, please try again.
                    </div>
                    <c:remove var="login" scope="session" /> 
                </c:when>
            </c:choose>
            <form action="login" method="POST">
                <h1 class="h3 mb-3 fw-normal">Sign in as Admin</h1>

                <div class="form-floating">
                    <input type="text" class="form-control" id="floatingInput" name="username" placeholder="Username">
                    <label for="floatingInput">Username</label>
                </div>

                <div class="form-floating">
                    <input type="password" class="form-control" id="floatingPassword" name="password" placeholder="Password">
                    <label for="floatingPassword">Password</label>
                </div>

                <button class="w-100 btn btn-lg btn-primary" type="submit">Sign in</button>
            </form>
        </main>
    </body>
</html>
