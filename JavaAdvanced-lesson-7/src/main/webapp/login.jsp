<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>login</title>

<style>
        .texts {
            font-size: 25px;
            display: flex;
            justify-content: center;
            margin-top: 25px;
        }

        .forms {
            width: 200px;
            height: 22px;
            font-size: 18px;
            border-color: lightslategray;
            display: flex;
            margin: auto;
        }

        .button {
            width: 140px;
            height: 30px;
            display: flex;
            margin: auto;
            padding-left: 40px;
            text-align: center;
            font-size: 18px;
        }
    </style>

</head>
<body>
<form action="login" method="post">
        <label for="login" class="texts">E-mail</label><input name="login" type="text" class="forms">
        <label for="password" class="texts">Password</label><input name="password" type="password" class="forms">
        <br><input type="submit" value="log in" class="button">
    </form>
</body>
</html>