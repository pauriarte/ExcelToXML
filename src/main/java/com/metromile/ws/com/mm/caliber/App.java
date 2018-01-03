package com.metromile.ws.com.mm.caliber;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.metromile.ws.com.mm.caliber.model.ABAutoRepairShop;
import com.metromile.ws.com.mm.caliber.model.ABContactAddress;
import com.metromile.ws.com.mm.caliber.model.ABContactTag;
import com.metromile.ws.com.mm.caliber.model.Address;
import com.metromile.ws.com.mm.caliber.model.ReferenceToAddress;
import com.metromile.ws.com.mm.caliber.model.ReferenceToContact;
import com.metromile.ws.com.mm.caliber.model.Wrapper;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws IOException {

		final String prefix = "caliber-";
		int counter = 1;

		File myFile = new File("C://Users/pedro.uriarte/Documents/Metromile/tickets/CMI-2438/contact_list_companies.xlsx");
		FileInputStream fis = new FileInputStream(myFile);

		XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);
		//XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);
		//XSSFSheet mySheet = myWorkBook.getSheetAt(0);
		XSSFSheet mySheet = myWorkBook.getSheetAt(0);
		Iterator<Row> rowIterator = mySheet.iterator();

		Wrapper wrapper = new Wrapper();

		while (rowIterator.hasNext()) {

			ABAutoRepairShop shop = new ABAutoRepairShop();
			shop.setPublicId(prefix.concat(String.valueOf(counter)));

			ABContactAddress abContactAddress = new ABContactAddress();
			abContactAddress.setId(prefix.concat(String.valueOf(counter)));

			Address address = new Address();
			address.setId(prefix.concat(String.valueOf(counter)));

			abContactAddress.setContact(new ReferenceToContact(shop.getPublicId()));
			abContactAddress.setAddress(new ReferenceToAddress(address.getId()));

			shop.setPrimaryAddress(new ReferenceToAddress(address.getId()));
			
			List<ABContactTag> tags = new LinkedList<>();
			ABContactTag tag = new ABContactTag();
			tag.setType("Vendor");
			tags.add(tag);
			shop.setTags(tags);
			
			shop.setName("Caliber Collision");

			Row row = rowIterator.next();

			int cellIndexCounter = 0;
			Iterator<Cell> cellIterator = row.cellIterator();

			while (cellIterator.hasNext()) {

				Cell cell = cellIterator.next();
				String value = "";

				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					value = cell.getStringCellValue();
					break;
				case Cell.CELL_TYPE_NUMERIC:
					value = String.valueOf(new Double(cell.getNumericCellValue()).intValue());
					break;
				case Cell.CELL_TYPE_BOOLEAN:
					value = String.valueOf(cell.getBooleanCellValue());
					break;
				default:
				}

				switch (cellIndexCounter) {
				case 0:
					shop.setMithcellid(value);
					break;
				case 1:
					shop.setNotes(value);
					break;
				case 2:
					address.setAddressLine1(value);
					break;
				case 3:
					address.setCity(value);
					break;
				case 4:
					address.setState(value);
					break;
				case 5:
					address.setPostalCode(value);
					break;
				case 6:
					shop.setWorkphone(value);
					break;
				case 7:
					shop.setEmail(value);
					break;
				case 12:
					shop.setYelp(value);
					break;
				default:
					break;
				}
				cellIndexCounter++;
			}

			wrapper.getAbAutoRepairShop().add(shop);
			wrapper.getAbContactAddress().add(abContactAddress);
			wrapper.getAddress().add(address);

			counter++;
		}

		XmlMapper mapper = new XmlMapper();
		mapper.setDefaultUseWrapper(false);
		mapper.getFactory().getXMLOutputFactory().setProperty("javax.xml.stream.isRepairingNamespaces", false);
		mapper.writeValue(System.out, wrapper);

	}
}
