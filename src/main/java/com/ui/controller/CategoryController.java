package com.ui.controller;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ui.dao.CategoryDAO;
import com.ui.model.Category;

@RestController
public class CategoryController {
	@Autowired
	CategoryDAO categoryDAO;

	Category category;

	private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

	@RequestMapping(value = "/getCategories", method = RequestMethod.GET, produces = "application/json")
	public List<Category> getCategories(HttpServletRequest request) {
		logger.info("***** GET CATEGORY *****");
		List<Category> category = categoryDAO.getAllCategories();
		return category;
	}

	@RequestMapping(value = "/getCategoriesByPage", method = RequestMethod.GET, produces = "application/json")
	public List<Category> getCategoriesByPage(int pagesize, int startindex, HttpServletRequest request) {
		logger.info("***** GET CATEGORY BY PAGE *****");
		List<Category> category = categoryDAO.getAllCategoriesByPage(pagesize, startindex);
		return category;
	}

	@RequestMapping(value = "/searchCategories", method = RequestMethod.GET, produces = "application/json")
	public List<Category> searchCategories(String keyword, HttpServletRequest request) {
		logger.info("***** SERACH CATEGORY *****");
		List<Category> category = categoryDAO.searchCategories(keyword);
		return category;
	}
	

	@RequestMapping(value = "addCategory", method = RequestMethod.POST)
	public String addCategory(@RequestParam(value = "image", required = false) MultipartFile image, String categoryname,
			String categorycode, String description, HttpServletRequest request, HttpSession session) {
		logger.info("***** ADD CATEGORY *****");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String result = null;
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		if(categorycode.equals("")){
			categorycode = null;
		}
		String s = "y";
		String image1 = "";
		try {
			if (image != null) {
				try {
					byte[] bytes = image.getBytes();

					File dir = new File(request.getSession().getServletContext().getRealPath("")
							+ "/resources/admin/images/" + File.separator + "Category");
					if (!dir.exists())
						dir.mkdirs();

					String path = request.getSession().getServletContext()
							.getRealPath("/resources/admin/images/Category/");
					File uploadfile = new File(path + File.separator + image.getOriginalFilename());

					int height = 480, width = 700;

					ByteArrayInputStream in = new ByteArrayInputStream(bytes);
					try {
						BufferedImage img = ImageIO.read(in);

						int original_width = img.getWidth();
						int original_height = img.getHeight();
						int bound_width = 700;
						int bound_height = 480;

						if (original_height / bound_height > original_width / bound_width) {
							bound_width = (int) (bound_height * original_width / original_height);
						} else {
							bound_height = (int) (bound_width * original_height / original_width);
						}

						Image scaledImage = img.getScaledInstance(bound_width, bound_height, Image.SCALE_SMOOTH);

						BufferedImage imageBuff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

						Graphics2D drawer = imageBuff.createGraphics();
						drawer.setBackground(Color.WHITE);
						drawer.clearRect(0, 0, width, height);

						imageBuff.getGraphics().drawImage(scaledImage, (width - bound_width) / 2,
								(height - bound_height) / 2, new Color(0, 0, 0), null);

						ByteArrayOutputStream buffer = new ByteArrayOutputStream();

						ImageIO.write(imageBuff, "jpg", buffer);

						bytes = buffer.toByteArray();
					} catch (IOException e) {

					}
					BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
							new FileOutputStream(uploadfile));
					bufferedOutputStream.write(bytes);
					bufferedOutputStream.close();
					image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
							+ "/opal/resources/admin/images/Category/" + image.getOriginalFilename();

					category = new Category(categoryname, categorycode, image1, description, s, id, IpAddress);
					categoryDAO.addCategory(category);

					return "Success";
				} catch (Exception e) {
					e.printStackTrace();
					category = new Category(categoryname, categorycode, image1, description, s, id, IpAddress);
					result = categoryDAO.addCategory(category);

					return result;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			category = new Category(categoryname, categorycode, image1, description, s, id, IpAddress);
			result = categoryDAO.addCategory(category);

			return result;
		}

		category = new Category(categoryname, categorycode, image1, description, s, id, IpAddress);
		result = categoryDAO.addCategory(category);

		return result;
	}

	@RequestMapping(value = "editCategory", method = RequestMethod.POST)
	public String editCategory(@RequestParam(value = "image", required = false) MultipartFile image, int categoryid,
			String categoryname, String categorycode, String description, String categoryimage,
			HttpServletRequest request, HttpSession session) {
		logger.info("***** EDIT CATEGORY *****");
		String result = null;
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		String image1 = "";
		try {
			if (image != null) {
				try {
					byte[] bytes = image.getBytes();

					File dir = new File(request.getSession().getServletContext().getRealPath("")
							+ "/resources/admin/images/" + File.separator + "Category");
					if (!dir.exists())
						dir.mkdirs();

					String path = request.getSession().getServletContext()
							.getRealPath("/resources/admin/images/Category/");
					File uploadfile = new File(path + File.separator + image.getOriginalFilename());				

					int height = 480, width = 700;

					ByteArrayInputStream in = new ByteArrayInputStream(bytes);
					try {
						BufferedImage img = ImageIO.read(in);

						int original_width = img.getWidth();
						int original_height = img.getHeight();
						int bound_width = 700;
						int bound_height = 480;
						if (original_height / bound_height > original_width / bound_width) {
							bound_width = (int) (bound_height * original_width / original_height);
						} else {
							bound_height = (int) (bound_width * original_height / original_width);
						}
						Image scaledImage = img.getScaledInstance(bound_width, bound_height, Image.SCALE_SMOOTH);
						BufferedImage imageBuff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
						Graphics2D drawer = imageBuff.createGraphics();
						drawer.setBackground(Color.WHITE);						
						drawer.clearRect(0, 0, width, height);

						imageBuff.getGraphics().drawImage(scaledImage, (width - bound_width) / 2,
								(height - bound_height) / 2, new Color(0, 0, 0), null);

						ByteArrayOutputStream buffer = new ByteArrayOutputStream();

						ImageIO.write(imageBuff, "jpg", buffer);

						bytes = buffer.toByteArray();
					} catch (IOException e) {						
					}					
					BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
							new FileOutputStream(uploadfile));
					bufferedOutputStream.write(bytes);
					bufferedOutputStream.close();				
					image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
							+ "/opal/resources/admin/images/Category/" + image.getOriginalFilename();

					category = new Category(categoryid, categoryname, categorycode, image1, description, id, IpAddress);
					categoryDAO.editCategory(category);

					return "Success";
				} catch (Exception e) {
					e.printStackTrace();
					category = new Category(categoryid, categoryname, categorycode, categoryimage, description, id, IpAddress);
					result = categoryDAO.editCategory(category);

					return result;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			category = new Category(categoryid, categoryname, categorycode, categoryimage, description, id, IpAddress);
			result = categoryDAO.editCategory(category);

			return result;
		}

		category = new Category(categoryid, categoryname, categorycode, categoryimage, description, id, IpAddress);
		result = categoryDAO.editCategory(category);

		return result;
	}

	@RequestMapping(value = "deleteCategory", method = RequestMethod.DELETE)
	public void delete(int categoryid) {
		logger.info("***** DELETE CATEGORY *****");
		categoryDAO.deleteCategory(categoryid);
	}

}