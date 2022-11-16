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
          <a class="nav-link px-3" href="logout">Sign out</a>
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
              <li class="nav-item">
                <a class="nav-link" href="dashboard/myalbums">
                  <span data-feather="folder"></span>
                  Albums
                </a>
              </li>
            </ul>
          </div>
        </nav>

        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
          <div
            class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom"
          >
            <h2>My Orders</h2>
          </div>
          <!-- Button trigger modal -->
          <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
            <div>
              <svg
                xmlns="http://www.w3.org/2000/svg"
                width="30"
                height="30"
                fill="currentColor"
                class="bi bi-clipboard-plus"
                viewBox="0 0 16 16"
              >
                <path
                  fill-rule="evenodd"
                  d="M8 7a.5.5 0 0 1 .5.5V9H10a.5.5 0 0 1 0 1H8.5v1.5a.5.5 0 0 1-1 0V10H6a.5.5 0 0 1 0-1h1.5V7.5A.5.5 0 0 1 8 7z"
                />
                <path
                  d="M4 1.5H3a2 2 0 0 0-2 2V14a2 2 0 0 0 2 2h10a2 2 0 0 0 2-2V3.5a2 2 0 0 0-2-2h-1v1h1a1 1 0 0 1 1 1V14a1 1 0 0 1-1 1H3a1 1 0 0 1-1-1V3.5a1 1 0 0 1 1-1h1v-1z"
                />
                <path
                  d="M9.5 1a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-.5.5h-3a.5.5 0 0 1-.5-.5v-1a.5.5 0 0 1 .5-.5h3zm-3-1A1.5 1.5 0 0 0 5 1.5v1A1.5 1.5 0 0 0 6.5 4h3A1.5 1.5 0 0 0 11 2.5v-1A1.5 1.5 0 0 0 9.5 0h-3z"
                />
              </svg>
            </div>
            New order
          </button>

          <div class="table-responsive">
            <table class="table table-striped table-sm">
              <thead>
                <tr>
                  <th scope="col">No</th>
                  <th scope="col">Date</th>
                  <th scope="col">Package</th>
                  <th scope="col">Photografer</th>
                  <th scope="col">Price</th>
                  <th scope="col">Status</th>
                </tr>
              </thead>
              <tbody>
                <c:set var="i" value="1" />
                <c:forEach items="${orders}" var="order">
                  <tr>
                    <td><c:out value="${i}" /></td>
                    <td>${order.date}</td>
                    <td>${order.packageName}</td>
                    <td>-</td>
                    <td>${order.price}</td>
                    <%-- Status Order --%>
                    <c:choose>
                      <c:when test="${order.status_order == 'Waiting'}">
                        <td class="bg-secondary text-white">Waiting</td>
                      </c:when>
                      <c:when test="${order.status_order == 'Scheduled'}">
                        <td class="bg-warning">Scheduled</td>
                      </c:when>
                      <c:when test="${order.status_order == 'Finished'}">
                        <td class="bg-success text-white">Finished</td>
                      </c:when>
                    </c:choose>
                  </tr>
                  <c:set var="i" value="${i + 1}" />
                </c:forEach>
                <%-- HARD CODE --%>
                <%-- <tr>
                  <td>1</td>
                  <td>Monday, 20/10/2022</td>
                  <td>Basic</td>
                  <td>-</td>
                  <td class="bg-secondary text-white">Waiting</td>
                </tr>
                <tr>
                  <td>2</td>
                  <td>Sunday, 19/10/2022</td>
                  <td>Basic</td>
                  <td>Sambo</td>
                  <td class="bg-warning">Scheduled</td>
                </tr>
                <tr>
                  <td>3</td>
                  <td>Sunday, 19/9/2022</td>
                  <td>Basic</td>
                  <td>Sambo</td>
                  <td class="bg-success">Finished</td>
                </tr> --%>
                <%-- END HARD CODE --%>
              </tbody>
            </table>
          </div>
        </main>
      </div>
    </div>

    <!-- Modal -->
    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">New Order</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <!-- form -->
          <form action="dashboard/oders/add" method="POST">
            <div class="modal-body">
              <div class="mb-3">
                <label for="date" class="form-label">Date</label>
                <input type="date" class="form-control" id="date" name="date" />
              </div>

              <div class="mb-3">
                <label for="package" class="form-label">Package</label>
                <select class="form-select mb-3" onchange="packagesChange(this.value)" name="package">
                  <option selected disabled="disabled">Open this select menu</option>
                    <c:forEach items="${packages}" var="item" >
                      <option value="${item.id}">${item.name}</option>
                    </c:forEach>
                </select>
              </div>

              <div class="mb-3">
                <label for="date" class="form-label">Price</label>
                <input type="text" class="form-control" id="price" disabled value="Rp. 0"/>
              </div>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
              <button type="submit" class="btn btn-primary">Submit</button>
            </div>
          </form>
        </div>
      </div>
    </div>

    <script>
      const myModal = document.getElementById("myModal");
      const myInput = document.getElementById("myInput");

      myModal.addEventListener("shown.bs.modal", () => {
        myInput.focus();
      });

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

