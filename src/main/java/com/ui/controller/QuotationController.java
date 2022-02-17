package com.ui.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ui.dao.FinancialYearDAO;
import com.ui.dao.QuotationDAO;
import com.ui.model.CoverLetter;
import com.ui.model.Quotation;
import com.ui.model.QuotationProduct;
import com.ui.model.QuotationProductScopeOfSupply;
import com.ui.model.QuotationProductSpecification;
import com.ui.model.QuotationTermStatement;
import com.ui.model.TermMaster;
import com.ui.model.TermStatement;

@RestController
public class QuotationController {
	@Autowired
	QuotationDAO quotationDao;
	@Autowired
	FinancialYearDAO financialYearDao;

	CoverLetter coverLetter;
	TermMaster termMaster;
	TermStatement termStatement;
	QuotationProduct quotationProduct;
	QuotationProductScopeOfSupply quotationProductScopeOfSupply;
	QuotationProductSpecification quotationProductSpecification;
	QuotationTermStatement quotationTermStatement;

	private static final Logger logger = LoggerFactory.getLogger(QuotationController.class);

	@RequestMapping(value = "editCoverLetter", method = RequestMethod.POST)
	public String editCoverLetter(@RequestParam(value = "description", required = false) String description, int id,
			HttpServletRequest request, HttpSession session) {
		logger.info("***** EDOT COVER LETTER *****");

		int cid = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}

		try {
			coverLetter = new CoverLetter(id, URLDecoder.decode(description, "UTF-8"), cid, IpAddress);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		quotationDao.editCoverLetter(coverLetter);

		return "Success";
	}

	@RequestMapping(value = "/getCoverLetterDetailById", method = RequestMethod.GET, produces = "application/json")
	public CoverLetter getCoverLetterDetailById(int id, HttpServletRequest request) {
		logger.info("***** GET COVER LETTER DETAIL BY ID *****");
		CoverLetter coverLetter = quotationDao.getCoverLetterDetailById(id);
		return coverLetter;
	}

	@RequestMapping(value = "/getAllTerm", method = RequestMethod.GET, produces = "application/json")
	public List<TermMaster> getAllTerm(HttpServletRequest request) {
		logger.info("***** GET ALL TERM *****");
		List<TermMaster> t = quotationDao.getAllTerm();
		return t;
	}

	@RequestMapping(value = "deleteTerm", method = RequestMethod.DELETE)
	public void delete(int termid) {
		logger.info("***** DELETE TERM *****");
		quotationDao.deleteTerm(termid);
	}

	@RequestMapping(value = "/getTermStatementById", method = RequestMethod.GET, produces = "application/json")
	public List<TermStatement> getTermStatementById(int termid, HttpServletRequest request) {
		logger.info("***** GET TERM STATEMENT BY ID *****");
		List<TermStatement> t = quotationDao.getTermStatementById(termid);
		return t;
	}

	@RequestMapping(value = "addTermItemRow", method = RequestMethod.POST)
	public String addTermItemRow(int termid, int sequence, String label, String statement, HttpServletRequest request,
			HttpSession session) {
		logger.info("***** ADD TERM ITEM ROW *****");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		if (label.equals("")) {
			label = null;
		}

		statement = statement.replace("$", "&");
		statement = statement.replace("~", "#");
		statement = statement.replace("!", "%");

		termStatement = new TermStatement(termid, sequence, label, statement, id, IpAddress);
		quotationDao.addTermItemRow(termStatement);

		return "Success";
	}

	@RequestMapping(value = "editTerm", method = RequestMethod.POST)
	public String editTerm(int termid, String termtitle, HttpServletRequest request, HttpSession session) {
		logger.info("***** EDIT TERM ITEM ROW *****");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}

		termtitle = termtitle.replace("$", "&");
		termtitle = termtitle.replace("~", "#");
		termtitle = termtitle.replace("!", "%");

		termMaster = new TermMaster(termid, termtitle, id, IpAddress);
		quotationDao.editTerm(termMaster);

		return "Success";
	}

	@RequestMapping(value = "deleteTermItem", method = RequestMethod.DELETE)
	public void deleteTermItem(int termstatementid) {
		logger.info("***** DELETE TERM ITEM *****");
		quotationDao.deleteTermItem(termstatementid);
	}

	@RequestMapping(value = "/getQuotationTermStatement", method = RequestMethod.GET, produces = "application/json")
	public List<TermStatement> getQuotationTermStatement(HttpServletRequest request) {
		logger.info("***** GET ALL TERM STATEMENT *****");
		List<TermStatement> t = quotationDao.getQuotationTermStatement();
		return t;
	}

	@RequestMapping(value = "generateQuotation", method = RequestMethod.POST)
	public String generateQuotation(@RequestParam(value = "coverletter", required = false) String coverletter,
			int enquiryid, String quotationdate, String purchaseorder, String podate, int clientid,
			HttpServletRequest request, HttpSession session) {
		logger.info("***** CREATE QUOTATION *****");

		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		String s = "y";
		if (purchaseorder.equals("")) {
			purchaseorder = null;
		}
		if (podate.equals("")) {
			podate = null;
		}
		String quotationno = null;
		int sequence = 0;
		String currentYearCode = financialYearDao.getCurrentFinancialYearCode();

		Quotation quotation = null;

		quotation = quotationDao.getLastQuotationDetail();

		if (quotation == null) {
			sequence = 1;
			quotationno = "OPL-" + currentYearCode + "-0001";
			try {
				quotation = new Quotation(sequence, enquiryid, quotationno, quotationdate, clientid, purchaseorder,
						podate, URLDecoder.decode(coverletter, "UTF-8"), s, id, IpAddress);
			} catch (UnsupportedEncodingException e) {
				return "Error";
			}
			quotationDao.createQuotation(quotation);
			return "Success";
		} else {
			String fc = quotation.getQuotationNo();
			String financialyearcode1 = fc.substring(4, 8);

			sequence = quotation.getSequence();

			if (sequence == 0) {
				sequence = 1;
				quotationno = "OPL-" + currentYearCode + "-0001";
				try {
					quotation = new Quotation(sequence, enquiryid, quotationno, quotationdate, clientid, purchaseorder,
							podate, URLDecoder.decode(coverletter, "UTF-8"), s, id, IpAddress);
				} catch (UnsupportedEncodingException e) {
					return "Error";
				}
				quotationDao.createQuotation(quotation);
				return "Success";

			} else {
				if (currentYearCode.equals(financialyearcode1)) {
					sequence = sequence + 1;
				} else {
					sequence = 1;
				}

				if (sequence >= 1 && sequence < 10) {
					quotationno = "OPL-" + currentYearCode + "-000" + Integer.toString(sequence);
				} else if (sequence >= 10 && sequence < 100) {
					quotationno = "OPL-" + currentYearCode + "-00" + Integer.toString(sequence);
				} else if (sequence >= 100 && sequence < 1000) {
					quotationno = "OPL-" + currentYearCode + "-0" + Integer.toString(sequence);
				} else if (sequence >= 1000 && sequence < 10000) {
					quotationno = "OPL-" + currentYearCode + "-" + Integer.toString(sequence);
				}
				try {
					quotation = new Quotation(sequence, enquiryid, quotationno, quotationdate, clientid, purchaseorder,
							podate, URLDecoder.decode(coverletter, "UTF-8"), s, id, IpAddress);
				} catch (UnsupportedEncodingException e) {
					return "Error";
				}
				quotationDao.createQuotation(quotation);
				return "Success";
			}
		}
	}

	@RequestMapping(value = "addQuotationProduct", method = RequestMethod.POST)
	public String addQuotationProduct(int productid, String productname, String partnumber, float productqty,
			float saleprice, HttpServletRequest request, HttpSession session) {
		logger.info("***** ADD QUOTATION PRODUCT *****");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}

		Quotation quotation = quotationDao.getLastQuotationDetail();

		quotationProduct = new QuotationProduct(quotation.getQuotationId(), productid, productname, partnumber,
				productqty, saleprice, id, IpAddress);
		quotationDao.addQuotationProduct(quotationProduct);

		return "Success";
	}

	@RequestMapping(value = "addQuotationProductScopeOfSupply", method = RequestMethod.POST)
	public String addQuotationProductScopeOfSupply(int productid, String particular, String value, float qty,
			String unitname, HttpServletRequest request, HttpSession session) {
		logger.info("***** ADD QUOTATION PRODUCT SCOPE OF SUPPLY *****");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}

		Quotation quotation = quotationDao.getLastQuotationDetail();

		quotationProductScopeOfSupply = new QuotationProductScopeOfSupply(quotation.getQuotationId(), productid,
				particular, value, qty, unitname, id, IpAddress);
		quotationDao.addQuotationProductScopeOfSupply(quotationProductScopeOfSupply);

		return "Success";
	}

	@RequestMapping(value = "addQuotationProductSpecification", method = RequestMethod.POST)
	public String addQuotationProductSpecification(int productid, String specification, String specvalue,
			String unitname, HttpServletRequest request, HttpSession session) {
		logger.info("***** ADD QUOTATION PRODUCT SPECIFICATION *****");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}

		Quotation quotation = quotationDao.getLastQuotationDetail();

		quotationProductSpecification = new QuotationProductSpecification(quotation.getQuotationId(), productid,
				specification, specvalue, unitname, id, IpAddress);
		quotationDao.addQuotationProductSpecification(quotationProductSpecification);

		return "Success";
	}

	@RequestMapping(value = "addQuotationTermStatement", method = RequestMethod.POST)
	public String addQuotationTermStatement(int termmasterid, String termtitle, int sequence, String label,
			String statement, HttpServletRequest request, HttpSession session) {
		logger.info("***** ADD QUOTATION TERM STATEMENT *****");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}

		if (label.equals("")) {
			label = null;
		}

		statement = statement.replace("$", "&");
		statement = statement.replace("~", "#");
		statement = statement.replace("!", "%");

		Quotation quotation = quotationDao.getLastQuotationDetail();

		quotationTermStatement = new QuotationTermStatement(quotation.getQuotationId(), termmasterid, termtitle,
				sequence, label, statement, id, IpAddress);
		quotationDao.addQuotationTermStatement(quotationTermStatement);

		return "Success";
	}

	@RequestMapping(value = "/getQuotationByPage", method = RequestMethod.GET, produces = "application/json")
	public List<Quotation> getQuotationByPage(int pagesize, int startindex, HttpServletRequest request,
			HttpSession session) {
		logger.info("***** GET QUOTATION BY PAGE *****");
		List<Quotation> quotation = quotationDao.getQuotationByPage(pagesize, startindex);
		return quotation;
	}

	@RequestMapping(value = "/getAllQuotation", method = RequestMethod.GET, produces = "application/json")
	public List<Quotation> getAllQuotation(HttpServletRequest request, HttpSession session) {
		logger.info("***** GET QUOTATION *****");
		List<Quotation> quotation = quotationDao.getAllQuotation();
		return quotation;
	}

	@RequestMapping(value = "/searchQuotation", method = RequestMethod.GET, produces = "application/json")
	public List<Quotation> searchQuotation(String keyword, HttpServletRequest request, HttpSession session) {
		logger.info("***** SEARCH QUOTATION *****");
		List<Quotation> quotation = quotationDao.searchQuotation(keyword);
		return quotation;
	}

	@RequestMapping(value = "deleteQuotation", method = RequestMethod.DELETE)
	public void deleteQuotation(int quotationid) {
		logger.info("***** DELETE QUOTATION *****");
		quotationDao.deleteQuotation(quotationid);
	}

	@RequestMapping(value = "/getQuotationDetailById", method = RequestMethod.GET, produces = "application/json")
	public Quotation getQuotationDetailById(int quotationid, HttpServletRequest request, HttpSession session) {
		logger.info("***** GET QUOTATION DETAIL BY ID *****");
		Quotation quotation = quotationDao.getQuotationDetailById(quotationid);
		return quotation;
	}

	@RequestMapping(value = "/getQuotationProductByQuotationId", method = RequestMethod.GET, produces = "application/json")
	public List<QuotationProduct> getQuotationProductByQuotationId(int quotationid, HttpServletRequest request,
			HttpSession session) {
		logger.info("***** GET QUOTATION PRODUCT BY QUOTATION ID *****");
		List<QuotationProduct> quotationProduct = quotationDao.getQuotationProductByQuotationId(quotationid);

		for (QuotationProduct qp : quotationProduct) {
			qp.setSpecifications(quotationDao.getQuotationProductSpecificationByQuotationIdAndProductId(quotationid,
					qp.getProductId()));
			qp.setScopeOfSupplies(quotationDao.getQuotationProductScopeOfSupplyByQuotationIdAndProductId(quotationid,
					qp.getProductId()));
		}
		return quotationProduct;
	}

	@RequestMapping(value = "/getQuotationProductSpecificationByQuotationId", method = RequestMethod.GET, produces = "application/json")
	public List<QuotationProductSpecification> getQuotationProductSpecificationByQuotationId(int quotationid,
			HttpServletRequest request, HttpSession session) {
		logger.info("***** GET QUOTATION PRODUCT SPECIFICATION BY QUOTATION ID *****");
		List<QuotationProductSpecification> quotationProductSpecification = quotationDao
				.getQuotationProductSpecificationByQuotationId(quotationid);
		return quotationProductSpecification;
	}

	@RequestMapping(value = "/getQuotationProductScopeOfSupplyByQuotationId", method = RequestMethod.GET, produces = "application/json")
	public List<QuotationProductScopeOfSupply> getQuotationProductScopeOfSupplyByQuotationId(int quotationid,
			HttpServletRequest request, HttpSession session) {
		logger.info("***** GET QUOTATION PRODUCT SCOPE OF SUPPLY BY QUOTATION ID *****");
		List<QuotationProductScopeOfSupply> quotationProductScopeOfSupply = quotationDao
				.getQuotationProductScopeOfSupplyByQuotationId(quotationid);
		return quotationProductScopeOfSupply;
	}

	@RequestMapping(value = "/getQuotationTermStatementByQuotationId", method = RequestMethod.GET, produces = "application/json")
	public List<QuotationTermStatement> getQuotationTermStatementByQuotationId(int quotationid, int termid,
			HttpServletRequest request, HttpSession session) {
		logger.info("***** GET QUOTATION PRODUCT SCOPE OF SUPPLY BY QUOTATION ID *****");
		List<QuotationTermStatement> quotationTermStatement = quotationDao
				.getQuotationTermStatementByQuotationId(quotationid, termid);
		return quotationTermStatement;
	}

}
