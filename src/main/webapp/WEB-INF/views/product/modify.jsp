<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../common/header.jsp" />
<jsp:include page="../common/nav.jsp" />

<div class="container">
  <main>
    <div class="py-5 text-center">
      <img class="d-block mx-auto mb-4" src="../assets/brand/bootstrap-logo.svg" alt="" width="72" height="57">
      <h2>Product Detail</h2>
      <p class="lead">Below is an example form built entirely with Bootstrap’s form controls. Each required form group has a validation state that can be triggered by attempting to submit the form without completing it.</p>
    </div>

    <div class="row g-5">
      <div class="col-md-5 col-lg-4 order-md-last">

        
      </div>
      
<!-- 게시글 수정란 시작 -->
      <div class="col-md-7 col-lg-8">
        <h4 class="mb-3">Board information modify</h4>
						<c:set value="${bdto.pvo }" var="pvo" />
        <form action="/product/modify" method="post" enctype="multipart/form-data">
        <input type="hidden" name="pno" value="${pvo.pno }">
        <input type="hidden" name="pageNo" value="${pgvo.pageNo }">
        <input type="hidden" name="type" value="${pgvo.type }">
        <input type="hidden" name="keyword" value="${pgvo.keyword }">
        
        <input type="hidden" name="qty" value="${pgvo.qty }">
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
								placeholder="Name" value="${pvo.pname }"  >
						</div>

						<div class="col-12">
							<label for="price" class="form-label">Price</label> 
							<input
								type="text" class="form-control" name="price" id="price"
								placeholder="Price" value="${pvo.price }"  >
						</div>

						<div class="col-6">
							<label for="category" class="form-label">Category</label>
							<input type="text" class="form-control" name="category" id="category" value="${pvo.category }"   >
						</div>
						
						<div class="col-6">
							<label for="madeBy" class="form-label">Made By</label>
							<input type="text" class="form-control" name="madeBy" id="madeBy" value="${pvo.madeBy }"   >
						</div>
						<div class="col-12">
							<label for="description" class="form-label">Description</label>
							<textarea id="description" class="form-control" name="description"  >${pvo.description }</textarea>
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
				     	<img alt="" src="/pfileUpload/${bfvo.saveDir }/${bfvo.uuid}_th_${bfvo.fileName}">
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
    		<button type="submit" id="regBtn" class="btn btn-outline-primary">edit</button>
        </div>
        </form>
      </div>
      <div class="input-group">
      	  <span class="input-group-text" id="cmtWriter">${ses.email }</span>
		  <input type="text" class="form-control" id="cmtText">
		  
		  <button class="btn btn-outline-primary" id="cmtPostBtn" type="button">Add Comment</button>
		</div>
		
		<ul class="list-group list-group-flush " id="cmtListArea">
  			<li class="list-group-item d-flex justify-content-between align-items-center">
  			  <div class="ms-2 me-auto">
    		  <div class="fw-bold">writer</div>
   			   Content for list item
 		      </div>
    		  <span class="badge bg-primary rounded-pill">modAt</span>
  			</li>
  		</ul>
  		
 
      
    </div>
  </main>
</div>
<script src="/resources/js/product.modify.js"></script>
<script src="/resources/js/product.register.js"></script>


<jsp:include page="../common/footer.jsp" />

<jsp:include page="../common/footer.jsp" />