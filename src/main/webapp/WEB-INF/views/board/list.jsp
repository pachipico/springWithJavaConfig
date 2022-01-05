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
      <td><a href="/board/detail?bno=${bvo.bno }&pageNo=${pgn.pgvo.pageNo}&qty=${pgn.pgvo.qty}">${bvo.title }</a></td>
      <td>${bvo.writer }</td>
      <td>${bvo.readCount }</td>
      <td>${bvo.modAt }</td>
    </tr>
    </c:forEach>    
  </tbody>
</table>
 <ul class="pagination justify-content-center">
    <li class="page-item">
      <c:if test="${pgn.prev }">
      	<a href="/board/list?pageNo=${pgn.startPage - 1 }&qty=${pgn.pgvo.qty}" class="page-link">Previous</a>
      </c:if>
    </li>
    <c:forEach begin="${pgn.startPage }" end="${pgn.endPage }"  var="i">
    
    <li class="page-item ${pgn.pgvo.pageNo == i ? 'active' : '' }"><a class="page-link" href="/board/list?pageNo=${i }&qty=${pgn.pgvo.qty}">${i }</a></li>
   </c:forEach>
    <li class="page-item">
      <c:if test="${pgn.next }">
	    <a class="page-link" href="/board/list?pageNo=${pgn.endPage + 1 }&qty=${pgn.pgvo.qty}">Next</a>
      </c:if>
    </li>
  </ul>
  <a href="/board/list?pageNo=1&qty=${pgn.pgvo.qty }" class="btn">처음으로</a>
  <a class="btn">마지막으로</a>
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