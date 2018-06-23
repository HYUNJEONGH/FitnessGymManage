<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*" import="jspbook.member.*"%>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script language=javascript>
		function refuse() {
			result = confirm("수정을 취소하시겠습니까?");
			
			if(result)
				history.go(-1);
			else
				return;
		}
		function del(id) {
			document.location.href="control.jsp?action=admin_member_delete&id=" + id;
		}
	</script>
	<title>Admin's Member Edit Page</title>
</head>
<body>
	<%
		Member member = (Member)request.getAttribute("member");
	%>
	<!-- 게시글 수정 테이블 -->
	<form name=form1 action="control.jsp">
		<input type=hidden name="action" value="admin_member_update">
		<input type=hidden name="id" value="<%= member.getId() %>">
		<table>
			<tr>
				<th>회원번호</th>
				<th><input type=text name=id value=<%= member.getId() %>></th>
				<th>이름</th>
				<th><input type=text name=name value=<%= member.getName() %>></th>
			</tr>
			<tr>
				<th>나이</th>
				<th><input type=text name=age value=<%= member.getAge() %>></th>
				<th>성별</th>
				<th><input type=text name=gender value=<%= member.getGender() %>></th>
			</tr>
			<tr>
				<th>주소</th>
				<th><input type=text name=address value=<%= member.getAddress() %>></th>
				<th>전화번호</th>
				<th><input type=text name=phone value=<%= member.getPhone() %>></th>
			</tr>
			<tr>
				<th>생년월일</th>
				<th><input type=text name=birthDay value=<%= member.getBirthDay() %>></th>
				<th>이메일</th>
				<th><input type=text name=email value=<%= member.getEmail() %>></th>
			</tr>
			<tr>
				<th>직업</th>
				<th><input type=text name=job value=<%= member.getJob() %>></th>
				<th>등록일</th>
				<th><input type=text name=registerDay value=<%= member.getRegisterDay() %>></th>
			</tr>
			<tr>
				<th>등록 기간</th>
				<th><input type=text name=registerPeriod value=<%= member.getRegisterPeriod() %>></th>
				<th>만료일</th>
				<th><%= member.getRegisterEndDate() %>></th>
			</tr>
			<tr>
				<td colspan=4>&nbsp;</td>
			</tr>
			<tr>
				<!-- 목록으로 돌아갈 수 있는 버튼 -->
				<td colspan=3 align=left><input type=button onClick="location.href='control.jsp?action=member';return false;" align=absmiddle></td>
				<td align=right><input type=button onclick="form1.submit();return false;" align=absmiddle>&nbsp;&nbsp;<input type=button onClick="del(<%= ""+member.getId() %>);return false;" align=absmiddle>&nbsp;&nbsp;<input type=button onClick="refuse();return false;" align=absmiddle></td>
			</tr>
		</table>
	</form>
</body>
</html>