package utilPackage;

import java.util.Comparator;
import java.util.Date;

public final class DateOfBithComparator implements Comparator<Date>
{

	@Override
	public int compare(Date d1, Date d2) 
	{
		if( d1.getMonth() == d2.getMonth() )
		{
			if( d1.getDate() == d2.getDate() )
				return 1;
			else
				return ( d1.getDate() - d2.getDate() );
		}
		else
			return ( d1.getMonth() - d2.getMonth() );
	}

}
