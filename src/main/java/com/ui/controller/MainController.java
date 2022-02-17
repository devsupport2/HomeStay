package com.ui.controller;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Locale locale, Model model, HttpSession session) {
		logger.info("##### LOGIN PAGE #####");
		if (session.getAttribute("useridadmin") == null
				|| Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0) {
			return "index";
		}		
		return "index";
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String adminhome(Locale locale, Model model, HttpSession session) {
		logger.info("##### DASHBOARD #####");
		if (session.getAttribute("useridadmin") == null
				|| Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0) {
			return "index";
		}
		return "home";
	}
	
	@RequestMapping(value = "/logoutadmin", method = RequestMethod.GET)
	public String logoutadmin(Locale locale, Model model, HttpSession session) {
		logger.info("##### ADMIN LOGOUT #####");
		try {
			if (session.getAttribute("useridadmin") != null
					|| Integer.parseInt(session.getAttribute("useridadmin").toString()) != 0) {
				session.setAttribute("useridadmin", null);
				session.setAttribute("shownameadmin", null);
				session.setAttribute("usertypeidadmin", null);
				session.setAttribute("emailidadmin", null);
				session.setAttribute("mobilenumberadmin", null);
				session.setAttribute("locationidadmin", null);
				return "index";
			}
		} catch (Exception e) {
			return "index";
		}
		return "index";
	}
	
	@RequestMapping(value = "/manage_country", method = RequestMethod.GET)
	public String manage_country(Locale locale, Model model, HttpSession session) {
		logger.info("##### MANAGE COUNTRY #####");
		if (session.getAttribute("useridadmin") == null
				|| Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0) {
			return "index";
		}
		return "manage_country";
	}
	
	@RequestMapping(value = "/manage_state", method = RequestMethod.GET)
	public String manage_state(Locale locale, Model model, HttpSession session) {
		logger.info("##### MANAGE STATE #####");
		if (session.getAttribute("useridadmin") == null
				|| Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0) {
			return "index";
		}
		return "manage_state";
	}
	
	@RequestMapping(value = "/manage_measurement_unit", method = RequestMethod.GET)
	public String manage_measurement_unit(Locale locale, Model model, HttpSession session) {
		logger.info("##### MANAGE UNIT #####");
		if (session.getAttribute("useridadmin") == null
				|| Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0) {
			return "index";
		}
		return "manage_measurement_unit";
	}
	
	@RequestMapping(value = "/manage_currency", method = RequestMethod.GET)
	public String manage_currency(Locale locale, Model model, HttpSession session) {
		logger.info("##### MANAGE CURRENCY #####");
		if (session.getAttribute("useridadmin") == null
				|| Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0) {
			return "index";
		}
		return "manage_currency";
	}
	
	@RequestMapping(value = "/manage_financial_year", method = RequestMethod.GET)
	public String manage_financial_year(Locale locale, Model model, HttpSession session) {
		logger.info("##### MANAGE FINANCIAL YEAR #####");
		if (session.getAttribute("useridadmin") == null
				|| Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0) {
			return "index";
		}
		return "manage_financial_year";
	}
	
	@RequestMapping(value = "/manage_user", method = RequestMethod.GET)
	public String manage_user(Locale locale, Model model, HttpSession session) {
		logger.info("##### MANAGE USER #####");
		if (session.getAttribute("useridadmin") == null
				|| Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0) {
			return "index";
		}
		return "manage_user";
	}
	
	@RequestMapping(value = "/manage_tax", method = RequestMethod.GET)
	public String manage_tax(Locale locale, Model model, HttpSession session) {
		logger.info("##### MANAGE TAX #####");
		if (session.getAttribute("useridadmin") == null
				|| Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0) {
			return "index";
		}
		return "manage_tax";
	}
	
	
	
	@RequestMapping(value = "/manage_task", method = RequestMethod.GET)
	public String manage_task(Locale locale, Model model, HttpSession session) {
		logger.info("##### MANAGE TAX #####");
		if (session.getAttribute("useridadmin") == null
				|| Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0) {
			return "index";
		}
		return "manage_task";
	}
	
	
	
	@RequestMapping(value = "/manage_task2", method = RequestMethod.GET)
	public String manage_task2(Locale locale, Model model, HttpSession session) {
		logger.info("##### MANAGE TAX #####");
		if (session.getAttribute("useridadmin") == null
				|| Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0) {
			return "index";
		}
		return "manage_task2";
	}
	
	
	
	@RequestMapping(value = "/manage_brand", method = RequestMethod.GET)
	public String manage_brand(Locale locale, Model model, HttpSession session) {
		logger.info("##### MANAGE BRAND #####");
		if (session.getAttribute("useridadmin") == null
				|| Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0) {
			return "index";
		}
		return "manage_brand";
	}
	
	@RequestMapping(value = "/manage_category", method = RequestMethod.GET)
	public String manage_category(Locale locale, Model model, HttpSession session) {
		logger.info("##### MANAGE CATEGORY #####");
		if (session.getAttribute("useridadmin") == null
				|| Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0) {
			return "index";
		}
		return "manage_category";
	}
	
	@RequestMapping(value = "/manage_subcategory", method = RequestMethod.GET)
	public String manage_subcategory(Locale locale, Model model, HttpSession session) {
		logger.info("##### MANAGE SUBCATEGORY #####");
		if (session.getAttribute("useridadmin") == null
				|| Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0) {
			return "index";
		}
		return "manage_subcategory";
	}
	
	@RequestMapping(value = "/manage_product", method = RequestMethod.GET)
	public String manage_product(Locale locale, Model model, HttpSession session) {
		logger.info("##### MANAGE PRODUCT #####");
		if (session.getAttribute("useridadmin") == null
				|| Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0) {
			return "index";
		}
		return "manage_product";
	}
	
	@RequestMapping(value = "/manage_company", method = RequestMethod.GET)
	public String manage_company(Locale locale, Model model, HttpSession session) {
		logger.info("##### MANAGE COMPANY #####");
		if (session.getAttribute("useridadmin") == null
				|| Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0) {
			return "index";
		}
		return "manage_company";
	}
	
	@RequestMapping(value = "/manage_enquiry", method = RequestMethod.GET)
	public String manage_enquiry(Locale locale, Model model, HttpSession session) {
		logger.info("##### MANAGE ENQUIRY #####");
		if (session.getAttribute("useridadmin") == null
				|| Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0) {
			return "index";
		}
		return "manage_enquiry";
	}
	
	@RequestMapping(value = "/manage_quotation_template", method = RequestMethod.GET)
	public String manage_quotation_template(Locale locale, Model model, HttpSession session) {
		logger.info("##### MANAGE ENQUIRY #####");
		if (session.getAttribute("useridadmin") == null
				|| Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0) {
			return "index";
		}
		return "manage_quotation_template";
	}
	
	@RequestMapping(value = "/generate_quotation", method = RequestMethod.GET)
	public String generate_quotation(Locale locale, Model model, HttpSession session) {
		logger.info("##### GENERATE QUOTATION #####");		
		if (session.getAttribute("useridadmin") == null
				|| Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0) {
			return "index";
		}
		return "generate_quotation";
	}
	
	@RequestMapping(value = "/manage_quotation", method = RequestMethod.GET)
	public String manage_quotation(Locale locale, Model model, HttpSession session) {
		logger.info("##### MANAGE QUOTATION #####");
		if (session.getAttribute("useridadmin") == null
				|| Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0) {
			return "index";
		}
		return "manage_quotation";
	}
	
	@RequestMapping(value = "/print_quotation", method = RequestMethod.GET)
	public String print_quotation(int quotationid, Locale locale, Model model, HttpSession session) {
		logger.info("##### MANAGE QUOTATION #####");
		if (session.getAttribute("useridadmin") == null
				|| Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0) {
			return "index";
		}
		return "print_quotation";
	}
	
	@RequestMapping(value = "/forget_password", method = RequestMethod.GET)
	public String forget_password(Locale locale, Model model, HttpSession session) {
		logger.info("##### FORGET PASSWORD #####");
		return "forget_password";
	}

	@RequestMapping(value = "/reset_password", method = RequestMethod.GET)
	public String reset_password(Locale locale, Model model, HttpSession session) {
		logger.info("##### RESET PASSWORD #####");
		return "reset_password";
	}

	@RequestMapping(value = "/change_password", method = RequestMethod.GET)
	public String change_password(Locale locale, Model model, HttpSession session) {
		logger.info("##### CHANGE PASSWORD #####");
		if (session.getAttribute("useridadmin") == null
				|| Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0) {
			return "index";
		}
		return "change_password";
	}
	
	@RequestMapping(value = "/employee_activity", method = RequestMethod.GET)
    public String employee_activity(Locale locale, Model model, HttpSession session) {
        logger.info("##### MANAGE EMPLOYEE ACTIVITY #####");
        if (session.getAttribute("useridadmin") == null
                || Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0) {
            return "index";
        }
        return "employee_activity";
    }
}
