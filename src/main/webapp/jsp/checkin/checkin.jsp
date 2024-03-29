<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Check in</title>
    <link
            rel="stylesheet"
            type="text/css"
            href="https://cdn.jsdelivr.net/npm/toastify-js/src/toastify.min.css"
    />
    <link rel="stylesheet" href="../../style.css"/>
    <style>
        .container {
            display: none;
        }

        .btn-back {
            text-align: start;
            padding: 20px;
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


        .loading-container {
            display: block;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(255, 255, 255, 0.8);
            z-index: 9999;
        }

        .spinner {
            position: absolute;
            top: 50%;
            left: 50%;
            width: 40px;
            height: 40px;
            margin: auto;
            border-radius: 50%;
            border: 5px solid rgba(0, 0, 0, 0.2);
            border-top: 5px solid #3498db;
            animation: spin 1s linear infinite;
        }

        @keyframes spin {
            0% {
                transform: rotate(0deg);
            }
            100% {
                transform: rotate(360deg);
            }
        }

    </style>
</head>

<body>

<div class="btn-back">
    <button class="btn btn-intermediate">
        <a href="${linkBack}" style="text-decoration: none">Back</a>
    </button>
</div>
<h3>
    <%
        String word = (String) session.getAttribute("word");
    %>
    <%= word%>
</h3>
<div class="loading-container" id="loadingContainer">
    <div class="spinner"></div>
</div>


<div class="container" id="container">
    <div id="camera"></div>
    <button class="button-29" role="button" onclick="snapShot()">
        <%= word%> Here
    </button>
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
    const label = ${memberJs}
    const wordSession = "<%= word%>";
    let wordReq= '';

    if (wordSession === 'Check out') {
        wordReq = 'checkout';
    } else {
        wordReq = 'checkin';
    }
</script>
<script src="../../script.js"></script>

</html>