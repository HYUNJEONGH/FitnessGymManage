<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*" import="jspbook.member.*"%>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script language=javascript>
			function add() {
				document.location.href="control.jsp?action=admin_member_add";
			}
			function edit() {
				document.location.href="control.jsp?action=admin_member_edit";
			}
			function del() {
				document.location.href="control.jsp?action=admin_member_del";
			}
	</script>
	<title>Admin's Member Management Page</title>
</head>
<body>
	<form name=form1>
			<table>
				<tr>
					<th></th>
					<th>회원번호</th>
					<th>이름</th>
					<th>나이</th>
					<th>성별</th>
					<th>주소</th>
					<th>전화번호</th>
					<th>생년월일</th>
					<th>이메일</th>
					<th>직업</th>
					<th>등록일</th>
					<th>등록기간</th>
					<th>등록만료일</th>
				</tr>
			
				<%
					ArrayList<Member> members = (ArrayList<Member>)request.getAttribute("members");
					ArrayList<String> list = new ArrayList<String>();

					for(int i = 0 ; i < members.size() ; i++) {
						list.add("<tr><td></td><td><a href='control.jsp?action=admin_member_edit&id="
								+ members.get(i).getId() + "'>" + members.get(i).getId() + "</a></td><td>"
								+ members.get(i).getName() + "</td><td>"
								+ members.get(i).getAge() + "</td><td>"
								+ members.get(i).getGender() + "</td><td>"
								+ members.get(i).getAddress() + "</td><td>"
								+ members.get(i).getPhone() + "</td><td>"
								+ members.get(i).getBirthDay() + "</td><td>"
								+ members.get(i).getEmail() + "</td><td>"
								+ members.get(i).getJob() + "</td><td>"
								+ members.get(i).getRegisterDay() + "</td><td>"
								+ members.get(i).getRegisterPeriod() + "</td><td>"
								+ members.get(i).getRegisterEndDate() + "</td></tr>");
					}
					
					int index = 1;
					if(request.getParameter("index") != null)
						index = Integer.parseInt(request.getParameter("index"));
					
					for(int j = 20*(index-1) ; (j <= 20*(index-1) + 19) && (j <= list.size()-1) ; j++) {
						out.println(list.get(j));
					}
				
					out.println("<td colspan=13 align=right><input type=button value='추가' onClick='add();return false;' align=absmiddle>"
								+ "</td></tr>");
					out.println("</table>");
				
					out.println("<br><br><br>");
				
					int totalPage = list.size() / 20;
					if((double)list.size() % 20 != 0)
						totalPage = totalPage + 1;
					for(int j = 1 ; j <= totalPage ; j++) {
						if(j == index)
							out.println(j);
						else
							out.println("<a href='control.jsp?action=admin_member&index=" + j + "'>" + j + "</a>");
						
						if(j != totalPage)
							out.println("&nbsp;&nbsp;|&nbsp;&nbsp;");
					}
				%>
	</form>
	<br>
	
	<form name=form2 action="control.jsp">
		<input type=hidden name=action value="admin_member_search">
		<select name="sel">
			<option value="name" selected>이름</option>
			<option value="else">기타</option>
		</select>
		<input type=text name=find size=30>&nbsp;<input type=button onClick="form2.submit();return false;" align=absmiddle>
	</form>
</body>
</html>