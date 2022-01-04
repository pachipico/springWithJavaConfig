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
        <form action="/board/modify" method="post">
        <input type="hidden" name="bno" value="${bvo.bno }">
          <div class="row g-3">

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
              <input type="text" class="form-control" id="regAt" value="${bvo.regAt }" readonly >              
            </div>
            <div class="col-12">
              <label for="modAt" class="form-label">Mod At</label>
              <input type="text" class="form-control" id="modAt" value="${bvo.modAt }" readonly >              
            </div>
            <div class="col-12">
              <label for="readCount" class="form-label">Read Count</label>
              <input type="text" class="form-control" id="readCount" value="${bvo.readCount }"  readonly>              
            </div>
           
            
            <div class="col-12">
              <label for="title" class="form-label">Title</label>
              <input type="text" class="form-control" name="title"
               id="title" placeholder="Title" value="${bvo.title }" >              
            </div>

            <div class="col-12">
              <label for="content" class="form-label">Content</label>
              <textarea class="form-control" name="content" 
               id="content" placeholder="Content">${bvo.content }</textarea>              
            </div>

          

    		<button type="submit" id="modBtn" class="btn btn-outline-primary">edit</button>
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
  		<!-- The Modal -->
		<div class="modal" id="myModal">
		  <div class="modal-dialog">
		    <div class="modal-content">
		
		      <!-- Modal Header -->
		      <div class="modal-header">
		        <h4 class="modal-title">${ses.email }</h4>
		        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
		      </div>
		
		      <!-- Modal body -->
		      <div class="modal-body">
		      	 <div class="input-group">
		      	 	 
			 		 <input type="text" class="form-control" id="cmtModText">
					 <button class="btn btn-outline-primary" id="cmtModBtn" type="button">Modify Comment</button>
				</div>
		      </div>
		
		      <!-- Modal footer -->
		      <div class="modal-footer">
		        <button type="button" class="btn btn-danger" id="modalClose" data-bs-dismiss="modal">Close</button>
		      </div>
		
		    </div>
		  </div>
		</div>
 
      
    </div>
  </main>
</div>


<jsp:include page="../common/footer.jsp" />

<jsp:include page="../common/footer.jsp" />