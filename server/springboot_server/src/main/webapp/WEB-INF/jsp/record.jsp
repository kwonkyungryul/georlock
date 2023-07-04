<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="shortcut icon" href="/asset/images/favicon.ico" type="images/x-icon"/>
    <link rel="stylesheet" href="/asset/css/record.css"/>
    <title>출입기록</title>
    <script
            src="https://kit.fontawesome.com/a962b14940.js"
            crossorigin="anonymous"
    ></script>
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<%--header--%>
<header>
    <div class="logoImg">
        <a href="record" title="record">
            <img src="/asset/images/georlocklogo.png">
        </a>
    </div>
    <%--로그아웃--%>
    <div class="logout">
        <a href="logout" title="logout">
            <span>Logout</span>
        </a>
    </div>
</header>


<div id="main">
    <nav>
        <div class="nav-item">
            <ul>
                <li>
                    <a href="record" title="record">
                        <span>출입기록</span>
                    </a>
                </li>
                <li>
                    <a href="insert_btn" title="insert">
                        <span>출입권한등록</span>
                    </a>
                </li>
                <li>
                    <a href="access" title="access">
                        <span>출입권한설정</span>
                    </a>
                </li>
            </ul>
        </div>
    </nav>
    <article class="main-item">
        <p>출입기록</p>

        <%--검색창--%>
        <form id="searchForm" action="recordSearch" method="post">
            <div class="search">
                <input type="date" name="startDate"/> ~
                <input type="date" name="endDate"/>
                <input type="text" name="textSearch" class="search_txt" placeholder="Search..."/>
                <button type="submit" value="검색">
                    <i class="fas fa-search"></i>
                </button>
            </div>
        </form>

        <%--테이블--%>
        <table class="table">
            <colgroup>
                <col width="20%"/>
                <col width="20%"/>
                <col width="40%"/>
                <col width="20%"/>
            </colgroup>
            <tr>
                <thead>
                <th>NO</th>
                <th>출입시간</th>
                <th>사원번호</th>
                <th>이름</th>
                </thead>
            </tr>


            <c:forEach items="${recordlist}" var="list" varStatus="status">
                <tr>
                    <td>${status.count}</td>
                    <td>${list.intime}</td>
                    <td>${list.empNo}</td>
                    <td>${list.username}</td>
                </tr>
            </c:forEach>


        </table>
        <%--페이징--%>
        <a class="upscroll" href="#" title="맨 위로"><i class="fas fa-angle-double-up" fa-sm></i></a>
    </article>

    <%--navside--%>

</div>
</body>
</html>