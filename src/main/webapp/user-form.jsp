
<%-- 
    Document   : user-form
    Created on : May 25, 2025, 3:12:54 PM
    Author     : Mohamed Sayed
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>User Management Application</title>
    <!-- Bootstrap CSS for styling the page -->
    <link rel="stylesheet"
        href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
        crossorigin="anonymous">
</head>
<body>

    <header>
        <!-- Navigation bar using Bootstrap classes -->
        <nav class="navbar navbar-expand-md navbar-dark"
            style="background-color: blue">
            <div>
                <!-- Brand/logo link -->
                <a href="https://www.xadmin.net" class="navbar-brand"> User Management Application </a>
            </div>

            <ul class="navbar-nav">
                <!-- Link to users list page -->
                <li><a href="<%=request.getContextPath()%>/list"
                    class="nav-link">Users</a></li>
            </ul>
        </nav>
    </header>
    <br>

    <div class="container col-md-5">
        <div class="card">
            <div class="card-body">
                <!-- Check if user object exists, then set form action accordingly -->
                <c:if test="${user != null}">
                    <form action="update" method="post">
                </c:if>
                <c:if test="${user == null}">
                    <form action="insert" method="post">
                </c:if>

                <caption>
                    <h2>
                        <!-- Title changes depending if user is being edited or added -->
                        <c:if test="${user != null}">
                            Edit User
                        </c:if>
                        <c:if test="${user == null}">
                            Add New User
                        </c:if>
                    </h2>
                </caption>

                <!-- Hidden field to hold user ID when editing -->
                <c:if test="${user != null}">
                    <input type="hidden" name="id" value="<c:out value='${user.id}' />" />
                </c:if>

                <!-- User Name input field -->
                <fieldset class="form-group">
                    <label>User Name</label>
                    <input type="text"
                        value="<c:out value='${user.name}' />" class="form-control"
