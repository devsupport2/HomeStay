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

import com.ui.dao.BrandDAO;
import com.ui.model.Brand;

@RestController
public class BrandController {
	@Autowired
	BrandDAO brandDAO;

	Brand brand;

	private static final Logger logger = LoggerFactory.getLogger(BrandController.class);

	@RequestMapping(value = "/getBrands", method = RequestMethod.GET, produces = "application/json")
	public List<Brand> getBrands(HttpServletRequest request) {
		logger.info("***** GET BRANDS *****");
		List<Brand> b = brandDAO.getAllBrands();
		return b;
	}

	@RequestMapping(value = "/getBrandsByPage", method = RequestMethod.GET, produces = "application/json")
	public List<Brand> getBrandsByPage(int pagesize, int startindex, HttpServletRequest request) {
		logger.info("***** GET BRANDS BY PAGE *****");
		List<Brand> b = brandDAO.getAllBrandsByPage(pagesize, startindex);
		return b;
	}

	@RequestMapping(value = "/searchBrands", method = RequestMethod.GET, produces = "application/json")
	public List<Brand> searchBrands(String keyword, HttpServletRequest request) {
		logger.info("***** SEARCH BRANDS *****");
		List<Brand> b = brandDAO.searchBrands(keyword);
		return b;
	}

	@RequestMapping(value = "addBrand", method = RequestMethod.POST)
	public String addBrand(@RequestParam(value = "image", required = false) MultipartFile image, String brandname,
			String branddescription, HttpServletRequest request, HttpSession session) {
		logger.info("***** ADD BRAND *****");
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
							+ "/resources/admin/images/" + File.separator + "Brand");
					if (!dir.exists())
						dir.mkdirs();

					String path = request.getSession().getServletContext()
							.getRealPath("/resources/admin/images/Brand/");
					File uploadfile = new File(path + File.separator + image.getOriginalFilename());

					/********* Today Start **********/

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
						// Color c = Color.decode(color1);
						// drawer.setBackground(c);
						drawer.clearRect(0, 0, width, height);

						imageBuff.getGraphics().drawImage(scaledImage, (width - bound_width) / 2,
								(height - bound_height) / 2, new Color(0, 0, 0), null);

						ByteArrayOutputStream buffer = new ByteArrayOutputStream();

						ImageIO.write(imageBuff, "jpg", buffer);

						bytes = buffer.toByteArray();
					} catch (IOException e) {
						// throw new ApplicationException("IOException in
						// scale");
					}
					/********* Today End **********/

					System.out.println("*********************Path" + path);
					BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
							new FileOutputStream(uploadfile));
					bufferedOutputStream.write(bytes);
					bufferedOutputStream.close();
					image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
							+ "/opal/resources/admin/images/Brand/" + image.getOriginalFilename();

					brand = new Brand(brandname, image1, branddescription, s, id, IpAddress);
					brandDAO.addBrand(brand);

					return "Success";
				} catch (Exception e) {
					e.printStackTrace();
					brand = new Brand(brandname, image1, branddescription, s, id, IpAddress);
					brandDAO.addBrand(brand);

					return "Success";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			brand = new Brand(brandname, image1, branddescription, s, id, IpAddress);
			brandDAO.addBrand(brand);

			return "Success";
		}

		brand = new Brand(brandname, image1, branddescription, s, id, IpAddress);
		brandDAO.addBrand(brand);

		return "Success";
	}

	@RequestMapping(value = "editBrand", method = RequestMethod.POST)
	public String editBrand(@RequestParam(value = "image", required = false) MultipartFile image, int brandid,
			String brandname, String logo, String branddescription, HttpServletRequest request, HttpSession session) {
		logger.info("***** EDIT BRAND *****");
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
							+ "/resources/admin/images/" + File.separator + "Brand");
					if (!dir.exists())
						dir.mkdirs();

					String path = request.getSession().getServletContext()
							.getRealPath("/resources/admin/images/Brand/");
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
							+ "/opal/resources/admin/images/Brand/" + image.getOriginalFilename();

					brand = new Brand(brandid, brandname, image1, branddescription, id, IpAddress);
					brandDAO.editBrand(brand);

					return "Success";
				} catch (Exception e) {
					e.printStackTrace();
					brand = new Brand(brandid, brandname, logo, branddescription, id, IpAddress);
					brandDAO.editBrand(brand);
					return "Success";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			brand = new Brand(brandid, brandname, logo, branddescription, id, IpAddress);
			brandDAO.editBrand(brand);
			return "Success";
		}

		brand = new Brand(brandid, brandname, logo, branddescription, id, IpAddress);
		brandDAO.editBrand(brand);

		return "Success";
	}

	@RequestMapping(value = "deleteBrand", method = RequestMethod.DELETE)
	public void delete(int brandid) {
		logger.info("***** DELETE BRAND *****");
		brandDAO.deleteBrand(brandid);
	}

}