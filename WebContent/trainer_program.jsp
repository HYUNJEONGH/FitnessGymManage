<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*" import="jspbook.program.*"%>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Trainer's Program Management Page</title>
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
					ArrayList<Program> programs = (ArrayList<Program>)request.getAttribute("programs");
					ArrayList<String> list = new ArrayList<String>();

					for(int i = 0 ; i < programs.size() ; i++) {
						list.add("<tr><td></td><td><a href='control.jsp?action=trainer_program_member&programId="
								+ programs.get(i).getId() + "'>" + programs.get(i).getId() + "</a></td><td>"
								+ programs.get(i).getName() + "</td><td>"
								+ programs.get(i).getDay() + "</td><td>"
								+ programs.get(i).getTime() + "</td><td>"
								+ programs.get(i).getTime() + "</td></tr>");
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
							out.println("<a href='control.jsp?action=trainer_program&index=" + j + "'>" + j + "</a>");
						
						if(j != totalPage)
							out.println("&nbsp;&nbsp;|&nbsp;&nbsp;");
					}
				%>
	</form>
	<br>
	
	<form name=form2 action="control.jsp">
		<input type=hidden name=action value="trainer_program_member_search">
		<select name="sel">
			<option value="id" selected>회원번호</option>
			<option value="name">이름</option>
		</select>
		<input type=text name=find size=30>&nbsp;<input type=button onClick="form2.submit();return false;" align=absmiddle>
	</form>
</body>
</html>