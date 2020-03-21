package usePackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;
import objectsPackage.Contact;
import objectsPackage.ContactBook;
import utilPackage.DateOfBithComparator;
import utilPackage.RemoveExtraSpace;

public final class UseContactBook 
{

	//1 folder (to hold all CBs)for all run
	private static File folderOfCBs ;
	
	//path whre all cbs to be stored
	private static String pathForCBsStr ;
	//static initializers
	static
	{
		folderOfCBs =  new File("ALL CONTACTBOOKS IN THIS FOLDER");
		
		if(folderOfCBs.exists() == false )
			folderOfCBs.mkdir();
		pathForCBsStr = folderOfCBs.getAbsolutePath()+"/";
	}
	
	//isThere CBs ?	
	public static boolean isThereCBs()
	{		
			String [] allCBSA = folderOfCBs.list();
			if( allCBSA.length == 0 )
				return false ;
			//cerate a tempFile
			File tempFile = null;
			for(String str : allCBSA)
			{
				tempFile = new File(pathForCBsStr+str);
				if(tempFile.isFile() == true )
					return true ;												
			}
	
		return false ;
		
	}
	//isCb exist ?
	public static boolean isCBExist(String nameOfCBStr)
	{	
		if( isThereCBs() == false )
			return false ;
		String [] allCBSA = folderOfCBs.list();
		for (int i = 0; i < allCBSA.length; i++) 
		{			
			if( allCBSA[i].trim().equalsIgnoreCase(nameOfCBStr.trim()) == true )
					return true;
		}
		return false ;
	}
	
	//create a cb
	public static ContactBook createAContactBook(String nameOfCBStr)
	{
		if( isCBExist(nameOfCBStr) == true )
			throw new IllegalArgumentException("CONTACT BOOK WITH SAME NAME ALREADY EXIST ,CAN NOT CREATE "+nameOfCBStr);
		ContactBook cbObj = new ContactBook(nameOfCBStr);
		return cbObj ;
	}
	
	private static String getMatchingCBNameFromHDD(String cbNameToBeComparedStr)
	{
		String [] allCBSA = folderOfCBs.list();
		for (int i = 0; i < allCBSA.length; i++) 
		{
			if( allCBSA[i].trim().equalsIgnoreCase(cbNameToBeComparedStr.trim()) == true )
		      return allCBSA[i];				
		}
		return "";
	}
	//load a cb
	public static ContactBook loadAContactBook( String nameOfCBStr ) throws IOException, ClassNotFoundException
	{
		if( nameOfCBStr == null )
			throw new IllegalArgumentException("PLEASE ENTER NAME OF CONTACT BOOK TO LOAD [WHICH IS NOT NULL]");
		if( nameOfCBStr.trim().length() == 0)
			throw new IllegalArgumentException("PLEASE ENTER ANYTHING EXCLUDING SPACES TO LOAD CONTACT BOOK");
		if( isThereCBs() == false )
			throw new IllegalArgumentException("THERE ARE ZERO CONTACT BOOKS TO LAOD");
		nameOfCBStr = RemoveExtraSpace.removeExtraSpace(nameOfCBStr);
		if( isCBExist(nameOfCBStr.trim()) == false )
			throw new IllegalArgumentException("CONTACT BOOK "+nameOfCBStr.trim()+" DOES NOT EXISTS TO LOAD");
			
		//get matching file from hdd
		nameOfCBStr = getMatchingCBNameFromHDD(nameOfCBStr);
		//now cereat fis obj 
		FileInputStream fisObj = new FileInputStream(pathForCBsStr+nameOfCBStr);
		//cret a obi obj
		ObjectInputStream oisObj = new ObjectInputStream(fisObj);
		//read to oisobj that cb
		ContactBook cbLoadObj = (ContactBook) oisObj.readObject() ;
		//now return laoded cb obj
		oisObj.close();
		return cbLoadObj ;
		
	}
	
	private static int getTotalHowManyCBs()
	{
		if(isThereCBs() == false )
			return 0 ;
		File tempFile = null;
		
		String [] allCBSA = folderOfCBs.list();
		
		int totalCbsInt = 0 ;
		for(String str : allCBSA)
		{		
			tempFile = new File(pathForCBsStr+str);
			if(tempFile.isFile() == true )
				totalCbsInt ++;													
		}
		
		return totalCbsInt ;
		
	}
	//display all cbs
	public static void showAllContatcBooks()
	{
		if( isThereCBs() == false )
			throw new IllegalArgumentException("THERE ARE NO MORE CONTACT BOOKS TO SHOW ");
		//cerate a tempFile
		File tempFile = null;
		
		String [] allCBSA = folderOfCBs.list();
	
		if( getTotalHowManyCBs() == 1 )
		{
			oneCB : for(String fileStr : allCBSA )
			{
				tempFile =  new File(pathForCBsStr+fileStr);
				if( tempFile.isFile() == true )
				{
					System.out.println("THERE IS ONLY ONE CONTACT BOOK & IS ");
					System.out.println(fileStr);
					break oneCB ;
				}
			
			}				
		}
		else
		{
			System.out.println("THE CONTACT BOOKS ARE AS FOLLOWS");
			int indexCb = 1;
			for(String str : allCBSA)
			{		
				tempFile = new File(pathForCBsStr+str);
				if(tempFile.isFile() == true )
				{
					System.out.println(indexCb+" - "+str);
					indexCb++;	
				}
												
			}
		}
		
	}
	
	//search for a CB
	public static void searchForACB(String searchStr)
	{
		if( searchStr == null )
			throw new IllegalArgumentException("PLEASE ENTER SOMETHING TO SEARCH");
		if( searchStr.trim().length() == 0 )
			throw new IllegalArgumentException("PLEASE ENTER ANYTHING WHICH IS NOT EMPTY TO SEARCH");
		if( isThereCBs() ==  false )
			throw new IllegalArgumentException("THERE ARE NO CONTACT BOOKS TO SEARCH");
		//cerate a tempFile
		File tempFile = null;
		//sa[]
		String [] allCBSA = folderOfCBs.list();
		//create a collection to hold all Cbs
		Collection<String> collOfCBNames = new TreeSet<String>();
		for(String str: allCBSA )
		{
			tempFile = new File(pathForCBsStr+str);
			if( tempFile.isFile() == true )
			{
				if( str.toLowerCase().contains(searchStr.trim().toLowerCase()) == true )
				collOfCBNames.add(str);	
			}	
		}
		//now display all cbs
		int indexOfCBInt = 1;
		if(collOfCBNames.size() == 0 )
			System.out.println("THERE ARE NO CONTACT BOOKS FOUND WHOSE NAME CONTAINS \""+searchStr+"\"");
		else if(collOfCBNames.size() ==  1)
		{
			System.out.println("ONLY ONE CONTACT BOOK FOUND WHOSE NAME CONTAINS \""+searchStr+"\" & IS AS FOLLOWS ");
			
			System.out.println( new ArrayList<String>(collOfCBNames).get(0));
		}
			
		else
		{
			System.out.println(collOfCBNames.size()+" CONTACT BOOKS FOUND WHOSE NAME CONTAINS \""+searchStr+"\" & ARE AS FOLLOWS");
			for(String cbNameStr : collOfCBNames )
			{
				System.out.println(indexOfCBInt+" - "+cbNameStr);
				indexOfCBInt++;
			}
		}
				
	}
	
	//get all cbs
	private static Collection<ContactBook> getAllCBs() throws ClassNotFoundException, IOException
	{
		if(isThereCBs() == false )
			throw new IllegalArgumentException("THERE ARE ZERO CONTACT BOOKS TO GET");
		//cret a collection 
		Collection<ContactBook> cbColl = new TreeSet<ContactBook>();
		File tempFile = null;
		//sa[]
		String [] allCBSA = folderOfCBs.list();
		for(String cbNStr : allCBSA )
		{
			tempFile = new File(pathForCBsStr+cbNStr);	
			if(tempFile.isFile() == true )	
			cbColl.add(loadAContactBook(cbNStr));
		}
		
		return cbColl ;
	}
	
	//get all cnts for whom dob is availble
	private static Collection<Contact> getAllctsForWhomDOBisAvailable(Collection<ContactBook> cbCollToCkeck)
	{
		Collection<Contact> colCts = new ArrayList<Contact>();
		for(ContactBook cbObj : cbCollToCkeck )
		{
			if( cbObj.isCBEmpty() == false )
			{
				for(Contact ctObj : cbObj.getCollOfContacts() )
				{
					if( ctObj.getDobOfContact() != null )
						colCts.add(ctObj);
				}
			}
			
		}
		return colCts ;
	}
	//birthday reminder show
	public static void showBirthDayReminders() throws ClassNotFoundException, IOException
	{
		
		if(isThereCBs() == false )
			throw new IllegalArgumentException("THERE ARE NO CONTACT BOOKS AT AT ALL TO SHOW BIRTH DAY REMINDERS FROM THEIR CONTACTS");
		//loop over all cb's cntcts and fetch only those cntct for whoem Dob 's are added
				//need to create date compaert obj					
				DateOfBithComparator dbc = new DateOfBithComparator() ;
				
				Map<Date, String> mpDateWitCtsName = new TreeMap<Date, String>(dbc);
							
				Collection<ContactBook>  colOfAllCBs = getAllCBs();
				Collection<Contact> clOfAllctsHavingDOb = getAllctsForWhomDOBisAvailable(colOfAllCBs);
				
				for(Contact ctLoopObj : clOfAllctsHavingDOb )
				{
					mpDateWitCtsName.put(ctLoopObj.getDobOfContact(), ctLoopObj.getNameOfContactStr());
				}
				if(mpDateWitCtsName.size() == 0)
					throw new IllegalArgumentException("NONE OF THE CONTACT'S DOB IS SET TO SHOW YOU UPCOMING BIRTHDAYS");
					
				//after getting all ctc having dobs in asendig order with ct name as values
								
				//now add a dummy entry to map with current date to compare n get all reminds oops:(
				Date currentDate = new Date();
				mpDateWitCtsName.put(currentDate, "!@#$%^&*()");
							
				LinkedList<String> llOfDOBStrwithCtName1 = new LinkedList<String>();
				//second half
				LinkedList<String> llOfDOBStrwithCtName2 = new LinkedList<String>();
				//equal dates
				LinkedList<String> llOfDOBStrwithCtName3 = new LinkedList<String>();
				//4th
				LinkedList<String> llOfDOBStrwithCtName4 = new LinkedList<String>();
				//5th equl day n equl mnth
				LinkedList<String> llOfDOBStrwithCtName5 = new LinkedList<String>();
				//loop over map
				//looptillDummyCnt:
					for(Object obj : mpDateWitCtsName.entrySet() )
				{
							Entry<Date, String> entObj = (Entry<Date, String>) obj ;
											
					//Date currentDate = new Date();
					Date entDate = entObj.getKey() ;
					if( entObj.getValue().equalsIgnoreCase("!@#$%^&*()") == false )
					{
					  if( entDate.getMonth() < currentDate.getMonth() )
					  {
						llOfDOBStrwithCtName1.add(entObj.getValue()+" - "+getBirthDayReminderDOB(entDate));
				      }
					  else if(entDate.getMonth() > currentDate.getMonth() )
					   {							
						llOfDOBStrwithCtName2.add(entObj.getValue()+" - "+getBirthDayReminderDOB(entDate));
					   }
					    if(entDate.getMonth() == currentDate.getMonth() )
					    {
						  if(entDate.getDate() < currentDate.getDate() )
							llOfDOBStrwithCtName3.add(entObj.getValue()+" - "+getBirthDayReminderDOB(entDate));												
						  else if(entDate.getDate() == currentDate.getDate())
						  {
							  String todayBDayStr ="TODAY IS "+entObj.getValue()+"'s BIRTH DAY ";
							  llOfDOBStrwithCtName4.add(todayBDayStr);						
						  }
						 	
						  else if(entDate.getDate() > currentDate.getDate())
							llOfDOBStrwithCtName5.add(entObj.getValue()+" - "+getBirthDayReminderDOB(entDate));
							
					   }
					}
				
				}//map for ends
					
		//now display all linkedlists in order l1, l3 l4 l5 & l2
					System.out.println("UPCOMING BIRTH DAYS ARE ");		
					//l4
					int indexOfDispDOb = 0;
					for(String s4: llOfDOBStrwithCtName4 )
					{
						if(indexOfDispDOb < 10)
						System.out.println("      "+(++indexOfDispDOb)+"   "+s4);
						else if(indexOfDispDOb == 10)
						System.out.println("      "+(++indexOfDispDOb)+"  "+s4);
						else
						System.out.println("      "+(++indexOfDispDOb)+"  "+s4);
					}									
						
					//l5
					for(String s5: llOfDOBStrwithCtName5 )
					{
						if(indexOfDispDOb < 10)
						System.out.println("      "+(++indexOfDispDOb)+"   "+s5);
						else if(indexOfDispDOb == 10)
						System.out.println("      "+(++indexOfDispDOb)+"  "+s5);
						else
						System.out.println("      "+(++indexOfDispDOb)+"  "+s5);	
					}						
					//l2
					for(String s2 : llOfDOBStrwithCtName2)
					{
						if(indexOfDispDOb < 10)
						System.out.println("      "+(++indexOfDispDOb)+"   "+s2);
						else if(indexOfDispDOb == 10)
						System.out.println("      "+(++indexOfDispDOb)+"  "+s2);
						else
						System.out.println("      "+(++indexOfDispDOb)+"  "+s2);
					}						
					//l1
					for(String s1: llOfDOBStrwithCtName1 )
					{
						if(indexOfDispDOb < 10)
						System.out.println("      "+(++indexOfDispDOb)+"   "+s1);
						else if(indexOfDispDOb == 10)
						System.out.println("      "+(++indexOfDispDOb)+"  "+s1);
						else
						System.out.println("      "+(++indexOfDispDOb)+"  "+s1);	
					}
					
				//l3
				for(String s3: llOfDOBStrwithCtName3 )
				{
					if(indexOfDispDOb < 10)
					System.out.println("      "+(++indexOfDispDOb)+"   "+s3);
					else if(indexOfDispDOb == 10 )
					System.out.println("      "+(++indexOfDispDOb)+"  "+s3);	
					else 
					System.out.println("      "+(++indexOfDispDOb)+"  "+s3);
				}		
						
	}
	
	private static String getBirthDayReminderDOB(Date dob)
	{
		if(dob == null )
			throw new IllegalArgumentException("DOB CAN NOT BE NULL");
		String [] dobSA = dob.toString().split(" ");
		StringBuilder sbDob = new StringBuilder();
		sbDob.append(dobSA[1]+"-");
		sbDob.append(dobSA[2]);		
		return sbDob.toString();
	}
	
	public static void saveCB(ContactBook cbObjToBeSaved) throws IOException
	{
		File saveFile = new File(pathForCBsStr+cbObjToBeSaved.getNameOfContactBookStr());
		//fos
		FileOutputStream fosObj = new FileOutputStream(saveFile);
		//oos
		ObjectOutputStream oosObj = new ObjectOutputStream(fosObj);
		//now serialize it
		oosObj.writeObject(cbObjToBeSaved);
		if(oosObj != null )
			oosObj.close();
	}
	
	public static boolean deleteACB(String nameOfCBToBeDeleted)
	{
		if(nameOfCBToBeDeleted == null)
			throw new IllegalArgumentException("THERE IS NO CONTACT BOOK WHOSE NAME IS NULL TO DELETE");
		if(nameOfCBToBeDeleted.trim().length() == 0 )
			throw new IllegalArgumentException("THERE IS NO CONTACT BOOK WHOSE NAME IS NOT YET SET TO DELETE");
		if(isThereCBs() == false )
			throw new IllegalArgumentException("THERE ARE NO CONTACT BOOKS TO DELETE");
		nameOfCBToBeDeleted = RemoveExtraSpace.removeExtraSpace(nameOfCBToBeDeleted);
		if(isCBExist(nameOfCBToBeDeleted) == false )
			throw new IllegalArgumentException("CONTACT BOOK "+nameOfCBToBeDeleted+" DOES NOT EXISTS TO DELETE");
		
		String [] allCBSA = folderOfCBs.list();
		File tempDelFile = null ;
		for (int i = 0; i < allCBSA.length; i++) 
		{
			if( allCBSA[i].trim().equalsIgnoreCase(nameOfCBToBeDeleted) == true )
					tempDelFile = new File(pathForCBsStr+allCBSA[i]);
		}
		if(tempDelFile == null )
			throw new IllegalArgumentException("UNFORTUNATELLY COULD NOT DELETE CONTACT BOOK "+nameOfCBToBeDeleted);
		if(tempDelFile.delete() == true )
			return true ;
		else
			return false ;
	}
}
