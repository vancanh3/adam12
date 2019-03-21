<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Cổng thông tin Đại học Duy Tân</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="/resources/js/jquery.twbsPagination.min.js"/>

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<h1>Danh sách điểm đánh giá rèn luyện sinh viên lớp: ${tenlop.tenLop}, ${hocki.tenHK}, ${namhoc.tenNH}</h1>
<div class="container">
    <%int i = 0;%>
    <table class="table" id="dataTable">
        <thead>
        <tr>
            <th>Mã Sinh Viên</th>
            <th>Họ và tên </th>
            <th>Năm sinh</th>
            <th>Điểm</th>
            <th>Xếp loại</th>
        </tr>
        </thead>
        <<a href="tongdiem?id={lop.getId()}"></a>
        <tbody>
        <c:forEach items="${tongxl}" var="xl">
            <tr class="rem1">
                <td >${xl.sinhVien.maSV}</td>
                <td >${xl.sinhVien.tenSV}</td>
                <td >${xl.sinhVien.ngaysinh}</td>
                <td >${xl.tongDiem}</td>
                <td >${xl.xepLoai}</td>
            </tr>

        </c:forEach>
        </tbody>
    </table>
    <nav aria-label="Page navigation">
        <ul class="pagination" id="pagination"></ul>
    </nav>
   >
</div>
<input type="text" value="${tongpage}" style="display: none" id="sumpage">
</body>
<script type="text/javascript">
    $(function () {
        var tongpage = $("#sumpage").val();
        alert("tong so phan tu la: " +tongpage);
        var s = parseInt(tongpage);
        var kq = parseInt(s/3);
        alert("tong so trang la: " +kq);
        window.pagObj = $('#pagination').twbsPagination({
            totalPages: kq,
            visiblePages: 3
        }).on('page', function (event, page) {
            $.ajax({
                type : "GET",
                contentType : "application/json",
                url : "/rank/" + page,
                //data : comment,
                timeout : 100000,
                success : function(data) {
                    console.log(data);
                    display(data);
                }
            });
        });


        function display(data) {
            $("#dataTable tbody").html(data);
        }
    });
</script>
<style>
    #pagination{
        margin-left: 350px;
    }
</style>
</html>
