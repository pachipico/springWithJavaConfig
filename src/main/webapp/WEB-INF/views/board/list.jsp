<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="../common/header.jsp" />
<jsp:include page="../common/nav.jsp" />
<div class="container my-3">
<div class="row">
	<div class="col-sm-12 col-md-6">
		<h2>Board List
		 <sec:authorize access="isAuthenticated()" >
	         <sec:authentication property="principal.mvo.email" var="authEmail" />
	         <sec:authentication property="principal.mvo.nickName" var="authNick" />
	         <sec:authentication property="principal.mvo.authList" var="auths" />
	         <c:if test="${ auths.stream().anyMatch(authVO -> authVO.auth.equals('ROLE_USER')).get() }">
				<a href="/board/register" class="btn btn-outline-primary">REG</a>
			 </c:if>
		</sec:authorize>
		</h2>	
	</div>
	<div class="col-sm-12 col-md-6">
	<form action="/board/list" method="get">
		<div class="input-group mb-3">
		<c:set value="${pgn.pgvo.type }" var="typed"/>
  			<select class="form-select" name="type">
    			<option ${typed == null ? 'selected':'' }>Choose...</option>
    			<option value="t" ${typed eq 't' ? 'selected':'' }>Title</option>
    			<option value="c" ${typed eq 'c' ? 'selected':'' }>Content</option>
    			<option value="w" ${typed eq 'w' ? 'selected':'' }>Writer</option>
    			<option value="tc" ${typed eq 'tc' ? 'selected':'' }>Title or Content</option>
    			<option value="tw" ${typed eq 'tw' ? 'selected':'' }>Title or Writer</option>
    			<option value="cw" ${typed eq 'cw' ? 'selected':'' }>Content or Writer</option>
  			</select>
  			<input class="form-control" type="text" name="keyword" placeholder="Search" value="${pgn.pgvo.keyword }">
  			<input type="hidden" name="pageNo" value="1">
  			<input type="hidden" name="qty" value="${pgn.pgvo.qty }">
  			<button type="submit" class="btn btn-success position-relative">
  			Search
  			<span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
    		${pgn.totalCount }
    		<span class="visually-hidden">unread messages</span></span>
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
      <!-- <th scope="col">Qty</th> -->
      <th scope="col">Mod At</th>
      
      <th scope="col">Attached</th>
    </tr>
  </thead>
  <tbody>
  	<c:forEach items="${list }" var="bvo">
    <tr>
      <th scope="row">${bvo.bno }</th>
      <td><a href="/board/detail?bno=${bvo.bno }&pageNo=${pgn.pgvo.pageNo}&qty=${pgn.pgvo.qty}&type=${pgn.pgvo.type}&keyword=${pgn.pgvo.keyword}">${bvo.title }	
      <c:if test="${bvo.cmtQty > 0 }">
      <span class="badge rounded-pill bg-info">
    		${bvo.cmtQty  }
    		<span class="visually-hidden">unread messages</span></span></a></td>
      </c:if>
      <td>${bvo.writer }</td>
      <td>${bvo.readCount }</td>
      <%-- <td>${bvo.cmtQty }</td> --%>
      <td>${bvo.modAt }</td>
      
      <td>${bvo.hasFile > 0 ? '????????????' : ''}</td>
    </tr>
    </c:forEach>    
  </tbody>
</table>
<ul class="pagination justify-content-center">
	<c:if test="${pgn.prev == true }">
    <li class="page-item">
      <a href="/board/list?pageNo=${pgn.startPage - 1 }&qty=${pgn.pgvo.qty}&type=${pgn.pgvo.type}&keyword=${pgn.pgvo.keyword}" class="page-link">Prev</a>
    </li>
    </c:if>
    <c:forEach begin="${pgn.startPage }" end="${pgn.endPage }" var="i">
    <li class="page-item ${pgn.pgvo.pageNo == i ? 'active':''}"
     aria-current="page">
      <a class="page-link" href="/board/list?pageNo=${i }&qty=${pgn.pgvo.qty}&type=${pgn.pgvo.type}&keyword=${pgn.pgvo.keyword}">${i }</a>
    </li>
    </c:forEach>
    <c:if test="${pgn.next == true }">
    <li class="page-item">
      <a class="page-link" href="/board/list?pageNo=${pgn.endPage + 1 }&qty=${pgn.pgvo.qty}&type=${pgn.pgvo.type}&keyword=${pgn.pgvo.keyword}">Next</a>
    </li>
    </c:if>
  </ul>
   <a href="/board/list?pageNo=1&qty=${pgn.pgvo.qty }&type=${pgn.pgvo.type}&keyword=${pgn.pgvo.keyword}" class="btn">????????????</a>
  <a class="btn" href="/board/list?pageNo=${pgn.realEndPage }&qty=${pgn.pgvo.qty }&type=${pgn.pgvo.type}&keyword=${pgn.pgvo.keyword}">???????????????</a>
</div>
<script>
let isUp = '<c:out value="${isUp}"/>';
let isDel = '<c:out value="${isDel}"/>';
if (parseInt(isUp)) {
	alert('????????? ?????? ??????~');
}
if (parseInt(isDel)) {
	alert('????????? ?????? ??????~');
}
</script>
<jsp:include page="../common/footer.jsp" />