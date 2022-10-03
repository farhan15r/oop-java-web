<%-- 
    Document   : register
    Created on : Sep 27, 2022, 7:14:26 AM
    Author     : nakro
--%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta name="description" content="" />
    <meta name="generator" content="Hugo 0.88.1" />
    <title>Register</title>

    <link
      rel="canonical"
      href="https://getbootstrap.com/docs/5.1/examples/sign-in/"
    />

    <!-- Bootstrap core CSS -->
    <link href="../assets/dist/css/bootstrap.min.css" rel="stylesheet" />

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
    <link href="../assets/dist/css/signin.css" rel="stylesheet" />
  </head>
  <body class="text-center">
    <main class="form-signin">
      <form method="POST" action="register">
        <h1 class="h3 mb-3 fw-normal">Please sign up</h1>

        <div class="form-floating">
          <input
            type="text"
            class="form-control"
            id="name"
            placeholder="Full name"
            name="name"
          />
          <label for="floatingInput">Full Name</label>
        </div>
        <div class="form-floating">
          <input
            type="text"
            class="form-control"
            id="username"
            placeholder="Username"
            name="username"
          />
          <label for="floatingInput">Username</label>
        </div>
        <div class="form-floating">
          <input
            type="email"
            class="form-control"
            id="email"
            placeholder="Email address"
            name="email"
          />
          <label for="floatingInput">Email address</label>
        </div>
        <div class="form-floating">
          <input
            type="password"
            class="form-control"
            id="password"
            placeholder="Password"
            name="password"
          />
          <label for="floatingInput">Password</label>
        </div>
          
        <button class="w-100 btn btn-lg btn-primary" type="submit">
          Sign up
        </button>
      </form>
        <p>Already have account? <a href="login">Login now!</a></p>
    </main>
  </body>
</html>

