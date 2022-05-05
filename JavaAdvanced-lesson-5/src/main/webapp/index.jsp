<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>index</title>

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

<form action="registration" method="post">
        <label for= "firstName" class="texts">First name</label><input name="firstName" type="text" class="forms">
        <label for="lastName" class="texts">Last name</label><input name="lastName" type="text" class="forms">
        <label for="email" class="texts">E-mail</label><input name="email" type="text" class="forms">
        <label for="password" class="texts">Password</label><input name="password" type="password" class="forms">
        <br><input type="submit" value="register" class="button">
    </form>

</body>
</html>
