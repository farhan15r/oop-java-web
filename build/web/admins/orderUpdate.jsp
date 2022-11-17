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
                <a class="nav-link active" href="../../dashboard">
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
            <h2>Update Order</h2>
          </div>
          <div class="col-lg-6">
          <%-- FORM --%>
            <form action="./${order.id}" method="POST">
              <div class="modal-body">
                <div class="mb-3">
                  <label for="fullname" class="form-label">Full Name</label>
                  <input
                    type="text"
                    class="form-control"
                    id="fullname"
                    value="${order.name}"
                    disabled
                    readonly
                  />
                </div>

                <div class="mb-3">
                  <label for="username" class="form-label">Username</label>
                  <input
                    type="text"
                    class="form-control"
                    id="username"
                    value="${order.username}"
                    disabled
                    readonly
                  />
                </div>

                <div class="mb-3">
                  <label for="date" class="form-label">Date</label>
                  <input type="date" class="form-control" id="date" name="date" value="${order.date}"/>
                </div>

                <div class="mb-3">
                  <label for="package" class="form-label">Package</label>
                  <select class="form-select mb-3" onchange="packagesChange(this.value)" name="package">
                    <c:forEach items="${packages}" var="item" >
                      <c:choose>
                        <c:when test="${item.id == order.packageId}">
                          <option value="${item.id}" selected>${item.name}</option>
                        </c:when>
                        <c:otherwise>
                          <option value="${item.id}">${item.name}</option>
                        </c:otherwise>
                      </c:choose>
                    </c:forEach>
                  </select>
                </div>

                <div class="mb-3">
                  <label for="photografer" class="form-label">Photografer</label>
                  <select class="form-select mb-3" name="photographers">
                    <option selected disabled="disabled">Open this select menu</option>
                    <c:forEach items="${photographers}" var="item" >
                      <c:choose>
                        <c:when test="${item.id == order.photographerId}">
                          <option value="${item.id}" selected>${item.fullName}</option>
                        </c:when>
                        <c:otherwise>
                          <option value="${item.id}">${item.fullName}</option>
                        </c:otherwise>
                      </c:choose>
                    </c:forEach>
                  </select>
                </div>

                <div class="mb-3">
                  <label for="payment" class="form-label">Payment</label>
                  <select class="form-select mb-3" name="status_payment">
                    <c:choose>
                      <c:when test="${order.statusPayment == 'Pending'}">
                        <option value="Pending" selected>Pending</option>
                        <option value="Success">Success</option>
                      </c:when>
                      <c:when test="${order.statusPayment == 'Success'}">
                        <option value="Pending">Pending</option>
                        <option value="Success" selected>Success</option>
                      </c:when> 
                    </c:choose> 
                  </select>
                </div>
                
                <div class="mb-3">
                  <label for="order" class="form-label">Order Status</label>
                  <select class="form-select mb-3" name="status_order">
                    <c:choose>
                      <c:when test="${order.statusOrder == 'Finished'}">
                        <option value="Finished" selected>Finished</option>
                        <option value="Scheduled">Scheduled</option>
                        <option value="Success">Success</option>
                      </c:when>
                      <c:when test="${order.statusOrder == 'Scheduled'}">
                        <option value="Finished">Finished</option>
                        <option value="Scheduled" selected>Scheduled</option>
                        <option value="Pending">Pending</option>
                      </c:when> 
                      <c:when test="${order.statusOrder == 'Waiting'}">
                        <option value="Finished">Finished</option>
                        <option value="Scheduled">Scheduled</option>
                        <option value="Waiting" selected>Waiting</option>
                      </c:when> 
                    </c:choose> 
                  </select>
                </div>
                
                <div class="mb-3">
                  <label for="payment" class="form-label">Price</label>
                  <input type="text" class="form-control" id="price" disabled value="${order.price}"/>
                </div>
                
                <div class="mb-3">
                  <label for="admin_name" class="form-label">Last Modify by</label>
                  <input type="text" class="form-control" id="admin_name" disabled value="${order.adminName}"/>
                </div>
              </div>
              <div class="modal-footer justify-content-between">
                <a href="../../dashboard" class="btn btn-secondary">Close</a>
                <div>
                  <a href="./${order.id}?action=delete" class="btn btn-danger mx-3">Delete</a>
                  <button type="submit" class="btn btn-primary">Update</button>
                </div>
              </div>
            </form>
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

    <script>
      const myInput = document.getElementById("myInput");

      function packagesChange(value) {
        const price = document.getElementById("price");
        switch (value) {
          <c:forEach items="${packages}" var="item" >
            case "${item.id}":
              price.value = "${item.price}";
              break;
          </c:forEach>
        }
      }
    </script>
  </body>
</html>

