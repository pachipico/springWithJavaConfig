<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../common/header.jsp" />
<jsp:include page="../common/nav.jsp" />
<div class="container my-3">
<h2>Product List
<a href="/board/register" class="btn btn-outline-primary">REG</a>
</h2>
<table class="table table-hover">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">Title</th>
      <th scope="col">Writer</th>
      <th scope="col">Read Count</th>
      <th scope="col">Mod At</th>
    </tr>
  </thead>
  <tbody>
  	<c:forEach items="${list }" var="bvo">
    <tr>
      <th scope="row">${bvo.bno }</th>
      <td><a href="/board/detail?bno=${bvo.bno }">${bvo.title }</a></td>
      <td>${pvo.writer }</td>
      <td>${pvo.readCount }</td>
      <td>${pvo.modAt }</td>
    </tr>
    </c:forEach>    
  </tbody>
</table>
</div>
<script>
let isReg = '<c:out value="${isReg}"/>';
let isDel = '<c:out value="${isDel}"/>';
if (parseInt(isReg)) {
	alert('등록 성공~');
}
if (parseInt(isDel)) {
	alert('삭제 성공~');
}
</script>
<jsp:include page="../common/footer.jsp" />