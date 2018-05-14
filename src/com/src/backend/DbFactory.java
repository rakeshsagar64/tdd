package com.src.backend;

public class DbFactory {
	
	
		private DbFactory() {
					
		}
		
		
		private static DbIntr di;
		
		public static DbIntr getInstance() {
			di=new DbClass();
			return di;
			
			
		}
}
