package com.ui.dao;

import java.util.List;

import com.ui.model.TermMaster;
import com.ui.model.TermStatement;
import com.ui.model.CoverLetter;
import com.ui.model.Quotation;
import com.ui.model.QuotationProduct;
import com.ui.model.QuotationProductScopeOfSupply;
import com.ui.model.QuotationProductSpecification;
import com.ui.model.QuotationTermStatement;

public interface QuotationDAO {
	public void editCoverLetter(CoverLetter c);

	public CoverLetter getCoverLetterDetailById(int id);

	public List<TermMaster> getAllTerm();

	public void deleteTerm(int termid);

	public List<TermStatement> getTermStatementById(int termid);

	public void addTermItemRow(TermStatement t);

	public void deleteTermItem(int termstatementid);

	public void editTerm(TermMaster t);

	public List<TermStatement> getQuotationTermStatement();

	public Quotation getLastQuotationDetail();

	public void createQuotation(Quotation q);

	public void addQuotationProduct(QuotationProduct q);

	public void addQuotationProductScopeOfSupply(QuotationProductScopeOfSupply q);

	public void addQuotationProductSpecification(QuotationProductSpecification q);

	public void addQuotationTermStatement(QuotationTermStatement q);

	public List<Quotation> getQuotationByPage(int pagesize, int startindex);

	public List<Quotation> getAllQuotation();

	public List<Quotation> searchQuotation(String keyword);

	public void deleteQuotation(int quotationid);

	public Quotation getQuotationDetailById(int quotationid);

	public List<QuotationProduct> getQuotationProductByQuotationId(int quotationid);

	public List<QuotationProductSpecification> getQuotationProductSpecificationByQuotationId(int quotationid);

	public List<QuotationProductSpecification> getQuotationProductSpecificationByQuotationIdAndProductId(
			int quotationid, int productid);

	public List<QuotationProductScopeOfSupply> getQuotationProductScopeOfSupplyByQuotationId(int quotationid);

	public List<QuotationProductScopeOfSupply> getQuotationProductScopeOfSupplyByQuotationIdAndProductId(
			int quotationid, int productid);

	public List<QuotationTermStatement> getQuotationTermStatementByQuotationId(int quotationid, int termid);
}
