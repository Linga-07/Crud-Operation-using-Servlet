 import java.io.IOException; 
 
import java.io.PrintWriter; 
import java.sql.*; 
import java.sql.Connection; 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
 
/** 
* Servlet implementation class ServerServlet 
*/ 
@WebServlet("/Post") public class Post extends HttpServlet { private static final long serialVersionUID = 1L; 
 
public Post() { super(); 
} 
 
 
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
 
} 
protected void doPost(HttpServletRequest request, HttpServletResponse response) 
throws ServletException, IOException { 
String action = request.getParameter("action"); 
 
PrintWriter out = response.getWriter(); String name = request.getParameter("name"); 
int id = Integer.parseInt(request.getParameter("id").toString()); 
String quantity = request.getParameter("quantity"); 
String price = request.getParameter("price"); 
 try { 
Class.forName("org.postgresql.Driver"); 
Connection conn = 
DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "alagu4321"); 
 
if(action.equals("insert")) 
{ 
String query = "insert into public.product values(?,?,?,?)"; PreparedStatement st = conn.prepareStatement(query); st.setString(1, name); st.setInt(2, id); st.setString(3, quantity); st.setString(4,price); int c = st.executeUpdate(); if(c>0) { 
out.println("<html><body>"); 
out.println("<h1 style=\"text-align:center\";>Inserted Successfully</h1>"); out.println("</body></html>"); 
} 
} else if(action.equals("read")) 
{ 
String query = "select * from public.product where id=?"; PreparedStatement st = conn.prepareStatement(query); st.setInt(1, id); 
ResultSet rs = st.executeQuery(); out.println("<html><body><center>"); 
out.println("<table border=\"4\" cellpadding=\"10\">"); out.println("<tr>"); out.println("<th>Name</th>"); out.println("<th>ID</th>"); out.println("<th>Quantity</th>"); out.println("<th>Price</th>"); 
out.println("</tr>"); while(rs.next()) { out.println("<tr>"); out.println("<td>"+rs.getString(1)+"</td>"); out.println("<td>"+rs.getInt(2)+"</td>"); out.println("<td>"+rs.getString(3)+"</td>"); out.println("<td>"+rs.getString(4)+"</td>"); 
out.println("</tr>"); 
} 
out.println("</table>"); 
out.println("<h3 style=\"text-align:center\";>Read successfully</h3>"); out.println("</center></body></html>"); 
} 
else if(action.equals("update")) 
{ 
 	String query = "update public.product set name=?,quantity=?,price=? where id=?"; 	 
PreparedStatement st = conn.prepareStatement(query); st.setString(1, name); st.setString(2, quantity); st.setString(3, price); st.setInt(4,id); int c = st.executeUpdate(); if(c>0) { 
out.println("<html><body>"); 
out.println("<h1 style=\"text-align:center\";>Updated Successfully</h1>"); out.println("</body></html>"); 
} 
} else 
{ 
String query = "delete from public.product where id=?"; PreparedStatement st = conn.prepareStatement(query); st.setInt(1, id); int c = st.executeUpdate(); if(c>0) { 
out.println("<html><body>"); 
out.println("<h1 style=\"text-align:center\";>Deleted Successfully</h1>"); out.println("</body></html>"); 
} 
} 
conn.close(); 
 
} catch(Exception e) { 
e.printStackTrace(); out.println("<html><body>"); 
out.println("<h1>Error: " + e.getMessage() + "</h1>"); out.println("</body></html>"); 
} 
out.println("<center><button>"); 
out.println("<a href=\"NewFile.html\" style=\"text-decoration:none\";>Back to 
Form</a>"); out.println("</center></button>"); 
 
} 
} 
