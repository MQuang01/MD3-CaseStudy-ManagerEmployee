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
  <link rel="stylesheet" href="../../style.css" />
</head>

<body>

<h3>Check in</h3>
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