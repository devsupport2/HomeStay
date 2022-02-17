package com.ui.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ui.dao.CompanyDAO;
import com.ui.dao.EnquiryDAO;
import com.ui.dao.ProductDAO;
import com.ui.dao.UserDAO;
import com.ui.model.Enquiry;
import com.ui.model.EnquiryAssign;
import com.ui.model.EnquiryFollowup;
import com.ui.model.EnquiryFollowupMember;
import com.ui.model.EnquiryLogMember;
import com.ui.model.EnquiryStatus;
import com.ui.model.FollowupEmployee;
import com.ui.model.User;

/**
 * @author user7
 *
 */
@RestController
public class EmailController {
	@Autowired
	UserDAO userDao;
	@Autowired
	CompanyDAO companyDao;
	@Autowired
	EnquiryDAO enquiryDao;
	@Autowired
	ProductDAO productDao;

	User user;

	private static final Logger logger = LoggerFactory.getLogger(EmailController.class);
	private static final String username = "homestay@ultrasmarterpsolutions.com";
	private static final String password = "JF-elH!LQbKi";
	
	static Session session1  = null;
	 
 	public static Session mailConfiguration() {
 		
 		Properties props = new Properties();
        
        props.put("mail.smtp.host", "mail.ultrasmarterpsolutions.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
 		 
        return session1 = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("homestay@ultrasmarterpsolutions.com", "JF-elH!LQbKi");
            }
        });
 	}
	
	@RequestMapping(value = "sendLogNotification", method = RequestMethod.POST)
	public String sendLogNotification(HttpSession session, HttpServletRequest request, HttpServletResponse res) {
		logger.info("***** INSIDE SEND LOG NOTIFICATION *****");

		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());

		User u = userDao.getUserDetailById(id);

		String notifyviaemail = (String) session.getAttribute("notifylogviaemail");
		String notifyviasms = (String) session.getAttribute("notifylogviasms");

		session.setAttribute("notifylogviaemail", "No");
		session.setAttribute("notifylogviasms", "No");

		EnquiryStatus enquiryStatus = enquiryDao.getLastEnquiryStatusDetail();
		Enquiry enquiry = enquiryDao.getEnquiryDetailsById(enquiryStatus.getEnquiryId());
		User user = userDao.getUserDetailById(enquiry.getClientId());
		
		List<EnquiryLogMember> em = enquiryDao.getEnquiryAssignMemberById(enquiryStatus.getEnquiryStatusId());
		
		String emailmember = "";
        String mobilenum = "";
        if(em!=null){
          for(EnquiryLogMember tams : em){
            emailmember = emailmember +','+tams.getEmail();
            mobilenum = mobilenum+','+tams.getMobileNumber();
          }
        }
        
        System.out.println(emailmember+"----------------member Email");

		if (notifyviaemail.equals("Yes")) {
			
			try {
				InternetAddress[] myBccList = InternetAddress.parse("emailrelay@hsstructures.com");
				
				session1 = mailConfiguration();
				
				Message message = new MimeMessage(session1);
				message.setFrom(new InternetAddress("emailrelay@hsstructures.com", "HomeStay Structures"));
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailmember));
				message.setRecipients(Message.RecipientType.BCC, myBccList);

				message.setSubject("Enquiry Log");
				StringBuilder sb = new StringBuilder();
				sb.append(
						"<table style='border:solid 1px #cfd4d8; font-family: Arial,Helvetica,sans-serif;' align='center' width='750' cellspacing='0' cellpadding='0' border='0'>"
								+ "<tbody>" + "<tr>"
								+ "<td style='padding:15px 0px;  font-size: 16px;  color: #263247; ' align='center' valign='middle'>"
								+ "<a href='https://ultrasmarterpsolutions.com/HomeStay'> <img border='0' src=''/></a>"
								+ "</td>" + "</tr>" + "<tr>"
								+ "<td style='line-height:0;font-size:0;vertical-align:top;padding:0px;text-align:left;border-bottom:4px solid #cfd4d8'></td>"
								+ "</tr>" + "<tr>"
								+ "<td style='font-family:Arial,Helvetica,sans-serif;font-size:12px; color:#373737; background-color:#fff; padding:20px;' align='left' valign='top'><p> Dear ,<strong> ");
				
				sb.append("</strong></p><br><br> An action taken on your enquiry, <br><br>" + "-"
						+ enquiryStatus.getRemark() + "<br><br>Log Date Time: " + enquiryStatus.getLogDateTime()
						+ "</tr>" + "<tr>"
						+ "<td style='font-family:Arial,Helvetica,sans-serif;font-size:12px; color:#373737; background-color:#fff; padding:20px;' align='left' valign='top'>Thanks & Regards<br>"
						+ "<strong>HomeStay Structures</strong></td>" + "</tr>" + "</tbody>" + "</table>" + "</td>"
						+ "</tr>" + "</tbody>" + "</table>");
				message.setContent(sb.toString(), "text/html");
				Transport.send(message);
				System.out.println("E-Mail Send Suceessfully For Delivery...Using JSP.........");
			} catch (Exception msg) {
				System.out.println("Not send mail " + msg);
			}
		}
		/*
		 * if (notifyviasms.equals("Yes")) { try { String url =
		 * "http://sms.astartechnologies.net/sendsms.aspx"; URL obj = new URL(url);
		 * HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		 * con.setRequestMethod("POST"); con.setRequestProperty("Accept-Language",
		 * "en-US,en;q=0.5"); String urlParameters =
		 * "mobile=9624024813&pass=EGIQT&senderid=ASTECH&to=" + mobilenum + "&msg=" +
		 * enquiryStatus.getRemark(); con.setDoOutput(true); DataOutputStream wr = new
		 * DataOutputStream(con.getOutputStream()); wr.writeBytes(urlParameters);
		 * wr.flush(); wr.close(); int responseCode = con.getResponseCode(); if
		 * (responseCode == 200) { con.connect(); BufferedReader rd = new
		 * BufferedReader(new InputStreamReader(con.getInputStream())); StringBuffer
		 * buffer = new StringBuffer(); String line; while ((line = rd.readLine()) !=
		 * null) { buffer.append(line).append("n"); }
		 * System.out.println(buffer.toString()); rd.close(); con.disconnect(); } else {
		 * System.out.println("http response code error: " + responseCode + "\n"); }
		 * System.out.println("responseCode ->" + responseCode + " --- " +
		 * con.getContent().toString()); } catch (Exception e) { e.printStackTrace(); }
		 * }
		 */

		return "Success";
	}

	
	
	
//	
//	
//	
//	
//	@RequestMapping(value = "sendFollowupNotification", method = RequestMethod.POST)
//	public String sendFollowupNotification(HttpSession session, HttpServletRequest request, HttpServletResponse res) {
//		logger.info("***** INSIDE SEND FOLLOWUP NOTIFICATION *****");
//
//		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
//
//		User u = userDao.getUserDetailById(id);
//
//		String notifyviaemail = (String) session.getAttribute("notifyfollowupviaemail");
//		String notifyviasms = (String) session.getAttribute("notifyfollowupviasms");
//
//		session.setAttribute("notifyfollowupviaemail", "No");
//		session.setAttribute("notifyfollowupviasms", "No");
//
//		EnquiryFollowup enquiryFollowup = enquiryDao.getLastEnquiryFollowupDetail();
//
//		Enquiry enquiry = null;
//		User user = null;
//		List<EnquiryAssign> enquiryAssign = null;
//		List<EnquiryFollowupMember> fe = null;
//		System.out.println(enquiryFollowup.getEnquiryId()+"===========================");
//		enquiry = enquiryDao.getEnquiryDetailsById(enquiryFollowup.getEnquiryId());
//	//	user = userDao.getUserDetailById(enquiry.getClientId());
//		enquiryAssign = enquiryDao.getEnquiryAssignByEnquiryId(enquiryFollowup.getEnquiryId());
//		
//		fe = enquiryDao.getEnquiryFollowupMemberById(enquiryFollowup.getFollowupId());
//        
//        String emailmember = "";
//        String mobilenum = "";
//        if(fe!=null){
//          for(EnquiryFollowupMember tams : fe){
//            emailmember = emailmember +','+tams.getEmail();
//            mobilenum = mobilenum+','+tams.getMobileNumber();
//          }
//        }
//        
//        System.out.println(emailmember+"----------------member Email");
//
//			if (notifyviaemail.equals("Yes")) {
//					/*User u1 = userDao.getUserDetailById(enquiryAssign.get(i).getUserId());*/
//
//					Properties props = new Properties();
//					props.put("mail.smtp.auth", "true");
//					props.put("mail.smtp.host", "mail.hsstructures.com");
//					props.put("mail.smtp.port", "587");
//					props.put("mail.smtp.starttls.enable", "true");
//					javax.mail.Session session1 = javax.mail.Session.getInstance(props, new javax.mail.Authenticator() {
//						protected PasswordAuthentication getPasswordAuthentication() {
//							return new PasswordAuthentication(username, password);
//						}
//					});
//					try {
//						InternetAddress[] myBccList = InternetAddress.parse("emailrelay@hsstructures.com");
//
//						Message message = new MimeMessage(session1);
//						message.setFrom(
//								new InternetAddress("emailrelay@hsstructures.com", "HomeStay Structures"));
//
//						message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailmember));
//
//						message.setRecipients(Message.RecipientType.BCC, myBccList);
//
//						message.setSubject("Followup");
//						StringBuilder sb = new StringBuilder();
//						sb.append(
//								"<table style='border:solid 1px #cfd4d8; font-family: Arial,Helvetica,sans-serif;' align='center' width='750' cellspacing='0' cellpadding='0' border='0'>"
//										+ "<tbody>" + "<tr>"
//										+ "<td style='padding:15px 0px;  font-size: 16px;  color: #373737; ' align='center' valign='middle'>"
//										+ "<a href='https://ultrasmarterpsolutions.com/HomeStay'> <img border='0' src=''/></a>"
//										+ "</td>" + "</tr>" + "<tr>"
//										+ "<td style='line-height:0;font-size:0;vertical-align:top;padding:0px;text-align:left;border-bottom:4px solid #cfd4d8'></td>"
//										+ "</tr>" + "<tr>"
//										+ "<td style='font-family:Arial,Helvetica,sans-serif;font-size:12px; color:#373737; background-color:#fff; padding:20px;' align='left' valign='top'><p> Dear ,<strong> ");
//						sb.append("</strong></p><br><br> You have new enquiry followup message, <br><br>"
//								+ "Enquiry No: " + enquiry.getEnquiryNo() + "<br><br>Client Company Name: "
//								+ enquiry.getUserCompanyName() + "<br><br>" + "Client Name: " + user.getFirstName()
//								+ " " + user.getLastName() + "<br><br>" + "Followup Message: "
//								+ enquiryFollowup.getRemark() + "<br><br>" + "Followup Added By: " + u.getFirstName()
//								+ " " + u.getLastName() + "<br><br>" + "Followup Date Time: "
//								+ enquiryFollowup.getFollowupDateTime() + "</td>" + "</tr>" + "<tr>"
//								+ "<td style='font-family:Arial,Helvetica,sans-serif;font-size:12px; color:#373737; background-color:#fff; padding:20px;' align='left' valign='top'>Thanks & Regards<br>"
//								+ "<strong>HomeStay Structures</strong></td>" + "</tr>" + "</tbody>" + "</table>"
//								+ "</td>" + "</tr>" + "</tbody>" + "</table>");
//						message.setContent(sb.toString(), "text/html");
//						Transport.send(message);
//						System.out.println("E-Mail Send Suceessfully For Delivery...Using JSP.........");
//					} catch (Exception msg) {
//						System.out.println("Not send mail " + msg);
//					}
//
//			/*
//			 * if (notifyviasms.equals("Yes")) { try { String url =
//			 * "http://sms.astartechnologies.net/sendsms.aspx"; URL obj = new URL(url);
//			 * HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//			 * con.setRequestMethod("POST"); con.setRequestProperty("Accept-Language",
//			 * "en-US,en;q=0.5"); String urlParameters =
//			 * "mobile=9624024813&pass=EGIQT&senderid=ASTECH&to=" + mobilenum + "&msg=" +
//			 * enquiryFollowup.getRemark() + ". for " + user.getFirstName() + " " +
//			 * user.getLastName(); ; con.setDoOutput(true); DataOutputStream wr = new
//			 * DataOutputStream(con.getOutputStream()); wr.writeBytes(urlParameters);
//			 * wr.flush(); wr.close(); int responseCode = con.getResponseCode(); if
//			 * (responseCode == 200) { con.connect(); BufferedReader rd = new
//			 * BufferedReader(new InputStreamReader(con.getInputStream())); StringBuffer
//			 * buffer = new StringBuffer(); String line; while ((line = rd.readLine()) !=
//			 * null) { buffer.append(line).append("n"); }
//			 * System.out.println(buffer.toString()); rd.close(); con.disconnect(); } else {
//			 * System.out.println("http response code error: " + responseCode + "\n"); }
//			 * System.out .println("responseCode ->" + responseCode + " --- " +
//			 * con.getContent().toString()); } catch (Exception e) { e.printStackTrace(); }
//			 * }
//			 */			
//			}
//
//		return "Success";
//	}
	
	
	@RequestMapping(value = "sendFollowupNotification", method = RequestMethod.POST)
	public String sendFollowupNotification(HttpSession session, HttpServletRequest request, HttpServletResponse res) {
		logger.info("***** INSIDE SEND FOLLOWUP NOTIFICATION *****");

		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());

		User u = userDao.getUserDetailById(id);

		String notifyviaemail = (String) session.getAttribute("notifyfollowupviaemail");
		String notifyviasms = (String) session.getAttribute("notifyfollowupviasms");

		System.out.println(notifyviaemail + "hhhhhhhhhhhhhhhhhhhhhhhhhhNotifyViaEmail");
		session.setAttribute("notifyfollowupviaemail", "No");
		session.setAttribute("notifyfollowupviasms", "No");

		EnquiryFollowup enquiryFollowup = enquiryDao.getLastEnquiryFollowupDetail();

		Enquiry enquiry = null;
		User user = null;
		List<EnquiryAssign> enquiryAssign = null;
		List<FollowupEmployee> followupEmployee = null;

		if (enquiryFollowup.getEnquiryId() != 0) {
			enquiry = enquiryDao.getEnquiryDetailsById(enquiryFollowup.getEnquiryId());
			user = userDao.getUserDetailById(enquiry.getClientId());
			enquiryAssign = enquiryDao.getEnquiryAssignByEnquiryId(enquiryFollowup.getEnquiryId());

			if (notifyviaemail.equals("Yes")) {
				for (int i = 0; i < enquiryAssign.size(); i++) {
					User u1 = userDao.getUserDetailById(enquiryAssign.get(i).getUserId());
					try {
						InternetAddress[] myBccList = InternetAddress.parse("webmaster@ultrainfotech.net");
						session1 = mailConfiguration();
						Message message = new MimeMessage(session1);
						message.setFrom(
								new InternetAddress("emailrelay@hsstructures.com", "HomeStay Structures"));

						message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(u1.getEmail()));

						message.setRecipients(Message.RecipientType.BCC, myBccList);

						//message.setSubject("Followup - "+enquiry.getEnquiryTitle());
						
						message.setSubject("Followup");
						StringBuilder sb = new StringBuilder();
						sb.append(
								"<table style='border:solid 1px #cfd4d8; font-family: Arial,Helvetica,sans-serif;' align='center' width='750' cellspacing='0' cellpadding='0' border='0'>"
										+ "<tbody>" + "<tr>"
										+ "<td style='padding:15px 0px;  font-size: 16px;  color: #263247; ' align='center' valign='middle'>"
										+ "<a href='http://ultrasmarterpsolutions.com/HomeStay'> <img border='0' src='http://ultrasmarterpsolutions.com/HomeStay/resources/admin/images/user2-160x160.png'/></a>"
										+ "</td>" + "</tr>" + "<tr>"
										+ "<td style='line-height:0;font-size:0;vertical-align:top;padding:0px;text-align:left;border-bottom:4px solid #cfd4d8'></td>"
										+ "</tr>" + "<tr>"
										+ "<td style='font-family:Arial,Helvetica,sans-serif;font-size:12px; color:#373737; background-color:#fff; padding:20px;' align='left' valign='top'><p> Dear <strong> ");
						sb.append(u1.getFirstName() + "   " + u1.getLastName() + ",");
						sb.append("</strong></p><br><br> You have new enquiry followup massage, <br><br>"
								+ "Enquiry No: " + enquiry.getEnquiryNo() + "<br><br>Client Company Name: "
								+ enquiry.getUserCompanyName() + "<br><br>" + "Client Name: " + user.getFirstName()
								+ " " + user.getLastName() + "<br><br>" + "Followup Massage: "
								+ enquiryFollowup.getRemark() + "<br><br>" + "Followup Added By: " + u.getFirstName()
								+ " " + u.getLastName() + "<br><br>" + "Followup Date Time: "
								+ enquiryFollowup.getFollowupDateTime() + "</td>" + "</tr>" + "<tr>"
								+ "<td style='font-family:Arial,Helvetica,sans-serif;font-size:12px; color:#373737; background-color:#fff; padding:20px;' align='left' valign='top'>Thanks & Regards<br>"
								+ "<strong>HomeStay Structures</strong></td>" + "</tr>" + "</tbody>" + "</table>"
								+ "</td>" + "</tr>" + "</tbody>" + "</table>");
						message.setContent(sb.toString(), "text/html");
						Transport.send(message);
						System.out.println("E-Mail Send Suceessfully For Delivery...Using JSP.........");
					} catch (Exception msg) {
						System.out.println("Not send mail " + msg);
					}

				//	if (notifyviasms.equals("Yes")) {
					if(false) {
					try {
							String url = "http://sms.astartechnologies.net/sendsms.aspx";
							URL obj = new URL(url);
							HttpURLConnection con = (HttpURLConnection) obj.openConnection();
							con.setRequestMethod("POST");
							con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
							String urlParameters = "mobile=9624024813&pass=EGIQT&senderid=ASTECH&to="
									+ u1.getMobileNumber() + "&msg=" + enquiryFollowup.getRemark() + ". for "
									+ user.getFirstName() + " " + user.getLastName();
							;
							con.setDoOutput(true);
							DataOutputStream wr = new DataOutputStream(con.getOutputStream());
							wr.writeBytes(urlParameters);
							wr.flush();
							wr.close();
							int responseCode = con.getResponseCode();
							if (responseCode == 200) {
								con.connect();
								BufferedReader rd = new BufferedReader(new InputStreamReader(con.getInputStream()));
								StringBuffer buffer = new StringBuffer();
								String line;
								while ((line = rd.readLine()) != null) {
									buffer.append(line).append("n");
								}
								System.out.println(buffer.toString());
								rd.close();
								con.disconnect();
							} else {
								System.out.println("http response code error: " + responseCode + "\n");
							}
							System.out
									.println("responseCode ->" + responseCode + " --- " + con.getContent().toString());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}

		} else {
			followupEmployee = enquiryDao.getFollowupEmployeeByFollowupId(enquiryFollowup.getFollowupId());
			
			for (int i = 0; i < followupEmployee.size(); i++) {
				
				User u1 = userDao.getUserDetailById(followupEmployee.get(i).getUserId());

				if (notifyviaemail.equals("Yes")) {
					
					try {
						
						InternetAddress[] myBccList = InternetAddress.parse(u.getEmail());
						session1 = mailConfiguration();
						Message message = new MimeMessage(session1);
						message.setFrom(
								new InternetAddress("emailrelay@hsstructures.com", "HomeStay Structures"));
                        System.out.println("u1---" +u1.getEmail());
						message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(u1.getEmail()));

						message.setRecipients(Message.RecipientType.BCC, myBccList);

						message.setSubject("Reminder");
						StringBuilder sb = new StringBuilder();
						sb.append(
								"<table style='border:solid 1px #cfd4d8; font-family: Arial,Helvetica,sans-serif;' align='center' width='750' cellspacing='0' cellpadding='0' border='0'>"
										+ "<tbody>" + "<tr>"
										+ "<td style='padding:15px 0px;  font-size: 16px;  color: #263247; ' align='center' valign='middle'>"
										+ "<a href='http://ultrasmarterpsolutions.com/HomeStay'> <img border='0' src='http://ultrasmarterpsolutions.com/HomeStay/resources/admin/images/user2-160x160.png'/></a>"
										+ "</td>" + "</tr>" + "<tr>"
										+ "<td style='line-height:0;font-size:0;vertical-align:top;padding:0px;text-align:left;border-bottom:4px solid #cfd4d8'></td>"
										+ "</tr>" + "<tr>"
										+ "<td style='font-family:Arial,Helvetica,sans-serif;font-size:12px; color:#373737; background-color:#fff; padding:20px;' align='left' valign='top'><p> Dear <strong> ");
						sb.append(u1.getFirstName() + "   " + u1.getLastName() + ",");
						sb.append("</strong></p><br><br> You have new followup massage, <br><br>" + "Followup Massage: "
								+ enquiryFollowup.getRemark() + "<br><br>" + "Followup Added By: " + u.getFirstName()
								+ " " + u.getLastName() + "<br><br>" + "Followup Date Time: "
								+ enquiryFollowup.getFollowupDateTime() + "</td>" + "</tr>" + "<tr>"
								+ "<td style='font-family:Arial,Helvetica,sans-serif;font-size:12px; color:#373737; background-color:#fff; padding:20px;' align='left' valign='top'>Thanks & Regards<br>"
								+ "<strong>HomeStay Structures </strong></td>" + "</tr>" + "</tbody>" + "</table>"
								+ "</td>" + "</tr>" + "</tbody>" + "</table>");
						message.setContent(sb.toString(), "text/html");
						Transport.send(message);
						
						/*
						 * Transport transport = session1.getTransport("smtps");
						 * transport.connect("mail.hsstructures.com", 587, username, password);
						 * transport.sendMessage(message, message.getAllRecipients());
						 * transport.close();
						 */
						System.out.println("E-Mail Send Suceessfully For Delivery...Using JSP.........");
					} catch (Exception msg) {
						System.out.println("Not send mail " + msg);
					}
				}
			//	if (notifyviasms.equals("Yes")) {
					
					
					
					if(false) {
					try {
						String url = "http://sms.astartechnologies.net/sendsms.aspx";
						URL obj = new URL(url);
						HttpURLConnection con = (HttpURLConnection) obj.openConnection();
						con.setRequestMethod("POST");
						con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
						String urlParameters = "mobile=9624024813&pass=EGIQT&senderid=ASTECH&to=" + u1.getMobileNumber()
								+ "&msg=" + enquiryFollowup.getRemark() + ". for " + u.getFirstName() + " "
								+ u.getLastName();
						con.setDoOutput(true);
						DataOutputStream wr = new DataOutputStream(con.getOutputStream());
						wr.writeBytes(urlParameters);
						wr.flush();
						wr.close();
						int responseCode = con.getResponseCode();
						if (responseCode == 200) {
							con.connect();
							BufferedReader rd = new BufferedReader(new InputStreamReader(con.getInputStream()));
							StringBuffer buffer = new StringBuffer();
							String line;
							while ((line = rd.readLine()) != null) {
								buffer.append(line).append("n");
							}
							System.out.println(buffer.toString());
							rd.close();
							con.disconnect();
						} else {
							System.out.println("http response code error: " + responseCode + "\n");
						}
						System.out.println("responseCode ->" + responseCode + " --- " + con.getContent().toString());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

		}

		return "Success";
	}
	
	
	

	
	
	
	/* ********************** this is edited by yuvraj ****************************************************** */
	
	@RequestMapping(value = "sendEnquiryEmailandMessage", method = RequestMethod.POST)
	public String sendEnquiryEmailandMessage(HttpSession session, HttpServletRequest request, HttpServletResponse res) {
		logger.info("***** INSIDE SEND ENQUIRY MAIL & MESSAGE *****");

		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());

		User u = userDao.getUserDetailById(id);

		/*
		 * EnquiryFollowup enquiryFollowup = enquiryDao.getLastEnquiryFollowupDetail();
		 */
		int enquryid = enquiryDao.getLastEnquiryId();

		Enquiry enquiry = null;
		User user = null;
		List<EnquiryAssign> enquiryAssign = null;
		List<EnquiryFollowupMember> fe = null;
		
		enquiry = enquiryDao.getEnquiryDetailsById(enquryid);
		user = userDao.getUserDetailById(enquiry.getClientId());
		enquiryAssign = enquiryDao.getEnquiryAssignByEnquiryId(enquryid);
		
        
        String emailmember = "info@hsstructures.com";
        String mobilenum = "";
		/*
		 * if(enquiryAssign!=null){ for(EnquiryAssign tams : enquiryAssign){ emailmember
		 * = emailmember +','+tams.getEmail(); mobilenum =
		 * mobilenum+','+tams.getMobileNumber(); } }
		 * 
		 * System.out.println(emailmember+"----------------member Email");
		 */
        
					/*User u1 = userDao.getUserDetailById(enquiryAssign.get(i).getUserId());*/

        if(enquiryAssign!=null)
        {
        	
        	for(EnquiryAssign tams : enquiryAssign){ 
        		
        		User u1 = userDao.getUserDetailById(tams.getUserId());
					try {
						session1 = mailConfiguration();
						InternetAddress[] myBccList = InternetAddress.parse("emailrelay@hsstructures.com, webmaster@ultrainfotech.net");

						Message message = new MimeMessage(session1);
						message.setFrom(
								new InternetAddress("emailrelay@hsstructures.com", "HomeStay Structures"));

						message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(tams.getEmail()));

						//message.setRecipients(Message.RecipientType.BCC, myBccList);

						message.setSubject("Enquiry");
						StringBuilder sb = new StringBuilder();
						sb.append(
								"<table style='border:solid 1px #cfd4d8; font-family: Arial,Helvetica,sans-serif;' align='center' width='750' cellspacing='0' cellpadding='0' border='0'>"
										+ "<tbody>" + "<tr>"
										+ "<td style='padding:15px 0px;  font-size: 16px;  color: #263247; ' align='center' valign='middle'>"
										+ "<a href='https://ultrasmarterpsolutions.com/HomeStay'> <img border='0' src=''/></a>"
										+ "</td>" + "</tr>" + "<tr>"
										+ "<td style='line-height:0;font-size:0;vertical-align:top;padding:0px;text-align:left;border-bottom:4px solid #cfd4d8'></td>"
										+ "</tr>" + "<tr>"
										+ "<td style='font-family:Arial,Helvetica,sans-serif;font-size:12px; color:#373737; background-color:#fff; padding:20px;' align='left' valign='top'><p> Dear "+tams.getFirstName()+" "+tams.getLastName()+",<strong> ");
						sb.append("</strong></p><br><br> You have been assigned a new enquiry, <br><br>"
								+ "Enquiry No: " + enquiry.getEnquiryNo() + "<br><br>Client Company Name: "
								+ enquiry.getUserCompanyName() + "<br><br>" + "Client Name: " + user.getFirstName()
								+ " " + user.getLastName() + "<br><br>" + "Enquiry added By: " + enquiry.getCreatedByName()+"<br><br>" + "Enquiry Date Time: "
								+ enquiry.getEnquiryDate() + "</td>" + "</tr>" + "<tr>"
								+ "<td style='font-family:Arial,Helvetica,sans-serif;font-size:12px; color:#373737; background-color:#fff; padding:20px;' align='left' valign='top'>Thanks & Regards<br>"
								+ "<strong>HomeStay Structures</strong></td>" + "</tr>" + "</tbody>" + "</table>"
								+ "</td>" + "</tr>" + "</tbody>" + "</table>");
						message.setContent(sb.toString(), "text/html");
						Transport.send(message);
						System.out.println("E-Mail Send Suceessfully For Delivery...Using JSP.........");
					} catch (Exception msg) {
						System.out.println("Not send mail " + msg);
					}
        	}
        }
		/*
		 * try { String url = "http://sms.astartechnologies.net/sendsms.aspx"; URL obj =
		 * new URL(url); HttpURLConnection con = (HttpURLConnection)
		 * obj.openConnection(); con.setRequestMethod("POST");
		 * con.setRequestProperty("Accept-Language", "en-US,en;q=0.5"); String
		 * urlParameters = "mobile=9624024813&pass=EGIQT&senderid=ASTECH&to=" +
		 * mobilenum + "&msg=" + "You have been assigned a new enquiry. for " +
		 * user.getFirstName() + " " + user.getLastName(); ; con.setDoOutput(true);
		 * DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		 * wr.writeBytes(urlParameters); wr.flush(); wr.close(); int responseCode =
		 * con.getResponseCode(); if (responseCode == 200) { con.connect();
		 * BufferedReader rd = new BufferedReader(new
		 * InputStreamReader(con.getInputStream())); StringBuffer buffer = new
		 * StringBuffer(); String line; while ((line = rd.readLine()) != null) {
		 * buffer.append(line).append("n"); } System.out.println(buffer.toString());
		 * rd.close(); con.disconnect(); } else {
		 * System.out.println("http response code error: " + responseCode + "\n"); }
		 * System.out .println("responseCode ->" + responseCode + " --- " +
		 * con.getContent().toString()); } catch (Exception e) { e.printStackTrace(); }
		 */			
		return "Success";
	}
}
