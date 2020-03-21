package testPackage;

import java.util.ArrayList;
import java.util.Scanner;
import objectsPackage.Contact;
import objectsPackage.ContactBook;
import usePackage.UseContactBook;
import utilPackage.RemoveExtraSpace;

public final class TestContactApp 
{
		
	private static Scanner scWord = new Scanner(System.in);
	private static Scanner scLine = new Scanner(System.in);
	
	//static initil
	static 
	{
		System.out.println("");	
		System.out.println("-------------------------------------------WELCOME TO CHETAN'S CONTACT APP-----------------------------------------------------------");

	}
	//default statment
	private static void defaultOfSwitch(int enteredInt , int endInt)
	{
		System.out.println("YOU HAVE ENTERED "+enteredInt+" WHICH WAS NOT IN THE OPTIONS ,i.e. FROM 1 TO "+endInt);
	}
	//avoid adding multiple time same tab
	private static void avoidAddingExistingAttribute(int enteredInt)
	{
		System.out.println("YOU HAVE ENTERED "+enteredInt+" WHICH WAS NOT IN THE OPTIONS LIST");
	}
	//line speartr
		private static void lineSeparator()
		{
			System.out.println("__________________________________________________________________________________________________________________________________________");
		}
		//repeating if i/p not integer
		private static void repeatUntilInputIsNotInt()
		{
			while( scWord.hasNextInt() == false)
			{
				System.out.println("PLEASE ENTER ONLY NUMBERS");
				scWord.next();
			}			
		}
		
		//edita ctc details
		private static Contact editDetailsOfContact(ContactBook cbObjHoldingCtobjBeingEdited ,Contact ctObjToBeEdited)
		{
			int choiceEditCtDetailsInt = 0;
			String inputOfEditCtDetailsStr ="";
			Contact ctObjDuplicate = null ;		
			do
			{
				System.out.println("PRESS");
				System.out.println("1   TO EDIT CONTACT's NAME");
				System.out.println("2   TO EDIT A PHONE NUMBER");
				System.out.println("3   TO EDIT A  EMAIL ID");
				System.out.println("4   TO EDIT THE DOB [SET NEW DOB IF EXISTS OR ADD NEW ONE IF NOT EXISTS]");
				System.out.println("5   TO EDIT THE ADDRESS [SET NEW ADDRESS IF EXISTS OR ADD NEW ONE IF NOT EXISTS]");
				System.out.println("6   TO EDIT THE PET NAME [SET NEW PET NAME IF EXISTS OR ADD NEW ONE IF NOT EXISTS]");
				System.out.println("7   TO EDIT THE TAG [SET NEW TAG IF EXISTS OR ADD NEW ONE IF NOT EXISTS]");
				//remove ct details
				System.out.println("8   TO REMOVE A PHONE NUMBER");
				System.out.println("9   TO REMOVE A  EMAIL ID");
				System.out.println("10  TO REMOVE THE DOB");
				System.out.println("11  TO REMOVE THE ADDRESS");
				System.out.println("12  TO REMOVE THE PET NAME");
				System.out.println("13  TO REMOVE THE TAG");
				//add new phn and?or email
				System.out.println("14  TO ADD A NEW PHONE NUMBER");
				System.out.println("15  TO ADD A NEW EMAIL ID");
				System.out.println("16  TO SAVE IT & RETURN TO PREVIOUS MENU");
				repeatUntilInputIsNotInt();
				choiceEditCtDetailsInt = scWord.nextInt();
				switch (choiceEditCtDetailsInt) 
				{
				case 1://EDIT NAM OF CTC
				
					try 
					{					
						System.out.println("ENTER NEW NAME FOR "+ctObjToBeEdited.getNameOfContactStr());
						inputOfEditCtDetailsStr =scLine.nextLine();
						if( inputOfEditCtDetailsStr == null || inputOfEditCtDetailsStr.trim().length() == 0 )
							throw new IllegalArgumentException("CAN NOT ADD A NULL/BLANK/EMPTY AS NEW NAME FOR CONTACT "+ctObjToBeEdited.getNameOfContactStr());
						inputOfEditCtDetailsStr = RemoveExtraSpace.removeExtraSpace(inputOfEditCtDetailsStr) ;
						if (ctObjToBeEdited.getNameOfContactStr().trim().equalsIgnoreCase(inputOfEditCtDetailsStr) == true )
							throw new IllegalArgumentException("WHY ARE YOU WASTING TIME TO GIVE SAME NAME IN EDITION");
						
						if( cbObjHoldingCtobjBeingEdited.isContactExists(inputOfEditCtDetailsStr) == true )
							throw new IllegalArgumentException("THE NAME ENTERED BY YOU IS ALREADY A NAME OF "
									+ "AONTHER CONTACT IN THE CONTACT BOOK "
						+cbObjHoldingCtobjBeingEdited.getNameOfContactBookStr()+" SO CAN NOT ADD DUPLICATE NAME");
											
						ctObjToBeEdited.setNameOfContactStr(inputOfEditCtDetailsStr);						
					}
					catch (Exception e) 
					{
						System.out.println(e.getLocalizedMessage());
					}
					
					break;
				case 2://edit ph no
					try 
					{
						 if(ctObjToBeEdited.getCollOfPhoneNumbers().size() == 0)
							 throw new IllegalArgumentException("THERE ARE NO PHONE NUMBERS TO EDIT");
						 int indexOfPnInt = 1;
						for(String pNStr : ctObjToBeEdited.getCollOfPhoneNumbers() )
						{
							if(ctObjToBeEdited.getCollOfPhoneNumbers().size() == 1)
							{
								System.out.println("THERE IS ONLY 1 PHONE NUMBER FOR YOU TO EDIT & i.e");
								System.out.println(pNStr);								
							}
							else
								{
								System.out.println(indexOfPnInt+" - "+pNStr);
								indexOfPnInt ++ ;
								}
						}
						if(ctObjToBeEdited.getCollOfPhoneNumbers().size() == 1)
						{
							ArrayList<String>  alPhN = new ArrayList<String>(ctObjToBeEdited.getCollOfPhoneNumbers());
							inputOfEditCtDetailsStr = alPhN.get(0);
							
						}
						else
						{
							 System.out.println("SELECT ONE PHONE NUMBER FROM ABOVE LIST TO EDIT (ENTER PHONE NUMBER)");
							 inputOfEditCtDetailsStr = scLine.nextLine() ;
						}
						if(inputOfEditCtDetailsStr == null || inputOfEditCtDetailsStr.trim().length() == 0 )
							throw new IllegalArgumentException("THERE IS NO PHONE NUMBER WHOSE VALUE IS NULL/EMPTY/BLANK IN CONTACT BOOK TO EDIT");
						
						inputOfEditCtDetailsStr =  RemoveExtraSpace.removeExtraSpace(inputOfEditCtDetailsStr) ;
					
						 if(ctObjToBeEdited.getCollOfPhoneNumbers().contains(inputOfEditCtDetailsStr.trim()) == false )
							 throw new IllegalArgumentException("YOU HAVE ENTERED "+inputOfEditCtDetailsStr+" WHICH WAS NOT SHOWN TO YOU ");
						 
						 System.out.println("ENTER NEW PHONE NUMBER TO REPLACE "+inputOfEditCtDetailsStr);
						         String newPNStr = scLine.nextLine() ;
						         if(newPNStr == null || newPNStr.trim().length() == 0 )
						        	 throw new IllegalArgumentException("CAN NOT REPLACE "+inputOfEditCtDetailsStr+" BY A NULL/BLANK/EMPTY NUMBER");
						         newPNStr = RemoveExtraSpace.removeExtraSpace(newPNStr) ;
						if(newPNStr.equalsIgnoreCase(inputOfEditCtDetailsStr) == true )
								throw new IllegalArgumentException("NO NEED TO REPLACE PHONE NUMBER WITH SAME VALUE ");
							
						 if(ctObjToBeEdited.addPhoneNumber(newPNStr) == false )
								throw new IllegalArgumentException("EDITING FALIED DUE TO SOME TECHNICAL PROBLEMS ");
						 
						 if(ctObjToBeEdited.removePhoneNumber(inputOfEditCtDetailsStr) == false )
							 throw new IllegalArgumentException(inputOfEditCtDetailsStr+" COULD NOT BE EDITED DUE TO SOME TECHNICAL PROBLEMS ");
						
							
							System.out.println("THE PHONE NUMBER CHANGED TO "+newPNStr);
							
					}
					catch (Exception e)
					{
						System.out.println(e.getLocalizedMessage());
					}
				    
				    	 
					break;
					
				case 3://edit a email
					try 
					{
						if ( ctObjToBeEdited.isThereEmails() == false )
							throw new IllegalArgumentException("THERE ARE NO EMIALS TO EDIT");
						 ctObjToBeEdited.displayAllEmails();
						 if(ctObjToBeEdited.getCollOfEmails().size() == 1 )
						 {
							 ArrayList<String> alEmails = new ArrayList<String>(ctObjToBeEdited.getCollOfEmails());
							 inputOfEditCtDetailsStr = alEmails.get(0);
						 }
						 else
						 {
							 System.out.println("SELECT ONE EMAIL-ID FROM ABOVE LIST TO EDIT (ENTER EMAIL-ID)");
							 inputOfEditCtDetailsStr = scLine.nextLine() ;
						 }
						 if(inputOfEditCtDetailsStr == null || inputOfEditCtDetailsStr.trim().length() == 0 )
							 throw new IllegalArgumentException("THERE ARE NO EMAIL-ID WITH BLANK/EMPTY VALUE IN CONTACT TO EDIT");
						 inputOfEditCtDetailsStr = RemoveExtraSpace.removeExtraSpace(inputOfEditCtDetailsStr) ;
						 if( ctObjToBeEdited.isEmailExists(inputOfEditCtDetailsStr) == false )
							 throw new IllegalArgumentException("THE EMAIL-ID YOU SELECTED "+inputOfEditCtDetailsStr+" DOES NOT EXISTS IN CONTACT");
						 //now receive new email id
				         System.out.println("NOW ENTER THE NEW EMAIL-ID TO REPLACE "+inputOfEditCtDetailsStr);
				         String newEmailStr = scLine.nextLine() ;				         
					
				         if(newEmailStr == null || newEmailStr.trim().length() == 0 )
				        	 throw new IllegalArgumentException("CAN NOT REPLACE "+inputOfEditCtDetailsStr+" BY A BLANK/EMPTY EMAIL-ID");
				         newEmailStr = RemoveExtraSpace.removeExtraSpace(newEmailStr) ;
				         if( inputOfEditCtDetailsStr.equalsIgnoreCase(newEmailStr) == true )
				        	 throw new IllegalArgumentException("NO WORTH OF REPLACING A E-MAIL-ID BY SAME VALUE");
				         
						 if(ctObjToBeEdited.addEmail(newEmailStr) == false )
							 throw new IllegalArgumentException("EDITION OF EMAIL ID FAILED");
						 
						 if(ctObjToBeEdited.removeEmail(inputOfEditCtDetailsStr) == false )
							 throw new IllegalArgumentException("EMAIL-ID "+inputOfEditCtDetailsStr+" COULD NOT BE EDITTED DUE TO SOME REASONS");
					
						 System.out.println("EMAIL-ID IS UPDATED TO "+newEmailStr);
						 
					}
					catch (Exception e)
					{
						System.out.println(e.getLocalizedMessage());
					}
					break;
				case 4://EDIT DOB
					try
					{
						if(ctObjToBeEdited.getDobOfContact() == null )
						{
							System.out.println("DOB OF "+ctObjToBeEdited.getNameOfContactStr()+" IS NOT YET ADDED");
							System.out.println("ENTER THE DOB IN THE FORMAT DD/MM/YYYY TO SET");
						}						
						else
						{
							System.out.println("DOB OF "+ctObjToBeEdited.getNameOfContactStr()+" IS ");
							System.out.println( ContactBook.getReadableDOB(ctObjToBeEdited.getDobOfContact()));
							System.out.println("ENTER THE NEW DOB IN THE FORMAT DD/MM/YYYY TO REPLACE "+ContactBook.getReadableDOB(ctObjToBeEdited.getDobOfContact()));
						}
						inputOfEditCtDetailsStr =scLine.nextLine();
						ctObjToBeEdited.setDobOfContact(inputOfEditCtDetailsStr);
						System.out.println("DOB IS UPDATED SUCCESSFULLY");
					} 					
					catch (Exception e) {
						System.out.println(e.getLocalizedMessage());
					}
					break;
				case 5://edit address
					try
					{
						if(ctObjToBeEdited.getAddressOfContactStr() == null )
						{
							System.out.println("ADDRESS OF "+ctObjToBeEdited.getNameOfContactStr()+" IS NOT YET ADDED");
							System.out.println("ENTER THE NEW ADDRESS TO SET");
						}						
						else
						{
							System.out.println("ADDRESS OF "+ctObjToBeEdited.getNameOfContactStr()+" IS ");
							System.out.println(ctObjToBeEdited.getAddressOfContactStr());
							System.out.println("ENTER THE NEW ADDRESS TO REPLACE "+ctObjToBeEdited.getAddressOfContactStr());
						}
						inputOfEditCtDetailsStr =scLine.nextLine();
						ctObjToBeEdited.setAddressOfContactStr(inputOfEditCtDetailsStr);
						System.out.println("ADDRESS IS UPDATED SUCCESSFULLY");
					}
					catch (Exception e) 
					{
						System.out.println(e.getLocalizedMessage());
					}
					break;
					
				case 6://edit the pet name
					try
					{
						if(ctObjToBeEdited.getPetNameStr() == null )
						{
							System.out.println("PET NAME OF "+ctObjToBeEdited.getNameOfContactStr()+" IS NOT YET ADDED");
							System.out.println("ENTER THE NEW PET NAME TO SET");
						}						
						else
						{
							System.out.println("PET NAME OF "+ctObjToBeEdited.getNameOfContactStr()+" IS ");
							System.out.println(ctObjToBeEdited.getPetNameStr());
							System.out.println("ENTER THE NEW PET NAME TO REPLACE "+ctObjToBeEdited.getPetNameStr());
						}
						inputOfEditCtDetailsStr =scLine.nextLine();
						ctObjToBeEdited.setPetNameStr(inputOfEditCtDetailsStr);
						System.out.println("PET NAME IS UPDATED SUCCESSFULLY");
					}
					catch (Exception e) 
					{
						System.out.println(e.getLocalizedMessage());
					}
					break;
					
				case 7://edit tag
					try
					{
						if(ctObjToBeEdited.getTagStr() == null )
						{
							System.out.println("TAG OF "+ctObjToBeEdited.getNameOfContactStr()+" IS NOT YET ADDED");
							System.out.println("ENTER THE NEW TAG TO SET");
						}						
						else
						{
							System.out.println("TAG OF "+ctObjToBeEdited.getNameOfContactStr()+" IS ");
							System.out.println(ctObjToBeEdited.getTagStr());
							System.out.println("ENTER THE NEW TAG TO REPLACE "+ctObjToBeEdited.getTagStr());
						}
						inputOfEditCtDetailsStr =scLine.nextLine();
						ctObjToBeEdited.setTagStr(inputOfEditCtDetailsStr);
						System.out.println("TAG IS UPDATED SUCCESSFULLY");
					}
					catch (Exception e) 
					{
						System.out.println(e.getLocalizedMessage());
					}
					break;
					
				case 8://remove Ph
					try
					{
						if(ctObjToBeEdited.isTherePhoneNumbers() == false )
							throw new IllegalArgumentException("THERE ARE ZERO PHONE NUMBERS TO REMOVE");
						ctObjToBeEdited.displayAllPhoneNumbers();
						System.out.println("ENTER 1 PHONE NUMBER FROM ABOVE LIST TO REMOVE FROM CONTACT "+ctObjToBeEdited.getNameOfContactStr());
						inputOfEditCtDetailsStr = scLine.nextLine();
						if(ctObjToBeEdited.removePhoneNumber(inputOfEditCtDetailsStr) == false )
							throw new IllegalArgumentException("PHONE NUMBER "+inputOfEditCtDetailsStr+" COULD NOT BE REMOVED DUE TO SOME REASONS");
						System.out.println("PHONE NUMBER "+inputOfEditCtDetailsStr.trim()+" IS BEEN REMOVED FROM CONTACT "+ctObjToBeEdited.getNameOfContactStr());
					}
					catch (Exception e) 
					{
						System.out.println(e.getLocalizedMessage());
					}
					break;
					
				case 9://remove email id
					try
					{
						if(ctObjToBeEdited.isThereEmails() == false )
							throw new IllegalArgumentException("THERE ARE ZERO EMAIL-IDs TO REMOVE");
						ctObjToBeEdited.displayAllEmails();;
						System.out.println("ENTER 1 EMAIL-ID FROM ABOVE LIST TO REMOVE FROM CONTACT "+ctObjToBeEdited.getNameOfContactStr());
						inputOfEditCtDetailsStr = scLine.nextLine();
						if(ctObjToBeEdited.removeEmail(inputOfEditCtDetailsStr) == false )
							throw new IllegalArgumentException("EMAIL-IDs "+inputOfEditCtDetailsStr+" COULD NOT BE REMOVED DUE TO SOME REASONS");
						System.out.println("EMAIL-IDs "+inputOfEditCtDetailsStr.trim()+" IS BEEN REMOVED FROM CONTACT "+ctObjToBeEdited.getNameOfContactStr());
					}
					catch (Exception e) 
					{
						System.out.println(e.getLocalizedMessage());
					}
					break;
					
				case 10://remove dob
					try 
					{
						if(ctObjToBeEdited.getDobOfContact() == null )
							throw new IllegalArgumentException("DOB OF CONTACT "+ctObjToBeEdited.getNameOfContactStr()+" IS NOT YET ADDED TO REMOVE");
					
						ctObjDuplicate = new Contact(ctObjToBeEdited.getNameOfContactStr());
						//add all available values 
					
						ctObjDuplicate.setDateOfContactCreated(ctObjToBeEdited.getDateOfContactCreated());						
						ctObjDuplicate.setCollOfPhoneNumbers(ctObjToBeEdited.getCollOfPhoneNumbers());
						ctObjDuplicate.setCollOfEmails(ctObjToBeEdited.getCollOfEmails());
						if(ctObjToBeEdited.getAddressOfContactStr() != null)
						ctObjDuplicate.setAddressOfContactStr(ctObjToBeEdited.getAddressOfContactStr());
						if(ctObjToBeEdited.getPetNameStr() != null )
						ctObjDuplicate.setPetNameStr(ctObjToBeEdited.getPetNameStr());
						if( ctObjToBeEdited.getTagStr() != null )
						ctObjDuplicate.setTagStr(ctObjToBeEdited.getTagStr());
						ctObjToBeEdited = ctObjDuplicate;
						if( ctObjToBeEdited.getDobOfContact() != null )
							throw new IllegalArgumentException("DOB OF CONTACT "+ctObjToBeEdited.getNameOfContactStr()+" COULD NOT BE REMOVED");
						System.out.println("DOB OF CONTACT "+ctObjToBeEdited.getNameOfContactStr()+" REMOVED SUCCESSFULLY");
						
					}
					catch (Exception e)
					{
						System.out.println(e.getLocalizedMessage());
					}
					break;
					
				case 11://remv adress
					try 
					{
						if(ctObjToBeEdited.getAddressOfContactStr() == null )
							throw new IllegalArgumentException("ADDRESS OF CONTACT "+ctObjToBeEdited.getNameOfContactStr()+" IS NOT YET ADDED TO REMOVE");
					
						ctObjDuplicate = new Contact(ctObjToBeEdited.getNameOfContactStr());
						//add all available values 
					
						ctObjDuplicate.setCollOfPhoneNumbers(ctObjToBeEdited.getCollOfPhoneNumbers());
						ctObjDuplicate.setCollOfEmails(ctObjToBeEdited.getCollOfEmails());
					
						ctObjDuplicate.setDateOfContactCreated(ctObjToBeEdited.getDateOfContactCreated());
						if(ctObjToBeEdited.getDobOfContact() != null)
							ctObjDuplicate.setDobOfContact(ctObjToBeEdited.getDobOfContact());
						if(ctObjToBeEdited.getPetNameStr() != null )
						ctObjDuplicate.setPetNameStr(ctObjToBeEdited.getPetNameStr());
						if( ctObjToBeEdited.getTagStr() != null )
						ctObjDuplicate.setTagStr(ctObjToBeEdited.getTagStr());
						ctObjToBeEdited = ctObjDuplicate;
						if( ctObjToBeEdited.getAddressOfContactStr() != null )
							throw new IllegalArgumentException("ADDRESS OF CONTACT "+ctObjToBeEdited.getNameOfContactStr()+" COULD NOT BE REMOVED");
						System.out.println("ADDRESS OF CONTACT "+ctObjToBeEdited.getNameOfContactStr()+" REMOVED SUCCESSFULLY");
						
					}
					catch (Exception e)
					{
						System.out.println(e.getLocalizedMessage());
					}
					break;
					
				case 12://remov petname
					try 
					{
						if(ctObjToBeEdited.getPetNameStr() == null )
							throw new IllegalArgumentException("PET NAME OF CONTACT "+ctObjToBeEdited.getNameOfContactStr()+" IS NOT YET ADDED TO REMOVE");
					
						ctObjDuplicate = new Contact(ctObjToBeEdited.getNameOfContactStr());
						//add all available values exlsidng petnmae
					
						ctObjDuplicate.setDateOfContactCreated(ctObjToBeEdited.getDateOfContactCreated());
						
						ctObjDuplicate.setCollOfPhoneNumbers(ctObjToBeEdited.getCollOfPhoneNumbers());
						
						ctObjDuplicate.setCollOfEmails(ctObjToBeEdited.getCollOfEmails());
											
					
						if(ctObjToBeEdited.getDobOfContact() != null)
							ctObjDuplicate.setDobOfContact(ctObjToBeEdited.getDobOfContact());
						
						if(ctObjToBeEdited.getAddressOfContactStr() != null)
							ctObjDuplicate.setAddressOfContactStr(ctObjToBeEdited.getAddressOfContactStr());
				
						if( ctObjToBeEdited.getTagStr() != null )
						ctObjDuplicate.setTagStr(ctObjToBeEdited.getTagStr());
						ctObjToBeEdited = ctObjDuplicate;
						if( ctObjToBeEdited.getPetNameStr() != null )
							throw new IllegalArgumentException("PET NAME OF CONTACT "+ctObjToBeEdited.getNameOfContactStr()+" COULD NOT BE REMOVED");
						System.out.println("PET NAME OF CONTACT "+ctObjToBeEdited.getNameOfContactStr()+" REMOVED SUCCESSFULLY");
						
					}
					catch (Exception e)
					{
						System.out.println(e.getLocalizedMessage());
					}
					break;
					
				case 13://remove tag
					try 
					{
						if(ctObjToBeEdited.getTagStr() == null )
							throw new IllegalArgumentException("TAG OF CONTACT "+ctObjToBeEdited.getNameOfContactStr()+" IS NOT YET ADDED TO REMOVE");
					
						ctObjDuplicate = new Contact(ctObjToBeEdited.getNameOfContactStr());
						//add all available values exlsidng petnmae
					
						ctObjDuplicate.setDateOfContactCreated(ctObjToBeEdited.getDateOfContactCreated());
						
						ctObjDuplicate.setCollOfPhoneNumbers(ctObjToBeEdited.getCollOfPhoneNumbers());
						
						ctObjDuplicate.setCollOfEmails(ctObjToBeEdited.getCollOfEmails());
											
					
						if(ctObjToBeEdited.getDobOfContact() != null)
							ctObjDuplicate.setDobOfContact(ctObjToBeEdited.getDobOfContact());
						
						if(ctObjToBeEdited.getAddressOfContactStr() != null)
							ctObjDuplicate.setAddressOfContactStr(ctObjToBeEdited.getAddressOfContactStr());
						
						if(ctObjToBeEdited.getPetNameStr() != null )
						ctObjDuplicate.setPetNameStr(ctObjToBeEdited.getPetNameStr());
						
						ctObjToBeEdited = ctObjDuplicate;
						if( ctObjToBeEdited.getTagStr() != null )
							throw new IllegalArgumentException("TAG OF CONTACT "+ctObjToBeEdited.getNameOfContactStr()+" COULD NOT BE REMOVED");
						System.out.println("TAG OF CONTACT "+ctObjToBeEdited.getNameOfContactStr()+" REMOVED SUCCESSFULLY");
						
					}
					catch (Exception e)
					{
						System.out.println(e.getLocalizedMessage());
					}
					
					break;
				case 14://add new Ph
					try 
					{
						if(ctObjToBeEdited.isTherePhoneNumbers() == true )
						{
							ctObjToBeEdited.displayAllPhoneNumbers();
							System.out.println("ENTER ANY PHONE NUMBER WHICH IS NOT AVAILABLE IN ABOVE LIST [REASON : DUPLICATES ARE NOT ALLOWED]");
						}
						System.out.println("ENTER A BRAND NEW PHONE NUMBER FOR CONTACT "+ctObjToBeEdited.getNameOfContactStr());
						inputOfEditCtDetailsStr = scLine.nextLine();
						if(ctObjToBeEdited.addPhoneNumber(inputOfEditCtDetailsStr) == false )
							throw new IllegalArgumentException("PHONE NUMBER "+inputOfEditCtDetailsStr.trim()+" COULD NOT BE ADDED TO CONTACT "+ctObjToBeEdited.getNameOfContactStr());
						else
						System.out.println("PHONE NUMBER "+inputOfEditCtDetailsStr+" IS BEEN ADDED TO CONTACT "+ctObjToBeEdited.getNameOfContactStr()+" SUCCESSFULLY");	
							
					} catch (Exception e) 
					{
						System.out.println(e.getLocalizedMessage());
					}
					
					break;
				case 15://ADDD NEW EAIL
					try 
					{
						if(ctObjToBeEdited.isThereEmails() == true )
						{
							ctObjToBeEdited.displayAllEmails();
							System.out.println("ENTER ANY EMAIL-ID WHICH IS NOT AVAILABLE IN ABOVE LIST [REASON : DUPLICATES ARE NOT ALLOWED]");
						}
						System.out.println("ENTER A BRAND NEW EMAIL-ID FOR CONTACT "+ctObjToBeEdited.getNameOfContactStr());
						inputOfEditCtDetailsStr = scLine.nextLine();
						if(ctObjToBeEdited.addEmail(inputOfEditCtDetailsStr) == false )
							throw new IllegalArgumentException("EMAIL-ID "+inputOfEditCtDetailsStr.trim()+" COULD NOT BE ADDED TO CONTACT "+ctObjToBeEdited.getNameOfContactStr());
						else
						System.out.println("EMAIL-ID "+inputOfEditCtDetailsStr+" IS BEEN ADDED TO CONTACT "+ctObjToBeEdited.getNameOfContactStr()+" SUCCESSFULLY");	
							
					} catch (Exception e) 
					{
						System.out.println(e.getLocalizedMessage());
					}
					break;
				case 16://save n rteirn
					System.out.println("YOUR CONTACT "+ctObjToBeEdited.getNameOfContactStr()+" WILL BE UPDATED RETURNING TO PREVIOUS MENU");
					break;
			
				default:defaultOfSwitch(choiceEditCtDetailsInt, 16);
					break;
				}
			}
			while(choiceEditCtDetailsInt != 16);
			return ctObjToBeEdited ;
		}
		//ADD COATC DETAIS
		private static Contact addDetailsForContact(Contact ctObj)
		{
			int choiceCtDetailsInt = 0;
			String inputOfAddCtDetailsStr = "";
			
			do
			{
				System.out.println("PRESS");
				System.out.println("1  TO ADD A PHONE NUMBER TO CONTACT "+ctObj.getNameOfContactStr());
				System.out.println("2  TO ADD A EMAIL ID TO CONTACT "+ctObj.getNameOfContactStr());
				if( ctObj.getDobOfContact() == null )
				System.out.println("3  TO ADD DOB OF CONTACT "+ctObj.getNameOfContactStr());
				if( ctObj.getAddressOfContactStr() == null )
				System.out.println("4  TO ADD ADDRESS OF CONTACT "+ctObj.getNameOfContactStr());
				if (ctObj.getPetNameStr() == null )
				System.out.println("5  TO ADD PET NAME OF CONTACT "+ctObj.getNameOfContactStr());
				if( ctObj.getTagStr() == null )
				System.out.println("6  TO ADD TAG OF CONTACT "+ctObj.getNameOfContactStr());
				System.out.println("7  TO SAVE CONTACT");
				repeatUntilInputIsNotInt();
				choiceCtDetailsInt = scWord.nextInt();
				switch (choiceCtDetailsInt)
				{
				case 1://add phn					
					System.out.println("ENTER A PHONE NUMBER TO CONTACT");
					inputOfAddCtDetailsStr = scLine.nextLine();
					try
					{
						ctObj.addPhoneNumber(inputOfAddCtDetailsStr);	
					}
					catch (Exception e)
					{
						System.out.println(e.getLocalizedMessage());
					}					
					break;
					
				case 2:// email
					System.out.println("ENTER A EMAIL-ID TO CONTACT ");
				    inputOfAddCtDetailsStr = scLine.nextLine();				    				
				    try
					{
						ctObj.addEmail(inputOfAddCtDetailsStr);
					}
					catch (Exception e)
					{
						System.out.println(e.getLocalizedMessage());
					}
					break;
					
				case 3://DOB
					if( ctObj.getDobOfContact() != null )
					{
						avoidAddingExistingAttribute(choiceCtDetailsInt);
					}
					else
					{
						System.out.println("ENTER THE DOB OF CONTACT IN THE FORMAT DD/MM/YYYY");
						 inputOfAddCtDetailsStr = scLine.nextLine(); 
						 try
							{
								ctObj.setDobOfContact(inputOfAddCtDetailsStr);
							}
							catch (Exception e)
							{
								System.out.println(e.getLocalizedMessage());
							}
					}					
					break;
					
				case 4://address
					if( ctObj.getAddressOfContactStr() != null )
					{
						avoidAddingExistingAttribute(choiceCtDetailsInt);
					}
					else
					{
						System.out.println("ENTER THE ADDRESS OF CONTACT");
						 inputOfAddCtDetailsStr = scLine.nextLine(); 
						 try
							{
								ctObj.setAddressOfContactStr(inputOfAddCtDetailsStr);
							}
							catch (Exception e)
							{
								System.out.println(e.getLocalizedMessage());
							}
					}				
					break;
					
				case 5://pet name
					if( ctObj.getPetNameStr() != null )
					{
						avoidAddingExistingAttribute(choiceCtDetailsInt);
					}
					else
					{
						System.out.println("ENTER THE PET NAME OF CONTACT");
						 inputOfAddCtDetailsStr = scLine.nextLine(); 
						 try
							{
								ctObj.setPetNameStr(inputOfAddCtDetailsStr);
							}
							catch (Exception e)
							{
								System.out.println(e.getLocalizedMessage());
							}
					}				
					break;			
			case 6://tag
				if( ctObj.getTagStr() != null )
				{
					avoidAddingExistingAttribute(choiceCtDetailsInt);
				}
				else
				{
					System.out.println("ENTER THE TAG OF CONTACT");
					 inputOfAddCtDetailsStr = scLine.nextLine(); 
					 try
						{
							ctObj.setTagStr(inputOfAddCtDetailsStr);
						}
						catch (Exception e)
						{
							System.out.println(e.getLocalizedMessage());
						}
				}				
				break;					
				case 7 ://save
					System.out.println("THE DETAILS ARE SAVED TO CONTACT "+ctObj.getNameOfContactStr());
			         break ;
				default:defaultOfSwitch(choiceCtDetailsInt, 7);
				  
					break;
				}
			}
			while(choiceCtDetailsInt != 7);
			return ctObj;
		}
		//SUB MENU
		private static ContactBook subMenu(ContactBook cbObjOfSubMenu)
		{
			int choiceSubMenuInt = 0;
			String inputOfSubMenuStr ="";				
			do
			{
				lineSeparator();
				System.out.println("PRESS");
				System.out.println("1  TO ADD A CONTACT");
				System.out.println("2  TO EDIT A CONTACT");
				System.out.println("3  TO REMOVE A CONTACT");
				System.out.println("4  TO LIST ALL THE CONTACTS");
				System.out.println("5  TO SEARCH CONTACT");
				System.out.println("6  TO GO BACK");
				repeatUntilInputIsNotInt();
				choiceSubMenuInt = scWord.nextInt();
				switch (choiceSubMenuInt)
				{
				case 1://ADD CONTACT
					lineSeparator();
					System.out.println("ADD DETAILS OF CONTACT");
					
					System.out.println("ENTER THE NAME OF CONTACT");
					inputOfSubMenuStr = scLine.nextLine();
					try
					{
						Contact ctObj = new Contact(inputOfSubMenuStr);
						ctObj = addDetailsForContact(ctObj);
						
						if(cbObjOfSubMenu.addAContact(ctObj) == true )
							System.out.println("YES "+ctObj.getNameOfContactStr()+" IS ADDED TO CONTACT BOOK "+cbObjOfSubMenu.getNameOfContactBookStr());
						else
							System.out.println("UNFORTUNATELLY "+ctObj.getNameOfContactStr()+" COULD NOT BE SAVED TO CONTACT BOOK "+cbObjOfSubMenu.getNameOfContactBookStr());
					}
					catch (Exception e) {
						System.out.println(e.getLocalizedMessage());
					}					
					break;
					
				case 2://edit a ctct
					
					Contact ctObjToBeSustituted = null ;					
					try
					{
						if(cbObjOfSubMenu.isCBEmpty() == true )
							throw new IllegalArgumentException("CONTACT BOOK IS EMPTY TO EDIT");
					 
						cbObjOfSubMenu.displayContactsByName();
						System.out.println("THESE ALL ARE CONTACTS WITH DETAILS SELECT WHICH 1 YOU WANT TO EDIT(ENTER CONTACT'S NAME)");
						inputOfSubMenuStr = scLine.nextLine();
					if( cbObjOfSubMenu.isContactExists(inputOfSubMenuStr) == false )
							throw new IllegalArgumentException("YOU HAVE ENTRED A CONTACT NAME WHICH WAS NOT SHOWN TO YOU [DOES NOT EXISTS IN CONTACT BOOK ]");
					Contact ctObjToBeEdited = cbObjOfSubMenu.getContctObjByKnowingName(inputOfSubMenuStr) ;
					
						//after complet edition add back to CB thenew cntc
						ctObjToBeSustituted = editDetailsOfContact(cbObjOfSubMenu , ctObjToBeEdited);
					
						if ( cbObjOfSubMenu.editAContact(inputOfSubMenuStr, ctObjToBeSustituted) == false )
						throw new IllegalArgumentException("EDITION FAILED DUE TO SOME PROBLEMS");
					System.out.println("THE CONTACT "+inputOfSubMenuStr+" IS BEEN UPDATED SUCCESSFULLY");
					}
					catch (Exception e)
					{
						System.out.println(e.getLocalizedMessage());
					}
					break;
					
				case 3://3  TO REMOVE A CONTACT
					System.out.println("");
					try
					{
						int choiceDeletCtcInt = 0 ;
						if(cbObjOfSubMenu.isCBEmpty() == true )
							throw new IllegalArgumentException("THERE ARE NO CONTACTS TO REMOVE IN CONTACT BOOK "+cbObjOfSubMenu.getNameOfContactBookStr());
						cbObjOfSubMenu.displayContactsByName();
						System.out.println("ENTER 1 CONTACT's NAME FROM ABOVE LIST TO REMOVE");
						inputOfSubMenuStr = scLine.nextLine() ;
						if( inputOfSubMenuStr == null || inputOfSubMenuStr.trim().length() == 0 )
							throw new IllegalArgumentException("ENTER CONTACT'S NAME WHICH IS NOT NULL/BLANK/EMPTY TO REMOVE");
						if( cbObjOfSubMenu.isContactExists(inputOfSubMenuStr) ==  false )
							throw new IllegalArgumentException("CONTACT "+inputOfSubMenuStr+" DOES NOT EXISTS IN CONTACT BOOK "+cbObjOfSubMenu.getNameOfContactBookStr()+" TO DELETE");
						inputOfSubMenuStr = RemoveExtraSpace.removeExtraSpace(inputOfSubMenuStr) ;
						System.out.println("ARE YOU SURE YOU WANT TO REMOVE "+inputOfSubMenuStr.trim()+" ?");
						System.out.println("1  YES DELETE IT");
						System.out.println("2  NO, DO NOT DELETE");
						repeatUntilInputIsNotInt();
						choiceDeletCtcInt = scWord.nextInt();
						switch (choiceDeletCtcInt)
						{
						case 1:
							if(cbObjOfSubMenu.removeAContact(inputOfSubMenuStr) == false )
								throw new IllegalArgumentException("CONTACT "+inputOfSubMenuStr.trim()+" COULD NOT BE REMOVED");
							else
								System.out.println("CONTACT "+RemoveExtraSpace.removeExtraSpace(inputOfSubMenuStr)+" REMOVED SUCCESSFULLY");
							break;
						case 2:
							System.out.print("YOU HAVE SELECTED NOT TO DELETE.");
						       System.out.println(" SO YOUR CONTACT "+inputOfSubMenuStr+" IS SAFE");
							break;
						default:
							    System.out.println("YOU ENTERED "+choiceDeletCtcInt+" WHICH WAS NOT AT ALL A OPTION FOR YOU WHICH MUST BE EITHER 1 OR 2");
						        System.out.println("SO YOUR CONTACT IS SAFE");
							break;
						}
						
					} catch (Exception e)
					{
						System.out.println(e.getLocalizedMessage());
					}
					break;
					
				case 4:	//listing all cnts				
					try 
					{
						if( cbObjOfSubMenu.isCBEmpty() == true )
							throw new IllegalArgumentException("CONTACT BOOK IS EMPTY NOW TO SHOW");
						int choiceOfListCtsInt = 0;
						System.out.println("");
						System.out.println("PRESS");
						System.out.println("1  TO SEE ALL CONTACTS IN ALPHABETICAL ORDER OF NAMES");
						System.out.println("2  TO SEE ALL CONTACTS IN ALPHABETICAL ORDER OF TAGS");
						System.out.println("3  TO SEE ALL CONTACTS IN ASCENDING ORDER OF CREATED DATE");
						System.out.println("4  TO SEE ALL CONTACTS IN INCREMENTAL ORDER OF THEIR LENTHS");
						repeatUntilInputIsNotInt();
						choiceOfListCtsInt = scWord.nextInt();
						switch (choiceOfListCtsInt)
						{
						case 1:
							try
							{
								cbObjOfSubMenu.displayContactsByName();
							}
							catch (Exception e) {
								System.out.println(e.getLocalizedMessage());
							}
							break;
						case 2:
							try {
								cbObjOfSubMenu.displayContactsByTags();
							} catch (Exception e) {
								System.out.println(e.getLocalizedMessage());
							}
							break;
						case 3:
							try {
								cbObjOfSubMenu.displayContactsByDOC();
							} catch (Exception e) {
								System.out.println(e.getLocalizedMessage());
							}
							break;
						case 4:
							try {
								cbObjOfSubMenu.displayContactsbyLength();
							} catch (Exception e) 
							{
								System.out.println(e.getLocalizedMessage());
							}
							break;

						default:defaultOfSwitch(choiceOfListCtsInt, 4);
							break;
						}
						
					} catch (Exception e)
					{
						System.out.println(e.getLocalizedMessage());
					}
					
					lineSeparator();
					System.out.println("RETURNING TO PREVIOUS MENU");
					break;
				case 5:	//serach contc					
					try 
					{
						if(cbObjOfSubMenu.isCBEmpty() == true )
							throw new IllegalArgumentException("CONTACT BOOK IS EMPTY TO SEARCH");
						System.out.println("ENTER A SEARCH STRING");
						inputOfSubMenuStr =  scLine.nextLine();
						cbObjOfSubMenu.searchAContact(inputOfSubMenuStr);
					} 
					catch (Exception e) 
					{
						System.out.println(e.getLocalizedMessage());
					}				
					break;
				case 6:
					System.out.println("MOVING TO PREVIOUS MENU");
					break;

				default:defaultOfSwitch(choiceSubMenuInt, 6);
					break;
				}
			}
			while(choiceSubMenuInt != 6);
			return cbObjOfSubMenu;
		}
	//main 
	public static void main(String ...args)
	{
		//main menu choice 
		int choiceMainMenuInt = 0;
		String inputStr = "";
		ContactBook cbObjOfMainMenu = null ;
		do
		{
			lineSeparator();
			System.out.println("PRESS");
			System.out.println("1  TO CREATE A CONTACT BOOK");
			System.out.println("2  TO LOAD A CONTACT BOOK");
			System.out.println("3  TO SEARCH CONTACT BOOK");
			System.out.println("4  TO LIST CONTACT BOOKS");
			System.out.println("5  TO DELETE A CONTACT BOOK");
			System.out.println("6  FOR BIRTH DAY REMINDERS");
			System.out.println("7  TO EXIT");
			repeatUntilInputIsNotInt();
			choiceMainMenuInt = scWord.nextInt() ;
			switch (choiceMainMenuInt)
			{
			case 1://create a cb
				lineSeparator();
				System.out.println("CREATE YOUR CONTACT BOOK NOW");
				System.out.println("ENTER NAME OF THE CONTACT BOOK");
				inputStr = scLine.nextLine();
				try
				{
					cbObjOfMainMenu = UseContactBook.createAContactBook(inputStr);
					cbObjOfMainMenu = subMenu(cbObjOfMainMenu);
					UseContactBook.saveCB(cbObjOfMainMenu);
					
				} catch (Exception e) 
				{
					System.out.println(e.getLocalizedMessage());
				}
				break;
			case 2://laod cb
				lineSeparator();
				
				try
				{
					if(UseContactBook.isThereCBs() == false )
						throw new IllegalArgumentException("THERE ARE ZERO CONTACT BOOKS TO LAOD");
					System.out.println("LOADING CONTACT BOOK...........");
					UseContactBook.showAllContatcBooks();
					System.out.println("ENTER 1 NAME FROM CONTACT BOOKS SHOWN ABOVE TO LOAD");
					inputStr = scLine.nextLine();
					//load user reqired cb
					cbObjOfMainMenu = UseContactBook.loadAContactBook(inputStr);
					System.out.println(RemoveExtraSpace.removeExtraSpace(inputStr)+" IS BEEN LOADED SUCCESSFULLY NOW");
					cbObjOfMainMenu = subMenu(cbObjOfMainMenu);
					UseContactBook.saveCB(cbObjOfMainMenu);
				}
				catch (Exception e)
				{
					System.out.println(e.getLocalizedMessage());
				}				
				break;
			case 3://search cb
				try 
				{
					if(UseContactBook.isThereCBs() == false )
						throw new IllegalArgumentException("THERE ARE ZERO CONTACT BOOKS TO SEARCH");
					System.out.println("ENTER A SEARCH STRING");
					inputStr = scLine.nextLine();
					if( inputStr != null && inputStr.trim().length() != 0 )
						inputStr = RemoveExtraSpace.removeExtraSpace(inputStr);
					UseContactBook.searchForACB(inputStr);
				}
				catch (Exception e) 
				{
					System.out.println(e.getLocalizedMessage());
				}
				break;
			case 4://list cbs
				try {
					if(UseContactBook.isThereCBs() == false )
						throw new IllegalArgumentException("THERE ARE ZERO CONTACT BOOKS TO SHOW");
					UseContactBook.showAllContatcBooks();
				} catch (Exception e) 
				{
					System.out.println(e.getLocalizedMessage());
				}
				break;
			case 5://delet a cb
				try {
					
					if(UseContactBook.isThereCBs() == false )
						throw new IllegalArgumentException("THERE ARE ZERO CONTACT BOOKS TO DELETE");
					int choiceDeletCBInt = 0 ;
					UseContactBook.showAllContatcBooks();
					System.out.println("ENTER 1 NAME OF CONTACT BOOK FROM ABOVE SHOWN LIST TO DELETE");
					inputStr = scLine.nextLine() ;
					if(inputStr == null || inputStr.trim().length() == 0)
						throw new IllegalArgumentException("CAN NOT DELETE CONTACT BOOK WHICH HAS NO NAME");
					if( inputStr != null && inputStr.trim().length() != 0)
						inputStr = RemoveExtraSpace.removeExtraSpace(inputStr) ;
					if(UseContactBook.isCBExist(inputStr) == false )
						throw new IllegalArgumentException("CONTACT BOOK "+inputStr+" DOES NOT EXISTS TO DELETE");
					System.out.println("ARE YOU SURE TO DELETE CONTAT BOOK "+inputStr);
					System.out.println("1  YES, DELETE IT");
					System.out.println("2  NO, DO NOT DELETE IT");
					repeatUntilInputIsNotInt();
					choiceDeletCBInt = scWord.nextInt() ;
					switch (choiceDeletCBInt)
					{
					case 1:
						if(UseContactBook.deleteACB(inputStr) == false )
							throw new IllegalArgumentException("COULD NOT DELETE CONTACT BOOK "+RemoveExtraSpace.removeExtraSpace(inputStr));
						System.out.println("CONTACT BOOK "+RemoveExtraSpace.removeExtraSpace(inputStr)+" DELETED PERMANENTELLY");
						break;
						
					case 2:System.out.println("YOU DECIDED TO NOT TO DELETE THE CONTACT BOOK "+RemoveExtraSpace.removeExtraSpace(inputStr));
				           System.out.println("SO YOUR CONTACT BOOK IS SAFE");
						break;	

					default:System.out.println("YOU ENTERED "+choiceDeletCBInt+" WHICH WAS NOT AT ALL A OPTION .i.e EITHER 1 OR 2");
					         System.out.println("SO YOUR CONTACT BOOK IS SAFE");
						break;
					}
				
					
				}
				catch (Exception e) 
				{
					System.out.println(e.getLocalizedMessage());
				}
				break ;
			case 6://Bdy reminders
				lineSeparator();
				try
				{
					UseContactBook.showBirthDayReminders();
				}
				catch (Exception e)
				{
					System.out.println(e.getLocalizedMessage());
				}
				break;
			case 7://quit
				System.out.println("TQ");
				break;

			default:defaultOfSwitch(choiceMainMenuInt, 7);
				break;
			}
		}
		while( choiceMainMenuInt != 7 );
		scWord.close();
		scLine.close();
	}	
		

}
