<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../common/header.jsp" />
<jsp:include page="../common/nav.jsp" />

<div class="container">
	<main>
		<div class="py-5 text-center">
			<img class="d-block mx-auto mb-4"
				src="../assets/brand/bootstrap-logo.svg" alt="" width="72"
				height="57">
			<h2>Product Detail</h2>
			<p class="lead">Below is an example form built entirely with
				Bootstrap’s form controls. Each required form group has a validation
				state that can be triggered by attempting to submit the form without
				completing it.</p>
		</div>

		<div class="row g-5">
			<div class="col-md-5 col-lg-4 order-md-last"></div>
			<c:set value="${bdto.pvo }" var="pvo" />
			<!-- 상품상세정보란 시작 -->
			<div class="col-md-7 col-lg-8">
				<h4 class="mb-3">Board information</h4>
				<form action="/board/remove" method="post">
					<input type="hidden" name="pno" value="${pvo.pno }"> <input
						type="hidden" name="type" value="${pgvo.type }"> <input
						type="hidden" name="keyword" value="${pgvo.keyword }">
					<div class="row g-3">

						<div class="col-12">
							<label for="email" class="form-label">Writer</label>
							<div class="input-group has-validation">
								<span class="input-group-text">@</span> <input type="email"
									class="form-control" name="writer" id="writer"
									value="${pvo.writer }" readonly>
							</div>
						</div>

						<div class="col-12">
							<label for="regAt" class="form-label">Reg At</label> <input
								type="text" class="form-control" id="regAt"
								value="${pvo.regAt }" readOnly>
						</div>
						<div class="col-12">
							<label for="modAt" class="form-label">Mod At</label> <input
								type="text" class="form-control" id="modAt"
								value="${pvo.modAt }" readOnly>
						</div>
						<div class="col-12">
							<label for="readCount" class="form-label">Read Count</label> <input
								type="text" class="form-control" id="readCount"
								value="${pvo.readCount }" readOnly>
						</div>


						<div class="col-12">
							<label for="pname" class="form-label">Name</label> <input
								type="text" class="form-control" name="pname" id="pname"
								placeholder="Name" value="${pvo.pname }" readOnly>
						</div>

						<div class="col-12">
							<label for="price" class="form-label">Price</label> 
							<input
								type="text" class="form-control" name="price" id="price"
								placeholder="Price" value="${pvo.price }" readOnly>
						</div>

						<div class="col-6">
							<label for="category" class="form-label">Category</label>
							<input type="text" class="form-control" name="category" id="category" value="${pvo.category }" readOnly >
						</div>
						
						<div class="col-6">
							<label for="madeBy" class="form-label">Made By</label>
							<input type="text" class="form-control" name="madeBy" id="madeBy" value="${pvo.madeBy }" readOnly >
						</div>
						<div class="col-12">
							<label for="desc" class="form-label">Description</label>
							<textarea id="desc" class="form-control" name="desc" readOnly>${pvo.description }</textarea>
						</div>
						<c:set value="${bdto.bfList }" var="bfList" />
			
							<div class="col-12">
				              <ul class="list-group list-group-flush">
								<c:forEach items="${bfList }" var="bfvo">
								  <li class="list-group-item d-flex justify-content-between align-items-start">
								    <div class="ms-2 me-auto">
								    	<c:choose>
								      	<c:when test="${!empty bfvo.fileName }">
								     	<img alt="" src="/pfileUpload/${bfvo.saveDir }/${bfvo.uuid}_th_${bfvo.fileName}">
								      	</c:when>
								      	<c:otherwise>
								      		no image file.
								      	</c:otherwise>
								    	</c:choose>
								      <div class="fw-bold">${bfvo.fileName }</div>
								      ${bfvo.regAt}
								    </div>
								    <span class="badge bg-secondary rounded-pill">${bfvo.fileSize }</span>
								  </li>  
								</c:forEach>
							  </ul>  
				            </div>
						<div class="col-4">
							<a
								href="/product/list?pageNo=${pgvo.pageNo }&qty=${pgvo.qty}&type=${pgvo.type}&keyword=${pgvo.keyword}"
								class="btn btn-outline-primary">LIST</a>
						</div>
						<div class="col-4">
							<a
								href="/product/modify?pno=${pvo.pno }&pageNo=${pgvo.pageNo }&qty=${pgvo.qty}&type=${pgvo.type}&keyword=${pgvo.keyword}"
								type="button" id="modBtn" class="btn btn-outline-warning">MOD</a>
						</div>
						<div class="col-4">
							<button type="submit" id="delBtn" class="btn btn-outline-danger">DEL</button>
						</div>
					</div>
				</form>
			</div>
		
		
	

		</div>
	</main>
</div>
<form action="/product/remove" method="post" id="delForm">
	<input type="hidden" name="pno" value="${pvo.pno }"> <input
		type="hidden" name="pageNo" value="${pgvo.pageNo }"> <input
		type="hidden" name="type" value="${pgvo.type }"> <input
		type="hidden" name="keyword" value="${pgvo.keyword }">
</form>
<!-- 댓글영역 시작 -->
<div class="container">
<div class="input-group my-3">
	<span class="input-group-text" id="cmtWriter">Anonymous</span>
	<input type="text" class="form-control" id="cmtText" value="Test Add Comment ">
	<button class="btn btn-success position-relative" id="cmtPostBtn" type="button">Post
  			<span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
    		<span id="cmtCnt"></span>개	
    		<span class="visually-hidden">unread messages</span></span>
	</button>
</div>
<ul class="list-group list-group-flush" id="cmtListArea">
  <li class="list-group-item d-flex justify-content-between align-items-start">
    <div class="ms-2 me-auto">
      <div class="fw-bold">Writer</div>
      Content for Comment
    </div>
    <span class="badge bg-dark rounded-pill">modAt</span>
  </li>
</ul>
<div class="row" id="cmtPaging">

	<ul class="pagination justify-content-center">
	    <li class="page-item">
	      <a href="" class="page-link">Prev</a>
	    </li>
	    
	    <li class="page-item"
	     aria-current="page">
	      <a class="page-link" href="">${i }</a>
	    </li>
	    
	    <li class="page-item">
	      <a class="page-link" href="">Next</a>
	    </li>
    
  </ul>
</div>

<!-- The Modal -->
<div class="modal" id="myModal">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">Anonymous</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>

      <!-- Modal body -->
      <div class="modal-body">
        <div class="input-group my-3">
			<input type="text" class="form-control" id="cmtTextMod">
			<button class="btn btn-success" id="cmtModBtn" type="button">Post</button>
		</div>
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Close</button>
      </div>

    </div>
  </div>
</div>
</div>
<script type="text/javascript">
	const pnoVal = document.querySelector("input[name=pno]").value;
	const ses = '<c:out value="${ses.email}"/>'
</script>
<script src="/resources/js/board.detail.js"></script>
<script src="/resources/js/product.comment.js"></script> 
<script>
	let isMod = '<c:out value="${isMod}"/>';
	if (parseInt(isMod)) {
		alert('상품 수정 성공~');
	}
	getCommentList(pnoVal);
</script>
<jsp:include page="../common/footer.jsp" />