<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../common/header.jsp" />
<jsp:include page="../common/nav.jsp" />

<div class="container">
  <main>
    <div class="py-5 text-center">
      <img class="d-block mx-auto mb-4" src="../assets/brand/bootstrap-logo.svg" alt="" width="72" height="57">
      <h2>Board Modify</h2>
      <p class="lead">Below is an example form built entirely with Bootstrap’s form controls. Each required form group has a validation state that can be triggered by attempting to submit the form without completing it.</p>
    </div>

    <div class="row g-5">
      <div class="col-md-5 col-lg-4 order-md-last">
        <h4 class="d-flex justify-content-between align-items-center mb-3">
          <span class="text-primary">Your cart</span>
          <span class="badge bg-primary rounded-pill">3</span>
        </h4>
        <ul class="list-group mb-3">
          <li class="list-group-item d-flex justify-content-between lh-sm">
            <div>
              <h6 class="my-0">Product name</h6>
              <small class="text-muted">Brief description</small>
            </div>
            <span class="text-muted">$12</span>
          </li>
          <li class="list-group-item d-flex justify-content-between lh-sm">
            <div>
              <h6 class="my-0">Second product</h6>
              <small class="text-muted">Brief description</small>
            </div>
            <span class="text-muted">$8</span>
          </li>
          <li class="list-group-item d-flex justify-content-between lh-sm">
            <div>
              <h6 class="my-0">Third item</h6>
              <small class="text-muted">Brief description</small>
            </div>
            <span class="text-muted">$5</span>
          </li>
          <li class="list-group-item d-flex justify-content-between bg-light">
            <div class="text-success">
              <h6 class="my-0">Promo code</h6>
              <small>EXAMPLECODE</small>
            </div>
            <span class="text-success">−$5</span>
          </li>
          <li class="list-group-item d-flex justify-content-between">
            <span>Total (USD)</span>
            <strong>$20</strong>
          </li>
        </ul>

        <form class="card p-2">
          <div class="input-group">
            <input type="text" class="form-control" placeholder="Promo code">
            <button type="submit" class="btn btn-secondary">Redeem</button>
          </div>
        </form>
      </div>
      
<!-- 게시글 편집란 시작 -->
      <div class="col-md-7 col-lg-8">
        <h4 class="mb-3">Board information Modify</h4>
        <c:set value="${bdto.bvo }" var="bvo" />
        <form action="/board/modify" method="post" enctype="multipart/form-data">
        <input type="hidden" name="bno" value="${bvo.bno }">
        <input type="hidden" name="pageNo" value="${pgvo.pageNo }">
        <input type="hidden" name="qty" value="${pgvo.qty }">
        <input type="hidden" name="type" value="${pgvo.type }">
    	<input type="hidden" name="keyword" value="${pgvo.keyword }">
          <div class="row g-3">

            <c:set value="${bdto.bvo }" var="bvo" />

            <div class="col-12">
              <label for="email" class="form-label">Writer</label>
              <div class="input-group has-validation">
                <span class="input-group-text">@</span>
                <input type="email" class="form-control" name="writer"
                id="writer" value="${bvo.writer }" readonly>              
              </div>
            </div>
            
            <div class="col-12">
              <label for="regAt" class="form-label">Reg At</label>
              <input type="text" class="form-control" value="${bvo.regAt }" readOnly>              
            </div>
            <div class="col-12">
              <label for="modAt" class="form-label">Mod At</label>
              <input type="text" class="form-control" value="${bvo.modAt }" readOnly>              
            </div>
            <div class="col-12">
              <label for="readCount" class="form-label">Read Count</label>
              <input type="text" class="form-control" value="${bvo.readCount }" readOnly>              
            </div>
            <div class="col-12">
              <label for="title" class="form-label">Title</label>
              <input type="text" class="form-control" name="title"
               id="title" placeholder="Title" value="${bvo.title }" >              
            </div>
			
            <div class="col-12">
              <label for="cont" class="form-label">Content</label>
              <textarea class="form-control" name="content" 
               id="cont" placeholder="Content">${bvo.content }</textarea>              
            </div>
            
            <c:set value="${bdto.bfList }" var="bfList" />
			<!-- 새로운 파일 등록 -->
			
			 <div class=" col-12 d-grid">
  				<input class="form-control" type="file" style="display: none;"
  				 id="files" name="files" multiple>
  				<button type="button" id="trigger" class="btn btn-outline-primary btn-block">files upload</button>
			</div>
			<div class="col-12" id="fileZone">
			
			
			</div>
			<!-- 기존 파일 목록 -->
			<div class="col-12">
              <ul class="list-group list-group-flush">
				<c:forEach items="${bfList }" var="bfvo">
				  <li class="list-group-item d-flex justify-content-between align-items-start">
				    <div class="ms-2 me-auto">
				    	<c:choose>
				      	<c:when test="${!empty bfvo.fileName }">
				     	<img alt="" src="/fileUpload/${bfvo.saveDir }/${bfvo.uuid}_th_${bfvo.fileName}">
				      	</c:when>
				      	<c:otherwise>
				      		no image file.
				      	</c:otherwise>
				    	</c:choose>
				      <div class="fw-bold">${bfvo.fileName }</div>
				      ${bfvo.regAt}
				    </div>
				    <span class="badge bg-secondary rounded-pill">${bfvo.fileSize } Byte
				    </span>
				    
				    <button type="button" class="btn btn-sm btn-outline-danger file-x py-0" data-uuid="${bfvo.uuid }">x</button>
				  </li>  
				</c:forEach>
			  </ul>  
            </div>
    		<button type="submit" class="btn btn-outline-warning" id="regBtn">Submit</button>
        </div>
        </form>
      </div>
    </div>
  </main>
</div>
<script src="/resources/js/board.modify.js" ></script>
<script src="/resources/js/board.register.js" ></script>

<jsp:include page="../common/footer.jsp" />