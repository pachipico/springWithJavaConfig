<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../common/header.jsp" />
<jsp:include page="../common/nav.jsp" />
<div class="container my-3">
<div class="row">
	<div class="col-sm-12 col-md-6">
		<h2>Product List
			<a href="/product/register" class="btn btn-outline-primary">REG</a>
		</h2>
	</div>
	<div class="col-sm-12 col-md-6">
		<form action="/product/list" method="get">
			<div class="input-group mb-3">
			<c:set value="${pgn.pgvo.type }" var="typed" />
			  <select class="form-select" name="type">
			  	  <option ${typed == null ? 'selected' : '' } >Choose...</option>
			    <option value="d" ${typed == 'd' ? 'selected' : '' } >Description</option>
			    <option value="pn" ${typed == 'pn' ? 'selected' : '' }>Product Name</option>
			    <option value="w" ${typed == 'w' ? 'selected'  : ''}>Writer</option>
			    <option value="c" ${typed == 'c' ? 'selected' : '' }>Category</option>
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
      <th scope="col">Product Name</th>
      <th scope="col">price</th>
	  <th scope="col">Made By</th>      
      <th scope="col">Writer</th>
      <th scope="col">Read Count</th>
      <th scope="col">Qty</th>
      <th scope="col">Mod At</th>
      
    </tr>
  </thead>
  <tbody>
  	<c:forEach items="${list }" var="pvo">
    <tr>
      <th scope="row">${pvo.pno }</th>
      <td><a href="/product/detail?pno=${pvo.pno }&pageNo=${pgn.pgvo.pageNo}&qty=${pgn.pgvo.qty}&type=${pgn.pgvo.type}&keyword=${pgn.pgvo.keyword}">${pvo.pname } ${pvo.hasFile>0 ? '[첨부]' : '' }
      <c:if test="${pvo.cmtQty > 0 }">
      <span class="badge rounded-pill bg-info">
    		${pvo.cmtQty  }
    		</span></a></td>
      </c:if>
      </a></td>
      <td>${pvo.price }</td>
      <td>${pvo.madeBy }</td>
      <td>${pvo.writer }</td>
      <td>${pvo.readCount }</td>
      <td>${pvo.cmtQty }</td>
      <td>${pvo.modAt }</td>
    </tr>
    </c:forEach>    
  </tbody>
</table>
 <ul class="pagination justify-content-center">
    <li class="page-item">
      <c:if test="${pgn.prev }">
      	<a href="/product/list?pageNo=${pgn.startPage - 1 }&qty=${pgn.pgvo.qty}&type=${pgn.pgvo.type}&keyword=${pgn.pgvo.keyword}" class="page-link">Previous</a>
      </c:if>
    </li>
    <c:forEach begin="${pgn.startPage }" end="${pgn.endPage }"  var="i">
    
    <li class="page-item ${pgn.pgvo.pageNo == i ? 'active' : '' }"><a class="page-link" href="/product/list?pageNo=${i }&qty=${pgn.pgvo.qty}&type=${pgn.pgvo.type}&keyword=${pgn.pgvo.keyword}">${i }</a></li>
   </c:forEach>
    <li class="page-item">
      <c:if test="${pgn.next }">
	    <a class="page-link" href="/product/list?pageNo=${pgn.endPage + 1 }&qty=${pgn.pgvo.qty}&type=${pgn.pgvo.type}&keyword=${pgn.pgvo.keyword}">Next</a>
      </c:if>
    </li>
  </ul>
  <a href="/product/list?pageNo=1&qty=${pgn.pgvo.qty }&type=${pgn.pgvo.type}&keyword=${pgn.pgvo.keyword}" class="btn">처음으로</a>
  <a class="btn" href="/product/list?pageNo=${pgn.realEndPage }&qty=${pgn.pgvo.qty }&type=${pgn.pgvo.type}&keyword=${pgn.pgvo.keyword}">마지막으로</a>
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