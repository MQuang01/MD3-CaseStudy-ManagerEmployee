<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Check in</title>
  <link
          rel="stylesheet"
          type="text/css"
          href="https://cdn.jsdelivr.net/npm/toastify-js/src/toastify.min.css"
  />
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
          integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
          crossorigin="anonymous"></script>
  <link rel="stylesheet" href="../../style.css" />
  <style>
    .btn-back{
      text-align: start;
      padding: 20px;
    }

    .title-header {
      text-align: center;
      margin-bottom: 10px;
    }

    .btn {
      color: white;
      padding: .75rem 2.5rem;
      border: none;
      cursor: pointer;
      border-radius: 5px;
      font-weight: bold;
      font-size: 1.2rem;
      margin-left: 15px;
    }

    .btn-intermediate {
      background: linear-gradient(to right, #FF416C, #FF4B2B);
      color: white;
      border: none;
      cursor: pointer;
      border-radius: 10px;
      box-shadow: 0px 4px 5px rgba(0, 0, 0, 0.2);
    }

    .btn-intermediate:hover {
      transform: scale(1.05);
      animation-play-state: paused;
    }
  </style>
</head>

<body>
<div class="btn-back">
  <button class="btn btn-intermediate">
    <a href="${linkBack}" style="text-decoration: none">Back</a>
  </button>
</div>

<div class="title-header">
  <h3>Check In</h3>
</div>
<div class="container">
  <div id="camera"></div>
  <button class="button-29" role="button" onclick="snapShot()">Click here</button>
  <div id="formCaptured"></div>
</div>
</body>

<script
        src="https://cdn.jsdelivr.net/npm/face-api.js@0.22.2/dist/face-api.js"
        integrity="sha256-JOJ7NmVm2chxYZ1KPcAYd2bwVK7NaFj9QKMp7DClews="
        crossorigin="anonymous"
></script>
<script
        type="text/javascript"
        src="https://cdn.jsdelivr.net/npm/toastify-js"
></script>
<script
        src="https://cdn.jsdelivr.net/npm/webcamjs@1.0.26/webcam.min.js"
        integrity="sha256-M748/Ss9JXTdKfvK7guV7Upt/GE4hFMoVCDILqtuV3M="
        crossorigin="anonymous"
></script>
<script>
  const label = ${memberJs};
</script>
<script src="../../script.js"></script>

</html>