<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*" import="jspbook.member.*"%>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script language=javascript>
		function refuse() {
			result = confirm("입력을 취소하시겠습니까?");
			
			if(result)
				history.go(-1);
			else
				return;
		}
	</script>
	<title>Admin's Member Add Page</title>
</head>
<body>
	<%
		Member member = (Member)request.getAttribute("member");
	%>
	<!-- 게시글 수정 테이블 -->
	<form name=form1 action="control.jsp">
		<input type=hidden name="action" value="admin_member_insert">
		<table>
			<tr>
				<td>회원번호</td>
				<td><input type=text name=id></td>
				<td>이름</td>
				<td><input type=text name=name></td>
			</tr>
			<tr>
				<td>나이</td>
				<td><input type=text name=age></td>
				<td>성별</td>
				<td><input type=text name=gender></td>
			</tr>
			<tr>
				<td>주소</td>
				<td><input type=text name=address></td>
				<td>전화번호</td>
				<td><input type=text name=phone></td>
			</tr>
			<tr>
				<td>생년월일</td>
				<td><input type=text name=birthDay></td>
				<td>이메일</td>
				<td><input type=text name=email></td>
			</tr>
			<tr>
				<td>직업</td>
				<td><input type=text name=job></td>
				<td>등록일</td>
				<td><input type=text name=registerDay></td>
			</tr>
			<tr>
				<td>등록 기간</td>
				<td><input type=text name=registerPeriod></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td colspan=4>&nbsp;</td>
			</tr>
			<tr>
				<!-- 목록으로 돌아갈 수 있는 버튼 -->
				<td colspan=3 align=left><input type=button value="목록" onClick="location.href='control.jsp?action=member';return false;" align=absmiddle></td>
				<td align=right><input type=button value="등록" onclick="form1.submit();return false;" align=absmiddle>&nbsp;&nbsp;<input type=button value="취소" onClick="refuse();return false;" align=absmiddle></td>
			</tr>
		</table>
	</form>
</body>
</html>