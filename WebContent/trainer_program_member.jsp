<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*" import="jspbook.program.*" import="jspbook.member.*"%>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Trainer's Program Member Management Page</title>
</head>
<body>
		<form name=form1>
			<table>
				<tr>
					<th></th>
					<th>프로그램번호</th>
					<th>프로그램명</th>
					<th>프로그램 진행요일</th>
					<th>프로그램 진행시간</th>
					<th>내용</th>
				</tr>
			
				<%
					Program program = (Program)request.getAttribute("program");
					
				%>
				<tr>
					<td></td>
					<td><%= program.getId() %></td>
					<td><%= program.getName() %></td>
					<td><%= program.getDay() %></td>
					<td><%= program.getTime() %></td>
					<td><%= program.getContent() %></td>
				</tr>
			</table>
			<br><br>
				
				<%
					ArrayList<Member> members = (ArrayList<Member>)request.getAttribute("members");
					ArrayList<String> list = new ArrayList<String>();
				%>
			
			<br>
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
					for(int i = 0 ; i < members.size() ; i++) {
						list.add("<tr><td></td><td>" + members.get(i).getId() + "</td><td>"
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
					
					for(int j = 10*(index-1) ; (j <= 10*(index-1) + 9) && (j <= list.size()-1) ; j++) {
						out.println(list.get(j));
					}
				
					out.println("</table>");
				
					out.println("<br><br><br>");
				
					int totalPage = list.size() / 10;
					if((double)list.size() % 10 != 0)
						totalPage = totalPage + 1;
					for(int j = 1 ; j <= totalPage ; j++) {
						if(j == index)
							out.println(j);
						else
							out.println("<a href='control.jsp?action=trainer_program_member&index=" + j + "'>" + j + "</a>");
						
						if(j != totalPage)
							out.println("&nbsp;&nbsp;|&nbsp;&nbsp;");
					}
				%>
	</form>
	<br>
	
	<form name=form2 action="control.jsp">
		<input type=hidden name=action value="trainer_program_member_search">
		<input type=hidden name=programId value=<%= program.getId() %>>
		<select name="sel">
			<option value="id" selected>회원번호</option>
			<option value="name">이름</option>
		</select>
		<input type=text name=find size=30>&nbsp;<input type=button onClick="form2.submit();return false;" align=absmiddle>
	</form>
</body>
</html>