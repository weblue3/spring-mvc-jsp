<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  작성자: 김홍준
  작성일: 2020-12-27 오후 9:22
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!-- 상단 메뉴 부분 -->
<nav class="navbar navbar-expand-md bg-dark navbar-dark fixed-top shadow-lg">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/main">SoftCampus</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse"
            data-target="#navMenu">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navMenu">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a href="${pageContext.request.contextPath}/board/main" class="nav-link">자유게시판</a>
            </li>
            <li class="nav-item">
                <a href="${pageContext.request.contextPath}/board/main" class="nav-link">유머게시판</a>
            </li>
            <li class="nav-item">
                <a href="${pageContext.request.contextPath}/board/main" class="nav-link">정치게시판</a>
            </li>
            <li class="nav-item">
                <a href="${pageContext.request.contextPath}/board/main" class="nav-link">스포츠게시판</a>
            </li>
        </ul>

        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a href="${pageContext.request.contextPath}/user/login" class="nav-link">로그인</a>
            </li>
            <li class="nav-item">
                <a href="${pageContext.request.contextPath}/user/join" class="nav-link">회원가입</a>
            </li>
            <li class="nav-item">
                <a href="${pageContext.request.contextPath}/user/modify" class="nav-link">정보수정</a>
            </li>
            <li class="nav-item">
                <a href="${pageContext.request.contextPath}/user/logout" class="nav-link">로그아웃</a>
            </li>
        </ul>
    </div>
</nav>