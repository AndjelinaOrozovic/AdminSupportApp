package net.etfbl.ip.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.etfbl.ip.beans.AdminBean;
import net.etfbl.ip.beans.AttributesBean;
import net.etfbl.ip.beans.CategoriesBean;
import net.etfbl.ip.beans.LogsBean;
import net.etfbl.ip.beans.UsersBean;
import net.etfbl.ip.dto.Attribute;
import net.etfbl.ip.dto.Category;
import net.etfbl.ip.dto.User;

/**
 * Servlet implementation class AdminAppController
 */
@WebServlet("/AdminAppController")
public class AdminAppController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminAppController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String address = "/WEB-INF/pages/404.jsp";
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		session.setAttribute("notification", "");
		session.setAttribute("actionNotification", "");
		
		
		if(action == null || ("").equals(action)) {
			address = "/WEB-INF/pages/login.jsp";
		} 
		
		else if(("login").equals(action)) {
			
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			AdminBean adminBean = new AdminBean();
			
			if(adminBean.logIn(username, password)) {
				
				session.setAttribute("adminBean", adminBean);
				address = "WEB-INF/pages/categories.jsp";
				
			} else {
				
				session.setAttribute("notification", "Wrong username or password! Please enter your username and password again.");
				address = "/WEB-INF/pages/login.jsp";
				
			}
		} else if ("logout".equals(action)) {
			
			session.invalidate();
			address = "/WEB-INF/pages/login.jsp";
			
		} else {
			
			AdminBean adminBean = (AdminBean) session.getAttribute("adminBean");
			
			if(adminBean == null || !adminBean.isLoggedIn()) {
				address = "/WEB-INF/pages/login.jsp";
			} else {
				if("categories".equals(action)) {
					
					CategoriesBean categoriesBean = new CategoriesBean();
					AttributesBean attributesBean = new AttributesBean();
					session.setAttribute("categoriesBean", categoriesBean);
					session.setAttribute("attributesBean", attributesBean);
					address = "/WEB-INF/pages/categories.jsp";
					
				} else if("users".equals(action)) {
					
					UsersBean usersBean = new UsersBean();
					session.setAttribute("usersBean", usersBean);
					address = "/WEB-INF/pages/users.jsp";
				
				} else if("logs".equals(action)) {
					
					LogsBean logsBean = new LogsBean();
					session.setAttribute("logsBean", logsBean);
					address = "/WEB-INF/pages/logs.jsp";
					
				} else if ("deleteUser".equals(action) && (request.getParameter("id") !=null)) {
					Integer id = Integer.parseInt(request.getParameter("id"));
					
					UsersBean usersBean = (UsersBean) session.getAttribute("usersBean");
					boolean result = usersBean.deleteUser(id);
					if(result) {
						session.setAttribute("actionNotification", "User account successfully deleted!");
					} else {
						session.setAttribute("actionNotification", "Error while deleting user account!");
					}
					
					address = "/WEB-INF/pages/users.jsp";
					
				} else if ("deleteCategory".equals(action) && (request.getParameter("id") != null)) {
					Integer id = Integer.parseInt(request.getParameter("id"));
					
					CategoriesBean categoriesBean = (CategoriesBean) session.getAttribute("categoriesBean");
					
					boolean result = categoriesBean.deleteCategory(id);
					
					if(result) {
						session.setAttribute("actionNotification", "Category successfully deleted!");
					} else {
						session.setAttribute("actionNotification", "Error while deleting category!");
					}
					
					address = "/WEB-INF/pages/categories.jsp";
					
				} else if ("newUser".equals(action)) {
					address = "/WEB-INF/pages/newUser.jsp";
				} else if("addNewUser".equals(action) && (request.getParameter("submit") != null) && (request.getParameter("username") != null)) {
					
					UsersBean usersBean = (UsersBean) session.getAttribute("usersBean");
					String username = request.getParameter("username");
					
					if(!usersBean.isUsernameAllowed(username)) {
						session.setAttribute("notification", "Username already exists!");
					} else {
						User user = new User(null, request.getParameter("firstName"), request.getParameter("lastName"), request.getParameter("username"), 
								request.getParameter("city"), request.getParameter("email"), true, false, request.getParameter("avatar"));
						
						if(usersBean.addUser(user)) {
							session.setAttribute("notification", "New user added successfully!");
						} else {
							session.setAttribute("notification", "Error while adding new user!");
						}
					}
					
					address = "/WEB-INF/pages/newUser.jsp";
					
				} else if("editUser".equals(action) && (request.getParameter("id") != null)) {
					
					address = "/WEB-INF/pages/editUser.jsp?id=" + request.getParameter("id");
					
				} else if("updateUser".equals(action) && (request.getParameter("submit") != null) && (request.getParameter("username") != null) && request.getParameter("id") != null) {
					
					UsersBean usersBean = (UsersBean) session.getAttribute("usersBean");
					String username = request.getParameter("username");
					Integer id = Integer.parseInt(request.getParameter("id"));
					boolean isActivated = request.getParameter("isActive") != null;
					
					User user = new User(id, request.getParameter("firstName"), request.getParameter("lastName"), username, 
							request.getParameter("city"), request.getParameter("email"), isActivated, false, request.getParameter("avatar"));
					if(usersBean.updateUser(user)) {
						session.setAttribute("notification", "User updated successfully!");
					} else {
						session.setAttribute("notification", "Error while updating user!");
					}
					
					address = "/WEB-INF/pages/editUser.jsp";
					
				} else if("addNewCategory".equals(action) && (request.getParameter("submit") != null) && (request.getParameter("newCategory") != null)) {
					CategoriesBean categoriesBean = (CategoriesBean) session.getAttribute("categoriesBean");
					String name = request.getParameter("newCategory");
					
					if(!categoriesBean.isCategoryNameAllowed(name)) {
						session.setAttribute("notification", "Category already exists!");
					} else {
						Category category = new Category(null, null, name, false);
						if(categoriesBean.addCategory(category)) {
							session.setAttribute("notification", "New category successfully added!");
						} else {
							session.setAttribute("notification", "Error while adding new category!");
						}
					}
					
					address = "/WEB-INF/pages/categories.jsp";
				} else if("editCategory".equals(action) && request.getParameter("id") != null) {
					
					address = "/WEB-INF/pages/editCategory.jsp?id=" + request.getParameter("id");
					
				} else if("updateCategory".equals(action) && (request.getParameter("submit") != null) && (request.getParameter("categoryName") != null) && (request.getParameter("id") != null)) {
					
					CategoriesBean categoriesBean = (CategoriesBean) session.getAttribute("categoriesBean");
					
					String name = request.getParameter("categoryName");
					Integer id = Integer.parseInt(request.getParameter("id"));
					Integer idParent = categoriesBean.getCategoryById(id).getId_parent();
					
					if(!categoriesBean.isCategoryNameAllowed(name)) {
						session.setAttribute("notification", "Category name already exists!");
					} else {
						Category category = new Category(id, idParent, name, false);
						
						if(categoriesBean.updateCategory(category)) {
							session.setAttribute("notification", "Category data saved!");
						} else {
							session.setAttribute("notification", "Error while saving category data!");
						}
					}
					
					address = "/WEB-INF/pages/editCategory.jsp?id=" + id;
					
				} else if("addSubcategory".equals(action) && (request.getParameter("submit") != null) && (request.getParameter("subcategoryName") != null) && (request.getParameter("id") != null)) {
					
					CategoriesBean categoriesBean = (CategoriesBean) session.getAttribute("categoriesBean");
					String name = request.getParameter("subcategoryName");
					Integer idParentCategory = Integer.parseInt(request.getParameter("id"));
					
					if(!categoriesBean.isCategoryNameAllowed(name)) {
						session.setAttribute("notification", "Category name already exists!");
					} else {
						Category category = new Category(null, idParentCategory, name, false);
						if(categoriesBean.addCategory(category)) {
							session.setAttribute("notification", "New subcategory is added!");
						} else {
							session.setAttribute("notification", "Error while adding new subcategory!");
						}
					}
					
					address = "/WEB-INF/pages/editCategory.jsp?id=" + idParentCategory;
					
				} else if("addAttribute".equals(action) && (request.getParameter("submit") != null) && (request.getParameter("attributeName") != null) && (request.getParameter("id") != null)) {
					
					AttributesBean attributesBean = (AttributesBean) session.getAttribute("attributesBean");
					String name = request.getParameter("attributeName");
					Integer idCategory = Integer.parseInt(request.getParameter("id"));
					
					if(!attributesBean.isAttributeNameAllowed(idCategory, name)) {
						session.setAttribute("notification", "Attribute name already exists!");
					} else {
						
						Attribute attribute = new Attribute(null, idCategory, name, false);
						if(attributesBean.addAttributeForCategory(attribute)) {
							session.setAttribute("notification", "Attribute data saved!");
						} else {
							session.setAttribute("notification", "Error while saving attribute data!");
						}
						
					}
					
					address = "/WEB-INF/pages/editCategory.jsp?id=" + idCategory;
					
				} else if("deleteAttribute".equals(action) && (request.getParameter("id") != null)) {
					Integer id = Integer.parseInt(request.getParameter("id"));
					AttributesBean attributesBean = (AttributesBean) session.getAttribute("attributesBean");
					Integer idCategory = Integer.parseInt(request.getParameter("idCategory"));
					
					boolean result = attributesBean.deleteAttribute(id);
					
					if(result) {
						session.setAttribute("notifaction", "Attribute is successfully deleted!");
					} else {
						session.setAttribute("notification", "Error while deleting attribute!");
					}
					
					address = "/WEB-INF/pages/editCategory.jsp?id=" + idCategory;
				}
			}
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
