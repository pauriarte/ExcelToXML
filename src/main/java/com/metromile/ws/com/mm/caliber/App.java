package com.metromile.ws.com.mm.caliber;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.metromile.ws.com.mm.caliber.model.*;
import com.smartystreets.api.us_street.Candidate;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws IOException {

		final String prefix = "statefarm-";
		int counter = 1;

		//File myFile = new File("C://Users/pedro.uriarte/Documents/Metromile/tickets/CMI-2438/contact_list_companies.xlsx");
		File myFile = new File("/Users/pedrouriarte/Documents/contact_list_companies.xlsx");
		FileInputStream fis = new FileInputStream(myFile);

		XSSFSheet mySheet;
		Iterator<Row> rowIterator;
		Wrapper wrapper;
		XmlMapper mapper;
		try (XSSFWorkbook myWorkBook = new XSSFWorkbook(fis)) {
			//XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);
			//XSSFSheet mySheet = myWorkBook.getSheetAt(0);
			mySheet = myWorkBook.getSheetAt(0);
		}
		rowIterator = mySheet.iterator();

		wrapper = new Wrapper();
		UsExtract addressApi = new UsExtract();

		while (rowIterator.hasNext()) {

			//ABAutoRepairShop shop = new ABAutoRepairShop();
			InsuranceCompany company = new InsuranceCompany();
			//shop.setPublicId(prefix.concat(String.valueOf(counter)));
			company.setPublicId(prefix.concat(String.valueOf(counter)));

			ABContactAddress abContactAddress = new ABContactAddress();
			abContactAddress.setId(prefix.concat(String.valueOf(counter)));

			Address address = new Address();
			address.setId(prefix.concat(String.valueOf(counter)));

			abContactAddress.setContact(new ReferenceToContact(company.getPublicId()));
			abContactAddress.setAddress(new ReferenceToAddress(address.getId()));

			company.setPrimaryAddress(new ReferenceToAddress(address.getId()));
			
			List<ABContactTag> tags = new LinkedList<>();
			ABContactTag tag = new ABContactTag();
			tag.setType("Vendor");
			tags.add(tag);
			company.setTags(tags);

			Row row = rowIterator.next();

			int cellIndexCounter = 0;
			Iterator<Cell> cellIterator = row.cellIterator();

			while (cellIterator.hasNext()) {

				Cell cell = cellIterator.next();
				String value = cell.getStringCellValue();

				switch (cellIndexCounter) {
				case 0:
					System.out.println("NAME:: ");
					System.out.println(value);
					company.setName(value);
					break;
				case 1:
					company.setWorkphone(value);
					break;
				case 2:
					if (value != null) {
						try {
							System.out.println("ADDRESSSSS: ");
							System.out.println(value);
							com.smartystreets.api.us_extract.Address[] results = addressApi.getAddress(value);
							Candidate candidate = results[0].getCandidates()[0];
							address.setAddressLine1(candidate.getDeliveryLine1());
							address.setCity(candidate.getComponents().getCityName());
							address.setState(candidate.getComponents().getState());
							address.setPostalCode(candidate.getComponents().getZipCode());

						} catch (Exception e) {
							address.setAddressLine1(value);
							e.printStackTrace();
						}
					}
					break;
/*				case 7:
					shop.setEmail(value);
					break;
				case 12:
					shop.setYelp(value);
					break;*/
				default:
					break;
				}
				cellIndexCounter++;
			}

			wrapper.getInsuranceCompany().add(company);
			wrapper.getAbContactAddress().add(abContactAddress);
			wrapper.getAddress().add(address);
			counter++;

		}

		mapper = new XmlMapper();
		mapper.setDefaultUseWrapper(false);
		mapper.getFactory().getXMLOutputFactory().setProperty("javax.xml.stream.isRepairingNamespaces", false);
		mapper.writeValue(System.out, wrapper);

	}
}
