package objectsPackage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;
import utilPackage.DateComparator;
import utilPackage.LengthOfContactsComparator;
import utilPackage.RemoveExtraSpace;
import utilPackage.TagComparator;

public final class ContactBook implements Serializable,Comparable<ContactBook>
{
	//serialVersionUID
	private static final long serialVersionUID = 15021988L;

	                      //has-a es
	//cb name
	private String nameOfContactBookStr;
	
	//collection of contacts
	private Collection<Contact> collOfContacts = new TreeSet<Contact>();
	
	                      //validations
	//validate cb name
	private static void validateNameOfCB(String nameOfCBStr)
	{
		if(nameOfCBStr == null)
			throw new IllegalArgumentException("CONTACT BOOK NAME CAN NOT BE NULL");
		if(nameOfCBStr.trim().length() == 0)
			throw new IllegalArgumentException("CONTACT BOOK NAME CAN NOT BE EMPTY/BLANK");		
	}
	
	//VALIDTE COLLECTION
	private static void validateCollOfContacts(Collection<Contact> collOfCts)
	{
		if(collOfCts == null)
			throw new IllegalArgumentException("COLLECTION OF CONTACTS CAN NOT BE NULL");
		if(collOfCts.size() == 0)
			throw new IllegalArgumentException("THERE ARE NO CONTACTS IN A CONTACTS IN A CONTACT BOOK");
	}
	
	//vlaidate a contact
	private static void validateContact(Contact cObj)
	{
		if(cObj == null)
			throw new IllegalArgumentException("CONTACT CAN NOT BE NULL");	
		
	}
	
	             //CONSTRUCTORS
	public ContactBook(String nameOfCBStr)
	{
		validateNameOfCB(nameOfCBStr);		
		this.nameOfContactBookStr = RemoveExtraSpace.removeExtraSpace(nameOfCBStr);
	}
	            //getters
	//cb name
	public String getNameOfContactBookStr()
	{
		return this.nameOfContactBookStr;
	}
	
	// col of cntatcs
	public Collection<Contact> getCollOfContacts()
	{
		//difensively copy and send
		return new TreeSet<Contact>(this.collOfContacts);
	}
	                         
	                           //SETTERS
	//col of contatcs
	public void setcollOfContacts(Collection<Contact> collOfCts)
	{
		validateCollOfContacts(collOfContacts);
		//difensively copy and paste to this's col obj
		this.collOfContacts =  new TreeSet<Contact>(collOfCts);
	}
	     
	                 //BEHAVIOURS
	
	//is CB empty?
	public  boolean isCBEmpty()
	{
		if(this.collOfContacts.size() == 0)
			return true ;
		else
		return false ;
	}
	
	//IS contct exists?
	public boolean isContactExists(String cNStr)
	{
		if(cNStr == null || cNStr.trim().length() == 0 )
			throw new IllegalArgumentException("CONTACT NAME CAN NOT BE NULL/BLANK/EMPTY");
		if(isCBEmpty() == true)
	          return false ;
		cNStr = RemoveExtraSpace.removeExtraSpace(cNStr);
		for(Contact ctObj:this.collOfContacts)
		{
			if( ctObj.getNameOfContactStr().trim().equalsIgnoreCase(cNStr.trim()) == true )
				return true ;
		}
        return false ;
	}
	
	//add a contact
	public boolean addAContact(Contact cObj)
	{
		validateContact(cObj);
		if( isContactExists(cObj.getNameOfContactStr().trim()) == true )
			throw new IllegalArgumentException("CAN NOT ADD DUPLICATE CONTACT "+cObj.getNameOfContactStr());
		if(this.collOfContacts.add(cObj) == true )
			return true ;
		else
			return false ;
	}
	
	//remove a contact
	public boolean removeAContact(String cNStr)
	{
		if(cNStr == null || cNStr.trim().length() == 0 )
			throw new IllegalArgumentException("CAN NOT REMOVE A BLANK/EMPTY/NULL CONTACT,PLEASE ENTER CONTACT NAME TO REMOVE");
		if(isCBEmpty() == true )
			throw new IllegalArgumentException("CAN NOT REMOVE ANY CONTACT FROM  EMPTY CONTACT BOOK");
		cNStr = RemoveExtraSpace.removeExtraSpace(cNStr);
		if( isContactExists(cNStr) == false )
			throw new IllegalArgumentException("CONTACT BOOK "+this.nameOfContactBookStr+" DOES NOT CONTAIN CONTACT "+cNStr+" TO REMOVE");
		
		for(Contact ctObj : this.collOfContacts )
		{
			if( ctObj.getNameOfContactStr().trim().equalsIgnoreCase(cNStr.trim()) == true )
			{
				if(this.collOfContacts.remove(ctObj) == true )
					return true ;
				else
					return false ;
			}
		}
		return false ;
	}
	
	///getmatching ctc
	public Contact getContctObjByKnowingName( String cNameStr )
	{
		if( cNameStr == null || cNameStr.trim().length() == 0 )
			throw new IllegalArgumentException(" CONTACT BOOK DOES NOT CONTAIN ANY CONTACTS WITHOUT NAMES");
		cNameStr = RemoveExtraSpace.removeExtraSpace(cNameStr) ;
		Contact ctObjOfMtachingName = null ;
		for( Contact ctObjLoop : this.getCollOfContacts() )
		{
			if(ctObjLoop.getNameOfContactStr().equalsIgnoreCase(cNameStr) == true )
				ctObjOfMtachingName = new Contact(ctObjLoop) ;			
		}
		if ( ctObjOfMtachingName == null )
			throw new IllegalArgumentException("CONTACT BOOK "+this.nameOfContactBookStr+" DOES NOT CONTAIN "+cNameStr);
		return ctObjOfMtachingName ;
	}
	//editct
	public boolean editAContact(String cNameToBeEditedStr , Contact ctObjToBeAdded )
	{
		if(cNameToBeEditedStr == null || cNameToBeEditedStr.trim().length() == 0)
			throw new IllegalArgumentException("THERE ARE NO CONTACTS WITHOUT NAMES TO EDIT IN THE CONTACT BOOK "+this.nameOfContactBookStr);
		if(isCBEmpty() == true )
			throw new IllegalArgumentException("CONTACT BOOK DOES NOT CONTAIN ANYTHING TO EDIT");
		cNameToBeEditedStr =  RemoveExtraSpace.removeExtraSpace(cNameToBeEditedStr) ;
		if( isContactExists(cNameToBeEditedStr) == false )
			throw new IllegalArgumentException("CONTACT BOOK DOES NOT CONTAIN "+cNameToBeEditedStr+" TO EDIT");					
		
		Contact ctObjToBeRemoved = getContctObjByKnowingName(cNameToBeEditedStr) ;
		if(this.collOfContacts.remove(ctObjToBeRemoved) == false )
			throw new IllegalArgumentException("COULD NOT EDIT CONTACT "+cNameToBeEditedStr);
			//return false ;
		if( this.collOfContacts.add(ctObjToBeAdded) == true )
			return true ;
		else
			throw new IllegalArgumentException("COULD NOT EDIT CONTACT "+cNameToBeEditedStr);
						
	}
	
	//search a contact
	public void searchAContact(String searchStr)
	{
		if(searchStr == null )
			throw new IllegalArgumentException("NO NEED TO SEARCH FOR A NULL");
		if(searchStr.trim().length() == 0 )
			throw new IllegalArgumentException("CAN NOT SEARCH FOR A EMPTY/BLANK IN A CONTACT BOOK");
		searchStr = RemoveExtraSpace.removeExtraSpace(searchStr);
		StringBuilder sbCmp = new StringBuilder(searchStr.trim().toLowerCase());
		
		
		//collection holding all contacts whose name is matching
		Collection<String> colMatchCtNames = new LinkedList<String>();
		
		//coll holing contcts whose emails are matching
		Collection<String> colMatchCtNamesNEmails = new TreeSet<String>();
		
		//coll holding ctcs whose phone matching		
		Collection<String> colMatchCtNamesNPhones = new TreeSet<String>();
		
		//coll holding cts whose adrees r macthing
		Collection<Contact> colMatchCtAdress = new TreeSet<Contact>();
		//coll holding cts whose tags are matching
		Collection<Contact> colMatchCtTags = new TreeSet<Contact>();
		//coll holding cts whose pet nam are matching
		Collection<Contact> colMatchCtPetNames = new TreeSet<Contact>();
		//coll holding cts whose Date of creted are matchg
		Collection<Contact> colMatchCtDOCreated = new TreeSet<Contact>();
		//coll holding cts whose DOB r matching
		Collection<Contact> colMatchCtDOB = new TreeSet<Contact>();
		
		for(Contact ctObj : this.collOfContacts )
		{
			if(ctObj.getNameOfContactStr().toLowerCase().contains(sbCmp) == true )
			{
				colMatchCtNames.add(ctObj.getNameOfContactStr());
			}
			//adress
			if(ctObj.getAddressOfContactStr() != null )
			{
				if( ctObj.getAddressOfContactStr().toLowerCase().contains(sbCmp) == true )
					colMatchCtAdress.add(ctObj);
			}
			//tags
			if(ctObj.getTagStr() != null)
			{
				if( ctObj.getTagStr().toLowerCase().contains(sbCmp) == true)
					colMatchCtTags.add(ctObj);
			}
			//pet name
			if(ctObj.getPetNameStr() != null)
			{
				if( ctObj.getPetNameStr().toLowerCase().contains(sbCmp) == true )
					colMatchCtPetNames.add(ctObj);
			}
		
			//email
			if(ctObj.isThereEmails() == true )
			{
				
				for(String emailStr : ctObj.getCollOfEmails())
				{
					if(emailStr.toLowerCase().contains(sbCmp) == true )
					{
						
						colMatchCtNamesNEmails.add(ctObj.getNameOfContactStr()+" - "+emailStr);						
					}
				}
			}
			//phones
			if(ctObj.isTherePhoneNumbers() == true )
			{
				 for(String phnStr : ctObj.getCollOfPhoneNumbers() )
				{
					if( phnStr.toLowerCase().contains(sbCmp) == true )
					{
						colMatchCtNamesNPhones.add(ctObj.getNameOfContactStr()+" - "+phnStr);			
					
					}
				}
			}
			//doc
			if( ctObj.getDateOfContactCreated().toString().toLowerCase().contains(sbCmp) == true)
				colMatchCtDOCreated.add(ctObj);
			//dob
			if( ctObj.getDobOfContact() != null )
			{
				if( getReadableDOB(ctObj.getDobOfContact()).contains(sbCmp) == true )
					colMatchCtDOB.add(ctObj);
			}		
			
		}//looped once 
		
		//now display all (one by one)
		
		System.out.println("TOTAL NUMBER OF OCCURANCES = "+(colMatchCtNames.size()+colMatchCtAdress.size()+colMatchCtDOB.size()
				+colMatchCtDOCreated.size()+colMatchCtNamesNEmails.size()+colMatchCtNamesNPhones.size()
				+colMatchCtPetNames.size()+colMatchCtTags.size()));
		
		int indexInt = 1;
		//NAMES
		if(colMatchCtNames.size() > 0)
		{
			System.out.println("NUMBER OF OCCURANCES IN NAME OF CONTACTS = "+colMatchCtNames.size());
			System.out.println("MATCHES FOUND : ");
		
			if(colMatchCtNames.size() == 1)
			{
				ArrayList<String> al = new ArrayList<String>(colMatchCtNames);
				System.out.println(al.get(0));
				al = null ;
			}
			else
			{
				for(String loopStr : colMatchCtNames)
				{				
					System.out.println((indexInt++)+" = "+loopStr);			
				}
				indexInt = 1;
			}
		}
		
		//EMAILS
		if(colMatchCtNamesNEmails.size() > 0 )
		{
			System.out.println("");
			System.out.println("NUMBER OF OCCURANCES IN E-MAIL = "+colMatchCtNamesNEmails.size());
			System.out.println("MATCHES FOUND : ");
			if(colMatchCtNamesNEmails.size() == 1)
			{
				ArrayList<String> al = new ArrayList<String>(colMatchCtNamesNEmails);
				System.out.println(al.get(0));
				al = null ;
			}
			else
			{
				for(String loopStr : colMatchCtNamesNEmails)
				{				
					System.out.println((indexInt++)+" = "+loopStr);			
				}
				indexInt = 1;
			}
		}
		
			
		//phones
		if(colMatchCtNamesNPhones.size() > 0 )
		{
			System.out.println("");
			System.out.println("NUMBER OF OCCURANCES IN PHONE NUMBERS = "+colMatchCtNamesNPhones.size());
			System.out.println("MATCHES FOUND : ");
			if(colMatchCtNamesNPhones.size() == 1)
			{
				ArrayList<String> al = new ArrayList<String>(colMatchCtNamesNPhones);
				System.out.println(al.get(0));
				al = null ;
			}
			else
			{
				for(String loopStr : colMatchCtNamesNPhones)
				{				
					System.out.println((indexInt++)+" = "+loopStr);			
				}
				indexInt = 1;
			}
		}
		
		
		//adress
		if(colMatchCtAdress.size() > 0 )
		{
			System.out.println("");
			System.out.println("NUMBER OF OCCURANCES IN ADDRESSES = "+colMatchCtAdress.size());
			System.out.println("MATCHES FOUND : ");
			if(colMatchCtAdress.size() == 1)
			{			
				ArrayList<Contact> al = new ArrayList<Contact>(colMatchCtAdress);
				System.out.println(al.get(0).getNameOfContactStr()+" - "+al.get(0).getAddressOfContactStr());
				al = null ;
			}
			else
			{
				for(Contact loopCtObj : colMatchCtAdress)
				{				
					System.out.println((indexInt++)+" = "+loopCtObj.getNameOfContactStr()+" - "+loopCtObj.getAddressOfContactStr());			
				}
				indexInt = 1;
			}
			
		}
		
		//tags
		if(colMatchCtTags.size() > 0 )
		{
			System.out.println("");
			System.out.println("NUMBER OF OCCURANCES IN TAGS = "+colMatchCtTags.size());
			System.out.println("MATCHES FOUND : ");
			if(colMatchCtTags.size() == 1)
			{			
				ArrayList<Contact> al = new ArrayList<Contact>(colMatchCtTags);
				System.out.println(al.get(0).getNameOfContactStr()+" - "+al.get(0).getTagStr());
				al = null ;
			}
			else
			{
				for(Contact loopCtObj : colMatchCtTags)
				{				
					System.out.println((indexInt++)+" = "+loopCtObj.getNameOfContactStr()+" - "+loopCtObj.getTagStr());			
				}
				indexInt = 1;
			}
		}
		
		
		//pet names
		if(colMatchCtPetNames.size() > 0 )
		{
			System.out.println("");
			System.out.println("NUMBER OF OCCURANCES IN PET NAMES = "+colMatchCtPetNames.size());
			System.out.println("MATCHES FOUND : ");
			if(colMatchCtPetNames.size() == 1)
			{			
				ArrayList<Contact> al = new ArrayList<Contact>(colMatchCtPetNames);
				System.out.println(al.get(0).getNameOfContactStr()+" - "+al.get(0).getPetNameStr());
				al = null ;
			}
			else
			{
				for(Contact loopCtObj : colMatchCtPetNames)
				{				
					System.out.println((indexInt++)+" = "+loopCtObj.getNameOfContactStr()+" - "+loopCtObj.getPetNameStr());			
				}
				indexInt = 1;
			}
			
		}
	
		//doc
		if(colMatchCtDOCreated.size() > 0 )
		{
			System.out.println("");
			System.out.println("NUMBER OF OCCURANCES IN DATE OF CONTACT CREATED = "+colMatchCtDOCreated.size());
			System.out.println("MATCHES FOUND : ");
			if(colMatchCtDOCreated.size() == 1)
			{			
				ArrayList<Contact> al = new ArrayList<Contact>(colMatchCtDOCreated);
				System.out.println(al.get(0).getNameOfContactStr()+" - "+al.get(0).getDateOfContactCreated());
				al = null ;
			}
			else
			{
				for(Contact loopCtObj : colMatchCtDOCreated)
				{				
					System.out.println((indexInt++)+" = "+loopCtObj.getNameOfContactStr()+" - "+loopCtObj.getDateOfContactCreated());			
				}
				indexInt = 1;
			}
		}
		
		//dob
		if(colMatchCtDOB.size() > 0 )
		{
			System.out.println("");
			System.out.println("NUMBER OF OCCURANCES IN DATE OF BIRTHS = "+colMatchCtDOB.size());
			System.out.println("MATCHES FOUND : ");
			if(colMatchCtDOB.size() == 1)
			{			
				ArrayList<Contact> al = new ArrayList<Contact>(colMatchCtDOB);
				System.out.println(al.get(0).getNameOfContactStr()+" - "+getReadableDOB(al.get(0).getDobOfContact()));
				al = null ;
			}
			else
			{
				for(Contact loopCtObj : colMatchCtDOB)
				{				
					System.out.println((indexInt++)+" = "+loopCtObj.getNameOfContactStr()+" - "+getReadableDOB(loopCtObj.getDobOfContact()));			
				}
				
			}
		}
	
		//make all temp obh eligible for gc
		colMatchCtAdress = null;
		colMatchCtDOB = null ;
		colMatchCtDOCreated = null ;
		colMatchCtNames = null;
		colMatchCtNamesNEmails =null;
		colMatchCtNamesNPhones = null ;
		colMatchCtPetNames = null ;
		colMatchCtTags = null ;
		
	 }
	
	public static String getReadableDOB(Date dob)
	{
		if(dob == null )
			throw new IllegalArgumentException("DOB CAN NOT BE NULL");
		String [] dobSA = dob.toString().split(" ");
		StringBuilder sbDob = new StringBuilder();
		sbDob.append(dobSA[2]+"-");
		sbDob.append(dobSA[1]+"-");
		sbDob.append(dobSA[dobSA.length-1]);
		return sbDob.toString();
	}
	
	              //diplays
	//validate before display
	private  void validateBeforeDisplayContacts()
	{
		if(isCBEmpty() == true )
			throw new IllegalArgumentException("THERE IS NOTHING TO SHOW IN CONTACT BOOK "+this.nameOfContactBookStr);
	}
	//display
	private static void displayContatcs(Collection<Contact> clOfCts)
	{
		
		if(clOfCts.size() == 1 )
		{
			System.out.println("-----------------------------------------------------------------------------------------------------------");
			ArrayList<Contact> al = new ArrayList<Contact>(clOfCts);
			System.out.println("NAME                    : "+al.get(0).getNameOfContactStr());
			if(al.get(0).getCollOfPhoneNumbers().size() == 0)
			System.out.println("PHONE NUMBER            : "+"-");
			else if(al.get(0).getCollOfPhoneNumbers().size() == 1)
			{
				ArrayList<String> alTemp = new ArrayList<String>(al.get(0).getCollOfPhoneNumbers()); 
			System.out.println("PHONE NUMBER            : "+alTemp.get(0));
			}			
			else
			{
				int indexPNint = 1 ;
				for( String pNStr : al.get(0).getCollOfPhoneNumbers() )
				{
			System.out.println("PHONE NUMBER "+indexPNint+"          : "+pNStr);
			       indexPNint++;
				}
			}
			if(al.get(0).isThereEmails() == false )
			System.out.println("EMAIL                   : "+"-");
			else
			{
				int indexEmInt = 1;
				for(String emailStr : al.get(0).getCollOfEmails() )
				{
					if(al.get(0).getCollOfEmails().size() == 1)
			System.out.println("EMAIL                   : "+emailStr);
					else
			System.out.println("EMAIL "+indexEmInt+"                 : "+emailStr);
					indexEmInt++;
				}
			}
			if(al.get(0).getDobOfContact() == null )
			System.out.println("DATE OF BIRTH           : "+"-");
			else
			{
				String dobStr = getReadableDOB(al.get(0).getDobOfContact());
				System.out.println("DATE OF BIRTH           : "+dobStr);
			}
			
			System.out.println("DATE OF CONTACT CREATED : "+al.get(0).getDateOfContactCreated());
			if(al.get(0).getPetNameStr() == null )
			System.out.println("PET NAME                : "+"-");
			else
			System.out.println("PET NAME                : "+al.get(0).getPetNameStr());
			if(al.get(0).getTagStr() == null )
			System.out.println("TAG                     : "+"-");	
			else
			System.out.println("TAG                     : "+al.get(0).getTagStr());	
			if(al.get(0).getAddressOfContactStr() == null )
			System.out.println("ADDRESS                 : "+"-");		
			else
			System.out.println("ADDRESS                 : "+al.get(0).getAddressOfContactStr());	
		}
		//more than 1 contacts
		else
			{
			int indexCt = 1 ;
			for(Contact ctObj : clOfCts )
			{
			 System.out.println("-----------------------------------------------------------------------------------------------------------");
			 System.out.println("CONTACT "+(indexCt++));	
			 System.out.println("NAME                    : "+ctObj.getNameOfContactStr());
			 if(ctObj.isTherePhoneNumbers() ==  false )
			 System.out.println("PHONE NUMBER            : "+"-");	 
			 else
			 {
				 int indxPNInt = 1;
				 for(String pNStr : ctObj.getCollOfPhoneNumbers() )
				 {
					 if(ctObj.getCollOfPhoneNumbers().size() == 1 )
			 System.out.println("PHONE NUMBER            : "+pNStr);
					 else
			 System.out.println("PHONE NUMBER "+indxPNInt+"          : "+pNStr);
					 indxPNInt++;
				 }			 
				 
			 }
			 if(ctObj.isThereEmails() == false )
			System.out.println("EMAIL                   : "+"-");
			 else
				{
					int indxEmInt = 1;
					for(String emailStr : ctObj.getCollOfEmails() )
					{
						if(ctObj.getCollOfEmails().size() == 1)
				System.out.println("EMAIL                   : "+emailStr);
						else
				System.out.println("EMAIL "+indxEmInt+"                 : "+emailStr);
						indxEmInt++;
					}
				}
			 //dob 
			 if(ctObj.getDobOfContact() == null )
				System.out.println("DATE OF BIRTH           : "+"-");
			 else
				{
					String dobStr = getReadableDOB(ctObj.getDobOfContact());
					System.out.println("DATE OF BIRTH           : "+dobStr);
				}
			 
				
			 //doc
			    System.out.println("DATE OF CONTACT CREATED : "+ctObj.getDateOfContactCreated());
			 
			 //petname
				if(ctObj.getPetNameStr() == null )
				System.out.println("PET NAME                : "+"-");
					else
				System.out.println("PET NAME                : "+ctObj.getPetNameStr()); 
				
				//tag
				if(ctObj.getTagStr() == null )
				System.out.println("TAG                     : "+"-");	
					else
				System.out.println("TAG                     : "+ctObj.getTagStr());	
				
				//address
				if(ctObj.getAddressOfContactStr() == null )
				System.out.println("ADDRESS                 : "+"-");		
					else
				System.out.println("ADDRESS                 : "+ctObj.getAddressOfContactStr());
				
				
			}//for each ctc ends
			}
		
	}
	
	//display by alpbetical ord of name of C
		public void displayContactsByName()
		{
			validateBeforeDisplayContacts();
			displayContatcs(this.collOfContacts);
		}
		
		
	//display by alpbetical ord of tags
	public void displayContactsByTags()
	{	
		validateBeforeDisplayContacts();
		//create a tmep treemap and make tag as obj key and
		//add all contacts
		
		//creat obj of tagcompartr 
		TagComparator tcObj = new TagComparator();
		//add tcobj as parametr to maps constrctr 
		Map<String, Contact> mpTagWithContact = new TreeMap<String, Contact>(tcObj);
		//create a temp treeset to hold contct whose tag is null
		//so tehy are shown at end
		TreeSet<Contact> tsCntsWitoutTags = new TreeSet<Contact>();
		for(Contact ctObj : this.collOfContacts )
		{
			if(ctObj.getTagStr() == null )
				tsCntsWitoutTags.add(ctObj);
			else
				mpTagWithContact.put(ctObj.getTagStr(), ctObj);
		}
		
		//now display evry thing at a time
		//now create 1 linkedhashSet/linkedlist which preserves orde n no duplicates & just add all value obj of map to thth LHS
		LinkedList<Contact> llOfCts = new LinkedList<Contact>();
		for(Object obj : mpTagWithContact.entrySet() )
		{
			Entry<String, Contact> entObj = (Entry<String, Contact>) obj ;
			llOfCts.add(entObj.getValue());
		}
		//now call display by pssing LL to disp cts having tags
		if(llOfCts.size() == 0)
			System.out.println("THERE ARE NO CONTACTS HAVING TAGS");
		else
		{
			System.out.println("CONTACTS IN THE ALPHABETICAL ORDER OF TAGS");
			displayContatcs(llOfCts);
		}
		
		//now call display by pssing Treset to disp cts who not having tags
		System.out.println("____________________________________________________________________________-");
		System.out.println("REST OF THE CONTACTS WHOSE TAG FILED IS NOT YET SET");
		displayContatcs(tsCntsWitoutTags);
	}
	
	//disply by DOc
	public void displayContactsByDOC()
	{
		validateBeforeDisplayContacts();
		//create a dat cmptrt
		DateComparator dcObj = new DateComparator();
		//create a temp treemap 
		Map<Date, Contact> mpOfDatewithCts = new TreeMap<Date, Contact>(dcObj);
		
		for( Contact ctObj : this.collOfContacts )
		{
			mpOfDatewithCts.put(ctObj.getDateOfContactCreated(), ctObj);
		}
		//now cretae a LInedList and copy paste in same order
		LinkedList<Contact> llOfCts =  new LinkedList<Contact>();
		
		//loope over values of map and and add to LL
		for(Object obj : mpOfDatewithCts.entrySet() )
		{
			Entry<Date, Contact> entObj = (Entry<Date, Contact>) obj ;
			llOfCts.add(entObj.getValue());
		}
		//now call tradition disply as LL as param to displ
		
		System.out.println("THE CONTACTS IN ORDER OF THEIR CREATION DATE");
		displayContatcs(llOfCts);
	}
	
	//display by length total
	public void displayContactsbyLength()
	{
		validateBeforeDisplayContacts();
		//crt l c
		LengthOfContactsComparator lcObj  = new LengthOfContactsComparator();
		
		TreeSet<Contact> tsObj = new TreeSet<Contact>(lcObj);
		
		
		tsObj.addAll(this.collOfContacts);
		
		//now call traditionald isply as tsobj as pram
		System.out.println("CONTACTS BY ASCENDING ORDER OF THEIR LENGTHS");
		displayContatcs(tsObj);
	}

	   //INHERITED METHODS
	//toString
	@Override
	public String toString() {
		return "ContactBook [nameOfContactBookStr=" + nameOfContactBookStr
				+ ", collOfContacts=" + collOfContacts + "]";
	}

	//hash code
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((nameOfContactBookStr == null) ? 0 : nameOfContactBookStr
						.hashCode());
		return result;
	}

   //CHILD SPEC EQUALS
	 //equals
	public boolean equals(ContactBook cb) 
	{
		return ( this.nameOfContactBookStr.trim().equalsIgnoreCase(cb.nameOfContactBookStr.trim()) );
	}


	@Override
	public int compareTo(ContactBook cb) 
	{
		return ( RemoveExtraSpace.removeExtraSpace(this.nameOfContactBookStr).compareToIgnoreCase(RemoveExtraSpace.removeExtraSpace(cb.nameOfContactBookStr))); 
	}

}//class ends
