<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>iSports</title>

    <!-- Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Raleway:100,600" rel="stylesheet" type="text/css">

    <!-- Styles -->
    <style>
        html, body {
            background-color: #fff;
            color: #000000;
            font-family: 'Raleway', sans-serif;
            font-weight: 100;
            height: 100vh;
            margin: 0;
        }

        .full-height {
            height: 100vh;
        }

        .flex-center {
            align-items: center;
            display: flex;
            justify-content: center;
        }

        .position-ref {
            position: relative;
        }

        .top-right {
            position: absolute;
            right: 10px;
            top: 18px;
        }

        .content {
            text-align: center;
        }

        .title {
            font-size: 100px;
        }

        .links > a {
            color: beige;
            padding: 0 25px;
            font-size: 20px;
            font-weight: 800;
            letter-spacing: .1rem;
            text-decoration: none;
            text-transform: uppercase;
        }

        .m-b-md {
            margin-bottom: 30px;
        }
    </style>
</head>
<body background="images/background.jpg" style="background-repeat:no-repeat; background-position: center;">
<div class="flex-center position-ref full-height">

    <div class="content">
        <div class="title m-b-md">
            <img src="images/logo.png">
        </div>

        <div class="links">
            <a href="/student/studentLogin">学员平台</a>
            <a href="/organization/Login">机构平台</a>
            <a href="{{url('/moments')}}">经理登录</a>
            <a href="{{url('/about')}}">关于我们</a>
        </div>
    </div>
</div>
</body>
</html>