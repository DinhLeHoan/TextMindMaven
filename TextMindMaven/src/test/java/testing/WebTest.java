package testing;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFWorkbook; 
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;


import io.github.bonigarcia.wdm.WebDriverManager;

public class WebTest {
	public String url = "https://viindoo.com/web/login";
	private WebDriver driver;

	private XSSFWorkbook workbook;
	private XSSFSheet worksheet;

	// Bien ghi kqua ra file excel
	private Map<String, Object[]> TestNGResult;

	// Bien luu tru du lieu doc tu file Excel
	private Map<String, String[]> dataLogin;

	// duong dan cua file excel va hihnh anh test
	private final String EXCEL_DIR = "C:\\Users\\ducan\\Desktop\\TextMind\\TextMindMaven\\TextMindMaven\\src\\test\\resources\\data\\";
	private final String IMAGE_DIR = "C:\\Users\\ducan\\Desktop\\TextMind\\TextMindMaven\\TextMindMaven\\src\\test\\resources\\images\\";

	// doc du lieu tu file excel
	private void readDataFromExcel() {
		try {
			dataLogin = new HashMap<String, String[]>();
			worksheet = workbook.getSheet("testDataLogin"); // chi dinh truc tiep ten sheet dang co trong
			if (worksheet == null) {
				System.out.println("Trong file excel worksheet  khong ton tai !");
			} else {
				// Doc data tu worksh
Iterator<Row> rowIterator = worksheet.iterator(); // doc tung dong du lieu trong file excel
				DataFormatter df = new DataFormatter(); // doc data chinh xac
				while (rowIterator.hasNext()) {
					Row row = rowIterator.next();
					if (row.getRowNum() >= 1) {
						Iterator<Cell> cellIterator = row.cellIterator();
						String key = "";
						String username = "";
						String password = "";
						String expected = "";
						while (cellIterator.hasNext()) {
							Cell cell = cellIterator.next();
							if (cell.getColumnIndex() == 0) {
								key = df.formatCellValue(cell);
							} else if (cell.getColumnIndex() == 1) {
								username = df.formatCellValue(cell);
							} else if (cell.getColumnIndex() == 2) {
								password = df.formatCellValue(cell);
							} else if (cell.getColumnIndex() == 3) {
								expected = df.formatCellValue(cell);
							}
							String[] myArr = { username, password, expected };
							dataLogin.put(key, myArr);
						}
					}
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void takeScreenShot(WebDriver driver, String outPutScreen) throws IOException {
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot, new File(outPutScreen));
	}

	public void writeImg(String imgSrc, Row row, Cell cell, XSSFSheet sheet) throws IOException {
		InputStream is = new FileInputStream(imgSrc);
		byte[] bytes = IOUtils.toByteArray(is);
		int idImg = sheet.getWorkbook().addPicture(bytes, XSSFWorkbook.PICTURE_TYPE_PNG);
		is.close();

		XSSFDrawing drawing = sheet.createDrawingPatriarch();
		ClientAnchor anchor = new XSSFClientAnchor();

		anchor.setCol1(cell.getColumnIndex() + 1);
		anchor.setRow2(row.getRowNum());
		anchor.setCol1(cell.getColumnIndex() + 2);
		anchor.setRow2(row.getRowNum() + 1);

		drawing.createPicture(anchor, idImg);

	}
	
	@BeforeClass
	public void suiteTest() throws FileNotFoundException, IOException {
		try {
			TestNGResult = new LinkedHashMap<String, Object[]>();
			System.setProperty("webdriver.edge.driver",
					"C:\\Users\\ducan\\Desktop\\TextMind\\TextMindMaven\\TextMindMaven\\ExcelLibary\\edgedriver_win64\\msedgedriver.exe");
			driver = new EdgeDriver();
			driver.manage().window().maximize();
			
			workbook = new XSSFWorkbook(new FileInputStream(new File(EXCEL_DIR + "test_login.xlsx")));
			worksheet = workbook.getSheet("testDataLogin");
			readDataFromExcel();

			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			workbook = new XSSFWorkbook();
			worksheet = workbook.createSheet("TestNG Result Summary");

			CellStyle rowStyle = workbook.createCellStyle();
			rowStyle.setAlignment(HorizontalAlignment.CENTER);
			rowStyle.setVerticalAlignment(VerticalAlignment.CENTER);
			rowStyle.setWrapText(true);

			TestNGResult.put("1", new Object[] { "Test step no.", "Action", "Username", "Password", "Expected output",
"Actual expected", "Status", "Link", "Image" });
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@AfterClass
	public void suiteTearDown() {
		Set<String> keySet = TestNGResult.keySet();
		int rownum = 0;
		for (String key : keySet) {
			CellStyle rowStyle = workbook.createCellStyle();
			Row row = worksheet.createRow(rownum++);
			Object[] objArr = TestNGResult.get(key);
			int cellnum = 0;
			for (Object obj : objArr) {
				Cell cell = row.createCell(cellnum++);
				if (obj instanceof Date) {
					cell.setCellValue((Date) obj);
				} else if (obj instanceof Boolean) {
					cell.setCellValue((Boolean) obj);
				} else if (obj instanceof String) {
					cell.setCellValue((String) obj);
				} else if (obj instanceof Double) {
					cell.setCellValue((Double) obj);
				}

				if (obj.toString().contains("failure") && obj.toString().contains(".png")) {
					try {
						row.setHeightInPoints(80);
						writeImg(obj.toString(), row, cell, worksheet);
						CreationHelper creationHelper = worksheet.getWorkbook().getCreationHelper();
						XSSFHyperlink hyperlink = (XSSFHyperlink) creationHelper.createHyperlink(HyperlinkType.URL);
						cell.setCellValue("Full Image");
						hyperlink.setAddress(obj.toString().replace("\\", "/"));
						cell.setHyperlink(hyperlink);

					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}

			try {
				FileOutputStream out = new FileOutputStream(new File(EXCEL_DIR + "SaveTestNGResultToExcel.xlsx"));
				workbook.write(out);
				out.close();
				System.out.println("Successfully saved Selenium Webdriver TestNG result to Excel File !!!");

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			driver.quit();

		}

	}
	
	@Test
	public void LoginTest() {
		try {
			Set<String> keySet = dataLogin.keySet();
			int index = 1;
			for (String key : keySet) {
				String[] value = dataLogin.get(key);
				String username = value[0];
				String password = value[1];
				String expected = value[2];

				driver.get(url);

				driver.findElement(By.xpath("//input[@id='login']")).sendKeys(username);
				driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
					
				Thread.sleep(5000);

				driver.findElement(By.xpath("//button[@type='submit']")).click();
				WebElement message ;
				if(username == "" || password == "") {
					
					message = driver.findElement(By.xpath("//p[@class='fw-bold']"));
				}
				else if (username.equals("ceo") && password.equals("ceo")) {
					message = driver.findElement(By.xpath("//a[normalize-space()='Contact us']"));
				} else {
					message = driver.findElement(By.xpath("//p[@role='alert']"));
				}
String title = message.getText();
				
				Thread.sleep(1000);
				if (title.equalsIgnoreCase(expected)) {
					TestNGResult.put(String.valueOf(index + 1),
							new Object[] { String.valueOf(index + 1),
									"Test Login success with valid username and password", username, password, expected,
									title, "PASS", "" });
				} else {
					String path = IMAGE_DIR + "failure" + System.currentTimeMillis() + ".png";

					takeScreenShot(driver, path);
					TestNGResult.put(String.valueOf(index + 1),
							new Object[] { String.valueOf(index + 1),
									"Test Login failed with invalid username and password", username, password,
									expected, title, "FAILED", path.replace("\\", "/") });

				}
				index++ ;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}