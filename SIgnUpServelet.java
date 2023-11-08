import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String dob = request.getParameter("dob");
        String contact = request.getParameter("contact");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/car_dealership", "root", "EYLANMOL17");

            PreparedStatement ps = con.prepareStatement("INSERT INTO users(name, dob, contact, email, password) VALUES(?, ?, ?, ?, ?)");
            ps.setString(1, name);
            ps.setString(2, dob);
            ps.setString(3, contact);
            ps.setString(4, email);
            ps.setString(5, password);

            int result = ps.executeUpdate();
            if (result > 0) {
                out.println("Sign Up successful!");
            } else {
                out.println("Sign Up failed. Please try again.");
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
