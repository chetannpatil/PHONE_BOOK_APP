package utilPackage;

import java.util.Comparator;

import objectsPackage.Contact;

public final class LengthOfContactsComparator implements Comparator<Contact>
{
	@Override
	public int compare(Contact c1, Contact c2)
	{
	  if(c1.toString().length() == c2.toString().length())
		return 1;		
	  else
		  return( c1.toString().length() - c2.toString().length() );
	}
 
}
