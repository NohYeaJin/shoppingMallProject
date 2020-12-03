<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/WEB-INF/views/layout/top.jsp" %>

 <!-- BREADCRUMBS SETCTION START -->
        <div class="breadcrumbs-section plr-200 mb-80">
            <div class="breadcrumbs overlay-bg">
                <div class="container">
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="breadcrumbs-inner">
                                <h1 class="breadcrumbs-title">Login / Register</h1>
                                <ul class="breadcrumb-list">
                                    <li><a href="/">Home</a></li>
                                    <li>Login / Register</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- BREADCRUMBS SETCTION END -->

        <!-- Start page content -->
        <div id="page-content" class="page-wrapper">

            <!-- LOGIN SECTION START -->
            <div class="login-section mb-80">
                <div class="container">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="registered-customers">
                                <h6 class="widget-title border-left mb-50">REGISTERED CUSTOMERS</h6>
                                <form method="post" action="/loginAsk">
                                    <div class="login-account p-30 box-shadow">
                                        <p>If you have an account with us, Please log in.</p>
                                        <h1><c:out value = "${error}"/></h1>
                                        <h2><c:out value = "${logout}"/></h2>
                                        <input type="text" name="username" placeholder="UserName">
                                        <input type="password" name="password" placeholder="Password">
                                        <p><small><a href="#">Forgot our password?</a></small></p>
                                        <button class="submit-btn-1 btn-hover-1" type="submit">login</button>
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_crsf.token}"  />
                                    </div>
                                </form>
                            </div>
                        </div>
                        <!-- new-customers -->
                        <div class="col-md-6">
                            <div class="new-customers">
                                <form action="/registerAsk">
                                    <h6 class="widget-title border-left mb-50">NEW CUSTOMERS</h6>
                                    <div class="login-account p-30 box-shadow">
                                    	<div>
	                                    	<div class="col-sm-8">
	                                        	<input type="text" id="idsID" name="id" placeholder="Id here...">
	                                        </div>
	                                        <div class="col-sm-4">
	                                        	<button class="submit-btn-1 btn-hover-1" id="idDupCheck" type="button">중복확인</button>
	                                        </div>
	                                        <div id="idDupResult" style="color:red;"></div>
                                        </div>
                                        <input type="text" name="name" placeholder="Name here...">
                                        <input type="password"  name="password" placeholder="Password here...">
                                        <input type="text" name="email" placeholder="Email address here...">
                                        <input type="text" name="address" placeholder="Address here...">
                                        <input type="text" name="phoneNum" placeholder="Phone here...">
                                        
                                        <div class="row">
                                            <div class="col-md-6">
                                                <button class="submit-btn-1 mt-20 btn-hover-1" type="submit" value="register">Register</button>
                                            </div>
                                            <div class="col-md-6">
                                                <button class="submit-btn-1 mt-20 btn-hover-1 f-right" type="reset">Clear</button>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- LOGIN SECTION END -->             

        </div>
        
<%@include file="/WEB-INF/views/layout/foot.jsp" %>
        <!-- End page content -->
<script type="text/javascript">
	$('#idDupCheck').on('click', function(){
		var params = {
			id:$("#idsID").val()
		}

		$.ajax({
			type:"GET",
			url:"/IdDupCheck",
			data:params,
			success:function(data){
				if(data === "notContain"){
					$("#idDupResult").text("사용 가능 아이디 입니다.");
				}else{
					$("#idDupResult").text("이미 존재하는 아이디. 다른 아이디를 사용해주세요.");
				}
			},
			error : function(){
				alert("error");
			}
		});
	});
</script>
