<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*" import="jspbook.member.*" import="jspbook.program.*" import="jspbook.pt.*"%>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Fitness Gym Management System Control Page</title>
</head>
<body>
	<jsp:useBean id="member" class="jspbook.member.Member" />
	<jsp:setProperty name="member" property="*" />
	
	<jsp:useBean id="member_bean" class="jspbook.member.MemberBean" />
	<jsp:useBean id="programMember_bean" class="jspbook.program.ProgramMemberBean" />
	<jsp:useBean id="ptMember_bean" class="jspbook.pt.PtMemberBean" />
	
	<%
		String action = request.getParameter("action");
		ArrayList<Member> members = new ArrayList<Member>();
		ArrayList<Program> programs = new ArrayList<Program>();
		ArrayList<Pt> pts = new ArrayList<Pt>();
		int id;
		
		if(action != null) {
	
			switch(action) {
			case "admin_member":
				members = member_bean.getMemberList();
				request.setAttribute("members", members);
				pageContext.forward("admin_member.jsp");
				break;
			case "admin_member_add":
				response.sendRedirect("admin_member_add.jsp");
				break;
			case "admin_member_insert":
				if(member_bean.insertMember(member));
				else
					throw new Exception();
				response.sendRedirect("control.jsp?action=admin_member");
				break;
			case "admin_member_edit":
				id = Integer.parseInt(request.getParameter("id"));
				member = member_bean.getMember(id);
				request.setAttribute("member", member);
				pageContext.forward("admin_member_edit.jsp");
				break;
			case "admin_member_update":
				if(member_bean.updateMember(member));
				else
					throw new Exception();
				member = member_bean.getMember(member.getId());
				request.setAttribute("member", member);
				pageContext.forward("control.jsp?action=admin_member");
				break;
			case "admin_member_delete":
				id = Integer.parseInt(request.getParameter("id"));
				if(member_bean.deleteMember(id));
				else
					throw new Exception();
				response.sendRedirect("control.jsp?action=admin_member");
				break;
			case "admin_member_search":
				String find = request.getParameter("find");
				if(request.getParameter("sel").equals("name"))
					members = member_bean.searchMemberName(find);
				else if(request.getParameter("sel").equals("name"))
					members = member_bean.searchMemberName(find);
				request.setAttribute("members", members);
				pageContext.forward("admin_member.jsp");
				break;
			case "trainer_program":
				id = Integer.parseInt(request.getParameter("staffId"));
				programs = programMember_bean.getProgramList(id);
				request.setAttribute("programs", programs);
				pageContext.forward("trainer_program.jsp");
				break;
			case "trainer_program_member":
				id = Integer.parseInt(request.getParameter("programId"));
				Program program = programMember_bean.getProgram(id);
				request.setAttribute("program", program);
				members = programMember_bean.getMemberList(id);
				request.setAttribute("members", members);
				pageContext.forward("trainer_program_member.jsp");
				break;
			case "trainer_program_member_search1":
				id = Integer.parseInt(request.getParameter("staffId"));
				program = programMember_bean.getProgram(id);
				request.setAttribute("program", program);
				find = request.getParameter("find");
				if(request.getParameter("sel").equals("id"))
					members = programMember_bean.searchMemberId1(id, find);
				else if(request.getParameter("sel").equals("name"))
					members = programMember_bean.searchMemberName1(id, find);
				request.setAttribute("members", members);
				pageContext.forward("trainer_program_member.jsp");
				break;
			case "trainer_program_member_search":
				id = Integer.parseInt(request.getParameter("programId"));
				program = programMember_bean.getProgram(id);
				request.setAttribute("program", program);
				find = request.getParameter("find");
				if(request.getParameter("sel").equals("id"))
					members = programMember_bean.searchMemberId(id, find);
				else if(request.getParameter("sel").equals("name"))
					members = programMember_bean.searchMemberName(id, find);
				request.setAttribute("members", members);
				pageContext.forward("trainer_program_member.jsp");
				break;
			case "trainer_pt":
				id = Integer.parseInt(request.getParameter("staffId"));
				pts = ptMember_bean.getPtList(id);
				request.setAttribute("pts", pts);
				members = ptMember_bean.getMemberList(id);
				request.setAttribute("members", members);
				pageContext.forward("trainer_pt.jsp");
				break;
			case "trainer_pt_search":
				id = Integer.parseInt(request.getParameter("staffId"));
				find = request.getParameter("find");
				if(request.getParameter("sel").equals("name"))
					members = ptMember_bean.searchMemberName(id, find);
				else if(request.getParameter("sel").equals("day"))
					members = ptMember_bean.searchMemberDay(id, find);
				for(int i = 0 ; i < members.size() ; i++) {
					Pt pt = new Pt();
					pt = ptMember_bean.getPt(members.get(i).getId());
					pts.add(pt);
				}
				request.setAttribute("pts", pts);
				request.setAttribute("members", members);
				pageContext.forward("trainer_pt.jsp");
				break;
			}
		}
	%>
</body>
</html>