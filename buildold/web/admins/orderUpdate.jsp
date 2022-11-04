<%-- 
    Document   : dashboard
    Created on : Sep 28, 2022, 12:53:18 PM
    Author     : nakro
--%>

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
    <link href="../../assets/dist/css/bootstrap.min.css" rel="stylesheet" />

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
    <link href="../../assets/dist/css/dashboard.css" rel="stylesheet" />
  </head>
  <body>
    <header class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
      <a class="navbar-brand col-md-3 col-lg-2 me-0 px-3" href="../../">Photography</a>
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
          <a class="nav-link px-3" href="../../users/logout">Sign out</a>
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
            <h2>Update Order</h2>
          </div>
          <div class="col-6">
            <form>
              <div class="modal-body">
                <div class="mb-3">
                  <label for="fullname" class="form-label">Full Name</label>
                  <input
                    type="text"
                    class="form-control"
                    id="fullname"
                    name="fullname"
                    value="Akif Rizky"
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
                    name="username"
                    value="Akif_"
                    disabled
                    readonly
                  />
                </div>

                <div class="mb-3">
                  <label for="date" class="form-label">Date</label>
                  <input type="date" class="form-control" id="date" name="date" />
                </div>

                <div class="mb-3">
                  <label for="package" class="form-label">Package</label>
                  <select class="form-select mb-3" onchange="packagesChange(this.value)">
                    <option value="1" name="package" selected>One</option>
                    <option value="2" name="package">Two</option>
                    <option value="3" name="package">Three</option>
                  </select>
                </div>

                <div class="mb-3">
                  <label for="photografer" class="form-label">Photografer</label>
                  <select class="form-select mb-3">
                    <option selected disabled="disabled">Open this select menu</option>
                    <option value="1" name="photografer">Ujang</option>
                    <option value="2" name="photografer">Udin</option>
                  </select>
                </div>

                <div class="mb-3">
                  <label for="payment" class="form-label">Payment</label>
                  <select class="form-select mb-3">
                    <option value="1" name="payment" selected>Pending</option>
                    <option value="2" name="payment">Success</option>
                  </select>
                </div>

                <div class="mb-3 row">
                  <div class="col">
                    <p class="h5 fw-normal">Price</p>
                  </div>
                  <div class="col">
                    <p class="h5 fw-normal" id="price">Rp 1.000.000</p>
                  </div>
                </div>
              </div>
              <div class="modal-footer">
                <a href="../dashboard" class="btn btn-secondary">Close</a>
                <button type="submit" class="btn btn-primary">Update</button>
              </div>
            </form>
          </div>
        </main>
                
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
        if (value == 1) {
          price.innerHTML = "Rp. 1.000.000";
        } else if (value == 2) {
          price.innerHTML = "Rp. 2.000.000";
        } else if (value == 3) {
          price.innerHTML = "Rp. 3.000.000";
        }
      }
    </script>

    <script src="../../assets/dist/js/bootstrap.bundle.min.js"></script>

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
    <script src="../../assets/dist/js/dashboard.js"></script>
  </body>
</html>

