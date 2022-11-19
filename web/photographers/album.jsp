<%-- 
    Document   : myPhotos
    Created on : Oct 3, 2022, 3:06:50 PM
    Author     : nakro
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta name="description" content="" />
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors" />
    <meta name="generator" content="Hugo 0.88.1" />
    <title>Dashboard</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/5.1/examples/dashboard/" />

    <!-- Bootstrap core CSS -->
    <link href="../../../assets/dist/css/bootstrap.min.css" rel="stylesheet" />

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
    <link href="../../../assets/dist/css/dashboard.css" rel="stylesheet" />
  </head>
  <body>
    <header class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
      <a class="navbar-brand col-md-3 col-lg-2 me-0 px-3" href="../../../">Photography</a>
      <button
        class="navbar-toggler position-absolute d-md-none collapsed"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#sidebarMenu"
        aria-controls="sidebarMenu"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="navbar-nav">
        <div class="nav-item text-nowrap">
            <a class="nav-link px-3" href="../../../users/logout">Sign out</a>
        </div>
      </div>
    </header>

    <div class="container-fluid">
      <div class="row">
        <nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
          <div class="position-sticky pt-3">
            <div class="m-3 h4 border-bottom"><%= session.getAttribute("username") %></div>
            <ul class="nav flex-column">
              <li class="nav-item">
                <a class="nav-link" href="../../dashboard">
                  <span data-feather="file"></span>
                  Orders
                </a>
              </li>
            </ul>
          </div>
        </nav>

        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
          <div
            class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom"
          >
            <form action="${id}" method="POST" enctype="multipart/form-data">
              <div>
                <label for="formFile" class="form-label"><h2>Upload Image</h2></label>
                <input class="form-control form-control" id="formFileLg" type="file" name="image" required>
              </div>
              <div>
                <button class="btn btn-primary my-3" type="submit">Upload</button>
              </div>
            </form>
          </div>
          <div
            class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom"
          >
            <h2>Album</h2>
          </div>
          <div class="row">
            <c:forEach items="${images}" var="image">
              <div class="col-lg-3 justify-content-center pb-3" >
                <img src="../../../${image}" class="img-thumbnail" alt="..." style="max-width: 100%; max-height: 200px; overflow: hidden" />
              </div>
            </c:forEach>
            <%-- <div class="col-3 justify-content-center pb-3">
              <img src="../../../albums/images/roman-ivasyk-nwULyAEDHB8-unsplash.jpg" class="img-thumbnail" alt="..." />
            </div>
            <div class="col-3 justify-content-center pb-3">
              <img src="../../../albums/images/roman-ivasyk-nwULyAEDHB8-unsplash.jpg" class="img-thumbnail" alt="..." />
            </div>
            <div class="col-3 justify-content-center pb-3">
              <img src="../../../albums/images/roman-ivasyk-nwULyAEDHB8-unsplash.jpg" class="img-thumbnail" alt="..." />
            </div>
            <div class="col-3 justify-content-center pb-3">
              <img src="../../../albums/images/roman-ivasyk-nwULyAEDHB8-unsplash.jpg" class="img-thumbnail" alt="..." />
            </div>
            <div class="col-3 justify-content-center pb-3">
              <img src="../../../albums/images/roman-ivasyk-nwULyAEDHB8-unsplash.jpg" class="img-thumbnail" alt="..." />
            </div>
            <div class="col-3 justify-content-center pb-3">
              <img src="../../../albums/images/roman-ivasyk-nwULyAEDHB8-unsplash.jpg" class="img-thumbnail" alt="..." />
            </div> --%>
            
          </div>
        </main>
      </div>
    </div>

    <script src="../../../assets/dist/js/bootstrap.bundle.min.js"></script>
    <script
      src="https://cdn.jsdelivr.net/npm/feather-icons@4.28.0/dist/feather.min.js"
      integrity="sha384-uO3SXW5IuS1ZpFPKugNNWqTZRRglnUJK6UAZ/gxOX80nxEkN9NcGZTftn6RzhGWE"
      crossorigin="anonymous"
    ></script>
    <script
      src="https://cdn.jsdelivr.net/npm/chart.js@2.9.4/dist/Chart.min.js"
      integrity="sha384-zNy6FEbO50N+Cg5wap8IKA4M/ZnLJgzc6w2NqACZaK0u0FXfOWRRJOnQtpZun8ha"
      crossorigin="anonymous"
    ></script>
    <script src="../../../assets/dist/js/dashboard.js"></script>
  </body>
</html>
