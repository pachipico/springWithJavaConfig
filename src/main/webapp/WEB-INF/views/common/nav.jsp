<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

 <header class="p-3 bg-dark text-white">
    <div class="container">
      <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
        <a href="/" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
          <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap"><use xlink:href="#bootstrap"/></svg>
        </a>

        <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
          <li><a href="/" class="nav-link px-2 text-secondary">Home</a></li>
          <li><a href="/board/list" class="nav-link px-2 text-white">Board</a></li>
          
          	<li><a href="/product/list" class="nav-link px-2 text-white">Product</a></li>
          
          <sec:authorize access="isAuthenticated()" >
	          <sec:authentication property="principal.mvo.email" var="authEmail" />
	          <sec:authentication property="principal.mvo.nickName" var="authNick" />
	          <sec:authentication property="principal.mvo.authList" var="auths" />
	          
	          <c:choose>
	          	<c:when test="${ auths.stream().anyMatch(authVO -> authVO.auth.equals('ROLE_ADMIN')).get() }">
	          		<li><a href="/member/list" class="nav-link px-2 text-white">${authNick }(${authEmail })</a></li>
	          	</c:when>
	          	
	          	<c:otherwise>
	          		<li><a href="/member/detail?email=${authEmail }" class="nav-link px-2 text-white">${authNick }(${authEmail })</a></li>
	          	</c:otherwise>
	          </c:choose>
	          <form action="/member/logout" method="POST" id="logOutForm">
	          	<input type="hidden" name="email" value="${authEmail }" >
  			  </form>
	          <li><a href="" id="logOutLink" class="nav-link px-2 text-white">Log Out</a></li>
          </sec:authorize>
          
          <sec:authorize access="isAnonymous()">
	          <li><a href="/member/register" class="nav-link px-2 text-white">Sign Up</a></li>
	          
	          <li><a href="/member/login" class="nav-link px-2 text-white">Login</a></li>
          </sec:authorize>
        </ul>

       

        <div class="text-end">
        
        </div>
      </div>
    </div>
  </header>
  
  <script>
  	document.getElementById("logOutLink").addEventListener("click" , (e) => {
  		e.preventDefault();
  		console.log("logout");
  		document.getElementById("logOutForm").submit();
  	});
  	
  
  </script>