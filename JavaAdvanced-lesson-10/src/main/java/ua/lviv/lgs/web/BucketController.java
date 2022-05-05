package ua.lviv.lgs.web;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.lviv.lgs.domain.Bucket;
import ua.lviv.lgs.service.BucketService;
import ua.lviv.lgs.service.impl.BucketServiceImpl;

@WebServlet("/bucket")
public class BucketController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BucketService bs = BucketServiceImpl.getBucketService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String product_id = request.getParameter("product_id");
		
		HttpSession session = request.getSession();
		Integer user_id = (Integer) session.getAttribute("user_id");
		@SuppressWarnings("unused")
		String role = (String) session.getAttribute("role");
		Bucket bucket = new Bucket(user_id, Integer.parseInt(product_id), new Date());
		bs.create(bucket);

		response.setContentType("text");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("success");
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		 String bucketId = req.getParameter("bucketId");
		 bs.delete(Integer.parseInt(bucketId));
		 
		 resp.setContentType("text"); 
		 resp.setCharacterEncoding("UTF-8");
		 resp.getWriter().write("success");

	}
}
