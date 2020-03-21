package utilPackage;

public final class RemoveExtraSpace 
{
	//util methods
		//remove extra space
		public static String removeExtraSpace(String remvExSpcStr)
		{
		 if( remvExSpcStr == null || remvExSpcStr.trim().length() == 0 )
			 throw new IllegalArgumentException("CAN NOT REMOVE EXTRA SPACE FROM NULL/BLANK/EMPTY STRING");
		 StringBuilder sb = new StringBuilder();
		 String [] saToBeEdoted = remvExSpcStr.trim().split(" ");
          for(int i=0 ; i<saToBeEdoted.length ;i++)
		 {
        	  if(saToBeEdoted[i].trim().length() != 0 )
        	  {
        		  sb.append(saToBeEdoted[i].trim());
					 sb.append(" ");
        	  }	
		
		 }
		 
		 return sb.toString().trim() ;
		}
		
}
