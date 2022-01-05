<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../common/header.jsp" />
<jsp:include page="../common/nav.jsp" />
<div class="container my-3">
<div class="row">
	<div class="col-sm-12 col-md-6">
		<h2>Product List
			<a href="/board/register" class="btn btn-outline-primary">REG</a>
		</h2>
	</div>
	<div class="col-sm-12 col-md-6">
		<form action="/board/list" method="get">
			<div class="input-group mb-3">
			<c:set value="${pgn.pgvo.type }" var="typed" />
			  <select class="form-select" name="type">
			  	  <option ${typed == null ? 'selected' : '' } >Choose...</option>
			    <option value="t" ${typed == 't' ? 'selected' : '' } >Title</option>
			    <option value="c" ${typed == 'c' ? 'selected' : '' }>Content</option>
			    <option value="w" ${typed == 'w' ? 'selected'  : ''}>Writer</option>
			    <option value="tc" ${typed == 'tc' ? 'selected' : '' }>Title or Content</option>
			    <option value="tw" ${typed == 'tw' ? 'selected' : '' }>Title or Writer</option>
			    <option value="cw" ${typed == 'cw' ? 'selected' : '' }>Content of Writer</option>
			  </select>
			  <input type="text" class="form-control" name="keyword" placeholder="search" value="${pgn.pgvo.keyword }">
			  <input type="hidden" name="pageNo" value="${pgn.pgvo.pageNo }" >
			  <input type="hidden" name="qty" value="${pgn.pgvo.qty }" >
			  <button type="submit" class="btn btn-outline-success position-relative">search
			  <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
    			${pgn.totalCount }
    			  <span class="visually-hidden">unread messages</span>
				  </span>
			  </button>
			</div>
		</form>
	</div>
</div>
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
      <td><a href="/board/detail?bno=${bvo.bno }&pageNo=${pgn.pgvo.pageNo}&qty=${pgn.pgvo.qty}&type=${pgn.pgvo.type}&keyword=${pgn.pgvo.keyword}">${bvo.title }</a></td>
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
      	<a href="/board/list?pageNo=${pgn.startPage - 1 }&qty=${pgn.pgvo.qty}&type=${pgn.pgvo.type}&keyword=${pgn.pgvo.keyword}" class="page-link">Previous</a>
      </c:if>
    </li>
    <c:forEach begin="${pgn.startPage }" end="${pgn.endPage }"  var="i">
    
    <li class="page-item ${pgn.pgvo.pageNo == i ? 'active' : '' }"><a class="page-link" href="/board/list?pageNo=${i }&qty=${pgn.pgvo.qty}&type=${pgn.pgvo.type}&keyword=${pgn.pgvo.keyword}">${i }</a></li>
   </c:forEach>
    <li class="page-item">
      <c:if test="${pgn.next }">
	    <a class="page-link" href="/board/list?pageNo=${pgn.endPage + 1 }&qty=${pgn.pgvo.qty}&type=${pgn.pgvo.type}&keyword=${pgn.pgvo.keyword}">Next</a>
      </c:if>
    </li>
  </ul>
  <a href="/board/list?pageNo=1&qty=${pgn.pgvo.qty }&type=${pgn.pgvo.type}&keyword=${pgn.pgvo.keyword}" class="btn">처음으로</a>
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