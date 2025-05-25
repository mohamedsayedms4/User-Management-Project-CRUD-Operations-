<%-- 
    Document   : user-list
    Created on : May 25, 2025, 3:12:54 PM
    Author     : Mohamed Sayed
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>User Management Application</title>
    <!-- Bootstrap CSS for responsive and styled U  I -->
    <link rel="stylesheet"
        href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
        crossorigin="anonymous">
</head>
<body>

<header>
    <!-- Navigation bar with brand and users link -->
    <nav class="navbar navbar-expand-md navbar-dark bg-primary">
        <a class="navbar-brand" href="https://www.xadmin.net">User Management Application</a>
        <div class="collapse navbar-collapse">
            <ul class="navbar-nav ml-auto">
                <!-- Link to the users list page -->
                <li class="nav-item">
                    <a href="<%=request.getContextPath()%>/list" class="nav-link">Users</a>
                </li>
            </ul>
        </div>
    </nav>
</header>

<main class="container mt-4">
    <!-- Page heading -->
    <h3 class="text-center mb-3">List of Users</h3>
    <!-- Button to add new user -->
    <div class="mb-3 text-left">
        <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add New User</a>
    </div>

    <!-- Users table -->
    <table class="table table-bordered table-striped table-hover">
        <thead class="thead-dark">
            <tr>
                <th>ID</th> <!-- User ID column -->
                <th>Name</th> <!-- User Name column -->
                <th>Email</th> <!-- User Email column -->
                <th>Country</th> <!-- User Country column -->
                <th>Actions</th> <!-- Edit and Delete buttons -->
            </tr>
        </thead>
        <tbody>
            <!-- Iterate over the listUser collection to display each user -->
            <c:forEach var="user" items="${listUser}">
                <tr>
                    <td><c:out value="${user.id}" /></td> <!-- Display user ID -->
                    <td><c:out value="${user.name}" /></td> <!-- Display user Name -->
                    <td><c:out value="${user.email}" /></td> <!-- Display user Email -->
                    <td><c:out value="${user.country}" /></td> <!-- Display user Country -->
                    <td>
                        <!-- Edit user button with user ID as parameter -->
                        <a href="edit?id=<c:out value='${user.id}' />" class="btn btn-sm btn-primary mr-2">Edit</a>
                        <!-- Delete user button with user ID as parameter -->
                        <a href="delete?id=<c:out value='${user.id}' />" class="btn btn-sm btn-danger">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</main>

</body>
</html>
