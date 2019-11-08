package com.atmecs.assignment.testscript;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.atmecs.assignment.base.Base;
import com.atmecs.assignment.config.Constant;
import com.atmecs.assignment.helper.Helper;
import com.atmecs.assignment.helper.Waits;
import com.atmecs.assignment.report.Reports;
/**Verify and validate the product
 * 
 * @author ranjitha.selvam
 *
 */

public class TestScript extends Base {

	public Helper helper = new Helper();
	public Waits waits = new Waits();
	public Reports report = new Reports();

	@BeforeClass
	public void browserLaunch() {
		getBrowser();
		report.logInfo("user sucessfully landed");
		getUrl();
	}
	
	 @DataProvider
	  public Object[][] productSearch() throws Exception 
     { 
		  Object data[][] = utils.getExcel(Constant.productData_file, "Sheet1");
	 
	  
	  return data;
	  }
	  
	  @Test(dataProvider="productSearch")
	  public void productValidation(String categories,String productPrice,String exTax,String description ) throws Exception {

		helper.elementIsDisplayed(driver, utils.propertyRead(Constant.homePage_file, "loc_home"));
		report.logInfo("validated home page");
		helper.scrollToBottomOfThePage(driver);
		report.logInfo("search product1 validation");
		helper.moveOver(driver, utils.propertyRead(Constant.homePage_file, "loc_product1"));
		String price1 = helper.getText(driver, utils.propertyRead(Constant.homePage_file, "loc_price1"));
		String exTax1 = helper.getText(driver, utils.propertyRead(Constant.homePage_file, "loc_tax1"));
		helper.clickOnWebElement(driver, utils.propertyRead(Constant.homePage_file,"loc_product1"));
		helper.pageValidation(price1, productPrice);
		helper.pageValidation(exTax1, exTax);

	}

	@AfterClass
	public void browserClose() {

		driverClose();
		report.logInfo("driver closed");
	}

}
