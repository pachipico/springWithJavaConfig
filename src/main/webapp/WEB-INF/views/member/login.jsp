
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author"
	content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<meta name="generator" content="Hugo 0.88.1">
<title>Signin Template · Bootstrap v5.1</title>

<link rel="canonical"
	href="https://getbootstrap.com/docs/5.1/examples/sign-in/">



<!-- Bootstrap core CSS -->
<link href="/resources/dist/css/bootstrap.min.css" rel="stylesheet">

<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}
</style>


<!-- Custom styles for this template -->
<link href="/resources/dist/css/signin.css" rel="stylesheet">
</head>
<body class="text-center">

	<main class="form-signin">
		<form action="/member/login" method="POST">
			
			<h1 class="h3 mb-3 fw-normal">Please sign in</h1>

			<div class="form-floating">
				<input type="email" class="form-control" id="floatingInput"
					name="email" placeholder="name@example.com" value="${email }"> <label
					for="floatingInput" >Email address</label>
			</div>
			<div class="form-floating">
				<input type="password" class="form-control" id="floatingPassword"
					name="pwd" placeholder="Password"> <label
					for="floatingPassword">Password</label>
			</div>
			<c:if test="${!empty errMsg }">
			<div class="text-danger mb-3">
				<c:choose>
					<c:when test="${errMsg == 'Bad credentials' }">
						<c:set value="이메일 혹은 비밀번호가 틀립니다." var="errText" />
					</c:when>
					<c:otherwise>
						<c:set value="관리자에게 문의하세요." var="errText" />
					</c:otherwise>
				</c:choose>
				${errText }
			</div>
			</c:if>
			<button class="w-100 btn btn-lg btn-primary" type="submit">Sign
				in</button>
			<p class="mt-5 mb-3 text-muted">&copy; 2017–2021</p>
		</form>
	</main>



</body>
</html>