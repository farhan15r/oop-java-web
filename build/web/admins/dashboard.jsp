<%-- 
    Document   : dashboard
    Created on : Sep 28, 2022, 12:53:18 PM
    Author     : nakro
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta name="description" content="" />
    <meta name="generator" content="Hugo 0.88.1" />
    <title>Dashboard</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/5.1/examples/dashboard/" />

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
    <link href="../assets/dist/css/dashboard.css" rel="stylesheet" />
  </head>
  <body>
    <header class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
      <a class="navbar-brand col-md-3 col-lg-2 me-0 px-3" href="../">Photography</a>
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
          <a class="nav-link px-3" href="../users/logout">Sign out</a>
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
                <a class="nav-link active" href="#">
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
            <h2>All Orders</h2>
          </div>
          <div class="table-responsive">
            <table class="table table-striped table-sm">
              <thead>
                <tr>
                  <th scope="col">No</th>
                  <th scope="col">Username</th>
                  <th scope="col">Date</th>
                  <th scope="col">Package</th>
                  <th scope="col">Photografer</th>
                  <th scope="col">Payment</th>
                  <th scope="col">Status</th>
                  <th scope="col">Actions</th>
                </tr>
              </thead>
              <tbody>
                <c:set var="i" value="1" />
                <c:forEach items="${orders}" var="order">
                  <tr>
                    <td><c:out value="${i}" /></td>
                    <td>${order.username}</td>
                    <td>${order.date}</td>
                    <td>${order.packageName}</td>
                    <td>${order.photographerName}</td>
                    <%-- Status Payment --%>
                    <c:choose>
                      <c:when test="${order.statusPayment == 'Pending'}">
                        <td class="bg-warning">Pending</td>
                      </c:when>
                      <c:when test="${order.statusPayment == 'Success'}">
                        <td class="bg-success text-white">Success</td>
                      </c:when>
                    </c:choose>
                    <%-- Status Order --%>
                    <c:choose>
                      <c:when test="${order.statusOrder == 'Waiting'}">
                        <td class="bg-secondary text-white">Waiting</td>
                      </c:when>
                      <c:when test="${order.statusOrder == 'Scheduled'}">
                        <td class="bg-warning">Scheduled</td>
                      </c:when>
                      <c:when test="${order.statusOrder == 'Finished'}">
                        <td class="bg-success text-white">Finished</td>
                      </c:when>
                    </c:choose>
                    <td><a href="dashboard/order/${order.id}" class="btn btn-warning text-white btn-sm">Update</a></td>
                  </tr>
                  <c:set var="i" value="${i + 1}" />
                </c:forEach>
              </tbody>
            </table>
          </div>
        </main>
      </div>
    </div>

    <script src="../assets/dist/js/bootstrap.bundle.min.js"></script>

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
    <script src="../assets/dist/js/dashboard.js"></script>
  </body>
</html>

