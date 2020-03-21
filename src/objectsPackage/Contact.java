package objectsPackage;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.TreeSet;
import utilPackage.RemoveExtraSpace;

final public class Contact implements Comparable<Contact>,Serializable
{
	
	//serilaiztion uid serialVersionUID
	private static final long serialVersionUID = 28021988L;
 private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	                           //has-a es
	//name
  private String nameOfContactStr;
  
  //DOC
  private Date dateOfContactCreated ;
  //dob
  private Date dobOfContact ;

  
  //address
  private String addressOfContactStr ;
  
  //tag
  private String tagStr;
  
  //email
  private Collection<String> collOfEmails = new TreeSet<String>();
  
  //phone no
  private Collection<String> collOfPhoneNumbers = new TreeSet<String>();
  
  //pet name
  private String petNameStr;
  
                                
                                         //validations
  //validate conatc name
  private static void validateNameOfContact(String cNameStr)
  {
	  if(cNameStr ==  null)
		  throw new IllegalArgumentException("CONTACT NAME CAN NOT BE NULL");
	  if(cNameStr.trim().length() == 0)
		  throw new IllegalArgumentException("CONTACT NAME CAN NOT BE BLANK/EMPTY");
	  
  }
  
  //validate dOb of contact
    private static void validateDOBOfContact(String dobStr) throws ParseException
    {
    	if(dobStr == null)
    		  throw new IllegalArgumentException("DOB OF CONTACT CAN NOT BE NULL");
    	if(dobStr.trim().length() == 0)
    		  throw new IllegalArgumentException("DOB OF CONTACT CAN NOT BE EMPTY/BLANK");
    	if((dobStr.trim().length() != 10) || (dobStr.contains("/") == false))
    		  throw new IllegalArgumentException("INVALID DOB FORMAT, PLEASE ENTER DOB IN DD/MM/YYYY FORMAT");
    	
    	String [] saDob = dobStr.split("/");
    	if(saDob.length != 3)
    		throw new IllegalArgumentException("INVALID DOB FORMAT, PLEASE ENTER DOB IN DD/MM/YYYY FORMAT");
    	String ddStr = saDob[0];
    	String mmStr = saDob[1];
    	String yyyyStr = saDob[2];
    	if((ddStr.trim().length() != 2) ||(mmStr.trim().length() != 2) || (yyyyStr.trim().length() != 4) )
    		throw new IllegalArgumentException("INVALID DOB FORMAT, PLEASE ENTER DOB IN DD/MM/YYYY FORMAT");
    	StringBuilder sb = new StringBuilder(dobStr.trim());
    	for(int i=0;i<sb.length() ;i++)
    	{
    		char chValid = sb.charAt(i);
    		if(  (chValid == 47)  || (chValid >= 48 && chValid <= 57)  )
    		{
    			//acceptable
    		}
    		else
    			throw new IllegalArgumentException("INVALID DOB FORMAT, PLEASE ENTER DOB IN DD/MM/YYYY FORMAT");
    	}
    	if( Integer.parseInt(ddStr) >=1 && Integer.parseInt(ddStr) <= 31 )
    			{
    		//acceptable
    			}
    	else
    		throw new IllegalArgumentException(ddStr+" CAN NOT BE A DAY");
    	if(Integer.parseInt(mmStr) >= 1 && Integer.parseInt(mmStr) <= 12)
    	{
    		//acceptable
    	}
    	else
    		throw new IllegalArgumentException(mmStr+" CAN NOT BE A MONTH");
    	if(Integer.parseInt(yyyyStr) == 0)
    		throw new IllegalArgumentException(yyyyStr+" CAN NOT BE A YEAR");
    	Date currentDate = new Date();
    	Date compDate = sdf.parse(dobStr);
  	  if((currentDate.getTime() - compDate.getTime()) < 0)
  		  throw new IllegalArgumentException("CAN NOT ADD CONTACT OF PERSON WHO IS NOT YET BORN");
  	  validateForCorrectDayOfMonth(yyyyStr,mmStr, ddStr);
  	  
    		
    }
    //validate for right day of month
    private static void validateForCorrectDayOfMonth(String yyyyStr ,String mmStr,String ddStr)
    {
    	int mmInt = Integer.parseInt(mmStr);
    	int ddInt = Integer.parseInt(ddStr);
    	int yyyyInt = Integer.parseInt(yyyyStr);
    	
    	switch (mmInt)
    	{
		case 1:
			if(ddInt >= 1 && ddInt <= 31)
			{
				//acceptable
			}
			else
				throw new IllegalArgumentException(ddStr+" NEVER COMES IN THE MONTH "+"JANUARY");
						
			break;

		case 2:
			if( (yyyyInt % 4) == 0)
			{
				if(ddInt >= 1 && ddInt <= 29)
				{
					//acceptable
				}
				else
					throw new IllegalArgumentException(ddStr+" NEVER COMES IN THE MONTH "+"FEBRUARY");
			}
			else
			{
				if(ddInt >= 1 && ddInt <= 28)
				{
					//acceptable
				}
				else if(ddInt == 29)
					throw new IllegalArgumentException("29th OF FEB COMES IN ONLY LEAP YEARS");
				else
					throw new IllegalArgumentException(ddStr+" NEVER COMES IN THE MONTH "+"FEBRUARY");
			}
			
						
			break;
			
		case 3:
			if(ddInt >= 1 && ddInt <= 31)
			{
				//acceptable
			}
			else
				throw new IllegalArgumentException(ddStr+" NEVER COMES IN THE MONTH "+"MARCH");
						
			break;
			
		case 4:
			if(ddInt >= 1 && ddInt <= 30)
			{
				//acceptable
			}
			else
				throw new IllegalArgumentException(ddStr+" NEVER COMES IN THE MONTH "+"APRIL");
						
			break;
			
		case 5:
			if(ddInt >= 1 && ddInt <= 31)
			{
				//acceptable
			}
			else
				throw new IllegalArgumentException(ddStr+" NEVER COMES IN THE MONTH "+"MAY");
						
			break;
			
		case 6:
			if(ddInt >= 1 && ddInt <= 30)
			{
				//acceptable
			}
			else
				throw new IllegalArgumentException(ddStr+" NEVER COMES IN THE MONTH "+"JUNE");
						
			break;
			
		case 7:
			if(ddInt >= 1 && ddInt <= 31)
			{
				//acceptable
			}
			else
				throw new IllegalArgumentException(ddStr+" NEVER COMES IN THE MONTH "+"JULY");
						
			break;
			
		case 8:
			if(ddInt >= 1 && ddInt <= 31)
			{
				//acceptable
			}
			else
				throw new IllegalArgumentException(ddStr+" NEVER COMES IN THE MONTH "+"AUGUST");
						
			break;
			
		case 9:
			if(ddInt >= 1 && ddInt <= 30)
			{
				//acceptable
			}
			else
				throw new IllegalArgumentException(ddStr+" NEVER COMES IN THE MONTH "+"SEPTEMBER");
						
			break;
			
		case 10:
			if(ddInt >= 1 && ddInt <= 31)
			{
				//acceptable
			}
			else
				throw new IllegalArgumentException(ddStr+" NEVER COMES IN THE MONTH "+"OCTOBER");
						
			break;
			
		case 11:
			if(ddInt >= 1 && ddInt <= 30)
			{
				//acceptable
			}
			else
				throw new IllegalArgumentException(ddStr+" NEVER COMES IN THE MONTH "+"NOVEMBER");
						
			break;
		case 12:
			if(ddInt >= 1 && ddInt <= 31)
			{
				//acceptable
			}
			else
				throw new IllegalArgumentException(ddStr+" NEVER COMES IN THE MONTH "+"DECEMBER");
						
			break;
		default:throw new IllegalArgumentException(ddStr+" NEVER COMES IN THE MONTH "+mmStr);
		
		}
    }
  
  //VALIDAT address
  private static void validateAddress(String addStr)
  {
	  if(addStr == null)
		  throw new IllegalArgumentException("ADDRESS CAN NOT BE NULL");
	  if(addStr.trim().length() == 0)
		  throw new IllegalArgumentException("ADDRESS CAN NOT BE BLANK/EMPTY");
  }
  
  //VALIDATE TAG
  private static void validateTag(String tagStr)
  {
	  if(tagStr == null)
		  throw new IllegalArgumentException("TAG CAN NOT BE NULL");
	  if(tagStr.trim().length() == 0)
			  throw new IllegalArgumentException("TAG CAN NOT BE BLANK/EMPTY");
  }
  
  //validate email
  private static void validateEmail(String emailStr)
  {
	  if(emailStr == null)
		  throw new IllegalArgumentException("EMAIL CAN NOT BE BLANK");
	  if(emailStr.trim().length() == 0)
		  throw new IllegalArgumentException("EMAIL CAN NOT BE EMPTY/BLANK");
	  if((emailStr.contains("@") == false) || (emailStr.contains(".") == false))
		  throw new IllegalArgumentException("INVALID EMAIL");
	  if((emailStr.charAt(0) == 46) ||(emailStr.charAt(emailStr.length()-1) == 46) )
		  throw new IllegalArgumentException("INVALID EMAIL");
		 
	  
	  StringBuilder sb = new StringBuilder(emailStr.trim().toLowerCase());
	  //chhecking first half 
	  String [] saAtTheRate = sb.toString().split("@");
	  //if not two parts
	  if(saAtTheRate.length != 2)
		  throw new IllegalArgumentException("INVALID EMAIL");
	  String firstHalfStr = saAtTheRate[0];
	  String secondHalfStr = saAtTheRate[1];
	  validateForlettersNumericNDot(firstHalfStr);
	  validateForlettersNumericNDot(secondHalfStr);
	  if( (firstHalfStr.trim().length() == 0) || ( secondHalfStr.trim().length() == 0) )
		  throw new IllegalArgumentException("INVALID EMAIL");
	  if(secondHalfStr.contains(".") ==  false )
		  throw new IllegalArgumentException("INVALID EMAIL");

	  String [] saDot = secondHalfStr.split("\\."); 
	
	  if(saDot.length < 2 )
	  {
		  throw new IllegalArgumentException("INVALID EMAIL");
	  }
	
	  
  }
  //check for numbers dot letters
  private static void validateForlettersNumericNDot(String validStr)
  {
	  StringBuilder sb = new StringBuilder(validStr.trim().toLowerCase());
	  for(int i=0 ; i<sb.length() ;i++)
	  {
		  char chValid = sb.charAt(i);
		  if((chValid == 46) || (chValid >= 48 && chValid <= 57) || (chValid >= 97 && chValid <= 122) ||(chValid == 95))
		  {
			  //acceptable
		  }
		  else
			  throw new IllegalArgumentException("INVALID EMAIL");  
	  }
	 
  }
  //validate phone number
  private static void validatePhoneNumber(String phoneNumberStr)
  {
	  if(phoneNumberStr == null)
		  throw new IllegalArgumentException("PHONE NUMBER CAN NOT BE NULL");
	  if(phoneNumberStr.trim().length() == 0 )
		  throw new IllegalArgumentException("PHONE NUMBER CAN NOT BE EMPTY/BLANK"); 
	
	  StringBuilder sb = new StringBuilder(phoneNumberStr.trim().toLowerCase());
	  for(int i=0; i<sb.length() ;i++)
	  {
		  char chValid = sb.charAt(i);
		  if( (chValid == 35) || (chValid == 42) || (chValid == 43) || (chValid >= 48 && chValid <= 57))
		  {
			  //acceptable
		  }
		  else
			  throw new IllegalArgumentException("INVALID PHONE NUMBER");
	  }
		  
  }
  
  //validate pet name
  private static void validatePetName(String petNStr)
  {
	  if(petNStr == null)
		  throw new IllegalArgumentException("PET NAME CAN NOT BE NULL");
	  if(petNStr.trim().length() == 0)
		  throw new IllegalArgumentException("PET NAME CAN NOT BE BLANK/EMPTY");
	  
  }
  
  //validate col of emails
  private static void validateColOfEmails(Collection<String> colEmail)
  {
	  if(colEmail == null)
		  throw new IllegalArgumentException("EMAILS COLLECTION CAN NOT BE NULL");
  }
  //validate col of phone
  private static void validateColOfPhoneNumbers(Collection<String> colPN)
  {
	  if(colPN == null)
		  throw new IllegalArgumentException("PHONE NUMBERS COLLECTION CAN NOT BE NULL"); 
  }
     
                       //CONSTRUCTORS
  
  public Contact(String cNameStr)
  {
	  validateNameOfContact(cNameStr);	
	  this.nameOfContactStr = RemoveExtraSpace.removeExtraSpace(cNameStr);
	  //doc 
	  this.dateOfContactCreated = new Date();
  }
  
  //copy constrtcr 
  public Contact( Contact ctObjToBeCopied )
  {
	  this.nameOfContactStr = ctObjToBeCopied.nameOfContactStr ;
	  this.dateOfContactCreated = ctObjToBeCopied.dateOfContactCreated ;
	  if(ctObjToBeCopied.collOfPhoneNumbers.size() != 0)
		  this.collOfPhoneNumbers = new TreeSet<String>(ctObjToBeCopied.collOfPhoneNumbers );
	  if( ctObjToBeCopied.collOfEmails.size() != 0 )
		  this.collOfEmails =  new TreeSet<String>(ctObjToBeCopied.collOfEmails );
	  if(ctObjToBeCopied.dobOfContact != null )
		  this.dobOfContact = new Date(ctObjToBeCopied.dobOfContact.getTime());
	  if(ctObjToBeCopied.addressOfContactStr != null )
		  this.addressOfContactStr = ctObjToBeCopied.addressOfContactStr ;
	  if( ctObjToBeCopied.tagStr != null )
		  this.tagStr = ctObjToBeCopied.tagStr ;
	  if( ctObjToBeCopied.petNameStr != null )
		  this.petNameStr = ctObjToBeCopied.petNameStr ;
  }
  
  
                 //getters 
//name
public String getNameOfContactStr() {
	return nameOfContactStr;
}
//DOC
public Date getDateOfContactCreated() 
{
	Date retDate = new Date(this.dateOfContactCreated.getTime());
	return retDate;
}
//DOB
public Date getDobOfContact()
{
	Date retDate = null;
	if(this.dobOfContact != null)
	retDate = new Date(this.dobOfContact.getTime());
	return retDate;
}
//address
public String getAddressOfContactStr() 
{
	return addressOfContactStr;
}
//tag
public String getTagStr()
{
	return tagStr;
}
//col of emails
public Collection<String> getCollOfEmails()
{
	//difensivly copy and return
	return new TreeSet<String>(this.collOfEmails);
}
//col of phns
public Collection<String> getCollOfPhoneNumbers()
{
	//difensivly copy and return
	return new TreeSet<String>(this.collOfPhoneNumbers);
}
//pet name
public String getPetNameStr() 
{
	return petNameStr;
}
             

                      //SETTERS

//name
public void setNameOfContactStr(String nameOfCtStr)
{
	validateNameOfContact(nameOfCtStr);	
	this.nameOfContactStr = RemoveExtraSpace.removeExtraSpace(nameOfCtStr);
}
//set doc
public void setDateOfContactCreated(Date doc)
{
	if(doc == null )
		throw new IllegalArgumentException("DATE OF CONTACT CREATED CAN NOT BE NULL ");
	Date tempDate = new Date(doc.getTime());
	this.dateOfContactCreated = tempDate ;
}

//dob
public void setDobOfContact(String dobStr) throws ParseException 
{
	validateDOBOfContact(dobStr);
	
	this.dobOfContact = sdf.parse(dobStr);
}
//overloaded setDOb
public void setDobOfContact(Date dob)
{
	if(dob == null )
		throw new IllegalArgumentException("DOB OF CONTACT CAN NOT BE NULL");
	Date tempDob = new Date(dob.getTime());
	this.dobOfContact = tempDob ;
}
//adress
public void setAddressOfContactStr(String addressOfCtStr)
{
	validateAddress(addressOfCtStr);	
	this.addressOfContactStr = RemoveExtraSpace.removeExtraSpace(addressOfCtStr);
}
//tag
public void setTagStr(String tgStr) 
{
	validateTag(tgStr);
	this.tagStr = RemoveExtraSpace.removeExtraSpace(tgStr);
}
//col of emails
public void setCollOfEmails(Collection<String> collOfEmails) 
{
	validateColOfEmails(collOfEmails);
	//difensivly copy and paste
	this.collOfEmails = new TreeSet<String>(collOfEmails);
}
//col of phn
public void setCollOfPhoneNumbers(Collection<String> collOfPhoneNumbers)
{
	validateColOfPhoneNumbers(collOfPhoneNumbers);
	//difensivly copy and paste
	this.collOfPhoneNumbers = new TreeSet<String>(collOfPhoneNumbers);
}
//pet name
public void setPetNameStr(String petNStr)
{
	validatePetName(petNStr);
	this.petNameStr = RemoveExtraSpace.removeExtraSpace(petNStr);
}

//toString
@Override
public String toString() 
{
	StringBuilder sbRet = new StringBuilder();
	sbRet.append(this.nameOfContactStr);
	
	if(this.collOfPhoneNumbers.size() > 0)
	{
		for(String pNStr : this.collOfPhoneNumbers )
			sbRet.append(pNStr);
	}
	if(this.collOfEmails.size() > 0 )
	{
		for( String emailStr : this.collOfEmails )
			sbRet.append(emailStr);
	}
	if( this.dobOfContact != null )
		sbRet.append(ContactBook.getReadableDOB(this.dobOfContact));
		
	if( this.petNameStr != null )
		sbRet.append(this.petNameStr);
	
	if( this.tagStr != null)
		sbRet.append(this.tagStr);
	
	if(this.addressOfContactStr != null )
		sbRet.append(this.addressOfContactStr);	
	return sbRet.toString() ;
}

//hashcode
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
			+ ((nameOfContactStr == null) ? 0 : nameOfContactStr.hashCode());
	return result;
}

//equals  child specific 
public boolean equals(Contact cObj) 
{
if(cObj == null)
	throw new IllegalArgumentException("WHY DO YOU WANT TO COMPARE "+this+" WITH NULL, HOWEVR NOT EQUAL");
return this.getNameOfContactStr().trim().equalsIgnoreCase(cObj.getNameOfContactStr().trim()) ;
}
  

                        //BEHAVIOURS OF A CONTACT
//isThere emails
public boolean isThereEmails()
{
	if(this.collOfEmails.size() == 0)
		return false;
	else
		return true ;
}
//isEmailExists ?
public boolean isEmailExists(String emailStr)
{
	if(emailStr == null || emailStr.trim().length() == 0)
		throw new IllegalArgumentException("EMAIL CAN NOT BE NULL/BLANK/EMPTY");
	if(this.collOfEmails.contains(emailStr.trim().toLowerCase()) == true )
		return true ;
	else
		return false ;
}
//addemail
public boolean addEmail(String emailStr)
{
	validateEmail(emailStr);
	if(isEmailExists(emailStr) == true )
		throw new IllegalArgumentException("EMAIL "+emailStr.trim()+" ALREADY EXISTS FOR CONTACT "+this.getNameOfContactStr());
	if( this.collOfEmails.add(emailStr.trim().toLowerCase()) == true )
		return true;
	else
		return false ;
}

//remove email
public boolean removeEmail(String emailStr)
{
	if(emailStr == null || emailStr.trim().length() == 0)
		throw new IllegalArgumentException("CAN NOT REMOVE NULL/BLANK/EMPTY EMAIL");
	if(isThereEmails() == false)
		throw new IllegalArgumentException("THERE ARE NO EMAILS TO REMOVE");
	if(isEmailExists(emailStr) == false )
		throw new IllegalArgumentException("EMAIL "+emailStr.trim()+" DOES NOT EXISTS ,SO CAN NOT REMOVE");
   if(this.collOfEmails.remove(emailStr.trim().toLowerCase()) == true)
	   return true ;
   else
	   return  false;
}

//isTherePhoneNumbers ?
public boolean isTherePhoneNumbers()
{
	if(this.collOfPhoneNumbers.size() == 0)
		return false ;
	else
		return true ;
}
//isPhoneNumberexists?
public boolean isPhoneNumberExists(String pNStr)
{
	if(isTherePhoneNumbers() == false)
		return false;
	if(pNStr == null || pNStr.trim().length() == 0)
		throw new IllegalArgumentException("PHONE NUMBER CAN NOT BE NULL/BLANK/EMPTY");
	if( this.collOfPhoneNumbers.contains(pNStr.trim()) == true )
		return true ;
	else
		return false ;
}
//add phn nm
public boolean addPhoneNumber(String pNStr)
{
	validatePhoneNumber(pNStr);
	if(isPhoneNumberExists(pNStr.trim()) == true )
		throw new IllegalArgumentException("PHONE NUMBER "+pNStr+" ALREADY EXISTS FOR CONTACT "+this.getNameOfContactStr()+" SO CAN NOT ADD");
	if(this.collOfPhoneNumbers.add(pNStr.trim()) == true )
		return true ;
	else
		return false ;	
}

//remove phone number
public boolean removePhoneNumber(String pNStr)
{
	if(pNStr == null || pNStr.trim().length() == 0)
		throw new IllegalArgumentException("CAN NOT REMOVE NULL/EMPTY/BLANK PHONE NUMBER");
	if(isTherePhoneNumbers() == false)
		throw new IllegalArgumentException("THERE ARE NO PHONE NUMBERS TO REMOVE");
	if( isPhoneNumberExists(pNStr.trim()) == false )
		throw new IllegalArgumentException("PHONE NUMBER "+pNStr+" DOES NOT EXISTS TO REMOVE");
	if( this.collOfPhoneNumbers.remove(pNStr.trim()) == true )
		return true ;
	else
		return false ;	
}



  @Override
  public int compareTo(Contact cObj)
   {
	 return (this.nameOfContactStr.trim().compareToIgnoreCase(cObj.nameOfContactStr.trim())) ;
   }

  //display all pHn
  public void displayAllPhoneNumbers()
  {
	  if(isTherePhoneNumbers() == false )
		  throw new IllegalArgumentException("THERE ARE NO PHONE NUMBERS TO SHOW");
	  int indexOfPnInt = 1;
	  int forFirstTime = 1;
		for(String pNStr : this.getCollOfPhoneNumbers() )
		{
			if(this.getCollOfPhoneNumbers().size() == 1)
			{
				System.out.println("THERE IS ONLY 1 PHONE NUMBER & i.e");
				System.out.println(pNStr);								
			}
			else
				{
				if(forFirstTime == 1)
					System.out.println("THE PHONE NUMBERS ARE AS FOLLOWS");
				forFirstTime ++;
				System.out.println(indexOfPnInt+" - "+pNStr);
				indexOfPnInt ++ ;
				}
		}
  }
  //display all emils
  public void displayAllEmails()
  {
	  if(isThereEmails() == false )
		  throw new IllegalArgumentException("THERE ARE NO EMAIL-IDs TO SHOW");
	  int indexOfPnInt = 1;
	  int forFirstTime = 1;
		for(String pNStr : this.getCollOfEmails() )
		{
			if(this.getCollOfEmails().size() == 1)
			{
				System.out.println("THERE IS ONLY 1 EMAIL-ID & i.e");
				System.out.println(pNStr);								
			}
			else
				{
				if(forFirstTime == 1)
					System.out.println("THE EMAIL-IDs ARE AS FOLLOWS");
				forFirstTime ++;
				System.out.println(indexOfPnInt+" - "+pNStr);
				indexOfPnInt ++ ;
				}
		}
  }

}//clas ends












