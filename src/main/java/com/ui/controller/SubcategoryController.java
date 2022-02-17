package com.ui.controller;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
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

import com.ui.dao.SubcategoryDAO;
import com.ui.model.Subcategory;

@RestController
public class SubcategoryController {
	@Autowired
	SubcategoryDAO subcategoryDAO;

	Subcategory subcategory;

	private static final Logger logger = LoggerFactory.getLogger(SubcategoryController.class);

	@RequestMapping(value = "/getSubcategories", method = RequestMethod.GET, produces = "application/json")
	public List<Subcategory> getSubcategories(HttpServletRequest request) {
		logger.info("***** GET SUBCATEGORY *****");
		List<Subcategory> subcategory = subcategoryDAO.getAllSubcategories();
		return subcategory;
	}

	@RequestMapping(value = "/getSubcategoriesByPage", method = RequestMethod.GET, produces = "application/json")
	public List<Subcategory> getSubcategoriesByPage(int pagesize, int startindex, HttpServletRequest request) {
		logger.info("***** GET SUBCATEGORY BY PAGE *****");
		List<Subcategory> subcategory = subcategoryDAO.getAllSubcategoriesByPage(pagesize, startindex);
		return subcategory;
	}

	@RequestMapping(value = "/searchSubcategories", method = RequestMethod.GET, produces = "application/json")
	public List<Subcategory> searchSubcategories(String keyword, HttpServletRequest request) {
		logger.info("***** SEARCH SUBCATEGORY *****");
		List<Subcategory> subcategory = subcategoryDAO.searchSubcategories(keyword);
		return subcategory;
	}

	@RequestMapping(value = "/getSubCategoriesByCategoryId", method = RequestMethod.GET, produces = "application/json")
	public List<Subcategory> getSubCategoriesByCategoryId(int categoryid, HttpServletRequest request) {
		logger.info("Inside Get All Subcategory By Category Id Controller");
		List<Subcategory> subcategory = subcategoryDAO.getSubcategoriesByCategoryId(categoryid);
		return subcategory;
	}

	@RequestMapping(value = "addSubcategory", method = RequestMethod.POST)
	public String addSubcategory(@RequestParam(value = "image", required = false) MultipartFile image, int categoryname,
			String subcategoryname, String subcategorycode, String description, int valuex, int valuey, int valuew,
			int valueh, HttpServletRequest request, HttpSession session) {
		logger.info("***** ADD SUBCATEGORY *****");	
		String result = null;
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		String s = "y";
		String image1 = "";
		try {
			if (image != null) {
				try {
					byte[] bytes = image.getBytes();

					File dir = new File(request.getSession().getServletContext().getRealPath("")
							+ "/resources/admin/images/" + File.separator + "Subcategory");
					if (!dir.exists())
						dir.mkdirs();

					String path = request.getSession().getServletContext()
							.getRealPath("/resources/admin/images/Subcategory/");
					File uploadfile = new File(path + File.separator + image.getOriginalFilename());				

					ByteArrayInputStream in = new ByteArrayInputStream(bytes);
					try {
						BufferedImage img = ImageIO.read(in);

						Image scaledImage = img.getScaledInstance(valuew, valueh, Image.SCALE_SMOOTH);
						BufferedImage SubImgage = img.getSubimage(valuex, valuey, valuew, valueh);
						Graphics2D drawer = SubImgage.createGraphics();
						drawer.setComposite(AlphaComposite.Src);
						drawer.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
								RenderingHints.VALUE_INTERPOLATION_BILINEAR);
						drawer.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
						drawer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
						drawer.drawImage(scaledImage, valuew, valueh, null);
						drawer.dispose();
						ByteArrayOutputStream buffer = new ByteArrayOutputStream();
						ImageIO.write(SubImgage, "jpg", buffer);
						bytes = buffer.toByteArray();
					} catch (IOException e) {
						
					}				
					BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
							new FileOutputStream(uploadfile));
					bufferedOutputStream.write(bytes);
					bufferedOutputStream.close();				
					image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
							+ "/opal/resources/admin/images/Subcategory/" + image.getOriginalFilename();

					subcategory = new Subcategory(subcategoryname, subcategorycode, image1, description, categoryname, s, id,
							IpAddress);
					subcategoryDAO.addSubcategory(subcategory);

					return "Success";
				} catch (Exception e) {
					e.printStackTrace();
					subcategory = new Subcategory(subcategoryname, subcategorycode, image1, description, categoryname, s, id,
							IpAddress);
					result = subcategoryDAO.addSubcategory(subcategory);

					return result;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			subcategory = new Subcategory(subcategoryname, subcategorycode, image1, description, categoryname, s, id, IpAddress);
			result = subcategoryDAO.addSubcategory(subcategory);

			return result;
		}

		subcategory = new Subcategory(subcategoryname, subcategorycode, image1, description, categoryname, s, id, IpAddress);
		result = subcategoryDAO.addSubcategory(subcategory);

		return result;
	}

	@RequestMapping(value = "editSubcategory", method = RequestMethod.POST)
	public String editSubcategory(@RequestParam(value = "image", required = false) MultipartFile image,
			int subcategoryid, int categoryname, String subcategoryname, String subcategorycode, String description,
			String subcategoryimage, int valuex, int valuey, int valuew, int valueh, HttpServletRequest request,
			HttpSession session) {
		logger.info("***** EDIT SUBCATEGORY *****");
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
							+ "/resources/admin/images/" + File.separator + "Subcategory");
					if (!dir.exists())
						dir.mkdirs();

					String path = request.getSession().getServletContext()
							.getRealPath("/resources/admin/images/Subcategory/");
					File uploadfile = new File(path + File.separator + image.getOriginalFilename());
					ByteArrayInputStream in = new ByteArrayInputStream(bytes);
					try {
						BufferedImage img = ImageIO.read(in);

						Image scaledImage = img.getScaledInstance(valuew, valueh, Image.SCALE_SMOOTH);
						BufferedImage SubImgage = img.getSubimage(valuex, valuey, valuew, valueh);
						Graphics2D drawer = SubImgage.createGraphics();
						drawer.setComposite(AlphaComposite.Src);
						drawer.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
								RenderingHints.VALUE_INTERPOLATION_BILINEAR);
						drawer.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
						drawer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
						drawer.drawImage(scaledImage, valuew, valueh, null);
						drawer.dispose();
						ByteArrayOutputStream buffer = new ByteArrayOutputStream();
						ImageIO.write(SubImgage, "jpg", buffer);
						bytes = buffer.toByteArray();
					} catch (IOException e) {
						
					}
					BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
							new FileOutputStream(uploadfile));
					bufferedOutputStream.write(bytes);
					bufferedOutputStream.close();				
					image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
							+ "/opal/resources/admin/images/Subcategory/" + image.getOriginalFilename();

					subcategory = new Subcategory(subcategoryid, subcategoryname, subcategorycode, image1, description, categoryname,
							id, IpAddress);
					result = subcategoryDAO.editSubcategory(subcategory);

					return result;
				} catch (Exception e) {
					e.printStackTrace();
					subcategory = new Subcategory(subcategoryid, subcategoryname, subcategorycode, subcategoryimage, description,
							categoryname, id, IpAddress);
					result = subcategoryDAO.editSubcategory(subcategory);

					return result;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			subcategory = new Subcategory(subcategoryid, subcategoryname, subcategorycode, subcategoryimage, description,
					categoryname, id, IpAddress);
			result = subcategoryDAO.editSubcategory(subcategory);

			return result;
		}

		subcategory = new Subcategory(subcategoryid, subcategoryname, subcategorycode, subcategoryimage, description, categoryname,
				id, IpAddress);
		result = subcategoryDAO.editSubcategory(subcategory);

		return result;
	}

	@RequestMapping(value = "deleteSubcategory", method = RequestMethod.DELETE)
	public void delete(int subcategoryid) {
		logger.info("***** DELETE SUBCATEGORY *****");
		subcategoryDAO.deleteSubcategory(subcategoryid);
	}

}