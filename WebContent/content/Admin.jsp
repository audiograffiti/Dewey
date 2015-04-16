<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:template>
	<title>Admin</title>
	<h3>
		<!-- value can be a field of the action method that has a get method -->
		Welcome <s:property value="user"></s:property>
	</h3>
	<div class="container">
		<form class="form-inline  col-md-12">
			<div class="form-group">
				<input class="form-control" type="text" placeholder="Search"/>
			</div>
			<div class="form-group">
				<button class="btn btn-default">Search</button>
			</div>
			<div class = "form-group">
				<input class = "form-control" type="text" placeholder = "Card Number"/>
			</div>
			<div class = "form-group">
				<input class = "form-control" type="text" placeholder = "ISBN"/>
			</div>
			<div class = "form-group">
				<button class ="btn btn-default">Check Out</button>
			</div>
			<div class = "form-group">
				<button class = "btn btn-default">Return</button>			
			</div>
		</form>
	</div>
</t:template>
