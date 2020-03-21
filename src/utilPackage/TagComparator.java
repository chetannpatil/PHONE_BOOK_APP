package utilPackage;

import java.util.Comparator;

public final class TagComparator implements Comparator<String>
{

	@Override
	public int compare(String s1, String s2)
	{
		if(s1.equalsIgnoreCase(s2) == true )
			return 1;
		else
			return ( s1.compareToIgnoreCase(s2));
	}

}
