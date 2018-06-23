<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*" import="jspbook.pt.*" import="jspbook.member.*"%>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Trainer's Pt Management Page</title>
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
					<th>pt 요일</th>
					<th>pt 시간</th>
					<th>주소</th>
					<th>전화번호</th>
					<th>생년월일</th>
					<th>이메일</th>
					<th>직업</th>
					<th>pt 시작시간</th>
					<th>pt 등록횟수</th>
					<th>출석</th>
				</tr>
			
				<%
					ArrayList<Pt> pts = (ArrayList<Pt>)request.getAttribute("pts");
					ArrayList<Member> members = (ArrayList<Member>)request.getAttribute("members");
					ArrayList<String> list = new ArrayList<String>();
					int staffId = (int)request.getAttribute("staffId");

					for(int i = 0 ; i < members.size() ; i++) {
						list.add("<tr><td></td><td>" + members.get(i).getId() + "</td><td>"
								+ members.get(i).getName() + "</td><td>"
								+ members.get(i).getAge() + "</td><td>"
								+ members.get(i).getGender() + "</td><td>"
								+ pts.get(i).getDay() + "</td><td>"
								+ pts.get(i).getTime() + "</td><td>"
								+ members.get(i).getAddress() + "</td><td>"
								+ members.get(i).getPhone() + "</td><td>"
								+ members.get(i).getBirthDay() + "</td><td>"
								+ members.get(i).getEmail() + "</td><td>"
								+ members.get(i).getJob() + "</td><td>"
								+ pts.get(i).getStartday() + "</td><td>"
								+ pts.get(i).getRegisterNum() + "</td><td>"
								+ "<input type=button value='출석' onClick='document.location.ahref=control.jsp?trainer_pt'></td></tr>");
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
							out.println("<a href='control.jsp?action=trainer_pt&index=" + j + "'>" + j + "</a>");
						
						if(j != totalPage)
							out.println("&nbsp;&nbsp;|&nbsp;&nbsp;");
					}
				%>
	</form>
	<br>
	
	<form name=form2 action="control.jsp">
		<input type=hidden name=action value="trainer_pt_search">
		<select name="sel">
			<option value="name" selected>이름</option>
			<option value="day">요일</option>
		</select>
		<input type=text name=find size=30>&nbsp;<input type=button onClick="form2.submit();return false;" align=absmiddle>
	</form>
</body>
</html>